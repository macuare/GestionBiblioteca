/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Registro_Nuevo_Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AN
 */
public class Nuevo_UsuarioController implements Initializable {

    private Registro_Nuevo_Usuario rnu = new Registro_Nuevo_Usuario();
    
    /**
     * Initializes the controller class.
     */
     @FXML //  fx:id="categoria"
    private ComboBox categoria; // Value injected by FXMLLoader

    @FXML //  fx:id="clave"
    private PasswordField clave; // Value injected by FXMLLoader

    @FXML //  fx:id="eliminar"
    private Button eliminar; // Value injected by FXMLLoader

    @FXML //  fx:id="nuevo"
    private Button nuevo; // Value injected by FXMLLoader

    @FXML //  fx:id="repetir"
    private PasswordField repetir; // Value injected by FXMLLoader

    @FXML //  fx:id="usuario"
    private TextField usuario; // Value injected by FXMLLoader

     @FXML //  fx:id="cedula"
    private TextField cedula; // Value injected by FXMLLoader

    
    
    // Handler for Button[fx:id="eliminar"] onAction
    public void ELIMINAR_USUARIO(ActionEvent event) {
        // handle the event here        
        rnu.eliminar_usuario(cedula.getText());
        cedula.setText("");
        
    }

    // Handler for Button[fx:id="nuevo"] onAction
    public void NUEVO_USUARIO(ActionEvent event) {
        // handle the event here
        rnu.crear_nuevo_usuario(cedula.getText(), usuario.getText(), clave.getText() , repetir.getText(), categoria.getSelectionModel().getSelectedItem().toString());
        cedula.setText("");
        usuario.setText("");
        clave.setText("");
        repetir.setText("");
    }

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        categoria.getSelectionModel().selectFirst();
    }    
}
