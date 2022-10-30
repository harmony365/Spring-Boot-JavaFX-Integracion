package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.cron;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services.DatabaseDerUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final static Logger LOGGER = LogManager.getLogger(DERScheduler.class.getName());

    @Scheduled(cron = "${cron.expression}")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis()/1000;
        //System.out.println("Scheldule task DER WSDL run: " + now);
        LOGGER.log(Level.INFO, "Scheldule task DER WSDL run: " + now);

        //List<Digic> digicLis = digicRepository.findAllByValorDocumentoEstatus("44303145Q", 2);
        List<DigicModoPago> digicModoPagoLis = digicModoPagoRepository.findAllByEstatus(2);

        try{

            if(!digicModoPagoLis.isEmpty()){

                //System.out.println("Total de Documento a procesar: " + digicModoPagoLis.size());
                //System.out.println("__________________________________________\n");

                LOGGER.log(Level.TRACE, "Total de Documento a procesar: " + digicModoPagoLis.size());

                for (DigicModoPago digicmodopago: digicModoPagoLis) {
                    //System.out.println("uuidProceso: " + digicmodopago.getUuidProceso());
                    //System.out.println("Valor Documento: " + digicmodopago.getValorDocumento());
                    //System.out.println("__________________________________________\n");

                    LOGGER.log(Level.TRACE, "uuidProceso: " + digicmodopago.getUuidProceso() +
                                               " | Valor Documento: " + digicmodopago.getValorDocumento());

                    //onWsdl(digicmodopago.getValorDocumento());
                    onWsdl(digicmodopago.getUuidProceso());

                }

            }


        }catch (Exception e){
            e.printStackTrace();
            LOGGER.log(Level.ERROR, "Error Encontrado: " + e.getCause());
        }

    }

    public void onWsdl(String uuidProceso){

        String WsdlTimeStamp = "";
        String WsdlResponse = "";

        try {

            ValidarRemesaDerResponse validarRemesaDerResponse;

            ValidarRemesaDer validarRemesaDer = databaseDerUtil.getDERtoSend(uuidProceso, 2, App.parametrosModel.getKIOSKOID());
            validarRemesaDerResponse = KioskoServiceClient.getInstance().validarRemesa(validarRemesaDer);
            KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);
            WsdlTimeStamp = validarRemesaDerResponse.getFechaEstado().toString();
            WsdlResponse  = validarRemesaDerResponse.getEstado();


        }catch (Exception e) {
            e.printStackTrace();
            WsdlResponse ="RED";
            LOGGER.log(Level.ERROR, "Problema de TCPIP: " + e.getCause());
        }

        //System.out.println("Respuesta WSDL CODE -> " + WsdlResponse);
        //System.out.println("Respuesta WSDL TimeStamp -> " + WsdlTimeStamp);
        LOGGER.log(Level.TRACE, "Respuesta WSDL CODE -> " + WsdlResponse + " TimeStamp -> " + WsdlTimeStamp);

        //TODO: validar bien el cambio de estatus. sólo falta ver bien cual sería la regla para los estatus ER.

        if(WsdlResponse.equals("KO") || WsdlResponse.equals("RED")  || WsdlResponse.equals("ER") ||
           WsdlResponse.equals("PR02") || WsdlResponse.length() > 5) {
            // TODO: Qué hacer en el caso de que nos siga dando el Error RED ó ER o sea una respuesta de error de mas de 5 carácteres?
            //  No seteamos la variable porque está dandoel mismo Error

            databaseDerUtil.DigicUpdatStatus(uuidProceso,2,2);
            //System.out.println("Respuesta WSDL -> Se encontró un error y no se pudo cambiar el estatus de la operación." );
            LOGGER.log(Level.ERROR, "Respuesta WSDL -> Se encontró un error y no se pudo cambiar el estatus de la operación." );
        }

        if(WsdlResponse.equals("OK")) {
            databaseDerUtil.DigicUpdatStatus(uuidProceso,2,1);
        }

    }


}

