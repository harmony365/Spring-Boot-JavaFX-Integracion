package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

@Component
public class PassPortLoginController  implements Initializable {
    
    private String ScannerReader ="";
    private ResourceBundle bundle;

    // Strings which hold css elements to easily re-use in the SpringJavaFxIntegrationApplicationlication
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String successStyle   = String.format("-fx-border-color: GREEN; -fx-border-width: 2; -fx-border-radius: 5;");
    String errorMessage   = String.format("-fx-text-fill: RED;");
    String errorStyle     = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");


    @FXML
    private Button btnCancelar, btnLogin, p3_btn_qr, p3_btn_demo;

    @FXML
    private Label p3_lb_nombre, p3_lb_numero_pasaporte;

    @FXML
    private VBox containerLeft, containerRight;

    @FXML
    private ImageView p3_logo_img, p3_passaport_img, p3_qrcode_img;

    @FXML
    private TextField p3_tf_numerodocumeto;

    @FXML
    void eventAction(ActionEvent event) {

    }

    @FXML
    void switchToQRCode(ActionEvent event) {
        p3_passaport_img.requestFocus();
    }

    @FXML 
    private void switchToAnterior() throws IOException {
        Locale locale = Locale.getDefault();
        App.setRoot("/views/primary",locale);

    }   

    @FXML 
    private void switchToAceptar() throws IOException {


        if (p3_tf_numerodocumeto.getText().isEmpty()) {
            //invalidDetails.setText("The Login fields are required!");
            
            p3_tf_numerodocumeto.setStyle(errorStyle);
            new animatefx.animation.Shake(p3_tf_numerodocumeto).play();
            new animatefx.animation.Wobble(p3_tf_numerodocumeto).play();

            p3_tf_numerodocumeto.requestFocus();
            
        } else {

            p3_tf_numerodocumeto.setStyle(successStyle);
            new animatefx.animation.Shake(p3_tf_numerodocumeto).play();
            new animatefx.animation.Pulse(p3_tf_numerodocumeto).play();

            App.parametrosModel.setNumeroPasaporte(p3_tf_numerodocumeto.getText().trim());
            App.parametrosModel.setDniNifNieTieDemo(p3_tf_numerodocumeto.getText().trim());
           // App.parametrosModel.setNombreViajero("");

            UUID uuid= UUID.randomUUID();

            App.UUIDProcess = (uuid.toString());

            Locale locale = Locale.getDefault();
            App.setRoot("/views/modelo_403_v2",locale);            
        }

    }   

 
    @FXML 
    private void QRcodeRead(String passcode) throws IOException {
 
        p3_passaport_img.requestFocus();

        String 
        index_passcode=null, 
        numero_passcode=null, 
        nombre_passcode=null,
        pais_passcode=null;

        Boolean success = false;

 
        try {
            
            
            System.out.print("\nPasswordCode: " + passcode + "\nTime: " + System.currentTimeMillis());

            /*
             * Seteo de Campos del formulario
            */
            index_passcode  = passcode.substring(0,1);

            App.parametrosModel.setTipoDoumento(index_passcode);
                    
            switch (index_passcode){
                case "P":

                    pais_passcode   = passcode.substring(2,5);
                    numero_passcode = passcode.substring(44, 54).replaceAll("<", " ").trim();
                    nombre_passcode = passcode.substring(5, 43).replaceAll("<<", " ").replaceAll("<", " ").trim();
                    App.parametrosModel.setNumeroPasaporte(numero_passcode);
                    App.parametrosModel.setNombreViajero(nombre_passcode);
                    success = true;
                    break;

                case "I":

                    pais_passcode   = passcode.substring(2,5);
                    numero_passcode = passcode.substring(15, 30).replaceAll("<", " ").trim();
                    nombre_passcode = passcode.substring(60, 90).replaceAll("<<", " ").replaceAll("<", " ").trim();
                    App.parametrosModel.setDniNifNieTieDemo(numero_passcode);
                    App.parametrosModel.setNombreViajero(nombre_passcode);
                    success = true;         
                    break;

                default:
                    break;

            }


            p3_lb_numero_pasaporte.setText(numero_passcode);            
            p3_lb_nombre.setText(nombre_passcode);
            p3_tf_numerodocumeto.setText(numero_passcode);
                    

            if (success) switchToAceptar();
            

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
        
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Funcion de inicio del Controlador
        //

        bundle = resources;
        p3_btn_demo.setVisible(App.parametrosModel.getAppDemo());
        p3_lb_nombre.setText("");
        p3_lb_numero_pasaporte.setText("");

        App.parametrosModel.setNumeroPasaporte("");
        App.parametrosModel.setDniNifNieTieDemo("");
        App.parametrosModel.setNombreViajero("");

        p3_passaport_img.requestFocus();
        p3_passaport_img.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                //System.out.println("p2_img_barcode Key Pressed: " + ke.getText());
                //System.out.println("p2_img_barcode Key Pressed: " + ke.getCharacter());
                ScannerReader = ScannerReader + ke.getCharacter(); 
                
                if (ke.getCode().equals(KeyCode.ENTER) || 
                    ke.getCharacter().getBytes()[0] == '\n' || 
                    ke.getCharacter().getBytes()[0] == '\r') {
                
                    System.out.println("\nData: " + ScannerReader);

                    try {

                        if(ScannerReader.length() <= 92 && ( ScannerReader.substring(0,1).equals("I") || ScannerReader.substring(0,1).equals("P"))) {
                            QRcodeRead(ScannerReader);
                        }else{

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText(null);
                            alert.setTitle("Error");
                            alert.setContentText("DOCUMENTO NO VALIDO.");
                            alert.showAndWait();
                            p3_passaport_img.requestFocus();
                        }
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

   

    @FXML private void QRcodeRead() throws IOException {
  
        //QRcodeRead(App.parametrosModel.getDniNifNieTieDemo());
        QRcodeRead(App.parametrosModel.getPasaporteDemo());
         
    }

  
}
