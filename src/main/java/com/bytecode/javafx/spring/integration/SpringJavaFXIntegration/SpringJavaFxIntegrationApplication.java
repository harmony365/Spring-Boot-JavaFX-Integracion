package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration;

import java.util.Locale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.comtel2000.keyboard.control.DefaultLayer;
import org.comtel2000.keyboard.control.KeyBoardPopup;
import org.comtel2000.keyboard.control.KeyBoardPopupBuilder;
import static org.comtel2000.keyboard.control.VkProperties.*;

@SpringBootApplication
public class SpringJavaFxIntegrationApplication extends Application {
	public static ConfigurableApplicationContext applicationContext;
	public static Parent rootNode;
	public static Stage stage;
	public KeyBoardPopup popup;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext = SpringApplication.run(SpringJavaFxIntegrationApplication.class);
		FXMLLoader loader  = new FXMLLoader(SpringJavaFxIntegrationApplication.class.getResource("/index.fxml"));
		loader.setControllerFactory(applicationContext::getBean);
		Scene scene = new Scene(loader.load(), 800, 800, false, SceneAntialiasing.BALANCED);
		
		primaryStage.setScene(scene);

		/*-- Moises Ramos --*/
        Locale locale = Locale.getDefault();

        //KeyBoardPopup popup = KeyBoardPopupBuilder.create().initLocale(locale).build();
        popup = KeyBoardPopupBuilder.create().initLocale(locale).build();
        popup.getKeyBoard().switchLayer(Boolean.TRUE ? DefaultLayer.NUMBLOCK : DefaultLayer.DEFAULT);
        //Double scale = new Double("123");
        //popup.setScale(Double.valueOf(scale));

        popup.registerScene(scene);
        popup.addGlobalFocusListener();
        popup.addGlobalDoubleClickEventFilter();

		/*-------- */


		primaryStage.show();
	}
}
