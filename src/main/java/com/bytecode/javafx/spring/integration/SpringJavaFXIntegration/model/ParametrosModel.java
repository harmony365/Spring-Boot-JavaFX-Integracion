package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model;

public class ParametrosModel {
    
    private String  WsdlUrl;
    private String  WsdlUser;
    private String  WsdlPassword;
    private String  QRPassDecoder;
    private String  QRCODEDEMO;
    private String  KIOSKOID;
    private String  NumeroPasaporte;
    private String  NumeroDniNifNieTie;
    private String  TipoDoumento;
    private String  NombreViajero;
    private String  PasaporteDemo;
    private String  DniNifNieTieDemo;
    private Boolean AppDemo;
    private String  FechaHoy;



    public void ParametrosModel() {}

    public void ParametrosModel(
        String WsdlUrl,
        String WsdlUser,
        String WsdlPassword,
        String QRPassDecoder,
        String QRCODEDEMO,
        String KIOSKOID,
        String PasaporteDemo,
        String DniNifNieTieDemo,
        Boolean AppDemo,
        String FechaHoy
        ){

            this.WsdlUrl          = WsdlUrl;
            this.WsdlUser         = WsdlUser;
            this.WsdlPassword     = WsdlPassword;
            this.QRPassDecoder    = QRPassDecoder;
            this.QRCODEDEMO       = QRCODEDEMO;
            this.KIOSKOID         = KIOSKOID;
            this.PasaporteDemo    = PasaporteDemo;
            this.DniNifNieTieDemo = DniNifNieTieDemo;
            this.AppDemo          = AppDemo;
            this.FechaHoy         = FechaHoy;

    }

    public String getFechaHoy() {
        return FechaHoy;
    }

    public void setFechaHoy(String FechaHoy) { this.FechaHoy = FechaHoy; }


    public Boolean getAppDemo() {
        return AppDemo;
    }

    public void setAppDemo(Boolean AppDemo) {
        this.AppDemo = AppDemo;
    }
    
    public String getDniNifNieTieDemo() {
        return DniNifNieTieDemo;
    }

    public void setDniNifNieTieDemo(String DniNifNieTieDemo) {
        this.DniNifNieTieDemo = DniNifNieTieDemo;
    }
    
    public String getPasaporteDemo() {
        return PasaporteDemo;
    }

    public void setPasaporteDemo(String PasaporteDemo) {
        this.PasaporteDemo = PasaporteDemo;
    }
    
    public String getQRCODEDEMO() {
        return QRCODEDEMO;
    }

    public void setQRCODEDEMO(String QRCODEDEMO) {
        this.QRCODEDEMO = QRCODEDEMO;
    }
    
    public String getQRPassDecoder() {
        return QRPassDecoder;
    }

    public void setQRPassDecoder(String QRPassDecoder) {
        this.QRPassDecoder = QRPassDecoder;
    }
    
    public String getNombreViajero() {
        return NombreViajero;
    }

    public void setNombreViajero(String NombreViajero) {
        this.NombreViajero = NombreViajero;
    }
    
    public String getTipoDoumento() {
        return TipoDoumento;
    }

    public void setTipoDoumento(String TipoDoumento) {
        this.TipoDoumento = TipoDoumento;
    }
    
    public String getNumeroPasaporte() {
        return NumeroPasaporte;
    }

    public void setNumeroPasaporte(String NumeroPasaporte) {
        this.NumeroPasaporte = NumeroPasaporte;
    }

    public String getNumeroDniNifNieTie() {
        return NumeroDniNifNieTie;
    }

    public void setNumeroDniNifNieTie(String NumeroDniNifNieTie) {
        this.NumeroDniNifNieTie = NumeroDniNifNieTie;
    }

    public String getWsdlUrl() {
        return WsdlUrl;
    }

    public void setWsdlUrl(String object) {
        this.WsdlUrl = object;
    }

    public String getWsdlUser() {
        return WsdlUser;
    }

    public void setWsdlUser(String WsdlUser) {
        this.WsdlUser = WsdlUser;
    }

    public String getWsdlPassword() {
        return WsdlPassword;
    }

   public void setWsdlPassword(String WsdlPassword) {
        this.WsdlPassword = WsdlPassword;
    }    
    
    public void setKIOSKOID(String KIOSKOID) {
        this.KIOSKOID = KIOSKOID;
    }

    public String getKIOSKOID() {
        return KIOSKOID;
    }

    @Override
    public String toString() {
        return "{ " +
                "\"WsdlUrl\" : \""          + WsdlUrl + "\" ,  " +
                "\"WsdlUser\" : \""         + WsdlUser + "\" ,  "  +
                "\"WsdlPassword\" : \""     + WsdlPassword + "\" ,  " +
                "\"QRPassDecoder\" : \""    + QRPassDecoder + "\" ,  " +        
                "\"QRCODEDEMO\" : \""       + QRCODEDEMO + "\" ,  " +        
                "\"AppDemo\" : \""          + AppDemo + "\" ,  " +        
                "\"FechaHoy\" : \""         + FechaHoy + "\" ,  " +
                "\"KIOSKOID\" : \""         + KIOSKOID + "\" ,  " +
                "\"NumeroPasaporte\" : \""  + NumeroPasaporte + "\" ,  " +
                "\"TipoDoumento\" : \""     + TipoDoumento + "\" ,  " +
                "\"NombreViajero\" : \""    + NombreViajero + "\",  " +
                "\"PasaporteDemo\" : \""    + PasaporteDemo + "\",  " +
                "\"DniNifNieTieDemo\" : \"" + DniNifNieTieDemo + "\"  " +
                " }";
    }
}
