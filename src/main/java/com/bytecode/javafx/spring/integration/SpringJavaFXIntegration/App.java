package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.ParametrosModel;
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
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.comtel2000.keyboard.control.DefaultLayer;
import org.comtel2000.keyboard.control.KeyBoardPopup;
import org.comtel2000.keyboard.control.KeyBoardPopupBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;


@SpringBootApplication
public class App extends Application {

	public static ConfigurableApplicationContext applicationContext;
	public static Parent rootNode;
	//public static Stage;

    private static Scene scene;
    String  Teclas="";
    public static JSONObject parametros;
    public static ParametrosModel parametrosModel = new ParametrosModel();
    public static Object ob;

    public LocalDate date = LocalDate.now();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	public KeyBoardPopup popup;

    public static String UUIDProcess;

    
    Timeline BackGroundWonder = new Timeline(new KeyFrame(Duration.seconds(30), new EventHandler<ActionEvent>() { 
        @Override public void handle(ActionEvent event) { 

            GetParametros();
            //System.out.println("this is called every 30 seconds on UI thread");

            /*
             *  llamada a la funcion de envio de datos al wsdl
             */
        } 
    })); 

    @Override
    public void init() {
        System.out.println("App: init");

        BackGroundWonder.setCycleCount(Timeline.INDEFINITE); 
        BackGroundWonder.play();        
    }

	public static void main(String[] args) {
		
		launch(args);
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
            parametrosModel.ParametrosModel(
                (String) parametros.get("WsdlUrl"),
                (String) parametros.get("WsdlUser"),
                (String) parametros.get("WsdlPassword"),
                (String) parametros.get("QRPassDecoder"),
                (String) parametros.get("QRCODEDEMO"),
                (String) MachineID,
                (String) parametros.get("PasaporteDemo"),
                (String) parametros.get("DniNifNieTieDemo"),
                (Boolean) parametros.get("AppDemo"),
                (String) date.format(formatter)
            );

        } catch (IOException | ParseException e) {

            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getLocalizedMessage());
            alert.showAndWait();    
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

        String cpuUUI = parametrosModel.getKIOSKOID();


        final ObservableList<Image> icons = primaryStage.getIcons();
        icons.add(new Image("/icons/favicon-128x128x32.png"));

        Locale locale = Locale.getDefault();
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
                    App.setRoot("views/ViewLogin", locale);

                } catch (IOException e) {

                    e.printStackTrace();

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(e.getLocalizedMessage());
                    alert.showAndWait();
                }

            }

            if (event.getCode() == KeyCode.ESCAPE) {
                System.out.println("ESCAPE pressed Target: " + target);
                //event.consume();
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
    }



}
