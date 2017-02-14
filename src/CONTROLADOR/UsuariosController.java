/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Modelo_Usuarios;
import MODELO.Registro_Usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AURORA
 */
public class UsuariosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML //  fx:id="apellidos"
    private TextField apellidos; // Value injected by FXMLLoader

    @FXML //  fx:id="cedula"
    private TextField cedula; // Value injected by FXMLLoader

    @FXML //  fx:id="correo"
    private TextField correo; // Value injected by FXMLLoader

    @FXML //  fx:id="direccion"
    private TextArea direccion; // Value injected by FXMLLoader

    @FXML //  fx:id="guardar"
    private Button guardar; // Value injected by FXMLLoader

    @FXML //  fx:id="institucion"
    private ComboBox institucion; // Value injected by FXMLLoader

    @FXML //  fx:id="carrera"
    private ComboBox carrera; // Value injected by FXMLLoader
    
    @FXML //  fx:id="nombres"
    private TextField nombres; // Value injected by FXMLLoader

    @FXML //  fx:id="telf_cel"
    private TextField telf_cel; // Value injected by FXMLLoader

    @FXML //  fx:id="telf_hab"
    private TextField telf_hab; // Value injected by FXMLLoader

    @FXML //  fx:id="t_estado"
    private TableColumn<Modelo_Usuarios, String> t_estado; // Value injected by FXMLLoader
    
    @FXML //  fx:id="t_apellidos"
    private TableColumn<Modelo_Usuarios, String> t_apellidos; // Value injected by FXMLLoader

    @FXML //  fx:id="t_cedula"
    private TableColumn<Modelo_Usuarios, String> t_cedula; // Value injected by FXMLLoader

    @FXML //  fx:id="t_correos"
    private TableColumn<Modelo_Usuarios, String> t_correos; // Value injected by FXMLLoader

    @FXML //  fx:id="t_nombres"
    private TableColumn<Modelo_Usuarios, String> t_nombres; // Value injected by FXMLLoader

    @FXML //  fx:id="t_telefonos"
    private TableColumn<Modelo_Usuarios, String> t_telefonos; // Value injected by FXMLLoader
    
    @FXML //  fx:id="t_institucion"
    private TableColumn<Modelo_Usuarios, String> t_institucion; // Value injected by FXMLLoader
    
    @FXML //  fx:id="t_carrera"
    private TableColumn<Modelo_Usuarios, String> t_carrera; // Value injected by FXMLLoader
    
    ObservableList<Modelo_Usuarios> datos = FXCollections.observableArrayList();
   
    @FXML //  fx:id="todos"
    private TableView<Modelo_Usuarios> todos; // Value injected by FXMLLoader


    // Handler for Button[fx:id="guardar"] onAction
    public void GUARDAR(ActionEvent event) {
        // handle the event here
        Registro_Usuarios mu = new Registro_Usuarios(cedula.getText(),
                                                 nombres.getText(),
                                                 apellidos.getText(),
                                                 telf_cel.getText(),
                                                 telf_hab.getText(),
                                                 correo.getText(),
                                                 direccion.getText(),
                                                 institucion.getSelectionModel().getSelectedItem().toString(),
                                                 "ACTIVO",
                                                 carrera.getSelectionModel().getSelectedItem().toString());
        mu.guardar_registros_usuario();
    }

    // Handler for ComboBox[fx:id="carrera"] onAction
    public void CARRERA(ActionEvent event) {
        // handle the event here
        new Registro_Usuarios().nueva_carrera(carrera);
    }
    
    // Handler for ComboBox[fx:id="institucion"] onAction
    public void INSTITUCION(ActionEvent event) {
        // handle the event here
        new Registro_Usuarios().nueva_institucion(institucion);
    }
    
    // Handler for MenuItem[javafx.scene.control.MenuItem@da2034] onAction
    public void ACTUALIZAR(ActionEvent event) {
        // handle the event here
        new Registro_Usuarios().todos_usuarios(datos, todos);        
    }
    
    // Handler for MenuItem[javafx.scene.control.MenuItem@127cf17] onAction
    public void DESACTIVAR_USUARIO(ActionEvent event) {
        // handle the event here
        new Registro_Usuarios().actualizar_estado_usuario(t_cedula.getCellData(todos.getSelectionModel().getSelectedIndex()).toString(),"INACTIVO");        
        new Registro_Usuarios().todos_usuarios(datos, todos);
    }
    
      // Handler for MenuItem[javafx.scene.control.MenuItem@37140d] onAction
    public void ACTIVAR_USUARIO(ActionEvent event) {
        // handle the event here
        new Registro_Usuarios().actualizar_estado_usuario(t_cedula.getCellData(todos.getSelectionModel().getSelectedIndex()).toString(),"ACTIVO");
        new Registro_Usuarios().todos_usuarios(datos, todos);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        t_estado.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("estado"));
        t_cedula.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("cedula"));
        t_nombres.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("nombres"));
        t_apellidos.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("apellidos"));
        t_telefonos.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("telefonos"));
        t_correos.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("correos"));
        t_carrera.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("carrera"));
        t_institucion.setCellValueFactory(new PropertyValueFactory<Modelo_Usuarios, String>("institucion"));
        
        new Registro_Usuarios().pre_carga_carreras(carrera);
        new Registro_Usuarios().pre_carga_instituciones(institucion);
        new Registro_Usuarios().todos_usuarios(datos, todos);
        
    }    
}
