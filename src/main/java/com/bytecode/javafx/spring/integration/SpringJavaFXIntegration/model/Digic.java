package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

}