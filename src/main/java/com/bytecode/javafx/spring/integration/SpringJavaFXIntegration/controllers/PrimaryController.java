package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class PrimaryController implements Initializable  {

    private Integer ContadorImgUno=0, ContadorImgDos=0;

    private final static Logger LOGGER = LogManager.getLogger(PrimaryController.class.getName());
    //public String Segunda_Pantalla = "modelo_403_v1";
    public String Segunda_Pantalla = "/views/PassPortLogin";
    public String Admin_Pantalla = "/views/ViewLogin";

    @FXML private ImageView imgProcesando;
    @FXML private ImageView p1_imgview_1;
    @FXML private ImageView p1_imgview_2;
    @FXML private ImageView p1_imgview_QR;
    @FXML private StackPane stpProcesando;

    @FXML
    private void LoadProccess(Boolean status) {
        stpProcesando.setVisible(status);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method, no se está usando el método por ahora
        //
        //LOGGER.log(Level.INFO, "User successfully logged in {}");

        LoadProccess(false);

        FileInputStream stream = null;
        try {
            stream = new FileInputStream("GuiaQR.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);
        p1_imgview_QR.setImage(image);


    }

    @FXML
    private void SetCounterToCero()  {
        ContadorImgUno=0; ContadorImgDos=0;
    }

    @FXML
    private void SetCounterToImgUno()  {
        ContadorImgUno=ContadorImgUno + 1;
    }

    @FXML
    private void SetCounterToImgDos() throws IOException {
        ContadorImgDos= ContadorImgDos + 1;
        if(ContadorImgUno.equals(5) && ContadorImgDos.equals(3)){
            Locale locale = new Locale("es", "ES");
            Locale.setDefault(locale);
            App.setRoot(Admin_Pantalla, locale);
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
    }

    @FXML
    private void switchToSP() throws IOException {
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToEN() throws IOException {
        Locale locale = new Locale("en", "US");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToPT() throws IOException {
        Locale locale = new Locale("pt", "PT");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToAR() throws IOException {
        Locale locale = new Locale("ar", "AR");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToZH() throws IOException {
        Locale locale = new Locale("zh", "ZH");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToFR() throws IOException {
        Locale locale = new Locale("fr", "FR");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToIT() throws IOException {
        Locale locale = new Locale("it", "IT");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToJA() throws IOException {
        Locale locale = new Locale("ja", "JA");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToKO() throws IOException {
        Locale locale = new Locale("ko", "KO");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToRU() throws IOException {
        Locale locale = new Locale("ru", "RU");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

    @FXML
    private void switchToDE() throws IOException {
        Locale locale = new Locale("de", "DE");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}

 
    @FXML
    private void switchToTH() throws IOException {
        Locale locale = new Locale("th", "TH");
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}


    @FXML
    private void switchToLanguage(String vlanguage, String vcountry) throws IOException {
        Locale locale = new Locale(vlanguage, vcountry);
        Locale.setDefault(locale);
        App.setRoot(Segunda_Pantalla, locale);
	}


    @FXML
    private void switchToExit() throws IOException {
        Platform.exit();
    }

}
