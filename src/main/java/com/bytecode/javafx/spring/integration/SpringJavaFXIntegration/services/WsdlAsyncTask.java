package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.grecasa.ext.mw.externo.KioskoServiceClient;
import org.grecasa.ext.mw.externo.KioskoServiceClientUtils;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDer;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;


public class WsdlAsyncTask extends AsyncTask {
    private final static Logger LOGGER = LogManager.getLogger(WsdlAsyncTask.class.getName());
    private ValidarRemesaDerResponse validarRemesaDerResponse;
    private ValidarRemesaDer validarRemesaDer;

    public WsdlAsyncTask(ValidarRemesaDerResponse validarRemesaDerResponse, ValidarRemesaDer validarRemesaDer) {
        this.validarRemesaDerResponse = validarRemesaDerResponse;
        this.validarRemesaDer= validarRemesaDer;
    }


    @Override
    public void onPreExecute() {
        LOGGER.log(Level.INFO,"Background Thread will start");
    }

    @Override
    public Object doInBackground(Object[] params) {
        LOGGER.log(Level.INFO,"Background Thread is running");

        try {

            LOGGER.log(Level.INFO, "Llamando al servicio WSDL de ValidarRemesa().");

            validarRemesaDerResponse = KioskoServiceClient.getInstance().validarRemesa(validarRemesaDer);
            KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);
            LOGGER.log(Level.INFO, "Resultado: Estado( " + validarRemesaDerResponse.getEstado() + " ) --> " + validarRemesaDerResponse.getFechaEstado().toString());

        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Ocurrio un Error de RED, time out de conexión.");
            e.printStackTrace();
            return false;
        }

        return null;
    }

    @Override
    public void onPostExecute(Object params) {
        LOGGER.log(Level.INFO,"Background Thread has stopped");
    }

    @Override
    public void progressCallback(Object[] params) {
        LOGGER.log(Level.INFO,"Progress " + params[0]);
    }

}
