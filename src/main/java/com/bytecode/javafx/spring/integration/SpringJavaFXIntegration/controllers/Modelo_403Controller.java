package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//------------------------
//--  QRCode

import java.io.*;
import javax.imageio.ImageIO;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.json.*;

import java.awt.image.BufferedImage;

import com.google.gson.JsonParser;
import com.google.zxing.*;
import com.google.zxing.Reader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

// -- end QRCode
//------------------------------

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor; 

import java.util.Random;

/* 
 *  CRUDHelper para SQLite * 
*/
    import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

import org.comtel2000.keyboard.control.DefaultLayer;
import org.comtel2000.keyboard.control.KeyBoardPopup;
import org.comtel2000.keyboard.control.KeyBoardPopupBuilder;
import static org.comtel2000.keyboard.control.VkProperties.*;

import org.grecasa.ext.mw.externo.KioskoServiceClient;
import org.grecasa.ext.mw.externo.KioskoServiceClientUtils;
import org.grecasa.ext.mw.externo.kiosko_service.KioskoService;
import org.grecasa.ext.mw.externo.kiosko_service.KioskoService_Service;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarDerResponse;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDer;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DummyData;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;

@Component
public class Modelo_403Controller implements Initializable {

    /* 
     * Conexion Bases de datos 
    */
        
    @Autowired
    private DigicRepository digicRepository;

    public String tableName = "digic_v2"; 
    public String DBLocal = "digic_v2"; 
    

    public Random rand = new Random();
    public String fechaHoy; 
    public LocalDate date = LocalDate.now();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    // Strings which hold css elements to easily re-use in the application
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String successStyle = String.format("-fx-border-color: GREEN; -fx-border-width: 2; -fx-border-radius: 5;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");


    /*
    *  Variables de la plantilla
    */

    @FXML private Button 
        p2_btn_aceptar,  
        p2_btn_qr, 
        p2_btn_salir, 
        p2_btn_demo;

    @FXML private Label 
        p2_lb_justificante,
        p2_lb_datos_establecimiento,
        p2_lb_nif,
        p2_lb_razon_social,
        p2_lb_numero_factura,
        p2_lb_fecha_factura,
        p2_lb_monto_factura,
        p2_lb_nombreViajero,
        p2_lb_apellidosViajero,
        p2_lb_tipoDocumento,
        p2_lb_valorDocumento,
        p2_lb_paisExpedicion,
        p2_lb_paisResidencia,
        p2_lb_fechaLimiteSalida,
        p2_lb_medioPago,
        p2_lb_cuentaInternacional,
        p2_lb_codigoBic,
        p2_lb_valorMedioPago,
        p2_lb_codigo_cuenta_internacional,
        p2_lb_codigo_cuenta_nacional,
        p2_lb_botonqr,
        p2_lb_clave_banco,
        p2_lb_clave_control,
        p2_lb_codigo_aba,
        p2_lb_cuenta_bancaria,
        p2_lb_descripcion_banco,
        p2_lb_modelo,
        p2_lb_pais_banco,
        p2_lb_plantilla,
        p2_lb_medioPago_txt,
        p2_lb_valorDocumentoEsperado;  

    @FXML private TextField 
        p2_tf_email,
        p2_tf_codigoBic,
        p2_tf_valorMedioPago,
        p2_tf_clave_banco,
        p2_tf_clave_control,
        p2_tf_codigo_aba,
        p2_tf_cuenta_bancaria,
        p2_tf_descripcion_banco,
        p2_tf_pais_banco,
        p2_tf_modoTransporte,
        p2_tf_identificadorBillete,
        p2_input_scanner;
    
    @FXML
    private Text p2_tx_info_item;
    
    @FXML private Rectangle p2_warning;
    @FXML private AnchorPane p2_an_warning,p2_an_info_item;
    
    @FXML private ImageView p2_img_barcode;

    @FXML public TableView<Justificante> p2_tv_justificantes = new TableView<Justificante>();
     
    @FXML public TableColumn<Justificante, String> p2_tc_listajustificantes = new TableColumn<Justificante, String>("JUSTIFICANTE");
    
    @FXML public TableColumn<Justificante, String> p2_tc_listajustificantesMonto  = new TableColumn<Justificante, String>("MONTO");
    
    private String ScannerReader ="";

    private ResourceBundle bundle;

    public class Justificante {
        private String numero;
        private String monto;
        
        public Justificante() {}

        public Justificante(String numero,String monto) {
            this.numero = numero;
            this.monto  = monto;
        }

        public String getfNumero() {
            return this.numero;
        }
        public void setfNumero(String numero) {
             this.numero = numero;
        }

        public StringProperty numeroProperty() {
            return new SimpleStringProperty(this.numero);
        }

        public String getfMonto() {
            return this.monto;
        }
        public void setfMonto(String monto) {
             this.monto = monto;
        }

        public StringProperty montoProperty() {
            return new SimpleStringProperty(this.monto);
        }


    }    

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Funcion de inicio del Controlador
        //
        bundle = resources;

        p2_tf_clave_banco.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p2_tf_codigo_aba.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p2_tf_email.getProperties().put(VK_TYPE, VK_TYPE_EMAIL);

       // LocalDate date = LocalDate.now();
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        fechaHoy = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(fechaHoy, formatter);

        p2_tc_listajustificantes.setCellValueFactory(new PropertyValueFactory<>("numero"));
        p2_tc_listajustificantesMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        
        //p2_tc_listajustificantes.setMinWidth(100);
        p2_tv_justificantes.setPlaceholder(new Label(bundle.getString( "p2_tv_Placeholder")));

        p2_tv_justificantes.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = p2_tv_justificantes.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                /*
                try {
                    
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                
                    System.out.println("Selected Value: " + val);

                    if(DIGIDAO.FindDIGI(val.toString())){
                        System.out.println("\ngetapellidosViajero: " + DIGIDAO.digic.getapellidosViajero());
                        FillPlantilla(new JSONObject(DIGIDAO.digic.toString()));
                    }
                    

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                     
                }
                    */
            }
        });        
        
        //p2_tv_justificantes.getColumns().addAll(p2_tc_listajustificantes);
        //p2_tv_justificantes.getColumns().addAll(p2_tc_listajustificantesMonto);

        ClearPlantilla();

        if(App.parametrosModel.getTipoDoumento() == null) App.parametrosModel.setTipoDoumento("I");

        
        switch (App.parametrosModel.getTipoDoumento()){
            case "P" : 
                p2_lb_valorDocumento.setText(App.parametrosModel.getNumeroPasaporte());
                p2_lb_valorDocumentoEsperado.setText(App.parametrosModel.getNumeroPasaporte());
                p2_lb_tipoDocumento.setText("PASAPORTE");
                break;
            case "I" : 
                p2_lb_valorDocumento.setText(App.parametrosModel.getDniNifNieTieDemo());
                p2_lb_valorDocumentoEsperado.setText(App.parametrosModel.getDniNifNieTieDemo());
                p2_lb_tipoDocumento.setText("NIF/NIE/TIE");
                break;
        }
       
        p2_lb_nombreViajero.setText(App.parametrosModel.getNombreViajero());

        p2_btn_demo.setVisible(App.parametrosModel.getAppDemo());

        p2_img_barcode.requestFocus();

        p2_img_barcode.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                //System.out.println("p2_img_barcode Key Pressed: " + ke.getText());
                //System.out.println("p2_img_barcode Key Pressed: " + ke.getCharacter());
                ScannerReader = ScannerReader + ke.getCharacter(); 
                
                if (ke.getCode().equals(KeyCode.ENTER) || 
                    ke.getCharacter().getBytes()[0] == '\n' || 
                    ke.getCharacter().getBytes()[0] == '\r') {
                
                    System.out.println("\nData: " + ScannerReader); 
                    
                    try {
                        QRcodeRead(ScannerReader);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();              

                    }

                    ScannerReader="";
    
                }                
            }
        });
        
    }  

    
    @FXML 
    private void BtnActionWsdl(ActionEvent event) throws SQLException, IOException, InvocationTargetException {
        switchToWSDL();

    }    

    @FXML 
    private void switchToAnterior() throws IOException {
        Locale locale = Locale.getDefault();
        App.setRoot("primary",locale);

    }


    @FXML 
    private void switchToWSDL() throws IOException, InvocationTargetException {
        
        
        ValidarRemesaDerResponse validarRemesaDerResponse;
        
        validarRemesaDerResponse  = KioskoServiceClient.getInstance().validarRemesa(DummyData.getExampleKO());
        KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);

        validarRemesaDerResponse = KioskoServiceClient.getInstance().validarRemesa(DummyData.getExample());
        KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);
        


    }


    @FXML
    private void switchToAceptar() throws IOException, SQLException, ClassNotFoundException {

/*
        if ( p2_lb_justificante.getText().length() > 0 && 
             !DIGIDAO.FindDIGI(p2_lb_justificante.getText()) ){

            if (p2_tf_pais_banco.getText().isEmpty() || 
                p2_tf_clave_banco.getText().isEmpty() ||             
                p2_tf_cuenta_bancaria.getText().isEmpty() ||             
                p2_tf_descripcion_banco.getText().isEmpty() ||             
                p2_tf_codigoBic.getText().isEmpty() ||             
                p2_tf_codigo_aba.getText().isEmpty() ||
                p2_tf_email.getText().isEmpty()) {

                //invalidDetails.setStyle(errorMessage);

                
                if (p2_tf_pais_banco.getText().isEmpty()) {
                    //invalidDetails.setText("The Login fields are required!");
                    p2_tf_pais_banco.setStyle(errorStyle);

                    new animatefx.animation.Shake(p2_tf_pais_banco).play();
                    new animatefx.animation.Wobble(p2_tf_pais_banco).play();
                    
                } else 
                    if (p2_tf_clave_banco.getText().isEmpty()) {
                        p2_tf_clave_banco.setStyle(errorStyle);
                        p2_tf_pais_banco.setStyle(successStyle);

                        new animatefx.animation.Shake(p2_tf_clave_banco).play();
                        new animatefx.animation.Pulse(p2_tf_clave_banco).play();

                    }  else 
                        if (p2_tf_cuenta_bancaria.getText().isEmpty()) {
                            p2_tf_cuenta_bancaria.setStyle(errorStyle);
                            p2_tf_pais_banco.setStyle(successStyle);     

                            new animatefx.animation.Shake(p2_tf_cuenta_bancaria).play();
                            new animatefx.animation.Wobble(p2_tf_cuenta_bancaria).play();
                            
                        }else 
                            if (p2_tf_descripcion_banco.getText().isEmpty()) {
                                p2_tf_descripcion_banco.setStyle(errorStyle);
                                p2_tf_cuenta_bancaria.setStyle(successStyle);     

                                new animatefx.animation.Shake(p2_tf_descripcion_banco).play();
                                new animatefx.animation.Wobble(p2_tf_descripcion_banco).play();
                                
                            } else 
                                if (p2_tf_codigoBic.getText().isEmpty()) {
                                    p2_tf_codigoBic.setStyle(errorStyle);
                                    p2_tf_descripcion_banco.setStyle(successStyle);     

                                    new animatefx.animation.Shake(p2_tf_codigoBic).play();
                                    new animatefx.animation.Wobble(p2_tf_codigoBic).play();

                                } else 
                                    if (p2_tf_codigo_aba.getText().isEmpty()) {
                                        p2_tf_codigo_aba.setStyle(errorStyle);
                                        p2_tf_codigoBic.setStyle(successStyle);     

                                        new animatefx.animation.Shake(p2_tf_codigo_aba).play();
                                        new animatefx.animation.Wobble(p2_tf_codigo_aba).play();
                                        
                                    } else 
                                        if (p2_tf_email.getText().isEmpty()) {
                                            p2_tf_email.setStyle(errorStyle);
                                            p2_tf_email.setStyle(successStyle);     

                                            new animatefx.animation.Shake(p2_tf_email).play();
                                            new animatefx.animation.Wobble(p2_tf_email).play();
                                        
                                    }

                            
            } else {

                    p2_tf_pais_banco.setStyle(successStyle); 
                    p2_tf_clave_banco.setStyle(successStyle);             
                    p2_tf_descripcion_banco.setStyle(successStyle);             
                    p2_tf_codigoBic.setStyle(successStyle);             
                    p2_tf_codigo_aba.setStyle(successStyle);
                    p2_tf_cuenta_bancaria.setStyle(successStyle);
                    p2_tf_email.setStyle(successStyle);

                    new animatefx.animation.Tada(p2_tf_pais_banco).play();
                    new animatefx.animation.Tada(p2_tf_clave_banco).play();
                    new animatefx.animation.Tada(p2_tf_descripcion_banco).play();
                    new animatefx.animation.Tada(p2_tf_codigoBic).play();
                    new animatefx.animation.Tada(p2_tf_codigo_aba).play();
                    new animatefx.animation.Tada(p2_tf_cuenta_bancaria).play();
                    new animatefx.animation.Tada(p2_tf_email).play();
                    
                    //InsertItem();

            }

        } else {
            p2_an_warning.setVisible(true);
        }
        */
    }

    @FXML
    private void switchToSQLiteConnect() throws IOException, SQLException, ClassNotFoundException {
        //Locale locale = Locale.getDefault();
        //App.setRoot("secondary",locale);
        SQLiteConnect();
    }

    @FXML private void switchToQRCode() throws IOException {

            ClearPlantilla();
            p2_img_barcode.requestFocus();

    }

    @FXML private void QRcodeRead() throws IOException, FormatException, ChecksumException, NotFoundException, ClassNotFoundException, SQLException {

        //String pathname = "DER_qrcode_temp.png";
        String pathname = App.parametrosModel.getQRCODEDEMO();
        String text     = readQR(pathname);
        QRcodeRead(text);       

    }

    @FXML private void QRcodeRead(String qr_text) throws IOException {
        
        ClearPlantilla();
        p2_img_barcode.requestFocus();
  
        try {
          
            System.out.print("readQR: " + qr_text);

            //String password = "Grecasa2022";
            String password = App.parametrosModel.getQRPassDecoder();
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(password);
            String plainText = textEncryptor.decrypt(qr_text);
            System.out.println("\nData Decrypted: "+ plainText);

            JSONObject myJson = new JSONObject(plainText);

            /*
             * Genera un numero Randon apartir del
             * numero de justificante para efecto de demo
             */

            if (App.parametrosModel.getAppDemo()){
                Integer n = rand.nextInt(5);
                String  ns = myJson.getString("justificante");
                String  as = myJson.getString("apellidosViajero");

                ns = ns + n.toString();
                as = as + " " + n.toString();

                myJson.put("justificante",ns);
                myJson.put("apellidosViajero",as);
                
            }

            /*
             * Seteo de Campos del formulario
             */

             if (!App.parametrosModel.getAppDemo() && Integer.parseInt(myJson.getString("fechaLimiteSalida")) < Integer.parseInt(App.parametrosModel.getFechaHoy())){

                System.out.print("\nfecha Limite Salida: Se encuentra caducada");
                p2_tx_info_item.setText(bundle.getString( "p2_tx_info_fechalimite"));
                p2_an_info_item.setVisible(true);

             }else  if ( !myJson.getString("valorDocumento").equals(p2_lb_valorDocumentoEsperado.getText())){

                System.out.print("\nvalorDocumento: No corresponde con el valor esperado");
                p2_tx_info_item.setText(bundle.getString( "p2_tx_info_item2"));
                p2_an_info_item.setVisible(true);
            
            }else if (/*DIGIDAO.FindDIGI(myJson.getString("justificante"))*/ false){

        
                System.out.print("\nreadQR: Registro ya procesado");
                
                p2_tx_info_item.setText(bundle.getString( "p2_tx_info_item"));                
                p2_an_info_item.setVisible(true);

            }else{
                FillPlantilla(myJson);
                InsertItem(myJson); 
            }            

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.printf("\nCausa: %s \nMensaje: %s\n Class: %s\n Localized Mensaje: %s\n" ,e.getCause(),e.getMessage(),e.getClass(),e.getLocalizedMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();              


        }
    }

    private static String readQR(String pathname) throws FormatException, ChecksumException, NotFoundException, IOException {

        InputStream qrInputStream     = new FileInputStream(pathname);
        BufferedImage qrBufferedImage = ImageIO.read(qrInputStream);

        LuminanceSource source = new BufferedImageLuminanceSource(qrBufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader reader = new MultiFormatReader();
        Result stringBarCode = reader.decode(bitmap);

        return stringBarCode.getText();
    }

    @FXML 
    private void ClearPlantilla(){

        p2_an_warning.setVisible(false);
        p2_an_info_item.setVisible(false);

        p2_lb_medioPago.setVisible(false);
        p2_lb_medioPago_txt.setVisible(false);
        p2_lb_cuentaInternacional.setVisible(false);
        
        /*
        * 
        * Limpiar campos
        * 
        */

            // Justificante Modelo 403
            p2_lb_justificante.setText("");

            // 1. Datos de Comercio
            p2_lb_nif.setText("");
            p2_lb_razon_social.setText("");

            // 2. Datos de la Factura
            p2_lb_numero_factura.setText("");
            p2_lb_fecha_factura.setText("");
            p2_lb_monto_factura.setText("");

            // 3. Datos del Viajero
            p2_lb_nombreViajero.setText("");
            p2_lb_apellidosViajero.setText("");
            p2_lb_tipoDocumento.setText("");
            p2_lb_valorDocumento.setText("");
            p2_lb_paisExpedicion.setText("");
            p2_lb_paisResidencia.setText("");
            p2_tf_email.setText("");
            
            // 4. Datos Transporte
            p2_lb_fechaLimiteSalida.setText("");

            // 5. Datos de medio de pago
            p2_lb_medioPago.setText("");
            p2_lb_cuentaInternacional.setText("");
            
            p2_tf_codigoBic.setText("");
            p2_tf_valorMedioPago.setText("");
 
            p2_lb_medioPago.setText("");
            p2_tf_pais_banco.setText("");
            p2_tf_clave_banco.setText("");
            p2_tf_cuenta_bancaria.setText("");
            p2_tf_descripcion_banco.setText("");
            p2_tf_codigo_aba.setText("");
            p2_tf_clave_control.setText("");            
            


    }

    @FXML private void FillPlantilla(JSONObject myJson){

            // Justificante Modelo 403
            p2_lb_justificante.setText((String) myJson.get("justificante"));

            // 1. Datos de Comercio
            p2_lb_nif.setText((String) myJson.get("nifEstablecimiento"));
            p2_lb_razon_social.setText((String) myJson.get("razonSocial"));


            // 2. Datos del Viajero
            LocalDate fechaFactura = LocalDate.parse((String) myJson.get("fechaFactura"), formatter);
            p2_lb_numero_factura.setText((String) myJson.get("numeroFactura"));
            p2_lb_fecha_factura.setText(fechaFactura.toString());
            p2_lb_monto_factura.setText(myJson.get("totalDigic").toString());

            // 3. Datos del Viajero
            p2_lb_nombreViajero.setText((String) myJson.get("nombreViajero"));
            p2_lb_apellidosViajero.setText((String) myJson.get("apellidosViajero"));
            p2_lb_tipoDocumento.setText((String) myJson.get("tipoDocumento"));
            p2_lb_valorDocumento.setText((String) myJson.get("valorDocumento"));
            p2_lb_paisExpedicion.setText(SeleccionarPais((String) myJson.get("paisExpedicion")));
            p2_lb_paisResidencia.setText(SeleccionarPais((String) myJson.get("paisResidencia")));
            p2_tf_email.setText((String) myJson.get("email"));
            
            // 4. Datos Transporte
            LocalDate fechaLimiteSalida = LocalDate.parse((String) myJson.get("fechaLimiteSalida"), formatter);
            p2_lb_fechaLimiteSalida.setText(fechaLimiteSalida.toString());

            // 5. Datos de medio de pago
            if (!myJson.isNull("cuentaSinIBAN")){
                p2_lb_cuentaInternacional.setText((String) myJson.get("cuentaSinIBAN"));
            }else{
                p2_lb_cuentaInternacional.setText("NO");
            }
            
            if (!myJson.isNull("paisBanco")){
                p2_tf_pais_banco.setText((String) myJson.get("paisBanco"));
            }
            
            if (!myJson.isNull("claveBanco")){
                p2_tf_clave_banco.setText((String) myJson.get("claveBanco"));
            }

            if (!myJson.isNull("valorMedioPago")){
                p2_tf_cuenta_bancaria.setText((String) myJson.get("valorMedioPago"));
            }

            if (!myJson.isNull("descInstFinanciera")){
                p2_tf_descripcion_banco.setText((String) myJson.get("descInstFinanciera"));
            }

            if (!myJson.isNull("numeroABA")){
                p2_tf_codigo_aba.setText((String) myJson.get("numeroABA"));
            }

            if (!myJson.isNull("claveControl")){
                p2_tf_clave_control.setText((String) myJson.get("claveControl"));
            }

            if (!myJson.isNull("modoPago")){
                p2_lb_medioPago.setText((String) myJson.get("modoPago"));
            }
        
            if (!myJson.isNull("codigoBic")){
                p2_tf_codigoBic.setText((String) myJson.get("codigoBic"));
            }

            if (!myJson.isNull("valorMedioPago")){
                p2_tf_valorMedioPago.setText((String) myJson.get("valorMedioPago"));
            }

            if (!myJson.isNull("modoTransporte")){
                p2_tf_modoTransporte.setText((String) myJson.get("modoTransporte"));
            }

            if (!myJson.isNull("identificadorBillete")){
                p2_tf_identificadorBillete.setText((String) myJson.get("identificadorBillete"));
            }


            // Colocar en Visible o No Visibles los campos de la plantilla

            if( !myJson.isNull("cuentaSinIBAN") && (boolean) myJson.get("cuentaSinIBAN").equals("SI")){
                System.out.println("SI: " + (String) myJson.get("cuentaSinIBAN"));
                
                p2_lb_codigoBic.setVisible(true);
                p2_lb_codigo_cuenta_internacional.setVisible(true);

                p2_tf_codigoBic.setVisible(true);

                p2_lb_pais_banco.setVisible(true);
                p2_tf_pais_banco.setVisible(true);
                p2_lb_clave_banco.setVisible(true);
                p2_tf_clave_banco.setVisible(true);
                p2_lb_cuenta_bancaria.setVisible(true);
                p2_tf_cuenta_bancaria.setVisible(true);
                p2_lb_clave_control.setVisible(true);
                p2_tf_clave_control.setVisible(true);
                p2_lb_codigo_aba.setVisible(true);
                p2_tf_codigo_aba.setVisible(true);
                p2_lb_descripcion_banco.setVisible(true);
                p2_tf_descripcion_banco.setVisible(true);

                p2_tf_valorMedioPago.setVisible(false);
                p2_lb_valorMedioPago.setVisible(false);
                p2_lb_codigo_cuenta_nacional.setVisible(false);



            }else{
                
                p2_lb_codigoBic.setVisible(false);
                p2_lb_codigo_cuenta_internacional.setVisible(false);

                p2_tf_codigoBic.setVisible(false);
                
                p2_lb_pais_banco.setVisible(false);
                p2_tf_pais_banco.setVisible(false);
                p2_lb_clave_banco.setVisible(false);
                p2_tf_clave_banco.setVisible(false);
                p2_lb_cuenta_bancaria.setVisible(false);
                p2_tf_cuenta_bancaria.setVisible(false);
                p2_lb_clave_control.setVisible(false);
                p2_tf_clave_control.setVisible(false);
                p2_lb_codigo_aba.setVisible(false);
                p2_tf_codigo_aba.setVisible(false);
                p2_lb_descripcion_banco.setVisible(false);
                p2_tf_descripcion_banco.setVisible(false);

                p2_tf_valorMedioPago.setVisible(true);
                p2_lb_valorMedioPago.setVisible(true);
                p2_lb_codigo_cuenta_nacional.setVisible(true);
                
            }
        

    }



    /*
     * 
     *  Conexion SQLite3
     * 
     */
    @FXML
    public void SQLiteConnect() throws SQLException, ClassNotFoundException {
        checkDrivers();    //check for driver errors
        Connection connection = connect(DBLocal);

    }

    private Connection connect(String location) throws ClassNotFoundException {
       
        Class.forName("org.sqlite.JDBC");
        
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPrefix + location);
            System.out.println("connection");
            System.out.println(connection);

        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                Level.SEVERE,
                LocalDateTime.now() + ": Could not connect to SQLite DB at " + location);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();              

            return null;
        }
        return connection;
    }

    private static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Start SQLite Drivers");
            System.out.println("Connect driver SQLite3");
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQLite Drivers");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(classNotFoundException.getMessage());
            alert.showAndWait();             

            return false;
        }
    }


    public void InsertItem(JSONObject myJson) throws SQLException, ClassNotFoundException {
        
            if (myJson.isNull("cuentaSinIBAN")){
                myJson.put("cuentaSinIBAN","NO");
            }
            
            if (myJson.isNull("paisBanco")){
                myJson.put("paisBanco","");
            }
            
            if (myJson.isNull("claveBanco")){
                myJson.put("claveBanco","");
            }

            if (myJson.isNull("valorMedioPago")){
                myJson.put("valorMedioPago","");
            }

            if (myJson.isNull("descInstFinanciera")){
                myJson.put("descInstFinanciera","");
            }

            if (myJson.isNull("numeroABA")){
                myJson.put("numeroABA","");
            }

            if (myJson.isNull("claveControl")){
                myJson.put("claveControl","");
            }

            if (myJson.isNull("modoPago")){
                myJson.put("modoPago","");
            }
        
            if (myJson.isNull("codigoBic")){
                myJson.put("codigoBic","");
            }

            if (myJson.isNull("valorMedioPago")){
                myJson.put("valorMedioPago","");
            };

            if (myJson.isNull("modoTransporte")){
                myJson.put("modoTransporte","");
            };

            if (myJson.isNull("identificadorBillete")){
                myJson.put("identificadorBillete","");
            };


            String monto = Double.toString((Double) myJson.get("totalDigic"));

        Digic digic = new Digic(
            (String) myJson.get("justificante"),
            (String) myJson.get("nombreViajero"),
            (String) myJson.get("apellidosViajero"),
            (String) myJson.get("tipoDocumento"),
            (String) myJson.get("valorDocumento"),
            (String) myJson.get("paisExpedicion"),
            (String) myJson.get("paisResidencia"),
            (String) myJson.get("nifEstablecimiento"),
            (String) myJson.get("razonSocial"),
            (String) myJson.get("numeroFactura"),
            (String) myJson.get("fechaFactura"),
            monto,
            (String) myJson.get("fechaLimiteSalida"),
            (String) myJson.get("cuentaSinIBAN"),
            (String) myJson.get("modoPago"),
            (String) myJson.get("email"),
            (String) myJson.get("codigoBic"),
            (String) myJson.get("valorMedioPago"),
            (String) myJson.get("claveControl"),
            (String) myJson.get("cuentaSinIBAN"),
            (String) myJson.get("numeroABA"),  
            (String) myJson.get("claveBanco"),
            (String) myJson.get("descInstFinanciera"),
            (String) myJson.get("paisBanco"),
            (String) myJson.get("modoTransporte"),
            (String) myJson.get("identificadorBillete"),
            0,
            "",
            ""
        );

        //ojo aqui
        //if (!DIGIDAO.InsertItem(digic)) p2_an_warning.setVisible(true);
        digicRepository.save(digic);

        Justificante p1 = new Justificante(p2_lb_justificante.getText(),p2_lb_monto_factura.getText());
        p2_tv_justificantes.getItems().addAll(p1);


        //p2_tv_justificantes.setTableMenuButtonVisible(true);
    }

    /*public void InsertItem() throws SQLException, ClassNotFoundException {

        DIGIModel_v2 digic = new DIGIModel_v2(
            p2_lb_justificante.getText(),
            p2_lb_nombreViajero.getText(),
            p2_lb_apellidosViajero.getText(),
            p2_lb_tipoDocumento.getText(),
            p2_lb_valorDocumento.getText(),
            p2_lb_paisExpedicion.getText(),
            p2_lb_paisResidencia.getText(),
            p2_lb_nif.getText(),
            p2_lb_razon_social.getText(),
            p2_lb_numero_factura.getText(),
            p2_lb_fecha_factura.getText(),
            p2_lb_monto_factura.getText(),
            p2_lb_fechaLimiteSalida.getText(),
            p2_lb_cuentaInternacional.getText(),
            p2_lb_medioPago.getText(),
            p2_tf_email.getText(),
            p2_tf_codigoBic.getText(),
            p2_tf_valorMedioPago.getText(),
            p2_tf_clave_control.getText(),
            p2_lb_cuentaInternacional.getText(),
            p2_tf_codigo_aba.getText(),  
            p2_tf_clave_banco.getText(),
            p2_tf_descripcion_banco.getText(),
            p2_tf_pais_banco.getText(),
            0,
            "",
            ""
        );

        if (!DIGIDAO.InsertItem(digic)) p2_an_warning.setVisible(true);

        Justificante p1 = new Justificante(p2_lb_justificante.getText());
        
        p2_tv_justificantes.getItems().addAll(p1);

        //p2_tv_justificantes.setTableMenuButtonVisible(true);
    }*/








    /*public static class MyJustificante {
        private final SimpleStringProperty JUSTIFICANTES;
     
        private MyJustificante(String fNumero) {
            this.JUSTIFICANTES = new SimpleStringProperty(fNumero);
        }
     
        public String getfNumero() {
            return JUSTIFICANTES.get();
        }
        public void setfNumero(String fNumero) {
            JUSTIFICANTES.set(fNumero);
        }
    }*/




    @FXML
    public void UpdateItem() throws SQLException, ClassNotFoundException {
        
        System.out.println("FXML UpdateItem");

        Digic digic = new Digic();

        digic.setjustificante(p2_lb_justificante.getText());
        digic.setemail(p2_tf_email.getText());
        digic.setcodigoBic(p2_tf_codigoBic.getText());
        digic.setvalorMedioPago(p2_tf_valorMedioPago.getText());
        
        digic.setclaveControl(p2_tf_clave_control.getText());
        digic.setcuentaSinIBAN(p2_lb_cuentaInternacional.getText());
        digic.setnumeroABA(p2_tf_codigo_aba.getText());
        digic.setclaveBanco( p2_tf_clave_banco.getText());
        digic.setdescInstFinanciera(p2_tf_descripcion_banco.getText());
        digic.setpaisBanco( p2_tf_pais_banco.getText());

        digic.setfecha_upload(LocalDateTime.now().toString());

        System.out.println("todo: " + digic.toString());        
        
        //if (!DIGIDAO.UpdateItem(digic)) p2_an_warning.setVisible(true);
        digicRepository.save(digic);
    }

    @FXML
    public void SelectItems()  throws SQLException, ClassNotFoundException {

        System.out.println("Select Items");

        Digic digic = new Digic();

        String sql = "SELECT * FROM " + tableName ;

        try (Connection connection = connect(DBLocal);) {
            /*
             * setear los parametros del where
             */
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("ResultSet");
            System.out.println(rs);

            while (rs.next()) {

                System.out.println("id: "          + rs.getLong("id"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Details: "     + rs.getString("details"));
                System.out.println("Done: "        + rs.getBoolean("done"));

                /* 
                digic.add(new DIGIModel(
                        rs.getLong("justificante"),
                        .....
                ));
                         */
            }

        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not Update from " + tableName + " because: \n" + e.toString() + "\n" + e.getMessage() + "\n" + e.getErrorCode());

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();              
        
        }finally{
            //SelectItems_sqlite3();
        }
     
    }


    public Digic getFromUI(){
        Digic digic = new Digic();

            digic.setjustificante(p2_lb_justificante.getText());
            digic.setnifEstablecimiento(p2_lb_nif.getText());
            digic.setrazonSocial(p2_lb_razon_social.getText());
            digic.setnumeroFactura(p2_lb_numero_factura.getText()); 
            digic.setfechaFactura(p2_lb_fecha_factura.getText()); 
            digic.settotalDigic(p2_lb_monto_factura.getText()); 
            digic.setnombreViajero(p2_lb_nombreViajero.getText()); 
            digic.setapellidosViajero(p2_lb_apellidosViajero.getText()); 
            digic.settipoDocumento(p2_lb_tipoDocumento.getText()); 
            digic.setvalorDocumento(p2_lb_valorDocumento.getText()); 
            digic.setpaisExpedicion(p2_lb_paisExpedicion.getText()); 
            digic.setpaisResidencia(p2_lb_paisResidencia.getText()); 
            digic.setemail(p2_tf_email.getText()); 
            digic.setfechaLimiteSalida(p2_lb_fechaLimiteSalida.getText()); 
            digic.setcuentaSinIBAN(p2_lb_cuentaInternacional.getText()); 
            digic.setpaisBanco(p2_tf_pais_banco.getText()); 
            digic.setclaveBanco(p2_tf_clave_banco.getText()); 
            digic.setvalorMedioPago(p2_tf_cuenta_bancaria.getText()); 
            digic.setdescInstFinanciera(p2_tf_descripcion_banco.getText()); 
            digic.setnumeroABA(p2_tf_codigo_aba.getText()); 
            digic.setclaveControl(p2_tf_clave_control.getText()); 
            digic.setmodoPago(p2_lb_medioPago.getText()); 
            digic.setcodigoBic(p2_tf_codigoBic.getText()); 
            digic.setvalorMedioPago(p2_tf_valorMedioPago.getText()); 
            digic.setmodoTransporte(p2_tf_modoTransporte.getText()); 
            digic.setidentificadorBillete(p2_tf_identificadorBillete.getText()); 


        return digic;
    }

    public void setFromUI(Digic digic) {

        p2_lb_justificante.setText(digic.getjustificante());
        p2_lb_nif.setText(digic.getnifEstablecimiento());
        p2_lb_razon_social.setText(digic.getrazonSocial());
        p2_lb_numero_factura.setText(digic.getnumeroFactura());
        p2_lb_fecha_factura.setText(digic.getfechaFactura());
        p2_lb_monto_factura.setText(digic.gettotalDigic().toString());
        p2_lb_nombreViajero.setText(digic.getnombreViajero());
        p2_lb_apellidosViajero.setText(digic.getapellidosViajero());
        p2_lb_tipoDocumento.setText(digic.gettipoDocumento());
        p2_lb_valorDocumento.setText(digic.getvalorDocumento());
        p2_lb_paisExpedicion.setText(SeleccionarPais(digic.getpaisExpedicion()));
        p2_lb_paisResidencia.setText(SeleccionarPais(digic.getpaisResidencia()));
        p2_tf_email.setText(digic.getemail());
        p2_lb_fechaLimiteSalida.setText(digic.getfechaLimiteSalida());
        p2_lb_cuentaInternacional.setText(digic.getcuentaSinIBAN());
        p2_tf_pais_banco.setText(digic.getpaisBanco());
        p2_tf_clave_banco.setText(digic.getclaveBanco());
        p2_tf_cuenta_bancaria.setText(digic.getvalorMedioPago());
        p2_tf_descripcion_banco.setText(digic.getdescInstFinanciera());
        p2_tf_codigo_aba.setText(digic.getnumeroABA());
        p2_tf_clave_control.setText(digic.getclaveControl());
        p2_lb_medioPago.setText(digic.getmodoPago());
        p2_tf_codigoBic.setText(digic.getcodigoBic());
        p2_tf_valorMedioPago.setText(digic.getvalorMedioPago());
        p2_tf_modoTransporte.setText(digic.getmodoTransporte());
        p2_tf_identificadorBillete.setText(digic.getidentificadorBillete());      

    }
    
    public void refesh() {
        //comboClientes.setItems(FXCollections.observableArrayList(clienteRepository.findAll()));
    }

    @FXML
    public boolean SelectItem(String justificante)  throws SQLException, ClassNotFoundException {


        System.out.println("Select Items");

        Digic digic = new Digic();

        String sql = "SELECT * FROM " + tableName + "  WHERE justificante = ? ";

        try (Connection connection = connect(DBLocal);) {
            /*
             * setear los parametros del where
             */
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, justificante);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("ResultSet");
            System.out.println(rs);

            while (rs.next()) {

                System.out.println("justificante: "     + rs.getString("justificante"));
                System.out.println("nombreViajero: "    + rs.getString("nombreViajero"));
                System.out.println("apellidosViajero: " + rs.getString("apellidosViajero"));
                System.out.println("tipoDocumento: "    + rs.getString("tipoDocumento"));

                p2_an_info_item.setVisible(true);

                /* 
                digic.add(new DIGIModel(
                        rs.getLong("justificante"),
                        .....
                ));
                         */
                return true;
            }
            

        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not Update from " + tableName + " because: \n" + e.toString() + "\n" + e.getMessage() + "\n" + e.getErrorCode());

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();              
        
        }finally{
            //SelectItems_sqlite3();
            
        }
        return false;
     
    }

    public String SeleccionarPais(String index){

        String pais = "";
        switch(index){
            case "AL" : pais = "Albania (AL)"; break;
            case "DZ" : pais = "Algeria (DZ)"; break;
            case "BH" : pais = "Bahrain (BH)"; break;
            case "EG" : pais = "Egypt (EG)"; break;
            case "IQ" : pais = "Iraq (IQ)"; break;
            case "JO" : pais = "Jordan (JO)"; break;
            case "KW" : pais = "Kuwait (KW)"; break;
            case "LB" : pais = "Lebanon (LB)"; break;
            case "LY" : pais = "Libya (LY)"; break;
            case "MA" : pais = "Morocco (MA)"; break;
            case "OM" : pais = "Oman (OM)"; break;
            case "QA" : pais = "Qatar (QA)"; break;
            case "SA" : pais = "Saudi Arabia (SA)"; break;
            case "SD" : pais = "Sudan (SD)"; break;
            case "SY" : pais = "Syria (SY)"; break;
            case "TN" : pais = "Tunisia (TN)"; break;
            case "AE" : pais = "United Arab Emirates (AE)"; break;
            case "YE" : pais = "Yemen (YE)"; break;
            case "BY" : pais = "Belarus (BY)"; break;
            case "BG" : pais = "Bulgaria (BG)"; break;
            case "CN" : pais = "China (CN)"; break;
            case "SG" : pais = "Singapore (SG)"; break;
            case "HK" : pais = "Hong Kong (HK)"; break;
            case "TW" : pais = "Taiwan (TW)"; break;
            case "HR" : pais = "Croatia (HR)"; break;
            case "CZ" : pais = "Czech Republic (CZ)"; break;
            case "DK" : pais = "Denmark (DK)"; break;
            case "NL" : pais = "Netherlands (NL)"; break;
            case "AU" : pais = "Australia (AU)"; break;
            case "IN" : pais = "India (IN)"; break;
            case "IE" : pais = "Ireland (IE)"; break;
            case "NZ" : pais = "New Zealand (NZ)"; break;
            case "PH" : pais = "Philippines (PH)"; break;
            case "ZA" : pais = "South Africa (ZA)"; break;
            case "GB" : pais = "United Kingdom (GB)"; break;
            case "US" : pais = "United States (US)"; break;
            case "EE" : pais = "Estonia (EE)"; break;
            case "FI" : pais = "Finland (FI)"; break;
            case "BE" : pais = "Belgium (BE)"; break;
            case "CA" : pais = "Canada (CA)"; break;
            case "FR" : pais = "France (FR)"; break;
            case "AT" : pais = "Austria (AT)"; break;
            case "DE" : pais = "Germany (DE)"; break;
            case "LU" : pais = "Luxembourg (LU)"; break;
            case "CY" : pais = "Cyprus (CY)"; break;
            case "GR" : pais = "Greece (GR)"; break;
            case "IL" : pais = "Israel (IL)"; break;
            case "HU" : pais = "Hungary (HU)"; break;
            case "IS" : pais = "Iceland (IS)"; break;
            case "ID" : pais = "Indonesia (ID)"; break;
            case "IT" : pais = "Italy (IT)"; break;
            case "CH" : pais = "Switzerland (CH)"; break;
            case "JP" : pais = "Japan (JP";
            case "KR" : pais = "South Korea (KR)"; break;
            case "LV" : pais = "Latvia (LV)"; break;
            case "LT" : pais = "Lithuania (LT)"; break;
            case "MK" : pais = "Macedonia (MK)"; break;
            case "MY" : pais = "Malaysia (MY)"; break;
            case "MT" : pais = "Malta (MT)"; break;
            case "NO" : pais = "Norway (NO)"; break;
            case "PL" : pais = "Poland (PL)"; break;
            case "BR" : pais = "Brazil (BR)"; break;
            case "PT" : pais = "Portugal (PT)"; break;
            case "RO" : pais = "Romania (RO)"; break;
            case "RU" : pais = "Russia (RU)"; break;
            case "BA" : pais = "Bosnia and Herzegovina (BA)"; break;
            case "ME" : pais = "Montenegro (ME)"; break;
            case "RS" : pais = "Serbia (RS)"; break;
            case "SK" : pais = "Slovakia (SK)"; break;
            case "SI" : pais = "Slovenia (SI)"; break;
            case "AR" : pais = "Argentina (AR)"; break;
            case "BO" : pais = "Bolivia (BO)"; break;
            case "CL" : pais = "Chile (CL)"; break;
            case "CO" : pais = "Colombia (CO)"; break;
            case "CR" : pais = "Costa Rica (CR)"; break;
            case "DO" : pais = "Dominican Republic (DO)"; break;
            case "EC" : pais = "Ecuador (EC)"; break;
            case "SV" : pais = "El Salvador (SV)"; break;
            case "GT" : pais = "Guatemala (GT)"; break;
            case "HN" : pais = "Honduras (HN)"; break;
            case "MX" : pais = "Mexico (MX)"; break;
            case "NI" : pais = "Nicaragua (NL)"; break;
            case "PA" : pais = "Panama (PA)"; break;
            case "PY" : pais = "Paraguay (PY)"; break;
            case "PE" : pais = "Peru (PE)"; break;
            case "PR" : pais = "Puerto Rico (PR)"; break;
            case "ES" : pais = "Spain (ES)"; break;
            case "UY" : pais = "Uruguay (UY)"; break;
            case "VE" : pais = "Venezuela (VE)"; break;
            case "SE" : pais = "Sweden (SE)"; break;
            case "TH" : pais = "Thailand (TH)"; break;
            case "TR" : pais = "Turkey (TR)"; break;
            case "UA" : pais = "Ukraine (UA)"; break;
            case "VN" : pais = "Vietnam (VN)"; break;
        }
        return pais;
    }

}