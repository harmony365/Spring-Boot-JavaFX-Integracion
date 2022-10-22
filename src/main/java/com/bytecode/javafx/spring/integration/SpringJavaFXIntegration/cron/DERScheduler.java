package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.cron;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services.DatabaseDerUtil;
import org.grecasa.ext.mw.externo.KioskoServiceClient;
import org.grecasa.ext.mw.externo.KioskoServiceClientUtils;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDer;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DERScheduler {

    @Autowired
    public DigicRepository digicRepository;

    @Autowired
    private DigicModoPagoRepository digicModoPagoRepository;

    @Autowired
    DatabaseDerUtil databaseDerUtil;


    @Scheduled(cron = "${cron.expression}")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis()/1000;
        System.out.println("Scheldule task DER WSDL run: " + now);

        //List<Digic> digicLis = digicRepository.findAllByValorDocumentoEstatus("44303145Q", 2);
        List<DigicModoPago> digicModoPagoLis = digicModoPagoRepository.findAllByEstatus(2);

        try{

            if(!digicModoPagoLis.isEmpty()){

                System.out.println("Total de Documento a procesar: " + digicModoPagoLis.size());
                System.out.println("__________________________________________\n");

                for (DigicModoPago digicmodopago: digicModoPagoLis) {
                    System.out.println("Valor Documento: " + digicmodopago.getValorDocumento());
                    System.out.println("Email: " + digicmodopago.getEmail());
                    System.out.println("__________________________________________\n");

                    onWsdl(digicmodopago.getValorDocumento());

                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void onWsdl(String valorDocumento){

        String WsdlTimeStamp = "";
        String WsdlResponse = "";

        try {

            ValidarRemesaDerResponse validarRemesaDerResponse;

            ValidarRemesaDer validarRemesaDer = databaseDerUtil.getDERtoSend(valorDocumento, 2, App.parametrosModel.getKIOSKOID());
            validarRemesaDerResponse = KioskoServiceClient.getInstance().validarRemesa(validarRemesaDer);
            KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);
            WsdlTimeStamp = validarRemesaDerResponse.getFechaEstado().toString();
            WsdlResponse  = validarRemesaDerResponse.getEstado();


        }catch (Exception e) {
            e.printStackTrace();
            WsdlResponse ="RED";
        }

        System.out.println("Respuesta WSDL CODE -> " + WsdlResponse);
        System.out.println("Respuesta WSDL TimeStamp -> " + WsdlTimeStamp);

        //TODO: validar bien el cambio de estatus. sólo falta ver bien cual sería la regla para los estatus ER.

        if(WsdlResponse.equals("RED")  || WsdlResponse.equals("ER") ||
           WsdlResponse.equals("PR02") || WsdlResponse.length() > 5) {
            // TODO: Qué hacer en el caso de que nos siga dando el Error RED ó ER o sea una respuesta de error de mas de 5 carácteres?
            //  No seteamos la variable porque está dandoel mismo Error

            DigicUpdatStatus(valorDocumento,2,2);
            System.out.println("Respuesta WSDL -> Se encontró un error y no se pudo cambiar el estatus de la operación." );
        }

        if(WsdlResponse.equals("KO")) {
            DigicUpdatStatus(valorDocumento,2,1);
        }
        if(WsdlResponse.equals("OK")) {
            DigicUpdatStatus(valorDocumento,2,1);
        }

    }

    public void DigicUpdatStatus(String valordocumento, Integer estatusbuscar, Integer estatuscambiar) {

        List<Digic> digicLis = digicRepository.findAllByValorDocumentoEstatus(valordocumento, estatusbuscar);

        try{
            if(!digicLis.isEmpty()){

                for (Digic digic: digicLis) {
                    digic.setEstatus_upload(estatuscambiar);
                }
                digicRepository.saveAll(digicLis);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        List<DigicModoPago> digicModoPagoList = digicModoPagoRepository.findAllByValorDocumentoEstatus(valordocumento, 3);

        try{
            if(!digicModoPagoList.isEmpty()){

                for (DigicModoPago digicModoPago: digicModoPagoList) {
                    digicModoPago.setEstatusUpload(2);
                }
                digicModoPagoRepository.saveAll(digicModoPagoList);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}

