package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


import org.comtel2000.keyboard.control.DefaultLayer;
import org.comtel2000.keyboard.control.KeyBoardPopup;
import org.comtel2000.keyboard.control.KeyBoardPopupBuilder;
import static org.comtel2000.keyboard.control.VkProperties.*;

import org.grecasa.ext.mw.externo.KioskoServiceClient;
import org.grecasa.ext.mw.externo.KioskoServiceClientUtils;
import org.grecasa.ext.mw.externo.kiosko_service.KioskoService;
import org.grecasa.ext.mw.externo.kiosko_service.KioskoService_Service;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarDerResponse;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDer;


import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DummyData;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Cliente;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.ClienteRepository;

import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;

@Component
public class Valida_Envia_DERController implements Initializable {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @FXML
    private Label lblTitulo;
    
    @FXML
    private Label WsdlResponse;
    
    @FXML
    private Label WsdlTimeStamp;

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtApellido;
    
    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<Cliente> comboClientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //lblTitulo.setText(titulo);
    
        txtTelefono.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
       // p2_tf_codigo_aba.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
      //  p2_tf_email.getProperties().put(VK_TYPE, VK_TYPE_EMAIL);
        refesh();    

    }

    
    @FXML 
    private void switchToAnterior() throws IOException  {

        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        App.setRoot("views/primary",locale);

    }   

    @FXML
    public void onSave(){

        clienteRepository.save(getFromUI());
        refesh();
    }

    @FXML
    public void onWsdl(){
        
        ValidarRemesaDerResponse validarRemesaDerResponse;
        
        validarRemesaDerResponse  = KioskoServiceClient.getInstance().validarRemesa(DummyData.getExampleKO());
        KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);

        //validarRemesaDerResponse = KioskoServiceClient.getInstance().validarRemesa(DummyData.getExample());
        //KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);

        WsdlTimeStamp.setText(validarRemesaDerResponse.getFechaEstado().toString());
        WsdlResponse.setText(validarRemesaDerResponse.getEstado());

    }

    public Cliente getFromUI(){
        Cliente cliente = new Cliente();
        cliente.setApellido(txtApellido.getText());
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());
        return cliente;
    }

    public void setFromUI(Cliente cliente) {
        txtApellido.setText(cliente.getApellido());
        txtNombre.setText(cliente.getNombre());
        txtTelefono.setText(cliente.getTelefono());
    }
    
    public void refesh() {
        comboClientes.setItems(FXCollections.observableArrayList(clienteRepository.findAll()));
    }

}
