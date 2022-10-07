package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model;

import org.grecasa.ext.mw.externo.kiosko_service.DerType;
import org.grecasa.ext.mw.externo.kiosko_service.MedioPagoType;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DummyData {

    public static ValidarRemesaDer getExample() {

        ValidarRemesaDer ValidarRemesaDer = new ValidarRemesaDer();
        ValidarRemesaDer.setTipoDocumento("DNI");
        ValidarRemesaDer.setValorTipoDocumento("548965432");
        ValidarRemesaDer.setNombreViajero("Jonathan");
        ValidarRemesaDer.setApellidosViajero("Ramos");
        ValidarRemesaDer.setPaisExpedicion("ES");
        ValidarRemesaDer.setPaisResidencia("ES");
        ValidarRemesaDer.getDerListKiosko().add(new DerType());
        ValidarRemesaDer.setMedioPago(new MedioPagoType());
        ValidarRemesaDer.setMailViajero("jonathan@hotmail.com");
        ValidarRemesaDer.setCodigoKiosko("1");
        ValidarRemesaDer.setOnline(Boolean.TRUE);
        ValidarRemesaDer.setFechaSolicitud(getFecha());
        ValidarRemesaDer.setFechaEnvioSolicitud(getFecha());

        return  ValidarRemesaDer;
    }
    public static ValidarRemesaDer getExampleKO() {

        ValidarRemesaDer ValidarRemesaDer = new ValidarRemesaDer();
        ValidarRemesaDer.setTipoDocumento("PASAPORTE");
        ValidarRemesaDer.setValorTipoDocumento("2AB000254AAF");
        ValidarRemesaDer.setNombreViajero("CHRISTIAN ALBERTO");
        ValidarRemesaDer.setApellidosViajero("GEUSCH GESSELLSCHAFT-BERATUNG");
        ValidarRemesaDer.setPaisExpedicion("GS");
        ValidarRemesaDer.setPaisResidencia("QU");

        DerType derType = new DerType();
        derType.setFechaFactura("20220715");
        derType.setJustificante("4032400002705");
        derType.setNifEstablecimiento("44303145Q");
        derType.setNumeroFactura("2022/0711");
        derType.setRazonSocial("Mario Rodriguez Reyes");
        derType.setTotalDigic(new BigDecimal(130.14));
        ValidarRemesaDer.getDerListKiosko().add(derType);

        DerType derType2 = new DerType();
        derType.setFechaFactura("20220715");
        derType.setJustificante("4032400002701");
        derType.setNifEstablecimiento("44303145Q");
        derType.setNumeroFactura("2022/0711");
        derType.setRazonSocial("Mario Rodriguez Reyes");
        derType.setTotalDigic(new BigDecimal(330.14));
        ValidarRemesaDer.getDerListKiosko().add(derType2);

        DerType derType3 = new DerType();
        derType.setFechaFactura("20220715");
        derType.setJustificante("4032400002702");
        derType.setNifEstablecimiento("44303145Q");
        derType.setNumeroFactura("2022/0711");
        derType.setRazonSocial("Mario Rodriguez Reyes");
        derType.setTotalDigic(new BigDecimal(1330.14));
        ValidarRemesaDer.getDerListKiosko().add(derType3);        

        MedioPagoType medioPago = new MedioPagoType();
        medioPago.setBIC("12345678901");
        medioPago.setCodMedioPago("CUENTA");
        medioPago.setCuentaSinIBAN(true);
        medioPago.setValorMedioPago("GB98MIDL07009312345678");
        medioPago.setDescInstFinanciera("GREAT RUSSELL STREET");
        medioPago.setPaisBanco("222");
        medioPago.setClaveBanco("456789112345678");
        medioPago.setClaveControl("41");
        medioPago.setNumeroABA("123547890");
        ValidarRemesaDer.setMedioPago(medioPago);

        ValidarRemesaDer.setMailViajero("ejimenez@asistacanarias.org");
        ValidarRemesaDer.setCodigoKiosko("9S716U722600ZL2000094");
        ValidarRemesaDer.setOnline(Boolean.TRUE);
        ValidarRemesaDer.setFechaSolicitud(getFecha());
        ValidarRemesaDer.setFechaEnvioSolicitud(getFecha());

        return  ValidarRemesaDer;
    }

    public static XMLGregorianCalendar getFecha() {

        try {

            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT")));

        } catch (DatatypeConfigurationException e1) {
            e1.printStackTrace();
        }

        return null;
    }
}
