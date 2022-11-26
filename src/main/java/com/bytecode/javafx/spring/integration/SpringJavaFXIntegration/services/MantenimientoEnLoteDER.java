package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;

@Service
public class MantenimientoEnLoteDER extends AsyncTask {

    private final static Logger LOGGER = LogManager.getLogger(MantenimientoEnLoteDER.class.getName());

    @Autowired
    private DigicRepository digicRepository;

    public MantenimientoEnLoteDER() throws IOException {

    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public Object doInBackground(Object[] params) throws IOException {

        String SAMPLE_CSV_FILE_PATH = "MantenimientoEstatusDER.csv";

        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);

        ) {
            SQLiteConnect();
            // Reading Records One by One in a String array
            String[] nextRecord;
            while (true) {
                try {
                    if (!((nextRecord = csvReader.readNext()) != null)) break;
                } catch (CsvValidationException e) {
                    LOGGER.log(Level.ERROR, e.getMessage());
                    throw new RuntimeException(e);
                }

                //System.out.println("KioskoID : " + nextRecord[0]);
                //System.out.println("Justificante : " + nextRecord[1]);
                //System.out.println("Estatus : " + nextRecord[2]);
                //System.out.println("==========================");

                if (nextRecord[0].equals(App.parametrosModel.getKIOSKOID())) {

                    //LOGGER.log(Level.INFO, "Justificante: " + nextRecord[1].toString() + " Actualizado a estatus: " + nextRecord[2].toString());

                    try{

                        //SelectItem(nextRecord[1].toString());
                        UpdateStatus("digic",nextRecord[1].toString(),nextRecord[2].toString());
                        UpdateStatus("digicmodopago",nextRecord[1].toString(),nextRecord[2].toString());


                    }catch (Exception e){
                        e.printStackTrace();
                        LOGGER.log(Level.ERROR, e.getClass() + " : " + e.getMessage());
                    }

                }
            }
        } catch (Exception e){
            LOGGER.log(Level.ERROR, e.getClass() + " : " + e.getMessage());
        }

        return null;
    }

    @Override
    public void onPostExecute(Object params) {

    }

    @Override
    public void progressCallback(Object[] params) {

    }

    /*
     *
     *  Conexion SQLite3
     *
     */
    public String DBLocal = "diva.canarias.db";
    public void SQLiteConnect() throws SQLException, ClassNotFoundException {
        //check for driver errors
        if(checkDrivers()){
            Connection connection = connect(DBLocal);
            //LOGGER.log(Level.INFO, LocalDateTime.now() + ": Connected to SQLite DB at " + DBLocal);
        }else{
            LOGGER.log(Level.ERROR, LocalDateTime.now() + ": Not Connected to SQLite DB at " + DBLocal);
        }
    }
    private Connection connect(String location) throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");

        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPrefix + location);
           // System.out.println("connection");
           // System.out.println(connection);

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, LocalDateTime.now() + ": Could not connect to SQLite DB at " + location);
            return null;
        }
        return connection;
    }
    private boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            //LOGGER.log(Level.INFO,LocalDateTime.now() + ": Start SQLite Drivers");
            //System.out.println("Connect driver SQLite3");
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Could not start SQLite Drivers");
            return false;
        }
    }
    private boolean SelectItem(String justificante) throws SQLException, ClassNotFoundException {

        //System.out.println("Select Items");

        String sql = "SELECT * FROM digic  WHERE justificante = ? ";

        try (Connection connection = connect(DBLocal)) {
            /*
             * setear los parametros del where
             */
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, justificante);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                System.out.println("justificante: "     + rs.getString("justificante"));
                System.out.println("nombreViajero: "    + rs.getString("nombre_viajero"));
                System.out.println("apellidosViajero: " + rs.getString("apellidos_viajero"));
                System.out.println("tipoDocumento: "    + rs.getString("tipo_documento"));
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Could not Select from digic because: \n" + e.toString() + "\n" + e.getMessage() + "\n" + e.getErrorCode());
        }finally{

        }
        return false;

    }

    public void UpdateStatus(String tabla, String justificante, String estatus) throws SQLException, ClassNotFoundException {

        String sql = "";
        switch (tabla){
            case "digic" :
                sql = "UPDATE " + tabla + " SET estatus_upload = ? WHERE estatus_upload <> 1 AND justificante = ?";
                break;
            case "digicmodopago" :
                sql = "UPDATE " + tabla + " SET estatusupload = ? WHERE estatusupload <> 1 AND uuid_proceso IN (SELECT uuid_proceso FROM digic WHERE justificante = ? GROUP BY uuid_proceso) ";
                break;
            default:
                break;
        }

        try (Connection connection = connect(DBLocal)) {

             PreparedStatement pstmt = connection.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, estatus);
            pstmt.setString(2, justificante);
            // update
            if(pstmt.executeUpdate() > 0 ){
                LOGGER.log(Level.INFO, "Justificante: " + justificante + " Actualizado a estatus: " + estatus);
            };

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Could not Update from " + tabla + " because: \n" + e.toString() + "\n" + e.getMessage() + "\n" + e.getErrorCode());

        }
    }
}
