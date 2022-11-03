package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Cliente;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DigicModoPago;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.ClienteRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services.DatabaseDerUtil;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.grecasa.ext.mw.externo.KioskoServiceClient;
import org.grecasa.ext.mw.externo.KioskoServiceClientUtils;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDer;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.comtel2000.keyboard.control.VkProperties.*;


@Component
public class Valida_Envia_DERController implements Initializable {

    private final static Logger LOGGER = LogManager.getLogger(Valida_Envia_DERController.class.getName());
    private ResourceBundle bundle;

    private ZonedDateTime now = ZonedDateTime.now();
    private ZonedDateTime fechaHoraLimite = now.plusHours(3).plusMinutes(30);

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
    private Rectangle p4_rec_mensaje;

    @FXML
    private ComboBox<Digic> p4_cb_clave_banco, p4_cb_clave_control, p4_cb_codigoBic, p4_cb_codigo_aba,
            p4_cb_cuenta_bancaria, p4_cb_descripcion_banco, p4_cb_email, p4_cb_identificadorBillete,
            p4_cb_modoTransporte, p4_cb_pais_banco, p4_cb_valorMedioPago, p4_cb_fechaLimiteSalida,
            p4_cb_cuetaiban, p4_cb_hora;

    @FXML
    private ComboBox<String> p4_cb_modoTransporteObj;

    @FXML
    private Label p2_lb_informacion, p2_lb_clave_banco, p2_lb_clave_control, p2_lb_codigoBic,
            p2_lb_codigo_cuenta_internacional, p2_lb_codigo_cuenta_nacional, p2_lb_cuenta_bancaria,
            p2_lb_datos_transporte, p2_lb_datos_viajeros, p2_lb_descripcion_banco, p2_lb_fechaLimiteSalida,
            p2_lb_identificadorBillete, p2_lb_medios_de_pago, p2_lb_modoTransporte, p2_lb_pais_banco,
            p2_lb_valorMedioPago, p2_lb_fecha_salida,
            p4_lb_mensaje, p4_ld_wsdl_raspuesta, p4_ld_wsdl_TimeStamp;
    @FXML
    private DatePicker p4_dtp_fechaLimiteSalida;

    @FXML
    private TextField p4_tf_clave_banco, p4_tf_clave_control, p4_tf_codigoBic, p4_tf_codigo_aba,
            p4_tf_cuenta_bancaria, p4_tf_descripcion_banco, p4_tf_email, p4_tf_identificadorBillete,
            p4_tf_modoTransporte, p4_tf_pais_banco, p4_tf_valorMedioPago, p4_tf_fechaLimiteSalida,
            p4_tf_fechaLimiteSalidaHora, p4_tf_fechaLimiteSalidaMinuto;


    @FXML
    private ToggleButton p4_tgb_cuetaibanno, p4_tgb_cuetaibansi;

    @FXML
    private Button p2_btn_aceptar, p2_btn_salir;

    @FXML
    private Pane p4_pane_numeroaba, p4_pane_cuentaiban, p4_pane_cuentainternacional;

    public String valorDocumento;

    // Strings which hold css elements to easily re-use in the SpringJavaFxIntegrationApplicationlication
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String successStyle = String.format("-fx-border-color: GREEN; -fx-border-width: 2; -fx-border-radius: 5;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");

    public Boolean procesoWsdl = false;
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public LocalDate date = LocalDate.now();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        procesoWsdl = false;
        bundle = resources;
        p4_rec_mensaje.requestFocus();

        //lblTitulo.setText(titulo);
        valorDocumento = App.parametrosModel.getNumeroPasaporte();

        txtTelefono.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);

        p4_tf_codigo_aba.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_tf_codigoBic.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_dtp_fechaLimiteSalida.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_tf_fechaLimiteSalidaHora.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_tf_fechaLimiteSalidaMinuto.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p4_tf_email.getProperties().put(VK_TYPE, VK_TYPE_EMAIL);

        p4_ld_wsdl_raspuesta.setVisible(false);
        p4_ld_wsdl_TimeStamp.setVisible(false);
        WsdlResponse.setVisible(false);
        WsdlTimeStamp.setVisible(false);
        p4_pane_numeroaba.setVisible(false);
        p4_cb_fechaLimiteSalida.setVisible(false);

        p4_cb_hora.setVisible(false);

        p4_cb_cuetaiban.setVisible(false);
        p4_cb_modoTransporte.setVisible(false);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        p4_dtp_fechaLimiteSalida.setValue(LocalDate.parse(dtf.format(now)));

        //Cambiar el formato del calendario con la plantilla dayCellFactory
        p4_dtp_fechaLimiteSalida.setDayCellFactory(dayCellFactory);

        iniFormDigicModoPago();
        refesh();

    }

    public boolean isValidEmail(String email) {
        boolean validar = false;

        // Patrón para validar el email
        //Pattern pattern = Pattern
        //        .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        //                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            validar = true;
        }

        return validar;
    }

    public boolean isValidLetter(String s) {
        String regex = "[A-Za-z\\s]+";
        return s.matches(regex);//returns true if input and regex matches otherwise false;
    }

    static int validaFechaHora(String start_date, String end_date, String retorno) {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            // Calucalte time difference
            // in milliseconds
            long difference_In_Time = d1.getTime() - d2.getTime();

            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds = (difference_In_Time / 1000) % 60;

            long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

            long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

            System.out.print("Difference " + "between two dates is: ");

            System.out.println(difference_In_Years + " years, "
                    + difference_In_Days + " days, "
                    + difference_In_Hours + " hours, "
                    + difference_In_Minutes + " minutes, "
                    + difference_In_Seconds + " seconds");

            switch (retorno) {
                case "Y":
                    return (int) difference_In_Years;
                case "D":
                    return (int) difference_In_Days;
                case "H":
                    return (int) difference_In_Hours;
                case "M":
                    return (int) difference_In_Minutes;
                case "S":
                    return (int) difference_In_Seconds;
                default:
                    return 0;
            }

        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }


    Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
        @Override
        public void updateItem(LocalDate item, boolean empty) {

            super.updateItem(item, empty);

            this.setDisable(false);
            this.setBackground(null);
            this.setTextFill(Color.BLACK);

            // deshabilitar las fechas futuras
            if (item.isAfter(LocalDate.now())) {
                this.setDisable(true);
            }

            // deshabilitar las fechas futuras
            if (item.isBefore(LocalDate.now())) {
                this.setDisable(true);
            }
            // marcar los dias de quincena
            int day = item.getDayOfMonth();
            if (day == 15 || day == 30) {

                Paint color = Color.RED;
                BackgroundFill fill = new BackgroundFill(color, null, null);

                this.setBackground(new Background(fill));
                this.setTextFill(Color.WHITESMOKE);
            }

            // fines de semana en color verde
            DayOfWeek dayweek = item.getDayOfWeek();
            if (dayweek == DayOfWeek.SATURDAY || dayweek == DayOfWeek.SUNDAY) {
                this.setTextFill(Color.GREEN);
            }

            if (item.isAfter(LocalDate.now())) {
                this.setDisable(true);
            }

        }
    };

    public class ProcesarWSDL {
        public Boolean valor;
    }

    @FXML
    private void switchToAceptar() throws IOException {
        //Boolean procesarWSDL = false;

        ProcesarWSDL procesarWSDL = new ProcesarWSDL();
        procesarWSDL.valor = false;

        // Given start Date

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String start_date = fechaHoraLimite.format(formatter);

        //String start_date = "19-10-2022 03:10:20";
        // Given end Date
        String end_date = p4_dtp_fechaLimiteSalida.getValue() + " " + p4_tf_fechaLimiteSalidaHora.getText() + ":" + p4_tf_fechaLimiteSalidaMinuto.getText() + ":00";

        //TODO: validar que está verificando que la fecha y hora de salida no superalas 3 horas ni es menor a o horas-

        if (p4_tf_fechaLimiteSalidaHora.getLength() > 0 && p4_tf_fechaLimiteSalidaMinuto.getLength() > 0) {
            int resultado = validaFechaHora(start_date, end_date, "H");
            if (resultado > 3 || resultado < 0) {
                PlayEmpty(p4_tf_fechaLimiteSalidaHora);
                PlayEmpty(p4_tf_fechaLimiteSalidaMinuto);
                procesarWSDL.valor = true;
                System.out.println("Fecha de salida supera las 3 horas reglamentarias");
            } else {
                p4_tf_fechaLimiteSalidaHora.setStyle(successStyle);
                p4_tf_fechaLimiteSalidaMinuto.setStyle(successStyle);
            }
        } else {
            PlayEmpty(p4_tf_fechaLimiteSalidaHora);
            PlayEmpty(p4_tf_fechaLimiteSalidaMinuto);
            procesarWSDL.valor = true;
            System.out.println("Fecha de salida no contiene horas reglamentarias");
        }

        VerificaEmpty(p4_tf_email, procesarWSDL);
        VerificaEmpty(p4_tf_identificadorBillete, procesarWSDL);
        VerificaEmpty(p4_tf_modoTransporte, procesarWSDL);

        VerificaEmail(p4_tf_email, procesarWSDL);

        if (p4_tgb_cuetaibanno.isSelected()) {

            VerificaEmpty(p4_tf_clave_banco, procesarWSDL);
            VerificaEmpty(p4_tf_clave_control, procesarWSDL);
            VerificaEmpty(p4_tf_codigoBic, procesarWSDL);
            VerificaEmpty(p4_tf_pais_banco, procesarWSDL);
            VerificaEmpty(p4_tf_descripcion_banco, procesarWSDL);
            VerificaEmpty(p4_tf_cuenta_bancaria, procesarWSDL);

            VerificaLetra(p4_tf_pais_banco, procesarWSDL);

            VerificaEmpty(p4_tf_codigo_aba, procesarWSDL);

            VerificaLargo(p4_tf_codigo_aba, 9, procesarWSDL);

            try {
                // IbanUtil.validate(p4_tf_cuenta_bancaria.getText());

            } catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException e) {
                // invalid
                PlayEmpty(p4_tf_cuenta_bancaria);
                procesarWSDL.valor = true;
            }

        }

        if (p4_tgb_cuetaibansi.isSelected()) {
            VerificaEmpty(p4_tf_valorMedioPago, procesarWSDL);
            //if (p4_tf_valorMedioPago.getText().isEmpty()){ PlayEmpty(p4_tf_valorMedioPago);procesarWSDL.valor = true;}else{p4_tf_valorMedioPago.setStyle(successStyle);};

            // How to validate Iban
            try {
                IbanUtil.validate(p4_tf_valorMedioPago.getText());
                //IbanUtil.validate("DE89 3704 0044 0532 0130 00", IbanFormat.Default);
                // valid
            } catch (IbanFormatException | InvalidCheckDigitException | UnsupportedCountryException e) {
                // invalid
                PlayEmpty(p4_tf_valorMedioPago);
                procesarWSDL.valor = true;
            }

        }

        if (!procesarWSDL.valor) onWsdl();
    }

    private void VerificaEmpty(TextField campo, ProcesarWSDL procesarWSDL) {
        if (campo.getText().isEmpty()) {
            PlayEmpty(campo);
            procesarWSDL.valor = true;
        } else {
            campo.setStyle(successStyle);
        }
        ;
    }

    private void VerificaEmail(TextField campo, ProcesarWSDL procesarWSDL) {
        if (!isValidEmail(campo.getText())) {
            PlayEmpty(campo);
            procesarWSDL.valor = true;
        } else {
            campo.setStyle(successStyle);
        }
        ;
    }

    private void VerificaLetra(TextField campo, ProcesarWSDL procesarWSDL) {
        if (!isValidLetter(campo.getText())) {
            PlayEmpty(campo);
            procesarWSDL.valor = true;
        } else {
            campo.setStyle(successStyle);
        }
        ;
    }

    private void VerificaLargo(TextField campo, int largo, ProcesarWSDL procesarWSDL) {
        if (campo.getText().length() < largo) {
            PlayEmpty(campo);
            procesarWSDL.valor = true;
        } else {
            campo.setStyle(successStyle);
        }
        ;
    }

    private void PlayEmpty(TextField tf) {
        //System.out.println("style: " + tf.getStyle());
        tf.setStyle(errorStyle);
        new animatefx.animation.Shake(tf).play();
        new animatefx.animation.Wobble(tf).play();
        tf.requestFocus();
    }

    @FXML
    private void switchToAnterior() throws IOException {
        Locale locale = Locale.getDefault();
        if (procesoWsdl) {
            App.setRoot("/views/primary", locale);
        } else {
            App.setRoot("/views/Modelo_403_v2", locale);
        }
    }

    @FXML
    public void onSave() {
        clienteRepository.save(getFromUI());
        refesh();
    }


    @Autowired
    DatabaseDerUtil databaseDerUtil;

    @FXML
    public void onWsdl() {

        procesoWsdl = true;
        try {

            digicModoPagoRepository.save(getDERFromUI());

            ValidarRemesaDerResponse validarRemesaDerResponse;

            //ValidarRemesaDer validarRemesaDer = databaseDerUtil.getDERtoSend(valorDocumento, 3, App.parametrosModel.getKIOSKOID());
            LOGGER.log(Level.INFO, "Armando la Estructura del DER para el envío.");
            ValidarRemesaDer validarRemesaDer = databaseDerUtil.getDERtoSend(App.UUIDProcess, 3, App.parametrosModel.getKIOSKOID());

            LOGGER.log(Level.INFO, "Llamando al servicio WSDL de ValidarRemesa().");
            validarRemesaDerResponse = KioskoServiceClient.getInstance().validarRemesa(validarRemesaDer);
            KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);
            WsdlTimeStamp.setText(validarRemesaDerResponse.getFechaEstado().toString());
            WsdlResponse.setText(validarRemesaDerResponse.getEstado());
            LOGGER.log(Level.INFO, "Resultado: Estado( " + validarRemesaDerResponse.getEstado() + " ) --> " + validarRemesaDerResponse.getFechaEstado().toString());


        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Ocurrio un Error de RED, time out de conexión.");
            e.printStackTrace();
            WsdlResponse.setText("RED");
        }

        //TODO: validar bien el cambio de estatus. sólo falta ver bien cual sería la regla para los estatus ER.

        if (WsdlResponse.getText().equals("RED")) {
            p4_rec_mensaje.setFill(Color.rgb(252, 227, 227, 1));
            p4_lb_mensaje.setText(bundle.getString("p4_lb_mensaje_wsdl_RED"));
            databaseDerUtil.DigicUpdatStatus(App.UUIDProcess, 3, 2);
        }
        if (WsdlResponse.getText().equals("ER")) {
            p4_rec_mensaje.setFill(Color.rgb(252, 227, 227, 1));
            p4_lb_mensaje.setText(bundle.getString("p4_lb_mensaje_wsdl_ER"));
            databaseDerUtil.DigicUpdatStatus(App.UUIDProcess, 3, 3);
        }
        if (WsdlResponse.getText().equals("PR02")) {
            p4_rec_mensaje.setFill(Color.rgb(252, 227, 227, 1));
            p4_lb_mensaje.setText(bundle.getString("p4_lb_mensaje_wsdl_ER"));
            databaseDerUtil.DigicUpdatStatus(App.UUIDProcess, 3, 2);
        }
        if (WsdlResponse.getText().equals("KO")) {
            p4_rec_mensaje.setFill(Color.rgb(227, 250, 228, 1));
            p4_lb_mensaje.setText(bundle.getString("p4_lb_mensaje_wsdl_KO"));
            databaseDerUtil.DigicUpdatStatus(App.UUIDProcess, 3, 2);
        }
        if (WsdlResponse.getText().equals("OK")) {
            p4_rec_mensaje.setFill(Color.rgb(227, 250, 228, 1));
            p4_lb_mensaje.setText(bundle.getString("p4_lb_mensaje_wsdl_OK"));
            databaseDerUtil.DigicUpdatStatus(App.UUIDProcess, 3, 1);
        }
        if (WsdlResponse.getText().length() > 5) {
            p4_rec_mensaje.setFill(Color.rgb(252, 227, 227, 1));
            p4_lb_mensaje.setText(bundle.getString("p4_lb_mensaje_wsdl_" + WsdlResponse.getText()));
        }

        p4_ld_wsdl_raspuesta.setVisible(true);
        p4_ld_wsdl_TimeStamp.setVisible(true);
        WsdlResponse.setVisible(true);
        WsdlTimeStamp.setVisible(true);

        p2_btn_aceptar.setVisible(false);
        p2_btn_salir.requestFocus();

    }

    public void setFromUI(Cliente cliente) {
        txtApellido.setText(cliente.getApellido());
        txtNombre.setText(cliente.getNombre());
        txtTelefono.setText(cliente.getTelefono());
    }

    public Cliente getFromUI() {
        Cliente cliente = new Cliente();
        cliente.setApellido(txtApellido.getText());
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());
        return cliente;
    }

    public DigicModoPago getDERFromUI() {
        DigicModoPago digicModoPago = new DigicModoPago();

        digicModoPago.setClaveBanco(p4_tf_clave_banco.getText());
        digicModoPago.setClaveControl(p4_tf_clave_control.getText());
        digicModoPago.setCodigoBic(p4_tf_codigoBic.getText());
        digicModoPago.setCuentaInternacional("SI");
        digicModoPago.setCuentaSinIBAN(p4_cb_cuetaiban.getValue() + "");
        digicModoPago.setDescInstFinanciera(p4_tf_descripcion_banco.getText());
        digicModoPago.setEmail(p4_tf_email.getText());
        digicModoPago.setIdentificadorBillete(p4_tf_identificadorBillete.getText());
        //TODO: validar modo pago en el mantenimiento de validación no sale esa opción.
        digicModoPago.setModoPago("CUENTA");
        digicModoPago.setModoTransporte(p4_tf_modoTransporte.getText());
        digicModoPago.setNumeroABA(p4_tf_codigo_aba.getText());
        digicModoPago.setPaisBanco(p4_tf_pais_banco.getText());
        digicModoPago.setValorDocumento(valorDocumento);
        digicModoPago.setValorMedioPago(p4_tf_valorMedioPago.getText());
        digicModoPago.setUuidProceso(App.UUIDProcess);

        digicModoPago.setEstatusUpload(3);

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
        p4_cb_valorMedioPago.setItems(FXCollections.observableArrayList(digicRepository.findAllValorMedioPagoNO(valorDocumento)));
        p4_cb_fechaLimiteSalida.setItems(FXCollections.observableArrayList(digicRepository.findAllFechaLimiteSalida(valorDocumento)));
        p4_cb_pais_banco.setItems(FXCollections.observableArrayList(digicRepository.findAllPaisBanco(valorDocumento)));
        p4_cb_identificadorBillete.setItems(FXCollections.observableArrayList(digicRepository.findAllIdentificadorBillete(valorDocumento)));
        p4_cb_descripcion_banco.setItems(FXCollections.observableArrayList(digicRepository.findAllDescInstFinanciera(valorDocumento)));
        p4_cb_cuenta_bancaria.setItems(FXCollections.observableArrayList(digicRepository.findAllValorMedioPagoSI(valorDocumento)));
        p4_cb_codigo_aba.setItems(FXCollections.observableArrayList(digicRepository.findAllNumeroAba(valorDocumento)));
        p4_cb_cuetaiban.setItems(FXCollections.observableArrayList(digicRepository.findAllCuentaSinIban(valorDocumento)));

        p4_cb_modoTransporteObj.getItems().addAll("AVION", "BARCO");

        p4_cb_clave_banco.getSelectionModel().selectFirst();
        p4_cb_clave_control.getSelectionModel().selectFirst();
        p4_cb_codigoBic.getSelectionModel().selectFirst();
        p4_cb_codigo_aba.getSelectionModel().selectFirst();
        p4_cb_cuenta_bancaria.getSelectionModel().selectFirst();
        p4_cb_descripcion_banco.getSelectionModel().selectFirst();
        p4_cb_email.getSelectionModel().selectFirst();
        p4_cb_identificadorBillete.getSelectionModel().selectFirst();
        p4_cb_modoTransporte.getSelectionModel().selectFirst();
        p4_cb_pais_banco.getSelectionModel().selectFirst();
        p4_cb_valorMedioPago.getSelectionModel().selectFirst();
        p4_cb_fechaLimiteSalida.getSelectionModel().selectFirst();
        p4_cb_cuetaiban.getSelectionModel().selectFirst();
        p4_cb_modoTransporteObj.getSelectionModel().selectFirst();

    }

    private void iniFormDigicModoPago() {

        /*
            TODO:
             para el DER 4032400003615 6958715644. el monto es un Integer 200 y no un double 200.00, y el pais del banco no es de 2 caracteres sino 3 MAU
             para el DER 4032400003606 6958715644. y el pais del banco no es de 2 caracteres sino 3 MAU
             Para el DER 4032400003563 42476822P arroja el error PR02
             Para el DER 4032400004220 42476822P el modo de transporte de salida es BARCO en el documento impreso y no aparece en la información del QR
             Para el DER 4032400003590 41780963Z el monto es un Integer 0 y no un double 0.00  arroja el error  ER
             Para el DER 4032400004674 41780963Z
             Para el DER 4032400003581 41780963Z


             todo:
              Revisar cuando se selecciona los der en el panel coloca CUENTA en el campo de modo de transporte
              cuando se está en la ventana de envío de los DER y nos retrocedemos, la variable del valor documento se pierde.

             TODO:
              CREAR la notificación del procesando en los procesos que quedan en espera.

         */
        p4_tf_pais_banco.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) ((StringProperty) observable).setValue(oldValue);

            if (!newValue.matches("\\sa-zA-Z*")) {
                p4_tf_pais_banco.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
            if (newValue.equals("US")) {
                p4_pane_numeroaba.setVisible(true);
            } else {
                p4_pane_numeroaba.setVisible(false);
            }

            p4_tf_pais_banco.setText(newValue.toUpperCase());
        });

        TextPropertyAddListener(p4_tf_codigoBic, 0);
        TextPropertyAddListener(p4_tf_codigo_aba, 9);
        TextPropertyAddListener(p4_tf_fechaLimiteSalidaMinuto, 2);
        TextPropertyAddListener(p4_tf_fechaLimiteSalidaHora, 2);

        ValuePropertyAddListener(p4_cb_email, p4_tf_email);
        ValuePropertyAddListener(p4_cb_clave_banco, p4_tf_clave_banco);
        ValuePropertyAddListener(p4_cb_clave_control, p4_tf_clave_control);
        ValuePropertyAddListener(p4_cb_descripcion_banco, p4_tf_descripcion_banco);
        ValuePropertyAddListener(p4_cb_identificadorBillete, p4_tf_identificadorBillete);
        ValuePropertyAddListener(p4_cb_modoTransporte, p4_tf_modoTransporte);
        ValuePropertyAddListener(p4_cb_codigo_aba, p4_tf_codigo_aba);
        ValuePropertyAddListener(p4_cb_codigoBic, p4_tf_codigoBic);
        ValuePropertyAddListener(p4_cb_modoTransporteObj, p4_tf_modoTransporte);
        p4_cb_modoTransporteObj.getSelectionModel().selectFirst();

        ValuePropertyAddListener(p4_cb_cuenta_bancaria, p4_tf_cuenta_bancaria, p4_tf_valorMedioPago);
        ValuePropertyAddListener(p4_cb_valorMedioPago, p4_tf_valorMedioPago, p4_tf_cuenta_bancaria);

        // TODO --> Desarrollar funcionabilidad de verificación de la fecha de salida
        //p4_cb_fechaLimiteSalida.setOnAction(e -> p4_dtp_fechaLimiteSalida.setValue(LocalDate.parse(p4_cb_fechaLimiteSalida.getValue()+"")));

        ValuePropertyAddListenerVisible(p4_tgb_cuetaibanno, p4_tgb_cuetaibansi, p4_pane_cuentainternacional, p4_pane_cuentaiban);
        ValuePropertyAddListenerVisible(p4_tgb_cuetaibansi, p4_tgb_cuetaibanno, p4_pane_cuentaiban, p4_pane_cuentainternacional);
        p4_tgb_cuetaibansi.setSelected(true);

        p4_cb_pais_banco.valueProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) {
                        String sn = (String) t1;
                        p4_tf_pais_banco.setText(sn);
                        if (sn.equals("US")) {
                            p4_pane_numeroaba.setVisible(true);
                        } else {
                            p4_pane_numeroaba.setVisible(false);
                        }
                    }
                });

        p4_cb_pais_banco.getSelectionModel().selectFirst();

        p4_tf_pais_banco.setOnInputMethodTextChanged((e) -> {
            if (p4_tf_pais_banco.getText().equals("US")) {
                p4_pane_numeroaba.setVisible(true);
            } else {
                p4_pane_numeroaba.setVisible(false);
            }
        });

    }

    private void ValuePropertyAddListener(ComboBox cbx, TextField txf) {
        cbx.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                txf.setText((String) t1);
            }
        });
    }

    private void ValuePropertyAddListener(ComboBox cbx, TextField txf, TextField txf2) {
        cbx.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                txf.setText((String) t1);
                //txf2.setText((String) t1);
            }
        });
    }

    private void TextPropertyAddListener(TextField txf, int largo) {
        txf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (largo > 0) if (newValue.length() > largo) ((StringProperty) observable).setValue(oldValue);

            if (!newValue.matches("\\d*")) {
                txf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void ValuePropertyAddListenerVisible(ToggleButton tgbtrue, ToggleButton tgbfalse, Pane pane1, Pane pane2) {

        tgbtrue.selectedProperty().addListener(((observable, oldValue, newValue) -> {

            pane1.setVisible(newValue);
            pane2.setVisible(oldValue);
            tgbfalse.setSelected(oldValue);

        }));


    }


}
