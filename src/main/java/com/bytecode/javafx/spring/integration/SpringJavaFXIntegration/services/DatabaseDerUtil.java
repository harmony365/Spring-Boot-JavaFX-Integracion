package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import org.grecasa.ext.mw.externo.kiosko_service.DerType;
import org.grecasa.ext.mw.externo.kiosko_service.MedioPagoType;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class DatabaseDerUtil {

    @Autowired
    private DigicModoPagoRepository digicModoPagoRepository;

    @Autowired
    private DigicRepository digicRepository;

    public ValidarRemesaDer getDERtoSend(String valorDocumento, Integer estatus) {

        ValidarRemesaDer validarRemesaDer = new ValidarRemesaDer();
        validarRemesaDer.setCodigoKiosko("NULL");

        List<Digic> digicLis = digicRepository.findAllByValorDocumentoEstatus(valorDocumento, estatus);

        if(!digicLis.isEmpty()){
            setRemesaDerFromDigicBD(digicLis.get(0), validarRemesaDer);
            setMedioPagoFromBd(digicLis.get(0), validarRemesaDer);
        }

        for (Digic digic: digicLis) {
            validarRemesaDer.getDerListKiosko().add(getDerTypeFromBD(digic));
        }

        return  validarRemesaDer;
    }

    private DerType getDerTypeFromBD(Digic digic) {
        DerType derType = new DerType();

        derType.setJustificante(digic.getJustificante());
        derType.setFechaFactura(digic.getFechaFactura());
        derType.setNifEstablecimiento(digic.getNifEstablecimiento());
        derType.setNumeroFactura(digic.getNumeroFactura());
        derType.setRazonSocial(digic.getRazonSocial());
        derType.setTotalDigic(new BigDecimal(digic.getTotalDigic()));

        return derType;
    }

    private void setMedioPagoFromBd(Digic digic, ValidarRemesaDer validarRemesaDer) {
        //TODO terminar de rellenar
        MedioPagoType medioPagoType = new MedioPagoType();
        for(DigicModoPago digicModoPago : digicModoPagoRepository.findByValorDocumento(digic.getValorDocumento()))
        {

            medioPagoType.setCodMedioPago(digicModoPago.getModoPago());

            Boolean r = true;
            if (digicModoPago.getCuentaSinIBAN().equals("SI")) r = false;

            medioPagoType.setCuentaSinIBAN(r);
            medioPagoType.setValorMedioPago(digicModoPago.getValorMedioPago());
            medioPagoType.setPaisBanco(digicModoPago.getPaisBanco());
            medioPagoType.setClaveBanco(digicModoPago.getClaveBanco());
            medioPagoType.setClaveControl(digicModoPago.getClaveControl());
            medioPagoType.setDescInstFinanciera(digicModoPago.getDescInstFinanciera());
            medioPagoType.setBIC(digicModoPago.getCodigoBic());
            medioPagoType.setNumeroABA(digicModoPago.getNumeroABA());

        }
        validarRemesaDer.setMedioPago(medioPagoType);
    }

    private void setRemesaDerFromDigicBD(Digic digic, ValidarRemesaDer validarRemesaDer) {
        //TODO terminar de rellenar
        validarRemesaDer.setMailViajero(digic.getEmail());
        validarRemesaDer.setOnline(Boolean.TRUE);


 /*
        ValidarRemesaDer ValidarRemesaDer = new ValidarRemesaDer();
        ValidarRemesaDer.setTipoDocumento("PASAPORTE");
        ValidarRemesaDer.setValorTipoDocumento("2AB000254AAF");
        ValidarRemesaDer.setNombreViajero("CHRISTIAN ALBERTO");
        ValidarRemesaDer.setApellidosViajero("GEUSCH GESSELLSCHAFT-BERATUNG");
        ValidarRemesaDer.setPaisExpedicion("GS");
        ValidarRemesaDer.setPaisResidencia("QU");

        ValidarRemesaDer.setMailViajero("ejimenez@asistacanarias.org");
        ValidarRemesaDer.setCodigoKiosko("9S716U722600ZL2000094");
        ValidarRemesaDer.setOnline(Boolean.TRUE);
        ValidarRemesaDer.setFechaSolicitud(getFecha());
        ValidarRemesaDer.setFechaEnvioSolicitud(getFecha());
  */




    }


    public XMLGregorianCalendar getFecha() {

        try {

            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT")));

        } catch (DatatypeConfigurationException e1) {
            e1.printStackTrace();
        }

        return null;
    }
}
