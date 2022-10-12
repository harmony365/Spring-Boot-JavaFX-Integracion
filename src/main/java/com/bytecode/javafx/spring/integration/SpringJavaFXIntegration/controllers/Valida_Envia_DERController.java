package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.ClienteRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;

import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;

@Component
public class Valida_Envia_DERController implements Initializable {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DigicModoPagoRepository digicModoPagoRepository;

    @Autowired
    private DigicRepository digicRepository;

    @FXML
    private Label lblTitulo, WsdlResponse, WsdlTimeStamp;

    @FXML
    private TextField txtNombre, txtApellido, txtTelefono;

    @FXML
    private ComboBox<Cliente> comboClientes;

    /*
     * 
     * Nueva implementación
     * 
     * 
     */

    @FXML
    private ComboBox<Digic> p4_cb_clave_banco, p4_cb_clave_control, p4_cb_codigoBic, p4_cb_codigo_aba,
            p4_cb_cuenta_bancaria, p4_cb_descripcion_banco, p4_cb_email, p4_cb_identificadorBillete,
            p4_cb_modoTransporte, p4_cb_pais_banco, p4_cb_valorMedioPago;

    @FXML
    private Label p4_lb_informacion,p4_lb_clave_banco,p4_lb_clave_control, p4_lb_codigoBic,
                  p4_lb_codigo_cuenta_internacional, p4_lb_codigo_cuenta_nacional, p4_lb_cuenta_bancaria,
                  p4_lb_datos_transporte, p4_lb_datos_viajeros, p4_lb_descripcion_banco, p4_lb_fechaLimiteSalida,
                  p4_lb_identificadorBillete, p4_lb_medios_de_pago, p4_lb_modoTransporte, p4_lb_pais_banco, 
                  p4_lb_valorMedioPago, p4_lb_mensaje;
    
    @FXML
    private TextField p4_tf_clave_banco, p4_tf_clave_control,p4_tf_codigoBic,p4_tf_codigo_aba,
                      p4_tf_cuenta_bancaria,p4_tf_descripcion_banco,p4_tf_email,p4_tf_identificadorBillete,
                      p4_tf_modoTransporte,p4_tf_pais_banco,p4_tf_valorMedioPago;

    public String valorDocumento;

    // Strings which hold css elements to easily re-use in the SpringJavaFxIntegrationApplicationlication
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String successStyle   = String.format("-fx-border-color: GREEN; -fx-border-width: 2; -fx-border-radius: 5;");
    String errorMessage   = String.format("-fx-text-fill: RED;");
    String errorStyle     = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //lblTitulo.setText(titulo);
        valorDocumento = App.parametrosModel.getNumeroPasaporte();//"44303145Q";
        txtTelefono.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);

        p4_tf_codigo_aba.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_tf_codigoBic.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_tf_email.getProperties().put(VK_TYPE, VK_TYPE_EMAIL);

        /*
         * 
         * p4_tf_clave_banco
         * 
         */
        
        p4_cb_email.setOnAction(e -> p4_tf_email.setText(p4_cb_email.getValue()+""));
        p4_cb_clave_banco.setOnAction(e -> p4_tf_clave_banco.setText(p4_cb_clave_banco.getValue()+""));
        p4_cb_clave_control.setOnAction(e -> p4_tf_clave_control.setText(p4_cb_clave_control.getValue()+""));
        p4_cb_codigoBic.setOnAction(e -> p4_tf_codigoBic.setText(p4_cb_codigoBic.getValue()+""));
        p4_cb_codigo_aba.setOnAction(e -> p4_tf_codigo_aba.setText(p4_cb_codigo_aba.getValue()+""));
        p4_cb_cuenta_bancaria.setOnAction(e -> p4_tf_cuenta_bancaria.setText(p4_cb_cuenta_bancaria.getValue()+""));
        p4_cb_descripcion_banco.setOnAction(e -> p4_tf_descripcion_banco.setText(p4_cb_descripcion_banco.getValue()+""));
        p4_cb_identificadorBillete.setOnAction(e -> p4_tf_identificadorBillete.setText(p4_cb_identificadorBillete.getValue()+""));
        p4_cb_modoTransporte.setOnAction(e -> p4_tf_modoTransporte.setText(p4_cb_modoTransporte.getValue()+""));
        p4_cb_pais_banco.setOnAction(e -> p4_tf_pais_banco.setText(p4_cb_pais_banco.getValue()+""));
        p4_cb_valorMedioPago.setOnAction(e -> p4_tf_valorMedioPago.setText(p4_cb_valorMedioPago.getValue()+""));

        refesh();    

    }


    @FXML
    private void switchToAceptar() throws IOException {

        if (p4_tf_email.getText().isEmpty()){ PlayEmpty(p4_tf_email);}else{p4_tf_email.setStyle(successStyle);};
        if (p4_tf_clave_banco.getText().isEmpty()){ PlayEmpty(p4_tf_clave_banco);}else{p4_tf_clave_banco.setStyle(successStyle);};
        if (p4_tf_clave_control.getText().isEmpty()){ PlayEmpty(p4_tf_clave_control);}else{p4_tf_clave_control.setStyle(successStyle);};
        if (p4_tf_codigoBic.getText().isEmpty()){ PlayEmpty(p4_tf_codigoBic);}else{p4_tf_codigoBic.setStyle(successStyle);};
        if (p4_tf_codigo_aba.getText().isEmpty()){ PlayEmpty(p4_tf_codigo_aba);}else{p4_tf_codigo_aba.setStyle(successStyle);};
        if (p4_tf_cuenta_bancaria.getText().isEmpty()){ PlayEmpty(p4_tf_cuenta_bancaria);}else{p4_tf_cuenta_bancaria.setStyle(successStyle);};
        if (p4_tf_descripcion_banco.getText().isEmpty()){ PlayEmpty(p4_tf_descripcion_banco);}else{p4_tf_descripcion_banco.setStyle(successStyle);};
        if (p4_tf_identificadorBillete.getText().isEmpty()){ PlayEmpty(p4_tf_identificadorBillete);}else{p4_tf_identificadorBillete.setStyle(successStyle);};
        if (p4_tf_modoTransporte.getText().isEmpty()){ PlayEmpty(p4_tf_modoTransporte);}else{p4_tf_modoTransporte.setStyle(successStyle);};
        if (p4_tf_pais_banco.getText().isEmpty()){ PlayEmpty(p4_tf_pais_banco);}else{p4_tf_pais_banco.setStyle(successStyle);};
        if (p4_tf_valorMedioPago.getText().isEmpty()){ PlayEmpty(p4_tf_valorMedioPago);}else{p4_tf_valorMedioPago.setStyle(successStyle);};
        
    }

    private void PlayEmpty(TextField tf){
        
        System.out.println("style: " + tf.getStyle());
        tf.setStyle(errorStyle);
        
        new animatefx.animation.Shake(tf).play();
        new animatefx.animation.Wobble(tf).play();

        tf.requestFocus();

    }

    @FXML 
    private void switchToAnterior() throws IOException  {

        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        App.setRoot("/views/Modelo_403_v2",locale);

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
    
    public DigicModoPago getDERFromUI(){
        DigicModoPago digicModoPago = new DigicModoPago();

        digicModoPago.setClaveBanco(p4_tf_clave_banco.getText()); 
        digicModoPago.setClaveControl(p4_tf_clave_control.getText());
        digicModoPago.setCodigoBic(p4_tf_codigoBic.getText());
        digicModoPago.setNumeroABA(p4_tf_codigo_aba.getText());
        //digicModoPago.setCuenta(p4_tf_cuenta_bancaria.getText());
        digicModoPago.setDescInstFinanciera(p4_tf_descripcion_banco.getText());
        digicModoPago.setEmail(p4_tf_email.getText());
        digicModoPago.setIdentificadorBillete(p4_tf_identificadorBillete.getText());
        digicModoPago.setModoPago(p4_tf_modoTransporte.getText());
        digicModoPago.setPaisBanco(p4_tf_pais_banco.getText());
        digicModoPago.setValorMedioPago(p4_tf_valorMedioPago.getText());

        return digicModoPago;
    }

    public void refesh() {
        comboClientes.setItems(FXCollections.observableArrayList(clienteRepository.findAll()));

        p4_cb_email.setItems(FXCollections.observableArrayList(digicRepository.findAllEmail(valorDocumento)));
        p4_cb_modoTransporte.setItems(FXCollections.observableArrayList(digicRepository.findAllModoTransporte(valorDocumento)));
        p4_cb_descripcion_banco.setItems(FXCollections.observableArrayList(digicRepository.findAllDescInstFinanciera(valorDocumento)));
        p4_cb_clave_banco.setItems(FXCollections.observableArrayList(digicRepository.findAllClaveBanco(valorDocumento)));
        p4_cb_clave_control.setItems(FXCollections.observableArrayList(digicRepository.findAllClaveControl(valorDocumento)));
        p4_cb_codigoBic.setItems(FXCollections.observableArrayList(digicRepository.findAllCodigoBic(valorDocumento)));
        p4_cb_valorMedioPago.setItems(FXCollections.observableArrayList(digicRepository.findAllValorMedioPago(valorDocumento)));
        p4_cb_pais_banco.setItems(FXCollections.observableArrayList(digicRepository.findAllPaisBanco(valorDocumento)));
        p4_cb_identificadorBillete.setItems(FXCollections.observableArrayList(digicRepository.findAllIdentificadorBillete(valorDocumento)));
        p4_cb_descripcion_banco.setItems(FXCollections.observableArrayList(digicRepository.findAllDescInstFinanciera(valorDocumento)));
        p4_cb_cuenta_bancaria.setItems(FXCollections.observableArrayList(digicRepository.findAllValorMedioPago(valorDocumento)));
        //p4_cb_codigo_aba.setItems(FXCollections.observableArrayList(digicRepository.findAllCodigoBaba(valorDocumento)));
    }




}
