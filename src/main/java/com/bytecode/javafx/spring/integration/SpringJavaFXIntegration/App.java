package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.ParametrosModel;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.repo.DigicRepository;
import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.services.MantenimientoEnLoteDER;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.comtel2000.keyboard.control.DefaultLayer;
import org.comtel2000.keyboard.control.KeyBoardPopup;
import org.comtel2000.keyboard.control.KeyBoardPopupBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@SpringBootApplication
public class App extends Application {

	public static ConfigurableApplicationContext applicationContext;
	public static Parent rootNode;
	//public static Stage;

    private static Scene scene;
    private Locale locale;

    String  Teclas="";
    public static JSONObject parametros;
    public static ParametrosModel parametrosModel = new ParametrosModel();
    public static Object ob;

    public LocalDate date = LocalDate.now();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	public KeyBoardPopup popup;

    public static String UUIDProcess,MensajeValidaDER_icon,MensajeValidaDER_error,MensajeValidaDER_Pais,MensajeValidaDER_email;

    public static Boolean MensajeValidaDER_action, Runtime_DEBUG = false;

    private final static Logger LOGGER = LogManager.getLogger(App.class.getName());

    Timeline BackGroundWonder = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() {

        @Override public void handle(ActionEvent event) {

            GetParametros();

            try {
                GenerarGuiaQR();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }

            MantenimientoEnLoteDER mantenimientoEnLoteDER = null;

            try {
                mantenimientoEnLoteDER = new MantenimientoEnLoteDER();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mantenimientoEnLoteDER.setDaemon(false);
            mantenimientoEnLoteDER.execute();


        } 
    })); 

    @Override
    public void init() {
        System.out.println("App: init");
        DigicRepository digicRepository;
        BackGroundWonder.setCycleCount(Timeline.INDEFINITE); 
        BackGroundWonder.play();        
    }

	public static void main(String[] args) {
        if (args.length > 1 || args[0].equals("DEBUG")) { //si hay más de 1 parámetro
            Runtime_DEBUG = true;
            System.out.println("La Aplicación está corriendo en Modo " + args[0]);
        }
		
		launch(args);
	}

    public void  GenerarGuiaQR() throws IOException, WriterException {
        //data that we want to store in the QR code
        String str= parametrosModel.getGuiaQR();
        //path where we want to get QR Code
        String path = "GuiaQR.png";
        //Encoding charset to be used
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        //generates QR code with Low level(L) error correction capability
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //invoking the user-defined method that creates the QR code
        generateQRcode(str, path, charset, hashMap, 200, 200);//increase or decrease height and width accodingly
        //prints if the QR code is generated
        //System.out.println("QR Code created successfully.");

    };
    public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException
    {
        //the BitMatrix class represents the 2D matrix of bits
        //MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }

    public void GetParametros(){
        // parsing file "Parametros.json"
        //Object ob;
        try {

            String command = "wmic csproduct get UUID";
            StringBuffer output = new StringBuffer();

            Process SerNumProcess = Runtime.getRuntime().exec(command);
            BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));

            String line = "";
            while ((line = sNumReader.readLine()) != null) {
                output.append(line + "\n");
            }
            String MachineID=output.toString().substring(output.indexOf("\n"), output.length()).trim();;
            //System.out.println(MachineID);

            ob = new JSONParser().parse(new FileReader("Parametros.json"));
            parametros = (JSONObject) ob;

            if((Boolean) parametros.get("AppDemo")){
                Runtime_DEBUG = true;
            }

            parametrosModel.ParametrosModel(
                (String) parametros.get("WsdlUrl"),
                (String) parametros.get("WsdlUser"),
                (String) parametros.get("WsdlPassword"),
                (String) parametros.get("QRPassDecoder"),
                (String) parametros.get("QRCODEDEMO"),
                (String) MachineID,
                (String) parametros.get("PasaporteDemo"),
                (String) parametros.get("DniNifNieTieDemo"),
                (String) parametros.get("GuiaQR"),
                Runtime_DEBUG,
                (String) date.format(formatter)
            );

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            LOGGER.log(Level.ERROR, e.getClass() + " : " + e.getMessage());
        }

        //System.out.println("Parametros: " + parametrosModel.toString() + "\n");

    }


	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext = SpringApplication.run(App.class);
		//FXMLLoader loader  = new FXMLLoader(SpringJavaFxIntegrationApplication.class.getResource("/index.fxml"));
		//loader.setControllerFactory(applicationContext::getBean);
		//Scene scene = new Scene(loader.load(), 800, 800, false, SceneAntialiasing.BALANCED);
		
		/*-- Moises Ramos --*/

        
        System.out.println("App: start");


        GetParametros();
        try {
            GenerarGuiaQR();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        String cpuUUI = parametrosModel.getKIOSKOID();


        final ObservableList<Image> icons = primaryStage.getIcons();
        icons.add(new Image("/icons/favicon-128x128x32.png"));

        locale = Locale.getDefault();
        //Locale locale = new Locale("es", "ES");
        //Locale.setDefault(locale);

        primaryStage.setTitle("Agencia Tributaria Canarias ( " + cpuUUI + " )");
        
        scene = new Scene(loadFXML("/views/primary",locale), 640, 480, false, SceneAntialiasing.BALANCED);
        //scene.getStylesheets().add("../../../resources/com/diva/styles.css");

        // Optener el tamaño de la pantalla
        //
        Screen screen = Screen.getPrimary();
        // Get current screen of the stage      
        Rectangle2D bounds = screen.getVisualBounds();

        System.out.printf("\ngetMinX: %s \ngetMinY: %s\n getWidth: %s\n getHeight: %s\n " ,bounds.getMinX(),bounds.getMinY(),bounds.getWidth(),bounds.getHeight());

        // Set current screen of the stage  
        /*     
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
         */
       
        KeyCombination Pantalla_Mantenimiento  = new KeyCodeCombination(KeyCode.ADD, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
        KeyCombination Pantalla_Mantenimiento1 = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
        KeyCombination Pantalla_Mantenimiento2 = new KeyCodeCombination(KeyCode.SUBTRACT, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
      
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            //System.out.println("Key pressed");

            String target = event.getTarget().getClass().getSimpleName();    

            if(Pantalla_Mantenimiento.match(event)) {
                System.out.println("KeyCombination.CONTROL_DOWN + : " + event.toString());
                try {
                    //App.setRoot("mantenimiento_der_v1", locale);
                    App.setRoot("/views/ViewLogin", locale);

                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.log(Level.ERROR, e.getClass() + " : " + e.getMessage());
                }

            }

            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("ESCAPE pressed Target: " + target);
                event.consume();

            } 

            /*            
            if (event.getCode() == KeyCode.SHIFT) {
                System.out.println("SHIFT pressed");
            }
            if (event.getCode() == KeyCode.CONTROL) {
                System.out.println("CONTROL pressed");
            }
            if (event.getCode() == KeyCode.ALT_GRAPH) {
                System.out.println("ALT_GRAPH pressed");
            }
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("\nENTER pressed Target: " + target);              
            }
            */
            //event.consume();
  
        });
      
        scene.addEventFilter(KeyEvent.KEY_TYPED, e -> {

            //Teclas = Teclas + e.getCharacter();            

        });

		primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        //stage.setResizable(false);

        //KeyBoardPopup popup = KeyBoardPopupBuilder.create().initLocale(locale).build();
        popup = KeyBoardPopupBuilder.create().initLocale(locale).build();
        popup.getKeyBoard().switchLayer(Boolean.TRUE ? DefaultLayer.NUMBLOCK : DefaultLayer.DEFAULT);
        //Double scale = new Double("123");
        //popup.setScale(Double.valueOf(scale));

        popup.registerScene(scene);
        popup.addGlobalFocusListener();
        popup.addGlobalDoubleClickEventFilter();

		/*-------- */

		primaryStage.setScene(scene);
		primaryStage.show();
	}


    public static void setRoot(String fxml,Locale locale) throws IOException {
        scene.setRoot(loadFXML(fxml, locale));
    }

    private static Parent loadFXML(String fxml,Locale locale) throws IOException {
		/*          
				FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		*/
	
		//Locale locale = Locale.getDefault();
		//Locale locale = new Locale("ja", "JP");
		//Locale locale = new Locale("sp", "ES");
		//Locale locale = new Locale("en", "US");
       
            ResourceBundle rb = ResourceBundle.getBundle("i18n/idioma", locale);

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( fxml + ".fxml"),rb);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            return fxmlLoader.load();

		
	}

	@Override
    public void stop() {
        System.out.println("App: stop");
        BackGroundWonder.stop();
        Platform.exit();
        System.exit(0);
    }



}
