/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Calendario;
import MODELO.Modelo_Prestamos;
import MODELO.Registro_Prestamos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author AURORA
 */
public class PrestamosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Registro_Prestamos rp = new Registro_Prestamos();
    
     
    @FXML
    private ObservableList<Modelo_Prestamos> datos = FXCollections.observableArrayList();
    
    
    @FXML //  fx:id="cedula"
    private TextField cedula; // Value injected by FXMLLoader

    @FXML //  fx:id="cota"
    private TextField cota; // Value injected by FXMLLoader

    @FXML //  fx:id="entrega"
    private TextField entrega; // Value injected by FXMLLoader

    @FXML //  fx:id="entregar"
    private MenuItem entregar; // Value injected by FXMLLoader

    @FXML //  fx:id="foto"
    private ImageView foto; // Value injected by FXMLLoader

    @FXML //  fx:id="prestamos"
    private TableView<Modelo_Prestamos> prestamos; // Value injected by FXMLLoader

    @FXML //  fx:id="descripcion"
    private TableColumn<Modelo_Prestamos,Pane> descripcion; // Value injected by FXMLLoader

    
    @FXML //  fx:id="prestar"
    private Button prestar; // Value injected by FXMLLoader

    @FXML //  fx:id="tipo"
    private ComboBox tipo; // Value injected by FXMLLoader

    @FXML //  fx:id="usuario"
    private TextField usuario; // Value injected by FXMLLoader


    // Handler for MenuItem[fx:id="entregar"] onAction
    public void ENTREGAR(ActionEvent event) {
        // handle the event here
        rp.entregando(cedula, datos, prestamos);
    }

    // Handler for TextField[fx:id="entrega"] onMouseClicked
    public void FECHA(MouseEvent event) {
        // handle the event here
        entrega.setText(new Calendario().interfaz_grafica());
    }

    // Handler for Button[fx:id="prestar"] onAction
    public void PRESTAR_LIBRO(ActionEvent event) {
        // handle the event here
        rp.prestar(cedula, cota, entrega, tipo,prestar,usuario,datos,prestamos);
      
    }

    // Handler for TextField[fx:id="cedula"] onAction
    public void VERIFICAR(ActionEvent event) {
        // handle the event here
       rp.verificacion(cedula, usuario, prestar,datos,prestamos);
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        descripcion.setCellValueFactory(new PropertyValueFactory<Modelo_Prestamos, Pane>("panel"));
        
        tipo.getItems().clear();
        tipo.getItems().addAll("INTERNO","CIRCULANTE");
        tipo.getSelectionModel().select(0);
        
        
        
        
    }    
}
