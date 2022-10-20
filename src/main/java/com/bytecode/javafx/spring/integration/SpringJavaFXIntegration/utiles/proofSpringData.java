package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.utiles;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@SpringBootApplication
public class proofSpringData implements Initializable {

    @Autowired
    public DigicRepository digicRepository;

    public static void main(String[] args) {

    }

    public void DigicUpdatStatus(String valordocumento) {

        List<Digic> digicLis = digicRepository.findAllByValorDocumentoEstatus(valordocumento, 3);

        try{
            if(!digicLis.isEmpty()){

                for (Digic digic: digicLis) {
                    digic.setEstatus_upload(2);
                }
                digicRepository.saveAll(digicLis);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DigicUpdatStatus("44303145Q");
    }
}
