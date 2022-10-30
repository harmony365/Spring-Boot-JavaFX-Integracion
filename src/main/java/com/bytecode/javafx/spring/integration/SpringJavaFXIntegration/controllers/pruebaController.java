/**
 * Sample Skeleton for 'prueba.fxml' Controller Class
 */
package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class pruebaController {

    public Modelo_403Controller m403 = new Modelo_403Controller();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        //m403.p2_img_barcode.requestFocus();

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
