/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Modelo_Historicos;
import MODELO.Modelo_Mensual;
import MODELO.Modelo_Vigentes;
import MODELO.Registro_Estadisticas;
import MODELO.Registro_Mensual;
import MODELO.Registro_Vigentes;
import MODELO.Registro_historicos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AURORA
 */
public class AnalisisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Registro_Mensual rm = new Registro_Mensual();
    Registro_historicos rh = new Registro_historicos();
    Registro_Vigentes rv = new Registro_Vigentes();
    Registro_Estadisticas re = new Registro_Estadisticas();
    
    
    @FXML
    private ObservableList<Modelo_Mensual> actual = FXCollections.observableArrayList();
    
    @FXML
    private ObservableList<Modelo_Historicos> total = FXCollections.observableArrayList();
    
     @FXML
    private ObservableList<Modelo_Vigentes> mdatos = FXCollections.observableArrayList();
    
     @FXML //  fx:id="cedulas"
    private TableColumn<Modelo_Mensual, String> cedulas; // Value injected by FXMLLoader

    @FXML //  fx:id="cotas"
    private TableColumn<Modelo_Mensual, String> cotas; // Value injected by FXMLLoader

    @FXML //  fx:id="estado"
    private TableColumn<Modelo_Mensual, String> estado; // Value injected by FXMLLoader
     
    @FXML //  fx:id="fentrega"
    private TableColumn<Modelo_Mensual, String> fentrega; // Value injected by FXMLLoader

    @FXML //  fx:id="fsalida"
    private TableColumn<Modelo_Mensual, String> fsalida; // Value injected by FXMLLoader

    

    @FXML //  fx:id="h_cedulas"
    private TableColumn<Modelo_Historicos, String> h_cedulas; // Value injected by FXMLLoader

    @FXML //  fx:id="h_cotas"
    private TableColumn<Modelo_Historicos, String> h_cotas; // Value injected by FXMLLoader

    @FXML //  fx:id="h_estado"
    private TableColumn<Modelo_Historicos, String> h_estado; // Value injected by FXMLLoader

    @FXML //  fx:id="h_fentrega"
    private TableColumn<Modelo_Historicos, String> h_fentrega; // Value injected by FXMLLoader

    @FXML //  fx:id="h_fsalida"
    private TableColumn<Modelo_Historicos, String> h_fsalida; // Value injected by FXMLLoader

    @FXML //  fx:id="h_refrescar"
    private MenuItem h_refrescar; // Value injected by FXMLLoader

    @FXML //  fx:id="historicos"
    private TableView<Modelo_Historicos> historicos; // Value injected by FXMLLoader

    @FXML //  fx:id="mensual"
    private TableView<Modelo_Mensual> mensual; // Value injected by FXMLLoader

    @FXML //  fx:id="refrescar"
    private MenuItem refrescar; // Value injected by FXMLLoader

    @FXML //  fx:id="tipo_analisis"
    private ComboBox tipo_analisis; // Value injected by FXMLLoader

     @FXML //  fx:id="vigentes"
    private TableView<Modelo_Vigentes> vigentes; // Value injected by FXMLLoader
    
    @FXML //  fx:id="vcedulas"
    private TableColumn<Modelo_Vigentes, String> vcedulas; // Value injected by FXMLLoader

    @FXML //  fx:id="vcotas"
    private TableColumn<Modelo_Vigentes, String> vcotas; // Value injected by FXMLLoader

    @FXML //  fx:id="vestado"
    private TableColumn<Modelo_Vigentes, String> vestado; // Value injected by FXMLLoader

    @FXML //  fx:id="vfllegada"
    private TableColumn<Modelo_Vigentes, String> vfentrega; // Value injected by FXMLLoader

    @FXML //  fx:id="vfsalida"
    private TableColumn<Modelo_Vigentes, String> vfsalida; // Value injected by FXMLLoader

    
    
    @FXML //  fx:id="grafica_barras"
    private BarChart<String,Number> barra_estadisticas; // Value injected by FXMLLoader

     @FXML
    private CategoryAxis Categoria;
      @FXML
    private NumberAxis Numero;
    
     @FXML
    private BarChart<String,Number> Barra_Otros;
    
    
    // Handler for ComboBox[fx:id="tipo_analisis"] onAction
    public void ANALISIS(ActionEvent event) {
        // handle the event here
       if(tipo_analisis.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("PRESTAMOS")){
        re.barras(barra_estadisticas);
         //re.barras(Barra_Otros);
       }
       
       if(tipo_analisis.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("LIBROS MAS PRESTADOS")){
        re.diez_libros_mas_solicitado(barra_estadisticas);
       }
    }

    // Handler for MenuItem[fx:id="h_refrescar"] onAction
    public void HREFRESCAR(ActionEvent event) {
        // handle the event here
        rh.historicos(total, historicos);
    }

    // Handler for MenuItem[fx:id="refrescar"] onAction
    public void REFRESCAR(ActionEvent event) {
        // handle the event here
        rm.libros_del_mes(actual, mensual);
    }
    
    
    // Handler for MenuItem[id="refrescar"] onAction
    public void VREFRESCAR(ActionEvent event) {
        // handle the event here
        rv.prestamos_vigentes(mdatos, vigentes);
    }
    
     @FXML
    void VBUSCAR_USUARIO(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Prestamos.fxml"));
            TextField cedula = null; //= (TextField) root.lookup("cedula");//buscando el campo cedula
            for(int i=0; i<root.getChildrenUnmodifiable().size(); i++){
              //  System.out.println("Elementos: "+root.getChildrenUnmodifiable().get(i));
                if(root.getChildrenUnmodifiable().get(i).getId()!= null && root.getChildrenUnmodifiable().get(i).getId().contains("cedula")){
                    cedula = (TextField) root.getChildrenUnmodifiable().get(i);
                }
            }
           // System.out.println("contenido: "+cedula.getText());            
            cedula.setText(vigentes.getSelectionModel().getSelectedItem().getV_cedulas());
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AnalisisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         tipo_analisis.getItems().clear();
         tipo_analisis.getItems().add("PRESTAMOS");
         tipo_analisis.getItems().add("LIBROS MAS PRESTADOS");
        
         cedulas.setCellValueFactory(new PropertyValueFactory<Modelo_Mensual, String>("cedulas"));
         cotas.setCellValueFactory(new PropertyValueFactory<Modelo_Mensual, String>("cotas"));
         estado.setCellValueFactory(new PropertyValueFactory<Modelo_Mensual, String>("estado"));
         fsalida.setCellValueFactory(new PropertyValueFactory<Modelo_Mensual, String>("fsalida"));
         fentrega.setCellValueFactory(new PropertyValueFactory<Modelo_Mensual, String>("fentrega"));
        
         h_cedulas.setCellValueFactory(new PropertyValueFactory<Modelo_Historicos, String>("h_cedulas"));
         h_cotas.setCellValueFactory(new PropertyValueFactory<Modelo_Historicos, String>("h_cotas"));
         h_estado.setCellValueFactory(new PropertyValueFactory<Modelo_Historicos, String>("h_estado"));
         h_fsalida.setCellValueFactory(new PropertyValueFactory<Modelo_Historicos, String>("h_fsalida"));
         h_fentrega.setCellValueFactory(new PropertyValueFactory<Modelo_Historicos, String>("h_fentrega"));
        
         vcedulas.setCellValueFactory(new PropertyValueFactory<Modelo_Vigentes, String>("v_cedulas"));
         vcotas.setCellValueFactory(new PropertyValueFactory<Modelo_Vigentes, String>("v_cotas"));
         vestado.setCellValueFactory(new PropertyValueFactory<Modelo_Vigentes, String>("v_estado"));
         vfsalida.setCellValueFactory(new PropertyValueFactory<Modelo_Vigentes, String>("v_fsalida"));
         vfentrega.setCellValueFactory(new PropertyValueFactory<Modelo_Vigentes, String>("v_fentrega"));
        
        //barra_estadisticas = new BarChart<String, Number>(Categoria, Numero);
         
         rm.libros_del_mes(actual, mensual);
         rh.historicos(total, historicos);
         rv.prestamos_vigentes(mdatos, vigentes);         
        // re.barras(barra_estadisticas);
    }    
}
