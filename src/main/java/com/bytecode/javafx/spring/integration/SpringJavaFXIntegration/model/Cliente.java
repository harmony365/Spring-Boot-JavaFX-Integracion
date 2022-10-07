package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name ="client")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String nombre;

    @Column(name = "surname")
    private String apellido;
    
    @Column(name = "phone")
    private String telefono;

    public Cliente(){}

    public Integer getNombgetId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " " + apellido;
    }
}