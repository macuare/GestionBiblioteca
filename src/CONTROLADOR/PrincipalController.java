/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Registro_Pdf;
import MODELO.Registro_Reportes;
import MODELO.Conexion_bd;
import MODELO.Registro_Morosos;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author AURORA
 */
public class PrincipalController implements Initializable {
    
    
    /**
     * Initializes the controller class.
     */
     @FXML //  fx:id="base"
    private AnchorPane base; // Value injected by FXMLLoader
    
    @FXML //  fx:id="sesion"
    private MenuItem sesion; // Value injected by FXMLLoader
    
    @FXML //  fx:id="analisis"
    private MenuItem analisis; // Value injected by FXMLLoader

    @FXML //  fx:id="cerrar"
    private MenuItem cerrar; // Value injected by FXMLLoader

    @FXML //  fx:id="libros"
    private MenuItem libros; // Value injected by FXMLLoader

    @FXML //  fx:id="prestamos"
    private MenuItem prestamos; // Value injected by FXMLLoader

    @FXML //  fx:id="usuarios"
    private MenuItem usuarios; // Value injected by FXMLLoader
    
    
    @FXML //  fx:id="menu_registros"
    private Menu menu_registros; // Value injected by FXMLLoader

    @FXML //  fx:id="menu_reportes"
    private Menu menu_reportes; // Value injected by FXMLLoader
    
    
    @FXML //  fx:id="panel"
    private Pane panel; // Value injected by FXMLLoader
    
    
    
     @FXML //  fx:id="estrella1"
    private ImageView estrella1; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella10"
    private ImageView estrella10; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella2"
    private ImageView estrella2; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella3"
    private ImageView estrella3; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella4"
    private ImageView estrella4; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella5"
    private ImageView estrella5; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella6"
    private ImageView estrella6; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella7"
    private ImageView estrella7; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella8"
    private ImageView estrella8; // Value injected by FXMLLoader

    @FXML //  fx:id="estrella9"
    private ImageView estrella9; // Value injected by FXMLLoader

    
     // Handler for MenuItem[fx:id="sesion"] onAction
    public void CREAR_NUEVO_USUARIO(ActionEvent event) {
        // handle the event here
      //  Hilo h = new Hilo();
      //  h.setInformacion("ESTO ES UNA PRUEBA DE CONEXION");
      //  h.ejecutar_segundo_plano();
        
       try {
            // handle the event here            
                Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Nuevo_Usuario.fxml")); 
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                stage.setScene(scene);
                stage.show();
                
                
                
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    // Handler for MenuItem[fx:id="libros"] onAction
    public void VENTANA_LIBROS(ActionEvent event) {
        try {
            // handle the event here
            
                Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Libros.fxml")); 
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                
                stage.setScene(scene);
                stage.show();
                
                
                
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

     // Handler for MenuItem[fx:id="analisis"] onAction
    public void VENTANA_ANALISIS(ActionEvent event) {
        // handle the event here
         try {
            // handle the event here
            
                Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Analisis.fxml")); 
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Handler for MenuItem[fx:id="prestamos"] onAction
    public void VENTANA_PRESTAMOS(ActionEvent event) {
        // handle the event here
         try {
            // handle the event here
            
                Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Prestamos.fxml")); 
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
                
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Handler for MenuItem[fx:id="usuarios"] onAction
    public void VENTANA_USUARIOS(ActionEvent event) {
        // handle the event here
         try {
            // handle the event here            
                Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Usuarios.fxml")); 
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();                
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ACERCADE(ActionEvent event) {
        // handle the event here
      try {
            // handle the event here
            
                Parent root = FXMLLoader.load(getClass().getResource("/VISTA/AcercaDe.fxml")); 
                final Stage stage = new Stage(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(null);
                scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            //System.out.println("CERRANDO VENTANA");
                           stage.close();
                        }
                });
                
                stage.setScene(scene);
                stage.showAndWait();
               
                root = FXMLLoader.load(getClass().getResource("/VISTA/Mensajes_SMS.fxml"));                                 
                scene = new Scene(root);
                scene.setFill(null);
                stage.setScene(scene);
                stage.show();
      
                
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
        
    }
    
     // Handler for MenuItem[fx:id="etiquetas"] onAction
    public void CREAR_ETIQUETAS_LIBROS(ActionEvent event) {
              
          new Registro_Pdf().crear_etiquetas_cotas("LIBROS");
        
    }
    
      // Handler for MenuItem[fx:id="etiquetas"] onAction
    public void CREAR_ETIQUETAS_TESIS(ActionEvent event) {
              
          new Registro_Pdf().crear_etiquetas_cotas("TESIS");
        
    }
    
    
    
    public void CREAR_LISTADO_LIBROS(ActionEvent event){
    
        new Registro_Reportes().lista_libros_ordenados(new Conexion_bd().getConexion());
        
    }
    
    
    public void CREAR_LISTADO_TESIS(ActionEvent event){    
        
        new Registro_Reportes().lista_tesis_pasantias_ordenados(new Conexion_bd().getConexion());
    }
    
     public void CERRAR_APLICACION(ActionEvent event) {
        // handle the event here
         javafx.application.Platform.exit();
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     //   menu_registros.setVisible(false);
     //   menu_reportes.setVisible(false);
        
        
        LinkedList<Node> nodos = new LinkedList<Node>();
        nodos.add(estrella9);
        nodos.add(estrella5);
        nodos.add(estrella2);
        nodos.add(estrella3);
        nodos.add(estrella10);        
        nodos.add(estrella7);
        nodos.add(estrella6);
        nodos.add(estrella1);
        nodos.add(estrella8);
        nodos.add(estrella4);
        
       for(int i=0; i<10; i++){ 
        FadeTransition ft = new FadeTransition(Duration.millis(1000*i),nodos.get(i));        
        ft.setFromValue(1.0f);
        ft.setToValue(0.0f);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true); 
        ft.play();
       }
        
    }    
}
