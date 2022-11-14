/**
 * Sample Skeleton for 'dialogo_validar_der.fxml' Controller Class
 */
package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.jfoenix.controls.JFXButton;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class dialogo_validar_derController implements Initializable  {

    public Modelo_403Controller m403 = new Modelo_403Controller();

    private ResourceBundle bundle;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // Image
    private ImageView icon_img;

    @FXML
    private Label p5_lb_mensaje;

    @FXML
    private Button p5_btn_salir;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources)  {

        bundle = resources;

        //App.MensajeValidaDER_icon  = "warning";
        //App.MensajeValidaDER_error = "p5_lb_mensaje_WARNING";

        if(!App.MensajeValidaDER_action) p5_btn_salir.setText(bundle.getString("p5_btn_revisar"));

        icon_img.setImage(new Image(getClass().getResourceAsStream("/img/icon-" + App.MensajeValidaDER_icon + ".png")));

        p5_lb_mensaje.setText(bundle.getString(App.MensajeValidaDER_error));

    }
    @FXML
    private JFXButton btn_salir;

    @FXML
    void switchToSalir(ActionEvent event) throws IOException {
        cerrarVentana(event);
    }

    public static void cerrarVentana(ActionEvent e) throws IOException {

        if(App.MensajeValidaDER_action){
            Locale locale = Locale.getDefault();
            App.setRoot("/views/primary",locale);
        }

        delay(800, () -> {
            Node source = (Node) e.getSource();     //Me devuelve el elemento al que hice click
            Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
            stage.close(); //Me cierra la ventana
        });

    }

    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }



}
