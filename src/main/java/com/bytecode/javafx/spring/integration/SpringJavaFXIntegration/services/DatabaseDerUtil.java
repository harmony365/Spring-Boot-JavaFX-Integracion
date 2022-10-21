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

    public ValidarRemesaDer getDERtoSend(String valorDocumento, Integer estatus, String kiosko) {

        ValidarRemesaDer validarRemesaDer = new ValidarRemesaDer();

        validarRemesaDer.setCodigoKiosko(kiosko);

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
        //TODO: Validar el formato de la fecha de la factura en la lista de DER enviada.
        //derType.setFechaFactura(String.valueOf(getFecha()));
        derType.setNifEstablecimiento(digic.getNifEstablecimiento());
        derType.setNumeroFactura(digic.getNumeroFactura());
        derType.setRazonSocial(digic.getRazonSocial());
        derType.setTotalDigic(new BigDecimal(digic.getTotalDigic()));

        return derType;
    }

    private void setMedioPagoFromBd(Digic digic, ValidarRemesaDer validarRemesaDer) {
        MedioPagoType medioPagoType = new MedioPagoType();
        for(DigicModoPago digicModoPago : digicModoPagoRepository.findByValorDocumento(digic.getValorDocumento()))
        {

            medioPagoType.setCodMedioPago(digicModoPago.getModoPago());

            //TODO: Verificar este campo deberia ir con los valores SI ó NO
            // NOTE: ya se verificó lo de la cuenta sin iban, es correcto el true y false.
            // Pregunta importante.

            Boolean r = false;
            if (digicModoPago.getCuentaSinIBAN().equals("SI")) r = true;

            medioPagoType.setCuentaSinIBAN(r);
            medioPagoType.setValorMedioPago(digicModoPago.getValorMedioPago());
            medioPagoType.setPaisBanco(digicModoPago.getPaisBanco());
            medioPagoType.setClaveBanco(digicModoPago.getClaveBanco());
            medioPagoType.setClaveControl(digicModoPago.getClaveControl());
            medioPagoType.setDescInstFinanciera(digicModoPago.getDescInstFinanciera());
            medioPagoType.setBIC(digicModoPago.getCodigoBic());
            medioPagoType.setNumeroABA(digicModoPago.getNumeroABA());

            validarRemesaDer.setMailViajero(digicModoPago.getEmail());
            //TODO: verificar por qué no se setea la fecha limite de salida.
            //validarRemesaDer.setF(digicModoPago.getFechaLimiteSalida());
        }
        validarRemesaDer.setMedioPago(medioPagoType);

    }

    private void setRemesaDerFromDigicBD(Digic digic, ValidarRemesaDer validarRemesaDer) {

        validarRemesaDer.setMailViajero(digic.getEmail());
        validarRemesaDer.setOnline(Boolean.TRUE);
        validarRemesaDer.setTipoDocumento(digic.getTipoDocumento());
        validarRemesaDer.setValorTipoDocumento(digic.getValorDocumento());
        validarRemesaDer.setNombreViajero(digic.getNombreViajero());
        validarRemesaDer.setApellidosViajero(digic.getApellidosViajero());
        validarRemesaDer.setPaisExpedicion(digic.getPaisExpedicion());
        validarRemesaDer.setPaisResidencia(digic.getPaisResidencia());
        validarRemesaDer.setFechaSolicitud(getFecha());
        validarRemesaDer.setFechaEnvioSolicitud(getFecha());

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
