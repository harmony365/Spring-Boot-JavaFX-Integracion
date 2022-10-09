package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name ="digic")
public class Digic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "justificante")
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
    private String fecha_upload;
    @Column(name = "fecha_creacion")
    private String fecha_creacion;

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
            this.fecha_upload        = fecha_upload;
            this.fecha_creacion      = fecha_creacion;

    }


    public String getjustificante() {
        return justificante;
    }
    public String getnombreViajero() {
        return nombreViajero;
    }
    public String getapellidosViajero() {
        return apellidosViajero;
    }
    public String gettipoDocumento() {
        return tipoDocumento;
    }
    public String getvalorDocumento() {
        return valorDocumento;
    }
    public String getpaisExpedicion() {
        return paisExpedicion;
    }
    public String getpaisResidencia() {
        return paisResidencia;
    }
    public String getemail() {
        return email;
    }
    public String getnifEstablecimiento() {
        return nifEstablecimiento;
    }
    public String getrazonSocial() {
        return razonSocial;
    }
    public String getnumeroFactura() {
        return numeroFactura;
    }
    public String getfechaFactura() {
        return fechaFactura;
    }
    public String gettotalDigic() {
        return totalDigic;
    }
    public String getfechaLimiteSalida() {
        return fechaLimiteSalida;
    }
    public String getcuentaInternacional() {
        return cuentaInternacional;
    }
    public String getmodoPago() {
        return modoPago;
    }
    public String getcodigoBic() {
        return codigoBic;
    }
    public String getvalorMedioPago() {
        return valorMedioPago;
    }
    public String getclaveControl() {
        return claveControl;
    }
    public String getcuentaSinIBAN() {
        return cuentaSinIBAN;
    }
    public String getnumeroABA() {
        return numeroABA;
    }
    public String getclaveBanco() {
        return claveBanco;
    }
    public String getdescInstFinanciera() {
        return descInstFinanciera;
    }
    public String getpaisBanco() {
        return paisBanco;
    }

    public String getmodoTransporte() {
        return modoTransporte;
    }

    public String getidentificadorBillete() {
        return identificadorBillete;
    }

    public Integer getestatus_upload() {
        return estatus_upload;
    }
    public String getfecha_upload() {
        return fecha_upload;
    }
    public String getfecha_creacion() {
        return fecha_creacion;
    }

    /*
     *   Funcion SET de campos
     */

    public void setjustificante(String justificante) {
        this.justificante = justificante;
    }
    public void setnombreViajero(String nombreViajero) {
        this.nombreViajero = nombreViajero;
    }
    public void setapellidosViajero(String apellidosViajero) {
        this.apellidosViajero = apellidosViajero;
    }
    public void settipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public void setvalorDocumento(String valorDocumento) {
        this.valorDocumento = valorDocumento;
    }
    public void setpaisExpedicion(String paisExpedicion) {
        this.paisExpedicion = paisExpedicion;
    }
    public void setpaisResidencia(String paisResidencia) {
        this.paisResidencia = paisResidencia;
    }
    public void setemail(String email) {
        this.email = email;
    }
    public void setnifEstablecimiento(String nifEstablecimiento) {
        this.nifEstablecimiento = nifEstablecimiento;
    }
    public void setrazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public void setnumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public void setfechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
    public void settotalDigic(String totalDigic) {
        this.totalDigic = totalDigic;
    }
    public void setfechaLimiteSalida(String fechaLimiteSalida) {
        this.fechaLimiteSalida = fechaLimiteSalida;
    }
    public void setcuentaInternacional(String cuentaInternacional) {
        this.cuentaInternacional = cuentaInternacional;
    }
    public void setmodoPago(String modoPago) {
        this.modoPago = modoPago;
    }
    public void setcodigoBic(String codigoBic) {
        this.codigoBic = codigoBic;
    }
    public void setvalorMedioPago(String valorMedioPago) {
        this.valorMedioPago = valorMedioPago;
    }
    public void setclaveControl(String claveControl) {
        this.claveControl = claveControl;
    }
    public void setcuentaSinIBAN(String cuentaSinIBAN) {
        this.cuentaSinIBAN = cuentaSinIBAN;
    }
    public void setnumeroABA(String numeroABA) {
        this.numeroABA = numeroABA;
    }
    public void setclaveBanco(String claveBanco) {
        this.claveBanco = claveBanco;
    }
    public void setdescInstFinanciera(String descInstFinanciera) {
        this.descInstFinanciera = descInstFinanciera;
    }
    public void setpaisBanco(String paisBanco) {
        this.paisBanco = paisBanco;
    }
    public void setmodoTransporte(String modoTransporte) {
        this.modoTransporte = modoTransporte;
    }
    public void setidentificadorBillete(String identificadorBillete) {
        this.identificadorBillete = identificadorBillete;
    }
    public void setestatus_upload(Integer estatus_upload) {
        this.estatus_upload = estatus_upload;
    }
    public void setfecha_upload(String localDateTime) {
        this.fecha_upload = localDateTime;
    }
    public void setfecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }


    @Override
    public String toString() {

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
                    "\"cuentaSinIBAN\" : \""        + cuentaSinIBAN + "\" ,  " +
                    "\"numeroABA\" : \""             + numeroABA + "\" ,  " +
                    "\"claveBanco\" : \""            + claveBanco + "\" ,  " +
                    "\"descInstFinanciera\" : \""    + descInstFinanciera + "\" ,  " +
                    "\"paisBanco\" : \""             + paisBanco + "\" ,  " +
                    "\"modoTransporte\" : \""        + modoTransporte + "\" ,  " +
                    "\"identificadorBillete\" : \""  + identificadorBillete + "\" ,  " +
                    "\"fecha_upload\" : \""          + fecha_upload + "\" ,  " +
                    "\"fecha_creacion\" : \""        + fecha_creacion + "\" ,  " +
                    "\"estatus_upload\" : "          + estatus_upload +  
                " }";

    }


}