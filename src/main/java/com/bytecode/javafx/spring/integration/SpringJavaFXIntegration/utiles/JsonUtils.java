package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.utiles;

import org.json.JSONObject;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;

public class JsonUtils {

    public static Digic convertJsonToDigic(JSONObject myJson) {

            if (myJson.isNull("cuentaSinIBAN")){
                myJson.put("cuentaSinIBAN","NO");
            }
            
            if (myJson.isNull("paisBanco")){
                myJson.put("paisBanco","");
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
                myJson.put("modoPago","");
            }
        
            if (myJson.isNull("codigoBic")){
                myJson.put("codigoBic","");
            }

            if (myJson.isNull("valorMedioPago")){
                myJson.put("valorMedioPago","");
            };

            if (myJson.isNull("modoTransporte")){
                myJson.put("modoTransporte","");
            };

            if (myJson.isNull("identificadorBillete")){
                myJson.put("identificadorBillete","");
            };

            String monto = Double.toString((Double) myJson.get("totalDigic"));

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
            digic.setFechaFactura((String) myJson.get("fechaFactura"));
            digic.setFechaLimiteSalida((String) myJson.get("fechaLimiteSalida"));
            digic.setCuentaSinIBAN((String) myJson.get("cuentaSinIBAN"));
            digic.setModoPago((String) myJson.get("modoPago"));
            digic.setEmail((String) myJson.get("email"));
            digic.setCodigoBic((String) myJson.get("codigoBic"));
            digic.setValorMedioPago((String) myJson.get("valorMedioPago"));
            digic.setClaveControl((String) myJson.get("claveControl"));
            digic.setCuentaSinIBAN((String) myJson.get("cuentaSinIBAN"));
            digic.setNumeroABA((String) myJson.get("numeroABA"));  
            digic.setClaveBanco((String) myJson.get("claveBanco"));
            digic.setDescInstFinanciera((String) myJson.get("descInstFinanciera"));
            digic.setPaisBanco((String) myJson.get("paisBanco"));
            digic.setModoTransporte((String) myJson.get("modoTransporte"));
            digic.setIdentificadorBillete((String) myJson.get("identificadorBillete"));
            digic.setEstatus_upload((Integer) 3);
            
                            /* 
                0,
                "",
                ""
            );
            // */

        return digic;
    }

    
}
