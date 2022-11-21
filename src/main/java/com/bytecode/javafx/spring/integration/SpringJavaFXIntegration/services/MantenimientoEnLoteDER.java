package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MantenimientoEnLoteDER extends AsyncTask {

    private final static Logger LOGGER = LogManager.getLogger(MantenimientoEnLoteDER.class.getName());

    @Autowired
    private DigicModoPagoRepository digicModoPagoRepository;

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
                java.io.Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
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
                    LOGGER.log(Level.INFO, "Justificante: " + nextRecord[1].toString() + " Actualizado a estatus: " + nextRecord[2].toString());
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
}
