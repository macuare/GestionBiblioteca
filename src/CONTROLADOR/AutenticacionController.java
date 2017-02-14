/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Conexion_bd;
import MODELO.Registro_Autenticacion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author AURORA
 */
public class AutenticacionController implements Initializable {
    private Registro_Autenticacion ra = new Registro_Autenticacion();
    
    
    /**
     * Initializes the controller class.
     */
    @FXML //  fx:id="clave"
    private PasswordField clave; // Value injected by FXMLLoader

    @FXML //  fx:id="ingresar"
    private Button ingresar; // Value injected by FXMLLoader

    @FXML //  fx:id="usuario"
    private TextField cedula; // Value injected by FXMLLoader

    
    
    
    
     // Handler for Button[fx:id="ingresar"] onAction
    public void INGRESAR(ActionEvent event) {
        // handle the event here
        
        Object informacion[] = ra.analisis(cedula.getText(), clave);
        
        informacion[0] = "true"; // solo pruebas
        
      if(String.valueOf(informacion[0]).equalsIgnoreCase("true")){//solo si esta autorizado
          //forma de cerrar la ventana desde el controlador.....se hace referencia desde un nodo contenido en el fxml
          Stage estage = (Stage) ingresar.getScene().getWindow();                        
          estage.close();
          
          try {
            // handle the event here   
                final Stage stage = new Stage(StageStyle.TRANSPARENT);
                Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Principal.fxml")); 
                Scene scene = new Scene(root);
             
                scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
                scene.setFill(null);
                Image imagen = new Image(getClass().getResourceAsStream("/IMAGENES/escudo.PNG"));
                stage.getIcons().add(imagen);
                stage.setTitle("SISTEMA DE BIBLIOTECA");
                stage.setScene(scene);
                stage.setResizable(false);
                
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        Platform.exit();
                    }
                });
                
                        stage.show();
                        ra.permisos(root, informacion[3].toString());
                        
/*                        
                //accediendo a los elementos contenidos en el fxml con exito        
               for(int i=0; i<root.getChildrenUnmodifiable().size(); i++){
                   System.out.println("elementos: "+root.getChildrenUnmodifiable().get(i).toString());
                   if(root.getChildrenUnmodifiable().get(i)instanceof MenuBar){
                       System.out.println("elemento hallado");
                       MenuBar x = (MenuBar) root.getChildrenUnmodifiable().get(i);
                       for(int c=0; c<x.getMenus().size(); c++){
                         System.out.println("hijos: "+x.getMenus().get(c).getId());
                         x.getMenus().get(c).setDisable(false);
                       }
                   }
               }
  */                     
                   //transicion de la ventana principal
                        FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), root);
                        fadeTransition.setFromValue(0.0f);
                        fadeTransition.setToValue(1.0f);
                        fadeTransition.play();                                                
                        
        } catch (IOException ex) {
            Logger.getLogger(AutenticacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }else{
        
         
        
      }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Conexion_bd cbd = new Conexion_bd();        
        Conexion_bd.setDireccion(cbd.direccion_ip_red());
       
        
    }    
}
