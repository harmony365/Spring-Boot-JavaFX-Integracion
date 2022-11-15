package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;


import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class Mantenimiento_DERController implements Initializable {

    @FXML
    private Button btn_salir;
    @FXML
    private JFXButton jfx_btn_salir;

    @FXML
    private void eventAction(ActionEvent event) throws IOException {

        Object evt = event.getSource();

        if(evt.equals(btn_salir) || evt.equals(jfx_btn_salir)){
            Locale locale = Locale.getDefault();
            App.setRoot("/views/primary",locale);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        if(App.parametrosModel.getAppDemo()){
        }

    }


}
