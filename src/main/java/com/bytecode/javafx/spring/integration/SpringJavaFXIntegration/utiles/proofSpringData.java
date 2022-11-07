package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.utiles;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class proofSpringData extends Application {

    @Autowired
    public DigicRepository digicRepository;

    @Override
    public void init(){

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //DigicUpdatStatus("44303145Q");
        DigicAllByuuiProceso("9cf31f0d-5e07-4281-81be-4b954ca16e17");

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


    public void DigicAllByuuiProceso(String uuidProceso) {
        try{

            List<Digic> digicLis = digicRepository.findByuuidProceso(uuidProceso);


            if(!digicLis.isEmpty()){

                for (Digic digic: digicLis) {
                    //digic.setEstatus_upload(2);
                }
                //digicRepository.saveAll(digicLis);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
