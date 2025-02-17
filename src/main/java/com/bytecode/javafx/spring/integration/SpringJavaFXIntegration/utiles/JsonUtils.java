package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.utiles;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JsonUtils {
    private static ZonedDateTime now = ZonedDateTime.now();
    public LocalDate date = LocalDate.now();
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static Digic convertJsonToDigic(JSONObject myJson) {
            String a;

            if (myJson.isNull("cuentaSinIBAN")){
                myJson.put("cuentaSinIBAN","NO");
            }
            a = (String) myJson.get("cuentaSinIBAN");
            if(a.length() == 0) myJson.put("cuentaSinIBAN","NO");

            if (myJson.isNull("paisBanco")){
                myJson.put("paisBanco","");
            }

            //TODO: Validar si van a ser códigos de2 o 3 dígitos
            String pais = (String) myJson.get("paisBanco");
            if (pais.length()>3){
                myJson.put("paisBanco",pais.substring(0,3));
            }

            if (myJson.isNull("claveBanco")){
                myJson.put("claveBanco","");
            }

            if (myJson.isNull("valorMedioPago")){
                myJson.put("valorMedioPago","");
            }

            if (myJson.isNull("descInstFinanciera")){
                myJson.put("descInstFinanciera","");
            }

            if (myJson.isNull("numeroABA")){
                myJson.put("numeroABA","");
            }

            if (myJson.isNull("claveControl")){
                myJson.put("claveControl","");
            }

            if (myJson.isNull("modoPago")){
                myJson.put("modoPago","CUENTA");
            }
            a = (String) myJson.get("modoPago");
            if(a.length() == 0) myJson.put("modoPago","CUENTA");

            if (myJson.isNull("codigoBic")){
                myJson.put("codigoBic","");
            }

            if (myJson.isNull("valorMedioPago")){
                myJson.put("valorMedioPago","");
            }

            if (myJson.isNull("modoTransporte")){
                myJson.put("modoTransporte","");
            }

            if (myJson.isNull("identificadorBillete")){
                myJson.put("identificadorBillete","");
            }

            String monto = "0";

            //System.out.println(myJson.get("totalDigic").getClass().getSimpleName());
            //if(!myJson.get("totalDigic").equals(0)) monto = Double.toString((Double) myJson.get("totalDigic"));

            switch (myJson.get("totalDigic").getClass().getSimpleName()){
                case "Integer":
                    monto = Integer.toString((Integer) myJson.get("totalDigic"));
                    break;
                case "Double":
                    monto = Double.toString((Double) myJson.get("totalDigic"));
                    break;
            }

            // CAMBIAMOS EL FORMATODE FECHA A FORAMATO ESPAÑA
            //
            LocalDate fechaFactura = LocalDate.parse((String) myJson.get("fechaFactura"), formatter);
            String tempFechaFactura = fechaFactura.toString();
            tempFechaFactura = tempFechaFactura.substring(8,10) + "/" + tempFechaFactura.substring(5,7) + "/" + tempFechaFactura.substring(0,4) ;

            LocalDate fechaLimiteSalida = LocalDate.parse((String) myJson.get("fechaLimiteSalida"), formatter);
            String tempfechaLimiteSalida = fechaLimiteSalida.toString();
            tempfechaLimiteSalida = tempfechaLimiteSalida.substring(8,10) + "/" + tempfechaLimiteSalida.substring(5,7) + "/" + tempfechaLimiteSalida.substring(0,4) ;


            Digic digic = new Digic();
                digic.setJustificante((String) myJson.get("justificante"));
                digic.setTotalDigic(monto);
                digic.setNombreViajero((String) myJson.get("nombreViajero"));
                digic.setApellidosViajero((String) myJson.get("apellidosViajero"));
                digic.setTipoDocumento((String) myJson.get("tipoDocumento"));
                digic.setValorDocumento((String) myJson.get("valorDocumento"));
                digic.setPaisExpedicion((String) myJson.get("paisExpedicion"));
                digic.setPaisResidencia((String) myJson.get("paisResidencia"));
                digic.setNifEstablecimiento((String) myJson.get("nifEstablecimiento"));
                digic.setRazonSocial((String) myJson.get("razonSocial"));
                digic.setNumeroFactura((String) myJson.get("numeroFactura"));
                digic.setFechaFactura(tempFechaFactura);
                digic.setFechaLimiteSalida(tempfechaLimiteSalida);
                digic.setModoPago((String) myJson.get("modoPago"));
                digic.setEmail((String) myJson.get("email"));
                digic.setCodigoBic((String) myJson.get("codigoBic"));
                digic.setValorMedioPago((String) myJson.get("valorMedioPago"));
                digic.setClaveControl((String) myJson.get("claveControl"));
                digic.setCuentaSinIBAN((String) myJson.get("cuentaSinIBAN"));
                digic.setCuentaInternacional((String) myJson.get("cuentaSinIBAN"));
                digic.setNumeroABA((String) myJson.get("numeroABA"));
                digic.setClaveBanco((String) myJson.get("claveBanco"));
                digic.setDescInstFinanciera((String) myJson.get("descInstFinanciera"));
                digic.setPaisBanco((String) myJson.get("paisBanco"));
                digic.setModoTransporte((String) myJson.get("modoTransporte"));
                digic.setIdentificadorBillete((String) myJson.get("identificadorBillete"));
                digic.setUuidProceso(App.UUIDProcess);
                digic.setFechaCreacion(now.toString());
                digic.setEstatus_upload(3);

                //digic.setClaveUuid((String) App.UUIDProcess);

            return digic;
    }

    
}
