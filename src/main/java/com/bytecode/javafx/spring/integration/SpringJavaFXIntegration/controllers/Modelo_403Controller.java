package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.DummyData;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicModoPagoRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.utiles.JsonUtils;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.grecasa.ext.mw.externo.KioskoServiceClient;
import org.grecasa.ext.mw.externo.KioskoServiceClientUtils;
import org.grecasa.ext.mw.externo.kiosko_service.ValidarRemesaDerResponse;
import org.jasypt.util.text.BasicTextEncryptor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import static org.comtel2000.keyboard.control.VkProperties.*;


@Component
public class Modelo_403Controller implements Initializable {

    private final static Logger LOGGER = LogManager.getLogger(Modelo_403Controller.class.getName());/*
     /* Conexion Bases de datos
    */
        
    @Autowired
    private DigicRepository digicRepository;
    @Autowired
    private DigicModoPagoRepository digicModoPagoRepository;

    public String tableName = "digic_v2"; 
    public String DBLocal = "digic_v2"; 
    

    public Random rand = new Random();
    public String fechaHoy; 
    public LocalDate date = LocalDate.now();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    // Strings which hold css elements to easily re-use in the application
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String successStyle = String.format("-fx-border-color: GREEN; -fx-border-width: 2; -fx-border-radius: 5;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");


    /*
    *  Variables de la plantilla
    */

    @FXML
    private JFXDialog p2_Dialog_Procesando;


    @FXML private Button p2_btn_aceptar,  p2_btn_qr, p2_btn_salir, p2_btn_demo, p2_btn_wsdl, p2_btn_add_403;

    @FXML private Label p2_lb_justificante, p2_lb_datos_establecimiento,  p2_lb_nif,  p2_lb_razon_social,
        p2_lb_numero_factura, p2_lb_fecha_factura,  p2_lb_monto_factura, p2_lb_nombreViajero, p2_lb_apellidosViajero,
        p2_lb_tipoDocumento, p2_lb_valorDocumento, p2_lb_paisExpedicion, p2_lb_paisResidencia, p2_lb_fechaLimiteSalida,
        p2_lb_medioPago, p2_lb_cuentaInternacional, p2_lb_codigoBic, p2_lb_valorMedioPago, p2_lb_codigo_cuenta_internacional,
        p2_lb_codigo_cuenta_nacional, p2_lb_botonqr, p2_lb_clave_banco, p2_lb_clave_control, p2_lb_codigo_aba, p2_lb_cuenta_bancaria,
        p2_lb_descripcion_banco, p2_lb_modelo, p2_lb_pais_banco, p2_lb_plantilla, p2_lb_medioPago_txt, p2_lb_valorDocumentoEsperado;

    @FXML private TextField p2_tf_email, p2_tf_codigoBic, p2_tf_valorMedioPago, p2_tf_clave_banco, p2_tf_clave_control,
        p2_tf_codigo_aba, p2_tf_cuenta_bancaria, p2_tf_descripcion_banco, p2_tf_pais_banco, p2_tf_modoTransporte,
        p2_tf_identificadorBillete, p2_input_scanner, p2_tf_montoTotalDigic,p2_img_barcode;
    
    @FXML private Text p2_tx_info_item;
    
    @FXML private Rectangle p2_warning;

    @FXML private AnchorPane p2_an_warning,p2_an_info_item;;

    @FXML public ImageView p2_img_barcode1;

   // @FXML public TableView<Justificante> p2_tv_justificantes = new TableView<Justificante>();
     
    //@FXML public TableColumn<Justificante, String> p2_tc_listajustificantes = new TableColumn<Justificante, String>("JUSTIFICANTE");
    
    //@FXML public TableColumn<Justificante, String> p2_tc_listajustificantesMonto  = new TableColumn<Justificante, String>("MONTO");


    @FXML public TableView<Digic> p2_tv_justificantesdigic ; //= new TableView<Digic>();

    private String ScannerReader ="";

    private ResourceBundle bundle;
/*
    public class Justificante {
        private String numero;
        private String monto;
        
        public Justificante() {}

        public Justificante(String numero,String monto) {
            this.numero = numero;
            this.monto  = monto;
        }

        public String getfNumero() {
            return this.numero;
        }
        public void setfNumero(String numero) {
             this.numero = numero;
        }

        public StringProperty numeroProperty() {
            return new SimpleStringProperty(this.numero);
        }

        public String getfMonto() {
            return this.monto;
        }
        public void setfMonto(String monto) {
             this.monto = monto;
        }

        public StringProperty montoProperty() {
            return new SimpleStringProperty(this.monto);
        }


    }*/
    private ObservableList<Digic> data;


    @FXML
    private StackPane root;
    //@FXML private StackPane Spinner;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Funcion de inicio del Controlador
        //

        p2_Dialog_Procesando.setTransitionType(JFXDialog.DialogTransition.CENTER);
        p2_Dialog_Procesando.setOverlayClose(false);
        p2_Dialog_Procesando.setDialogContainer(root);

        p2_img_barcode.getProperties().put(VK_STATE, VK_STATE_DISABLED);

        bundle = resources;

        p2_tf_clave_banco.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p2_tf_codigo_aba.getProperties().put(VK_TYPE, VK_TYPE_NUMERIC);
        p2_tf_email.getProperties().put(VK_TYPE, VK_TYPE_EMAIL);

       // LocalDate date = LocalDate.now();
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        fechaHoy = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(fechaHoy, formatter);

        if(App.parametrosModel.getTipoDoumento() == null) App.parametrosModel.setTipoDoumento("I");

        switch (App.parametrosModel.getTipoDoumento()){
            case "P" :
                p2_lb_valorDocumento.setText(App.parametrosModel.getNumeroPasaporte());
                p2_lb_valorDocumentoEsperado.setText(App.parametrosModel.getNumeroPasaporte());
                p2_lb_tipoDocumento.setText("PASAPORTE");
                break;
            case "I" :
                p2_lb_valorDocumento.setText(App.parametrosModel.getNumeroDniNifNieTie());
                p2_lb_valorDocumentoEsperado.setText(App.parametrosModel.getNumeroDniNifNieTie());
                //p2_lb_valorDocumento.setText(App.parametrosModel.getDniNifNieTieDemo());
                //p2_lb_valorDocumentoEsperado.setText(App.parametrosModel.getDniNifNieTieDemo());
                p2_lb_tipoDocumento.setText("NIF/NIE/TIE");
                break;
        }

        p2_lb_nombreViajero.setText(App.parametrosModel.getNombreViajero());

        p2_btn_demo.setVisible(App.parametrosModel.getAppDemo());
        p2_btn_wsdl.setVisible(App.parametrosModel.getAppDemo());

        p2_btn_aceptar.setVisible(false);

        //p2_tc_listajustificantes.setCellValueFactory(new PropertyValueFactory<>("numero"));
        //p2_tc_listajustificantesMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));

        //p2_tc_listajustificantes.setMinWidth(100);
        //p2_tv_justificantes.setPlaceholder(new Label(bundle.getString( "p2_tv_Placeholder")));

        //p2_tv_justificantes.getSelectionModel().setCellSelectionEnabled(true);
        //ObservableList selectedCells = p2_tv_justificantes.getSelectionModel().getSelectedCells();
        //Refresh();
        /*
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                
                try {
                 
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());

                    List<Digic> digicLits = digicRepository.findByJustificante((String)val, App.UUIDProcess);

                    Digic digic = digicLits.get(0);

                    FillPlantilla(digic);


                } catch (Exception e) {

                    e.printStackTrace();
                    System.out.println(e.getMessage());

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                     
                }
                
            }
        });     */
        
        //p2_tv_justificantes.getColumns().addAll(p2_tc_listajustificantes);
        //p2_tv_justificantes.getColumns().addAll(p2_tc_listajustificantesMonto);

        ClearPlantilla();

        p2_img_barcode.setOnKeyTyped(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                //System.out.println("p2_img_barcode Key Pressed: " + ke.getText());
                //System.out.println("p2_img_barcode Key Pressed: " + ke.getCharacter());
                ScannerReader = ScannerReader + ke.getCharacter(); 
                
                if (ke.getCode().equals(KeyCode.ENTER) || 
                    ke.getCharacter().getBytes()[0] == '\n' || 
                    ke.getCharacter().getBytes()[0] == '\r') {
                
                    System.out.println("\nData: " + ScannerReader); 
                    
                    try {
                        QRcodeRead(ScannerReader,0);
                    } catch (IOException e) {

                        e.printStackTrace();

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Error");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();              

                    }

                    ScannerReader="";
    
                }                
            }
        });

        try{
            RefreshTV();
            //p2_btn_add_403.fire();

            switchToQRCode();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void DisplaySelected(MouseEvent event) throws Exception {
        Digic digic = p2_tv_justificantesdigic.getSelectionModel().getSelectedItem();
        if(!(digic == null)){
            FillPlantilla(digic);
        }
    }

     @FXML
    private void RefreshTV() throws  IOException{

        try {
            List<Digic> personList = digicRepository.findByuuidProceso(App.UUIDProcess);
            data = FXCollections.observableArrayList(personList);

            TableColumn<Digic, String> justificnteColumn = new TableColumn<>(bundle.getString( "columna_titulo_justificante"));
            justificnteColumn.setCellValueFactory(new PropertyValueFactory<>("justificante"));

            TableColumn<Digic, String> razonSocialColumn = new TableColumn<>(bundle.getString( "columna_titulo_nombrecomercial"));
            razonSocialColumn.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));

            TableColumn<Digic, String> numeroFacturaColumn = new TableColumn<>(bundle.getString( "columna_titulo_numerofactura"));
            numeroFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("numeroFactura"));

            TableColumn<Digic, String> fechaFacturaColumn = new TableColumn<>(bundle.getString( "columna_titulo_fecha"));
            fechaFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaFactura"));

            TableColumn<Digic, String> totalDigicColumn = new TableColumn<>(bundle.getString( "columna_titulo_importedigic"));
            totalDigicColumn.setCellValueFactory(new PropertyValueFactory<>("totalDigic"));

            p2_tv_justificantesdigic.setPlaceholder(new Label(bundle.getString( "tv_justificantesdigic_Placeholder")));
            p2_tv_justificantesdigic.getColumns().setAll(justificnteColumn, razonSocialColumn, numeroFacturaColumn, fechaFacturaColumn, totalDigicColumn);
            p2_tv_justificantesdigic.setStyle("-fx-font-size: 20;");
            p2_tv_justificantesdigic.setItems(data);

            if(!personList.isEmpty()){

                Double montoTotalDigic= 0.00;

                for (Digic digic: personList) {
                    montoTotalDigic = montoTotalDigic + Double.valueOf (digic.getTotalDigic());
                }

                p2_tf_montoTotalDigic.setText(getTwoDecimals(montoTotalDigic));
                //floatTxtFld(p2_tf_montoTotalDigic);
                p2_btn_aceptar.setVisible(true);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static String getTwoDecimals(double value){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }
    public static void floatTxtFld(TextField field) {
        DecimalFormat format = new DecimalFormat("#");
        field.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(c.getControlNewText(), parsePosition);

            if ((object == null) || ((parsePosition.getIndex()) < (c.getControlNewText().length()))) {
                return null;
            } else {
                if (new BigDecimal(c.getControlNewText()).scale() <= 2)
                    return c;
                else
                    return null;
            }
        }));
    }

    @FXML
    private void LoadDialog(String title, String body){
        JFXDialogLayout content = new JFXDialogLayout();

        content.setHeading(new Text(title));
        content.setBody(new Text(body));
        content.setStyle("-fx-font-size: 20;");

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER, false);
        JFXButton button = new JFXButton(bundle.getString( "p2_btn_popup"));
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: #00bfff;");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                p2_img_barcode.requestFocus();
                dialog.close();
            }
        });

        content.setActions(button);
        p2_img_barcode.requestFocus();
        dialog.show();


    }


    @FXML
    private void LoadDialogEscanear(){
/*
        p2_btn_add_403.setDisable(true);
        p2_btn_salir.setDisable(true);
        p2_btn_wsdl.setDisable(true);
        p2_btn_demo.setDisable(true);
        //p2_tv_justificantesdigic.setDisable(true);
*/
        String title, body;

        title = "DIALOG" ;

        body = bundle.getString( "p2_lb_popup_escanee_qr");

        JFXDialogLayout content = new JFXDialogLayout();

        content.setHeading(new Text(title));
        content.setHeading(new ImageView("/img/scanearModelo403.png"));
        content.setBody(new Text(body));
        content.setStyle("-fx-font-size: 20;");

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER, false);
        JFXButton button = new JFXButton(bundle.getString( "p2_btn_popup"));
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: #00bfff;");
        button.setFocusTraversable(false);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                p2_img_barcode.requestFocus();
                p2_btn_add_403.setDisable(false);
                p2_btn_salir.setDisable(false);
                p2_btn_wsdl.setDisable(false);
                p2_btn_demo.setDisable(false);
                //p2_tv_justificantesdigic.setDisable(false);
                dialog.close();
            }
        });

        content.setActions(button);

        dialog.show();
        p2_img_barcode.requestFocus();

    }


    @FXML
    private void BtnActionWsdl(ActionEvent event) throws SQLException, IOException, InvocationTargetException {
        //switchToWSDL();

        LoadDialog("ESTA ES UNA PRUEBA","QUE TAL ÉSTA PRUEBA.\nSE VE MUY BIEN\nNO?");

    }    

    @FXML 
    private void switchToAnterior() throws IOException {
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Locale locale = Locale.getDefault();
            App.setRoot("/views/primary",locale);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
*/

        JFXDialogLayout content = new JFXDialogLayout();

        content.setHeading(new Text(bundle.getString( "p2_btn_anterior_dialogo_Heading")));
        content.setBody(new Text(bundle.getString( "p2_btn_anterior_dialogo_Body")));
        content.setStyle("-fx-font-size: 20;");

        JFXDialog dialog = new JFXDialog(root, content, JFXDialog.DialogTransition.CENTER, false);
        JFXButton buttonOK = new JFXButton(bundle.getString( "p2_btn_anterior_dialogo_ok"));
        JFXButton buttonCancel = new JFXButton(bundle.getString( "p2_btn_anterior_dialogo_cancelar"));
        buttonOK.setButtonType(JFXButton.ButtonType.RAISED);
        buttonOK.setStyle("-fx-background-color: #00bfff;");
        buttonCancel.setButtonType(JFXButton.ButtonType.RAISED);
        buttonCancel.setStyle("-fx-background-color: #00bfff;");
        buttonCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialog.close();
            }
        });
        buttonOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialog.close();

                try {

                   digicRepository.deleteAllByuuidProceso(App.UUIDProcess);
                   digicModoPagoRepository.deleteAllByuuidProceso(App.UUIDProcess);

                    Locale locale = Locale.getDefault();
                    App.setRoot("/views/primary",locale);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        content.setActions(buttonOK,buttonCancel);

        dialog.show();

    }

    @FXML 
    private void switchToWSDL() throws IOException, InvocationTargetException {
        
        
        ValidarRemesaDerResponse validarRemesaDerResponse;
        
        validarRemesaDerResponse  = KioskoServiceClient.getInstance().validarRemesa(DummyData.getExampleKO());
        KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);

        validarRemesaDerResponse = KioskoServiceClient.getInstance().validarRemesa(DummyData.getExample());
        KioskoServiceClientUtils.printResponse(validarRemesaDerResponse);
        


    }


    @FXML
    private void switchToAceptar() throws IOException {
        Locale locale = Locale.getDefault();
        App.setRoot("/views/Valida_Envia_DER",locale);

    }

    @FXML
    private void switchToSQLiteConnect() throws IOException, SQLException, ClassNotFoundException {
        //Locale locale = Locale.getDefault();
        //App.setRoot("secondary",locale);
        SQLiteConnect();
    }

    @FXML private void switchToQRCode() throws IOException {

            ClearPlantilla();
            LoadDialogEscanear();
            p2_img_barcode.requestFocus();


    }

    @FXML private void QRcodeRead() throws IOException, FormatException, ChecksumException, NotFoundException, ClassNotFoundException, SQLException {

        //String pathname = "DER_qrcode_temp.png";
        String pathname = App.parametrosModel.getQRCODEDEMO();
        String text     = readQR(pathname);
        QRcodeRead(text,1);

    }

    @FXML private void QRcodeRead(String qr_text, Integer index) throws IOException {

        p2_Dialog_Procesando.show();

        ClearPlantilla();
        p2_img_barcode.setText("");
        p2_img_barcode.requestFocus();
  
        try {
          
            if(App.parametrosModel.getAppDemo()) System.out.print("readQR: " + qr_text);

            //String password = "Grecasa2022";
            String password = App.parametrosModel.getQRPassDecoder();
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(password);
            String plainText = textEncryptor.decrypt(qr_text);
            System.out.println("\nData Decrypted: "+ plainText);

            JSONObject myJson = new JSONObject(plainText);

            /*
             * Genera un numero Randon apartir del
             * numero de justificante para efecto de demo
             */

            if (App.parametrosModel.getAppDemo() && index.equals(1)){
                Integer n = rand.nextInt(5);
                String  ns = myJson.getString("justificante");
                String  as = myJson.getString("apellidosViajero");

                ns = ns + n.toString();
                as = as + " " + n.toString();

                myJson.put("justificante",ns);
                myJson.put("apellidosViajero",as);
                
            }

            /*
             * Seteo de Campos del formulario
             */

             if (!App.parametrosModel.getAppDemo() && Integer.parseInt(myJson.getString("fechaLimiteSalida")) < Integer.parseInt(App.parametrosModel.getFechaHoy())){

                if(App.parametrosModel.getAppDemo()) System.out.print("\nfecha Limite Salida: Se encuentra caducada");
                p2_tx_info_item.setText(bundle.getString( "p2_tx_info_fechalimite"));
                //p2_an_info_item.setVisible(true);
                 p2_Dialog_Procesando.close();

                 LoadDialog("", bundle.getString( "p2_tx_info_fechalimite"));



             }else  if ( !myJson.getString("valorDocumento").equals(p2_lb_valorDocumentoEsperado.getText())){

                 if(App.parametrosModel.getAppDemo()) System.out.print("\nvalorDocumento: No corresponde con el valor esperado");
                p2_tx_info_item.setText(bundle.getString( "p2_tx_info_item2"));
                //p2_an_info_item.setVisible(true);
                 p2_Dialog_Procesando.close();

                 LoadDialog("", bundle.getString( "p2_tx_info_item2"));


             }else if (existJustificante(myJson.getString("justificante"))){

                 if(App.parametrosModel.getAppDemo()) System.out.print("\nreadQR: Registro ya procesado");
                
                p2_tx_info_item.setText(bundle.getString( "p2_tx_info_item"));                
                //p2_an_info_item.setVisible(true);
                 p2_Dialog_Procesando.close();

                 LoadDialog("", bundle.getString( "p2_tx_info_item"));


             }else{

                FillPlantilla(myJson);
                InsertItem(myJson);
                RefreshTV();
                p2_Dialog_Procesando.close();

            }            

        } catch (Exception e) {

            p2_Dialog_Procesando.close();

            e.printStackTrace();

            if(App.parametrosModel.getAppDemo()) System.out.printf("\nCausa: %s \nMensaje: %s\n Class: %s\n Localized Mensaje: %s\n" ,e.getCause(),e.getMessage(),e.getClass(),e.getLocalizedMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();              


        }
    }

    public Boolean existJustificante(String justificante){

        if(StringUtils.isBlank(justificante)){
            return false;
        }

        List<Digic> digicLits = digicRepository.findByJustificante(justificante, App.UUIDProcess);

        if(digicLits.size() == 0)
            return false;

        Digic digic = digicLits.get(0);



        return (digic == null || digic.getJustificante() == null) ? false : true;
    }



    private static String readQR(String pathname) throws FormatException, ChecksumException, NotFoundException, IOException {

        InputStream qrInputStream     = new FileInputStream(pathname);
        BufferedImage qrBufferedImage = ImageIO.read(qrInputStream);

        LuminanceSource source = new BufferedImageLuminanceSource(qrBufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader reader = new MultiFormatReader();
        Result stringBarCode = reader.decode(bitmap);

        return stringBarCode.getText();
    }

    @FXML 
    private void ClearPlantilla(){

        p2_an_warning.setVisible(false);
        p2_an_info_item.setVisible(false);

        p2_lb_medioPago.setVisible(false);
        p2_lb_medioPago_txt.setVisible(false);
        p2_lb_cuentaInternacional.setVisible(false);
        
        /*
        * 
        * Limpiar campos
        * 
        */

            // Justificante Modelo 403
            p2_lb_justificante.setText("");

            // 1. Datos de Comercio
            p2_lb_nif.setText("");
            p2_lb_razon_social.setText("");

            // 2. Datos de la Factura
            p2_lb_numero_factura.setText("");
            p2_lb_fecha_factura.setText("");
            p2_lb_monto_factura.setText("");

            // 3. Datos del Viajero
            p2_lb_nombreViajero.setText("");
            p2_lb_apellidosViajero.setText("");
            p2_lb_tipoDocumento.setText("");
            p2_lb_valorDocumento.setText("");
            p2_lb_paisExpedicion.setText("");
            p2_lb_paisResidencia.setText("");
            p2_tf_email.setText("");
            
            // 4. Datos Transporte
            p2_lb_fechaLimiteSalida.setText("");

            // 5. Datos de medio de pago
            p2_lb_medioPago.setText("");
            p2_lb_cuentaInternacional.setText("");
            
            p2_tf_codigoBic.setText("");
            p2_tf_valorMedioPago.setText("");
 
            p2_lb_medioPago.setText("");
            p2_tf_pais_banco.setText("");
            p2_tf_clave_banco.setText("");
            p2_tf_cuenta_bancaria.setText("");
            p2_tf_descripcion_banco.setText("");
            p2_tf_codigo_aba.setText("");
            p2_tf_clave_control.setText("");            

    }

    @FXML private void FillPlantilla(JSONObject myJson) throws Exception {

            p2_btn_aceptar.setVisible(true);

            // Justificante Modelo 403
            p2_lb_justificante.setText((String) myJson.get("justificante"));

            // 1. Datos de Comercio
            p2_lb_nif.setText((String) myJson.get("nifEstablecimiento"));
            p2_lb_razon_social.setText((String) myJson.get("razonSocial"));


            // 2. Datos del Viajero

            LocalDate fechaFactura = LocalDate.parse((String) myJson.get("fechaFactura"), formatter);
            p2_lb_numero_factura.setText((String) myJson.get("numeroFactura"));
            String tempFecha = fechaFactura.toString();
            tempFecha = tempFecha.substring(8,10) + "/" + tempFecha.substring(5,7) + "/" + tempFecha.substring(0,4) ;
            p2_lb_fecha_factura.setText(tempFecha);
            p2_lb_monto_factura.setText(myJson.get("totalDigic").toString());

            // 3. Datos del Viajero
            p2_lb_nombreViajero.setText((String) myJson.get("nombreViajero"));
            p2_lb_apellidosViajero.setText((String) myJson.get("apellidosViajero"));
            p2_lb_tipoDocumento.setText((String) myJson.get("tipoDocumento"));
            p2_lb_valorDocumento.setText((String) myJson.get("valorDocumento"));
            p2_lb_paisExpedicion.setText(SeleccionarPaisV2((String) myJson.get("paisExpedicion")));
            p2_lb_paisResidencia.setText(SeleccionarPaisV2((String) myJson.get("paisResidencia")));
            p2_tf_email.setText((String) myJson.get("email"));
            
            // 4. Datos Transporte
            LocalDate fechaLimiteSalida = LocalDate.parse((String) myJson.get("fechaLimiteSalida"), formatter);
            tempFecha = fechaLimiteSalida.toString();
            tempFecha = tempFecha.substring(8,10) + "/" + tempFecha.substring(5,7) + "/" + tempFecha.substring(0,4) ;

            p2_lb_fechaLimiteSalida.setText(tempFecha);

            // 5. Datos de medio de pago
            if (!myJson.isNull("cuentaSinIBAN")){
                p2_lb_cuentaInternacional.setText((String) myJson.get("cuentaSinIBAN"));
            }else{
                p2_lb_cuentaInternacional.setText("SI");
            }
            
            if (!myJson.isNull("paisBanco")){
                p2_tf_pais_banco.setText((String) myJson.get("paisBanco"));
            }
            
            if (!myJson.isNull("claveBanco")){
                p2_tf_clave_banco.setText((String) myJson.get("claveBanco"));
            }

            if (!myJson.isNull("valorMedioPago")){
                p2_tf_cuenta_bancaria.setText((String) myJson.get("valorMedioPago"));
            }

            if (!myJson.isNull("descInstFinanciera")){
                p2_tf_descripcion_banco.setText((String) myJson.get("descInstFinanciera"));
            }

            if (!myJson.isNull("numeroABA")){
                p2_tf_codigo_aba.setText((String) myJson.get("numeroABA"));
            }

            if (!myJson.isNull("claveControl")){
                p2_tf_clave_control.setText((String) myJson.get("claveControl"));
            }

            if (!myJson.isNull("modoPago")){
                p2_lb_medioPago.setText((String) myJson.get("modoPago"));
            }
        
            if (!myJson.isNull("codigoBic")){
                p2_tf_codigoBic.setText((String) myJson.get("codigoBic"));
            }

            if (!myJson.isNull("valorMedioPago")){
                p2_tf_valorMedioPago.setText((String) myJson.get("valorMedioPago"));
            }

            if (!myJson.isNull("modoTransporte")){
                p2_tf_modoTransporte.setText((String) myJson.get("modoTransporte"));
            }

            if (!myJson.isNull("identificadorBillete")){
                p2_tf_identificadorBillete.setText((String) myJson.get("identificadorBillete"));
            }


            // Colocar en Visible o No Visibles los campos de la plantilla

            if( !myJson.isNull("cuentaSinIBAN") && (boolean) myJson.get("cuentaSinIBAN").equals("SI")){
                System.out.println("SI: " + (String) myJson.get("cuentaSinIBAN"));
                
                p2_lb_codigoBic.setVisible(true);
                p2_lb_codigo_cuenta_internacional.setVisible(true);

                p2_tf_codigoBic.setVisible(true);

                p2_lb_pais_banco.setVisible(true);
                p2_tf_pais_banco.setVisible(true);
                p2_lb_clave_banco.setVisible(true);
                p2_tf_clave_banco.setVisible(true);
                p2_lb_cuenta_bancaria.setVisible(true);
                p2_tf_cuenta_bancaria.setVisible(true);
                p2_lb_clave_control.setVisible(true);
                p2_tf_clave_control.setVisible(true);
                p2_lb_codigo_aba.setVisible(true);
                p2_tf_codigo_aba.setVisible(true);
                p2_lb_descripcion_banco.setVisible(true);
                p2_tf_descripcion_banco.setVisible(true);

                p2_tf_valorMedioPago.setVisible(false);
                p2_lb_valorMedioPago.setVisible(false);
                p2_lb_codigo_cuenta_nacional.setVisible(false);



            }else{
                
                p2_lb_codigoBic.setVisible(false);
                p2_lb_codigo_cuenta_internacional.setVisible(false);

                p2_tf_codigoBic.setVisible(false);
                
                p2_lb_pais_banco.setVisible(false);
                p2_tf_pais_banco.setVisible(false);
                p2_lb_clave_banco.setVisible(false);
                p2_tf_clave_banco.setVisible(false);
                p2_lb_cuenta_bancaria.setVisible(false);
                p2_tf_cuenta_bancaria.setVisible(false);
                p2_lb_clave_control.setVisible(false);
                p2_tf_clave_control.setVisible(false);
                p2_lb_codigo_aba.setVisible(false);
                p2_tf_codigo_aba.setVisible(false);
                p2_lb_descripcion_banco.setVisible(false);
                p2_tf_descripcion_banco.setVisible(false);

                p2_tf_valorMedioPago.setVisible(true);
                p2_lb_valorMedioPago.setVisible(true);
                p2_lb_codigo_cuenta_nacional.setVisible(true);
                
            }
        

    }


    @FXML private void FillPlantilla(Digic myJson) throws Exception {

        p2_btn_aceptar.setVisible(true);

        // Justificante Modelo 403
        p2_lb_justificante.setText((String) myJson.getJustificante());

        // 1. Datos de Comercio
        p2_lb_nif.setText((String) myJson.getNifEstablecimiento());
        p2_lb_razon_social.setText((String) myJson.getRazonSocial());


        // 2. Datos del Viajero
        //LocalDate fechaFactura = LocalDate.parse((String) myJson.getFechaFactura(), formatter);
        p2_lb_numero_factura.setText((String) myJson.getNumeroFactura());
        //p2_lb_fecha_factura.setText(fechaFactura.toString());
        p2_lb_fecha_factura.setText(myJson.getFechaFactura());
        p2_lb_monto_factura.setText(myJson.getTotalDigic().toString());

        // 3. Datos del Viajero
        p2_lb_nombreViajero.setText((String) myJson.getNombreViajero());
        p2_lb_apellidosViajero.setText((String) myJson.getApellidosViajero());
        p2_lb_tipoDocumento.setText((String) myJson.getTipoDocumento());
        p2_lb_valorDocumento.setText((String) myJson.getValorDocumento());
        p2_lb_paisExpedicion.setText(SeleccionarPaisV2((String) myJson.getPaisExpedicion()));
        p2_lb_paisResidencia.setText(SeleccionarPaisV2((String) myJson.getPaisResidencia()));
        p2_tf_email.setText((String) myJson.getEmail());

        // 4. Datos Transporte
        //LocalDate fechaLimiteSalida = LocalDate.parse((String) myJson.getFechaLimiteSalida(), formatter);
        //p2_lb_fechaLimiteSalida.setText(fechaLimiteSalida.toString());
        p2_lb_fechaLimiteSalida.setText(myJson.getFechaLimiteSalida());

        //TODO: NO SE TIENE ESA FECHA DE SALIDA

        // 5. Datos de medio de pago
        //if (!myJson.isNull("cuentaSinIBAN")){
            p2_lb_cuentaInternacional.setText((String) myJson.getCuentaSinIBAN());
        //}else{
        //    p2_lb_cuentaInternacional.setText("NO");
        //}


        //if (!myJson.isNull("paisBanco")){
            p2_tf_pais_banco.setText((String) myJson.getPaisBanco());
        //}

        //if (!myJson.isNull("claveBanco")){
            p2_tf_clave_banco.setText((String) myJson.getClaveBanco());
       // }

        if(myJson.getCuentaSinIBAN().equals("SI")) {
            //if (!myJson.isNull("valorMedioPago")){
            p2_tf_cuenta_bancaria.setText((String) myJson.getValorMedioPago());
            //}
        }else{
            //if (!myJson.isNull("valorMedioPago")){
            p2_tf_valorMedioPago.setText((String) myJson.getValorMedioPago());
            //}
        }

        //if (!myJson.isNull("descInstFinanciera")){
            p2_tf_descripcion_banco.setText((String) myJson.getDescInstFinanciera());
        //}

        //if (!myJson.isNull("numeroABA")){
            p2_tf_codigo_aba.setText((String) myJson.getNumeroABA());
        //}

        //if (!myJson.isNull("claveControl")){
            p2_tf_clave_control.setText((String) myJson.getClaveControl());
        //}

        //if (!myJson.isNull("modoPago")){
            p2_lb_medioPago.setText((String) myJson.getModoPago());
        //}

        //if (!myJson.isNull("codigoBic")){
            p2_tf_codigoBic.setText((String) myJson.getCodigoBic());
        //}

        //if (!myJson.isNull("modoTransporte")){
            p2_tf_modoTransporte.setText((String) myJson.getModoPago());
        //}

        //if (!myJson.isNull("identificadorBillete")){
            p2_tf_identificadorBillete.setText((String) myJson.getIdentificadorBillete());
        //}


        // Colocar en Visible o No Visibles los campos de la plantilla

        if( (boolean) myJson.getCuentaSinIBAN().equals("SI")){
            if(App.parametrosModel.getAppDemo()) System.out.println("SI: " + (String) myJson.getCuentaSinIBAN());

            p2_lb_codigoBic.setVisible(true);
            p2_lb_codigo_cuenta_internacional.setVisible(true);

            p2_tf_codigoBic.setVisible(true);

            p2_lb_pais_banco.setVisible(true);
            p2_tf_pais_banco.setVisible(true);
            p2_lb_clave_banco.setVisible(true);
            p2_tf_clave_banco.setVisible(true);
            p2_lb_cuenta_bancaria.setVisible(true);
            p2_tf_cuenta_bancaria.setVisible(true);
            p2_lb_clave_control.setVisible(true);
            p2_tf_clave_control.setVisible(true);
            p2_lb_codigo_aba.setVisible(true);
            p2_tf_codigo_aba.setVisible(true);
            p2_lb_descripcion_banco.setVisible(true);
            p2_tf_descripcion_banco.setVisible(true);

            p2_tf_valorMedioPago.setVisible(false);
            p2_lb_valorMedioPago.setVisible(false);
            p2_lb_codigo_cuenta_nacional.setVisible(false);



        }else{

            p2_lb_codigoBic.setVisible(false);
            p2_lb_codigo_cuenta_internacional.setVisible(false);

            p2_tf_codigoBic.setVisible(false);

            p2_lb_pais_banco.setVisible(false);
            p2_tf_pais_banco.setVisible(false);
            p2_lb_clave_banco.setVisible(false);
            p2_tf_clave_banco.setVisible(false);
            p2_lb_cuenta_bancaria.setVisible(false);
            p2_tf_cuenta_bancaria.setVisible(false);
            p2_lb_clave_control.setVisible(false);
            p2_tf_clave_control.setVisible(false);
            p2_lb_codigo_aba.setVisible(false);
            p2_tf_codigo_aba.setVisible(false);
            p2_lb_descripcion_banco.setVisible(false);
            p2_tf_descripcion_banco.setVisible(false);

            p2_tf_valorMedioPago.setVisible(true);
            p2_lb_valorMedioPago.setVisible(true);
            p2_lb_codigo_cuenta_nacional.setVisible(true);

        }


    }


    /*
     * 
     *  Conexion SQLite3
     * 
     */
    @FXML
    public void SQLiteConnect() throws SQLException, ClassNotFoundException {
        checkDrivers();    //check for driver errors
        Connection connection = connect(DBLocal);

    }

    private Connection connect(String location) throws ClassNotFoundException {
       
        Class.forName("org.sqlite.JDBC");
        
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPrefix + location);
            System.out.println("connection");
            System.out.println(connection);

        } catch (SQLException e) {

            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Could not connect to SQLite DB at " + location);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();              

            return null;
        }
        return connection;
    }

    private static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Start SQLite Drivers");
            System.out.println("Connect driver SQLite3");
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Could not start SQLite Drivers");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(classNotFoundException.getMessage());
            alert.showAndWait();             

            return false;
        }
    }


    public void InsertItem(JSONObject myJson) throws SQLException, ClassNotFoundException {

        Digic digic = JsonUtils.convertJsonToDigic(myJson);        
        digicRepository.save(digic);

        //Justificante justificante = new Justificante(digic.getJustificante(), digic.getTotalDigic());
        //p2_tv_justificantes.getItems().addAll(justificante);
    }

    @FXML
    public void UpdateItem() throws SQLException, ClassNotFoundException {
        
        System.out.println("FXML UpdateItem");

        Digic digic = new Digic();

        /*
        digic.setjustificante(p2_lb_justificante.getText());
        digic.setemail(p2_tf_email.getText());
        digic.setcodigoBic(p2_tf_codigoBic.getText());
        digic.setvalorMedioPago(p2_tf_valorMedioPago.getText());

        digic.setclaveControl(p2_tf_clave_control.getText());
        digic.setcuentaSinIBAN(p2_lb_cuentaInternacional.getText());
        digic.setnumeroABA(p2_tf_codigo_aba.getText());
        digic.setclaveBanco( p2_tf_clave_banco.getText());
        digic.setdescInstFinanciera(p2_tf_descripcion_banco.getText());
        digic.setpaisBanco( p2_tf_pais_banco.getText());

        digic.setfecha_upload(LocalDateTime.now().toString());
        // */

        System.out.println("todo: " + digic.toString());        
        
        //if (!DIGIDAO.UpdateItem(digic)) p2_an_warning.setVisible(true);
        digicRepository.save(digic);
        
    }

    @FXML
    public void SelectItems()  throws SQLException, ClassNotFoundException {

        System.out.println("Select Items");

        Digic digic = new Digic();

        String sql = "SELECT * FROM " + tableName ;

        try (Connection connection = connect(DBLocal);) {
            /*
             * setear los parametros del where
             */
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("ResultSet");
            System.out.println(rs);

            while (rs.next()) {

                System.out.println("id: "          + rs.getLong("id"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Details: "     + rs.getString("details"));
                System.out.println("Done: "        + rs.getBoolean("done"));

                /* 
                digic.add(new DIGIModel(
                        rs.getLong("justificante"),
                        .....
                ));
                         */
            }

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Could not Update from " + tableName + " because: \n" + e.toString() + "\n" + e.getMessage() + "\n" + e.getErrorCode());

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();              
        
        }finally{
            //SelectItems_sqlite3();
        }
     
    }


    public Digic getFromUI(){
        Digic digic = new Digic();

            /* 
            digic.setJustificante(p2_lb_justificante.getText());
            digic.setnifEstablecimiento(p2_lb_nif.getText());
            digic.setrazonSocial(p2_lb_razon_social.getText());
            digic.setnumeroFactura(p2_lb_numero_factura.getText()); 
            digic.setfechaFactura(p2_lb_fecha_factura.getText()); 
            digic.settotalDigic(p2_lb_monto_factura.getText()); 
            digic.setnombreViajero(p2_lb_nombreViajero.getText()); 
            digic.setapellidosViajero(p2_lb_apellidosViajero.getText()); 
            digic.settipoDocumento(p2_lb_tipoDocumento.getText()); 
            digic.setvalorDocumento(p2_lb_valorDocumento.getText()); 
            digic.setpaisExpedicion(p2_lb_paisExpedicion.getText()); 
            digic.setpaisResidencia(p2_lb_paisResidencia.getText()); 
            digic.setemail(p2_tf_email.getText()); 
            digic.setfechaLimiteSalida(p2_lb_fechaLimiteSalida.getText()); 
            digic.setcuentaSinIBAN(p2_lb_cuentaInternacional.getText()); 
            digic.setpaisBanco(p2_tf_pais_banco.getText()); 
            digic.setclaveBanco(p2_tf_clave_banco.getText()); 
            digic.setvalorMedioPago(p2_tf_cuenta_bancaria.getText()); 
            digic.setdescInstFinanciera(p2_tf_descripcion_banco.getText()); 
            digic.setnumeroABA(p2_tf_codigo_aba.getText()); 
            digic.setclaveControl(p2_tf_clave_control.getText()); 
            digic.setmodoPago(p2_lb_medioPago.getText()); 
            digic.setcodigoBic(p2_tf_codigoBic.getText()); 
            digic.setvalorMedioPago(p2_tf_valorMedioPago.getText()); 
            digic.setmodoTransporte(p2_tf_modoTransporte.getText()); 
            digic.setidentificadorBillete(p2_tf_identificadorBillete.getText()); 
            // */

        return digic;
    }

    public void setFromUI(Digic digic) {

        /*
        p2_lb_justificante.setText(digic.getjustificante());
        p2_lb_nif.setText(digic.getnifEstablecimiento());
        p2_lb_razon_social.setText(digic.getrazonSocial());
        p2_lb_numero_factura.setText(digic.getnumeroFactura());
        p2_lb_fecha_factura.setText(digic.getfechaFactura());
        p2_lb_monto_factura.setText(digic.gettotalDigic().toString());
        p2_lb_nombreViajero.setText(digic.getnombreViajero());
        p2_lb_apellidosViajero.setText(digic.getapellidosViajero());
        p2_lb_tipoDocumento.setText(digic.gettipoDocumento());
        p2_lb_valorDocumento.setText(digic.getvalorDocumento());
        p2_lb_paisExpedicion.setText(SeleccionarPaisV2(digic.getpaisExpedicion()));
        p2_lb_paisResidencia.setText(SeleccionarPaisV2(digic.getpaisResidencia()));
        p2_tf_email.setText(digic.getemail());
        p2_lb_fechaLimiteSalida.setText(digic.getfechaLimiteSalida());
        p2_lb_cuentaInternacional.setText(digic.getcuentaSinIBAN());
        p2_tf_pais_banco.setText(digic.getpaisBanco());
        p2_tf_clave_banco.setText(digic.getclaveBanco());
        p2_tf_cuenta_bancaria.setText(digic.getvalorMedioPago());
        p2_tf_descripcion_banco.setText(digic.getdescInstFinanciera());
        p2_tf_codigo_aba.setText(digic.getnumeroABA());
        p2_tf_clave_control.setText(digic.getclaveControl());
        p2_lb_medioPago.setText(digic.getmodoPago());
        p2_tf_codigoBic.setText(digic.getcodigoBic());
        p2_tf_valorMedioPago.setText(digic.getvalorMedioPago());
        p2_tf_modoTransporte.setText(digic.getmodoTransporte());
        p2_tf_identificadorBillete.setText(digic.getidentificadorBillete());      
        //*/

    }
    
    public void refesh() {
        //comboClientes.setItems(FXCollections.observableArrayList(clienteRepository.findAll()));
    }

    @FXML
    public boolean SelectItem(String justificante)  throws SQLException, ClassNotFoundException {


        System.out.println("Select Items");

        Digic digic = new Digic();

        String sql = "SELECT * FROM " + tableName + "  WHERE justificante = ? ";

        try (Connection connection = connect(DBLocal);) {
            /*
             * setear los parametros del where
             */
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, justificante);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("ResultSet");
            System.out.println(rs);

            while (rs.next()) {

                System.out.println("justificante: "     + rs.getString("justificante"));
                System.out.println("nombreViajero: "    + rs.getString("nombreViajero"));
                System.out.println("apellidosViajero: " + rs.getString("apellidosViajero"));
                System.out.println("tipoDocumento: "    + rs.getString("tipoDocumento"));

                //p2_an_info_item.setVisible(true);

                LoadDialog("", bundle.getString( "p2_tx_info_item"));

                /* 
                digic.add(new DIGIModel(
                        rs.getLong("justificante"),
                        .....
                ));
                         */
                return true;
            }
            

        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,LocalDateTime.now() + ": Could not Update from " + tableName + " because: \n" + e.toString() + "\n" + e.getMessage() + "\n" + e.getErrorCode());

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();              
        
        }finally{
            //SelectItems_sqlite3();
            
        }
        return false;
     
    }

    private String SeleccionarPaisV2(String index)  throws Exception {

        String SAMPLE_CSV_FILE_PATH = "ISO-Codes.csv";

        try (
                java.io.Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            // Reading Records One by One in a String array
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {

                //System.out.println("English-Short-Name : " + nextRecord[0]);
                //System.out.println("Alpha-2-code : " + nextRecord[1]);
                //System.out.println("Alpha-3-code : " + nextRecord[2]);
                //System.out.println("Numeric-code : " + nextRecord[3]);
                //System.out.println("Independent : " + nextRecord[4]);
                //System.out.println("==========================");

                if (nextRecord[1].equals(index) || nextRecord[2].equals(index)) return nextRecord[0].toUpperCase();
            }
        }
        return "";
    }

    public String SeleccionarPais(String index){

        String pais = "";
        switch(index){
            case "AL" : pais = "Albania (AL)"; break;
            case "DZ" : pais = "Algeria (DZ)"; break;
            case "BH" : pais = "Bahrain (BH)"; break;
            case "EG" : pais = "Egypt (EG)"; break;
            case "IQ" : pais = "Iraq (IQ)"; break;
            case "JO" : pais = "Jordan (JO)"; break;
            case "KW" : pais = "Kuwait (KW)"; break;
            case "LB" : pais = "Lebanon (LB)"; break;
            case "LY" : pais = "Libya (LY)"; break;
            case "MA" : pais = "Morocco (MA)"; break;
            case "OM" : pais = "Oman (OM)"; break;
            case "QA" : pais = "Qatar (QA)"; break;
            case "SA" : pais = "Saudi Arabia (SA)"; break;
            case "SD" : pais = "Sudan (SD)"; break;
            case "SY" : pais = "Syria (SY)"; break;
            case "TN" : pais = "Tunisia (TN)"; break;
            case "AE" : pais = "United Arab Emirates (AE)"; break;
            case "YE" : pais = "Yemen (YE)"; break;
            case "BY" : pais = "Belarus (BY)"; break;
            case "BG" : pais = "Bulgaria (BG)"; break;
            case "CN" : pais = "China (CN)"; break;
            case "SG" : pais = "Singapore (SG)"; break;
            case "HK" : pais = "Hong Kong (HK)"; break;
            case "TW" : pais = "Taiwan (TW)"; break;
            case "HR" : pais = "Croatia (HR)"; break;
            case "CZ" : pais = "Czech Republic (CZ)"; break;
            case "DK" : pais = "Denmark (DK)"; break;
            case "NL" : pais = "Netherlands (NL)"; break;
            case "AU" : pais = "Australia (AU)"; break;
            case "IN" : pais = "India (IN)"; break;
            case "IE" : pais = "Ireland (IE)"; break;
            case "NZ" : pais = "New Zealand (NZ)"; break;
            case "PH" : pais = "Philippines (PH)"; break;
            case "ZA" : pais = "South Africa (ZA)"; break;
            case "GB" : pais = "United Kingdom (GB)"; break;
            case "US" : pais = "United States (US)"; break;
            case "EE" : pais = "Estonia (EE)"; break;
            case "FI" : pais = "Finland (FI)"; break;
            case "BE" : pais = "Belgium (BE)"; break;
            case "CA" : pais = "Canada (CA)"; break;
            case "FR" : pais = "France (FR)"; break;
            case "AT" : pais = "Austria (AT)"; break;
            case "DE" : pais = "Germany (DE)"; break;
            case "LU" : pais = "Luxembourg (LU)"; break;
            case "CY" : pais = "Cyprus (CY)"; break;
            case "GR" : pais = "Greece (GR)"; break;
            case "IL" : pais = "Israel (IL)"; break;
            case "HU" : pais = "Hungary (HU)"; break;
            case "IS" : pais = "Iceland (IS)"; break;
            case "ID" : pais = "Indonesia (ID)"; break;
            case "IT" : pais = "Italy (IT)"; break;
            case "CH" : pais = "Switzerland (CH)"; break;
            case "JP" : pais = "Japan (JP";
            case "KR" : pais = "South Korea (KR)"; break;
            case "LV" : pais = "Latvia (LV)"; break;
            case "LT" : pais = "Lithuania (LT)"; break;
            case "MK" : pais = "Macedonia (MK)"; break;
            case "MY" : pais = "Malaysia (MY)"; break;
            case "MT" : pais = "Malta (MT)"; break;
            case "NO" : pais = "Norway (NO)"; break;
            case "PL" : pais = "Poland (PL)"; break;
            case "BR" : pais = "Brazil (BR)"; break;
            case "PT" : pais = "Portugal (PT)"; break;
            case "RO" : pais = "Romania (RO)"; break;
            case "RU" : pais = "Russia (RU)"; break;
            case "BA" : pais = "Bosnia and Herzegovina (BA)"; break;
            case "ME" : pais = "Montenegro (ME)"; break;
            case "RS" : pais = "Serbia (RS)"; break;
            case "SK" : pais = "Slovakia (SK)"; break;
            case "SI" : pais = "Slovenia (SI)"; break;
            case "AR" : pais = "Argentina (AR)"; break;
            case "BO" : pais = "Bolivia (BO)"; break;
            case "CL" : pais = "Chile (CL)"; break;
            case "CO" : pais = "Colombia (CO)"; break;
            case "CR" : pais = "Costa Rica (CR)"; break;
            case "DO" : pais = "Dominican Republic (DO)"; break;
            case "EC" : pais = "Ecuador (EC)"; break;
            case "SV" : pais = "El Salvador (SV)"; break;
            case "GT" : pais = "Guatemala (GT)"; break;
            case "HN" : pais = "Honduras (HN)"; break;
            case "MX" : pais = "Mexico (MX)"; break;
            case "NI" : pais = "Nicaragua (NL)"; break;
            case "PA" : pais = "Panama (PA)"; break;
            case "PY" : pais = "Paraguay (PY)"; break;
            case "PE" : pais = "Peru (PE)"; break;
            case "PR" : pais = "Puerto Rico (PR)"; break;
            case "ES" : pais = "Spain (ES)"; break;
            case "UY" : pais = "Uruguay (UY)"; break;
            case "VE" : pais = "Venezuela (VE)"; break;
            case "SE" : pais = "Sweden (SE)"; break;
            case "TH" : pais = "Thailand (TH)"; break;
            case "TR" : pais = "Turkey (TR)"; break;
            case "UA" : pais = "Ukraine (UA)"; break;
            case "VN" : pais = "Vietnam (VN)"; break;
        }
        return pais;
    }

}