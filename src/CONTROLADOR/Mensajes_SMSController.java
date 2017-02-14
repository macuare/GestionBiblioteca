/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Registro_Morosos;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANDY
 */
public class Mensajes_SMSController implements Initializable {
    private Registro_Morosos rm;
    
     @FXML
    private TextField IP_Servidor;
     
     @FXML
    private Button Cerrar;

    @FXML
    private TextArea Resumen;

    @FXML
    private ProgressIndicator Procesado;

    @FXML
    void PROCESAR(ActionEvent event) {
       new Thread(new Runnable() {

           @Override
           public void run() {
               rm.procesar_morosos(IP_Servidor, Procesado, Resumen);
           }
       }).start();
                
            
         
    }

    @FXML
    void ESTABLECER_SERVIDOR(MouseEvent event) {
      rm.direccion_servidor(IP_Servidor);
    }
    
     @FXML
    void CERRAR(ActionEvent event) {
         Stage st = (Stage) Cerrar.getScene().getWindow();
         st.close();
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       rm = new Registro_Morosos(); 
    }    
    
}
