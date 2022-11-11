/**
 * Sample Skeleton for 'dialogo_validar_der.fxml' Controller Class
 */
package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class dialogo_validar_derController {

    public Modelo_403Controller m403 = new Modelo_403Controller();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // Image
    private ImageView icon_img;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws FileNotFoundException {

        //m403.p2_img_barcode.requestFocus();
        String imagen = "error";

        Image image1 = new Image(getClass().getResourceAsStream("/img/icon-" + imagen + ".png"));
        icon_img = new ImageView(image1);




    }
    @FXML
    private JFXButton btn_salir;

    @FXML
    void switchToSalir(ActionEvent event) {
        cerrarVentana(event);
    }

    public static void cerrarVentana(ActionEvent e) {
        Node source = (Node) e.getSource();     //Me devuelve el elemento al que hice click
        Stage stage = (Stage) source.getScene().getWindow();    //Me devuelve la ventana donde se encuentra el elemento
        stage.close();                          //Me cierra la ventana
    }
}
