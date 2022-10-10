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
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.ClienteRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;

import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;

@Component
public class Valida_Envia_DERController implements Initializable {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DigicModoPagoRepository digicModoPagoRepository;

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

    /*
     * 
     * Nueva implementación
     * 
     * 
     */

    @FXML
    private ComboBox<DigicModoPago> p4_cb_clave_banco;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_clave_control;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_codigoBic;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_codigo_aba;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_cuenta_bancaria;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_descripcion_banco;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_email;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_identificadorBillete;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_modoTransporte;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_pais_banco;

    @FXML
    private ComboBox<DigicModoPago> p4_cb_valorMedioPago;

    @FXML
    private Label p4_lb_clave_banco;

    @FXML
    private Label p4_lb_clave_control;

    @FXML
    private Label p4_lb_codigoBic;

    @FXML
    private Label p4_lb_codigo_aba;

    @FXML
    private Label p4_lb_codigo_cuenta_internacional;

    @FXML
    private Label p4_lb_codigo_cuenta_nacional;

    @FXML
    private Label p4_lb_cuenta_bancaria;

    @FXML
    private Label p4_lb_datos_transporte;

    @FXML
    private Label p4_lb_datos_viajeros;

    @FXML
    private Label p4_lb_descripcion_banco;

    @FXML
    private Label p4_lb_fechaLimiteSalida;

    @FXML
    private Label p4_lb_identificadorBillete;

    @FXML
    private Label p4_lb_medios_de_pago;

    @FXML
    private Label p4_lb_modoTransporte;

    @FXML
    private Label p4_lb_pais_banco;

    @FXML
    private Label p4_lb_valorMedioPago;

    @FXML
    private TextField p4_tf_clave_banco;

    @FXML
    private TextField p4_tf_clave_control;

    @FXML
    private TextField p4_tf_codigoBic;

    @FXML
    private TextField p4_tf_codigo_aba;

    @FXML
    private TextField p4_tf_cuenta_bancaria;

    @FXML
    private TextField p4_tf_descripcion_banco;

    @FXML
    private TextField p4_tf_email;

    @FXML
    private TextField p4_tf_identificadorBillete;

    @FXML
    private TextField p4_tf_modoTransporte;

    @FXML
    private TextField p4_tf_pais_banco;

    @FXML
    private TextField p4_tf_valorMedioPago;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //lblTitulo.setText(titulo);
    
        txtTelefono.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);

        p4_tf_codigo_aba.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_tf_email.getProperties().put(VK_TYPE, VK_TYPE_EMAIL);
        /*
         * 
         * p4_tf_clave_banco
         * 
         */
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
