package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name ="digicmodopago")
@Data 
public class DigicModoPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "valorDocumento", unique = true, nullable = false)
    private String valorDocumento;
    @Column(name = "email")
    private String email;
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
    @Column(name = "estatusupload")
    private Integer estatusUpload;
    @Column(name = "fechaupload")
    private String fechaUpload;
    @Column(name = "fechacreacion")
    private String fechaCreacion;

    public DigicModoPago() {
    }

}