package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.controllers;

import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ViewLoginController implements Initializable {

    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnLogin;

    @FXML
    private VBox containerLeft;

    @FXML
    private VBox containerRight;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    private void eventKey(KeyEvent event){
        
        Object evt = event.getSource();
        
        if(evt.equals(txtUser)){
            
            if(event.getCharacter().equals(" ")){
                event.consume();
            }
        
        }else if(evt.equals(txtPassword)){

            if(event.getCharacter().equals(" ")){
                event.consume();
            }            
        
        }

    }
    
    @FXML
    private void eventAction(ActionEvent event) throws IOException{
        
        Object evt = event.getSource();

        if(evt.equals(btnLogin)){
                                
            if(!txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty()){
            
                String user = txtUser.getText();
                String pass = txtPassword.getText();
                
                //int state = model.login(user, pass);
                int state = -1;

                if (user.equals("Admin")  && pass.equals("Admin2022")){state = 1;}
                
                if(state!=-1){

                    if(state == 1){

                        //JOptionPane.showMessageDialog(null, "Datos correctos puede ingresar al sistema");
   
                                               
                        Locale locale = Locale.getDefault();
                        App.setRoot("/views/Mantenimiento_DER_v1",locale);

                    }else{
                        //JOptionPane.showMessageDialog(null, "Error al iniciar sesión datos de acceso incorrectos", 
                        //                                    "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);   
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setTitle("ADVERTENCIA");
                        alert.setContentText("Error al iniciar sesión datos de acceso incorrectos");
                        alert.showAndWait();        
                                                            

                    }

                }else{            
                    //JOptionPane.showMessageDialog(null, "Error al iniciar sesión datos de acceso incorrectos", 
                    //                                   "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);                                
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("ADVERTENCIA");
                    alert.setContentText("Error al iniciar sesión datos de acceso incorrectos");
                    alert.showAndWait();     
                }                

            
            }else{            
                    //JOptionPane.showMessageDialog(null, "Error al iniciar sesión datos de acceso incorrectos", 
                    //                                    "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);     
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("ADVERTENCIA");
                    alert.setContentText("Error al iniciar sesión datos de acceso incorrectos");
                    alert.showAndWait();                                
            }
        
        }

        if(evt.equals(btnCancelar)){
            Locale locale = Locale.getDefault();
            App.setRoot("/views/primary",locale);
        }
    
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        if(App.parametrosModel.getAppDemo()){
            txtUser.setText("Admin");
            txtPassword.setText("Admin2022");
            //btnLogin.setDefaultButton(true);
            btnLogin.requestFocus();
        }
        
    }
    

}
