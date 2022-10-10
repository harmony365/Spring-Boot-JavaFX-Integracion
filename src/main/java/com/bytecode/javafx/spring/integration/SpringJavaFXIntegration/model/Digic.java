package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name ="digic")
@Data 
public class Digic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "justificante", unique = true, nullable = false)
    private String justificante;

    @Column(name = "nombreViajero")
    private String nombreViajero;
    @Column(name = "apellidosViajero")
    private String apellidosViajero;
    @Column(name = "tipoDocumento")
    private String tipoDocumento;
    @Column(name = "valorDocumento")
    private String valorDocumento;
    @Column(name = "paisExpedicion")
    private String paisExpedicion;
    @Column(name = "paisResidencia")
    private String paisResidencia;
    @Column(name = "email")
    private String email;
    @Column(name = "nifEstablecimiento")
    private String nifEstablecimiento;
    @Column(name = "razonSocial")
    private String razonSocial;
    @Column(name = "numeroFactura")
    private String numeroFactura;
    @Column(name = "fechaFactura")
    private String fechaFactura;
    @Column(name = "totalDigic")
    private String totalDigic;
    @Column(name = "fechaLimiteSalida")
    private String fechaLimiteSalida;
    @Column(name = "cuentaInternacional")
    private String cuentaInternacional;
    @Column(name = "modoPago")
    private String modoPago;
    @Column(name = "codigoBic")
    private String codigoBic;
    @Column(name = "valorMedioPago")
    private String valorMedioPago;
    @Column(name = "claveControl")
    private String claveControl;
    @Column(name = "cuentaSinIBAN")
    private String cuentaSinIBAN;
    @Column(name = "numeroABA")
    private String numeroABA;
    @Column(name = "claveBanco")
    private String claveBanco;
    @Column(name = "descInstFinanciera")
    private String descInstFinanciera;
    @Column(name = "paisBanco")
    private String paisBanco;
    @Column(name = "modoTransporte")
    private String modoTransporte;
    @Column(name = "identificadorBillete")
    private String identificadorBillete;
    @Column(name = "estatus_upload")
    private Integer estatus_upload;
    @Column(name = "fecha_upload")
    private String fechaUpload;
    @Column(name = "fecha_creacion")
    private String fechaCreacion;

    public Digic() {
    }

    public Digic(
        String  justificante,
        String  nombreViajero,
        String  apellidosViajero,
        String  tipoDocumento,
        String  valorDocumento,
        String  paisExpedicion,
        String  paisResidencia,
        String  nifEstablecimiento,
        String  razonSocial,
        String  numeroFactura,
        String  fechaFactura,
        String  totalDigic,
        String  fechaLimiteSalida,
        String  cuentaInternacional,
        String  modoPago,
        String  email,
        String  codigoBic,
        String  valorMedioPago,
        String  claveControl,
        String  cuentaSinIBAN,
        String  numeroABA,
        String  claveBanco,
        String  descInstFinanciera,
        String  paisBanco,
        String  modoTransporte,
        String  identificadorBillete,    
        Integer estatus_upload,
        String  fecha_upload,
        String  fecha_creacion 
        ){

            this.justificante        = justificante;
            this.nombreViajero       = nombreViajero;
            this.apellidosViajero    = apellidosViajero;
            this.tipoDocumento       = tipoDocumento;
            this.valorDocumento      = valorDocumento;
            this.paisExpedicion      = paisExpedicion;
            this.paisResidencia      = paisResidencia;
            this.nifEstablecimiento  = nifEstablecimiento;
            this.razonSocial         = razonSocial;
            this.numeroFactura       = numeroFactura;
            this.fechaFactura        = fechaFactura;
            this.totalDigic          = totalDigic;
            this.fechaLimiteSalida   = fechaLimiteSalida;
            this.cuentaInternacional = cuentaInternacional;
            this.modoPago            = modoPago;
            this.email               = email;
            this.codigoBic           = codigoBic;
            this.valorMedioPago      = valorMedioPago;
            this.claveControl        = claveControl;
            this.cuentaSinIBAN       = cuentaSinIBAN;
            this.numeroABA           = numeroABA;
            this.claveBanco          = claveBanco;
            this.descInstFinanciera  = descInstFinanciera;
            this.paisBanco           = paisBanco;
            this.modoTransporte      = modoTransporte;
            this.identificadorBillete= identificadorBillete;    
            this.estatus_upload      = estatus_upload;
            this.fechaUpload        = fecha_upload;
            this.fechaCreacion      = fecha_creacion;
    }

    public String toJASON() {

        return "{ " +
                    "\"justificante\" : \""          + justificante + "\" ,  " +
                    "\"nombreViajero\" : \""         + nombreViajero + "\" ,  "  +
                    "\"apellidosViajero\" : \""      + apellidosViajero + "\" ,  " +
                    "\"tipoDocumento\" : \""         + tipoDocumento + "\" ,  " +
                    "\"valorDocumento\" : \""        + valorDocumento + "\" ,  " +
                    "\"paisExpedicion\" : \""        + paisExpedicion + "\" ,  " +
                    "\"paisResidencia\" : \""        + paisResidencia + "\" ,  " +
                    "\"nifEstablecimiento\" : \""    + nifEstablecimiento  + "\" ,  " +
                    "\"razonSocial\" : \""           + razonSocial + "\" ,  " +
                    "\"numeroFactura\" : \""         + numeroFactura + "\" ,  " +
                    "\"fechaFactura\" : \""          + fechaFactura + "\" ,  " +
                    "\"totalDigic\" : \""            + totalDigic + "\" ,  "+
                    "\"fechaLimiteSalida\" : \""     + fechaLimiteSalida + "\" ,  " +
                    "\"cuentaInternacional\" : \""   + cuentaInternacional + "\" ,  " +
                    "\"modoPago\" : \""              + modoPago + "\" ,  " +
                    "\"email\" : \""                 + email + "\" ,  " +
                    "\"codigoBic\" : \""             + codigoBic + "\" ,  " +
                    "\"valorMedioPago\" : \""        + valorMedioPago + "\" ,  " +
                    "\"claveControl\" : \""          + claveControl + "\" ,  " +
                    "\"cuentaSinIBAN\" : \""         + cuentaSinIBAN + "\" ,  " +
                    "\"numeroABA\" : \""             + numeroABA + "\" ,  " +
                    "\"claveBanco\" : \""            + claveBanco + "\" ,  " +
                    "\"descInstFinanciera\" : \""    + descInstFinanciera + "\" ,  " +
                    "\"paisBanco\" : \""             + paisBanco + "\" ,  " +
                    "\"modoTransporte\" : \""        + modoTransporte + "\" ,  " +
                    "\"identificadorBillete\" : \""  + identificadorBillete + "\" ,  " +
                    "\"fecha_upload\" : \""          + fechaUpload + "\" ,  " +
                    "\"fecha_creacion\" : \""        + fechaCreacion + "\" ,  " +
                    "\"estatus_upload\" : "          + estatus_upload +  
                " }";

    }
    


}