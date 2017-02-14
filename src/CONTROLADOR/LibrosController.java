/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADOR;

import MODELO.Modelo_Inventario;
import MODELO.Registro_Libros;
import java.net.URL;
import java.util.LinkedList;
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
import javafx.scene.control.TextArea;
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
public class LibrosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Registro_Libros rl;
    private LinkedList<String> reg_temp;
    private int contador=0;
    
    private String ruta=null;
    
    @FXML //  fx:id="guardar"
    private Button guardar; // Value injected by FXMLLoader   
    
    @FXML
    private ObservableList<Modelo_Inventario> datos = FXCollections.observableArrayList();
    
     @FXML //  fx:id="cota"
    private TableColumn<Modelo_Inventario, String> cota; // Value injected by FXMLLoader

    @FXML //  fx:id="descripcion"
    private TableColumn<Modelo_Inventario, Pane> descripcion; // Value injected by FXMLLoader
    
     @FXML //  fx:id="inventario"
    private TableView<Modelo_Inventario> inventario; // Value injected by FXMLLoader
    
    @FXML //  fx:id="autores"
    private TextField autores; // Value injected by FXMLLoader

    @FXML //  fx:id="cantidad"
    private TextField cantidad; // Value injected by FXMLLoader

    @FXML //  fx:id="categorias"
    private ComboBox categorias; // Value injected by FXMLLoader
    
    @FXML //  fx:id="academico"
    private TextField academico; // Value injected by FXMLLoader
    
    @FXML //  fx:id="industrial"
    private TextField industrial; // Value injected by FXMLLoader
   
    @FXML //  fx:id="edicion"
    private TextField edicion; // Value injected by FXMLLoader

    @FXML //  fx:id="editorial"
    private ComboBox editorial; // Value injected by FXMLLoader

    @FXML //  fx:id="foto"
    private ImageView foto; // Value injected by FXMLLoader

    @FXML //  fx:id="indice"
    private TextArea indice; // Value injected by FXMLLoader

    @FXML //  fx:id="isbn"
    private TextField isbn; // Value injected by FXMLLoader
   
    @FXML //  fx:id="paginas"
    private TextField paginas; // Value injected by FXMLLoader
    
     @FXML //  fx:id="n_cota"
    private TextField n_cota; // Value injected by FXMLLoader

    @FXML //  fx:id="paises"
    private ComboBox paises; // Value injected by FXMLLoader

    @FXML //  fx:id="titulo"
    private TextArea titulo; // Value injected by FXMLLoader
    
    
    @FXML //  fx:id="refrescar"
    private MenuItem refrescar; // Value injected by FXMLLoader
  
   // Handler for ImageView[fx:id="foto"] onMouseClicked
    public void CARGAR_FOTO(MouseEvent event) {
        // handle the event here
        rl = new Registro_Libros();
        ruta = rl.cargar_foto(foto, ruta);

    }
 
  
    
    // Handler for ComboBox[fx:id="editorial"] onAction
    public void VERIFICAR_EDITORIAL(ActionEvent event) {
        // handle the event here        
       new Registro_Libros().nueva_editorial(editorial);
    }
    
    
    
     // Handler for Button[fx:id="guardar"] onAction
    public void GUARDAR_LIBRO(ActionEvent event) {
        // handle the event here
       // System.out.println("EJECUTANDO");
//System.out.println("------POSICION: "+contador+" - "+reg_temp.get(contador+3));
       
      /*  if(contador < reg_temp.size()){
            
            categorias.getSelectionModel().select(42);
            titulo.setText(reg_temp.get(contador));
            autores.setText(reg_temp.get(contador+2));
            editorial.getSelectionModel().select("UNEFA");            
            edicion.setText(reg_temp.get(contador+1));            
            isbn.setText("NO");
            indice.setText(" ");
            paginas.setText("0");
            cantidad.setText("1");
            paises.getSelectionModel().select("Venezuela");
            cantidad.setText("1");
            academico.setText("");
            industrial.setText("");           
            
        }*/       
        
                Registro_Libros rl = new Registro_Libros(
                  categorias.getSelectionModel().getSelectedItem().toString(),
                  titulo.getText(),
                  autores.getText(),
                  editorial.getSelectionModel().getSelectedItem().toString(),
                  edicion.getText(),
                  isbn.getText(),
                  indice.getText(),
                  paginas.getText(),
                  paises.getSelectionModel().getSelectedItem().toString(),
                  n_cota.getText(),
                  cantidad.getText(),
                  "NÚCLEO ARAGUA - EXTENSIÓN CAGUA",
                  academico.getText(),
                  industrial.getText(),
                  ruta 
                );
                
                    n_cota.setText(rl.crear_cota());//se crea la cota y se muestra
                    rl.guardar_informacion_libro(datos, inventario,categorias, titulo, autores, editorial, edicion, isbn, indice, paginas, paises, n_cota, cantidad, academico, industrial, foto, ruta);
                   
    //contador = contador + 3; 
    
    }
    
    
    // Handler for MenuItem[fx:id="eliminar"] onAction
    public void ELIMINAR_LIBRO(ActionEvent event) {
        // handle the event here
        System.out.println("ELIMINANDO LIBRO");
        
    }

   // Handler for MenuItem[fx:id="añadir"] onAction
    public void AÑADIR_LIBRO(ActionEvent event) {
        // handle the event here
        System.out.println("AÑADIENDO LIBRO");
        new Registro_Libros().incrementar_literatura(inventario.getSelectionModel().getSelectedItem().getCota(), datos, inventario);
        
    }
    
    // Handler for MenuItem[fx:id="modificar"] onAction
    public void MODIFICAR_LIBRO(ActionEvent event) {
        // handle the event here
        System.out.println("MODIFICANDO LIBRO");
    }
    
     // Handler for MenuItem[fx:id="refrescar"] onAction
    public void REFRESCAR_INVENTARIO(ActionEvent event) {
        // handle the event here
        new Registro_Libros().cargando_libros(datos, inventario);
    }
    
    
     // Handler for ComboBox[fx:id="categorias"] onAction
    public void AUTOMATICO(ActionEvent event) {
        // handle the event here
        if(categorias.getSelectionModel().getSelectedItem().toString().startsWith("040") || categorias.getSelectionModel().getSelectedItem().toString().startsWith("041")){
            editorial.getSelectionModel().select("UNEFA");
            isbn.setText("NO");
            cantidad.setText("1");
            paises.getSelectionModel().select("Venezuela");
        }
        
        
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cota.setCellValueFactory(new PropertyValueFactory<Modelo_Inventario, String>("cota"));
        descripcion.setCellValueFactory(new PropertyValueFactory<Modelo_Inventario, Pane>("contenedor"));
        
        categorias.setVisibleRowCount(20);
        
        new Registro_Libros().pre_carga_categorias(categorias);
        new Registro_Libros().pre_carga_eitoriales(editorial);
        new Registro_Libros().pre_carga_paises(paises);
       // new Registro_Libros().cargando_libros(datos, inventario);
        
  
        
        /*reg_temp = new Registro_Libros().temporal();///solo es temporal
      
        categorias.getSelectionModel().select(40);
            titulo.setText(reg_temp.get(contador));
            autores.setText(reg_temp.get(contador+2));
            editorial.getSelectionModel().select("UNEFA");            
            edicion.setText(reg_temp.get(contador+1));            
            isbn.setText("NO");
            indice.setText(" ");
            paginas.setText("0");
            cantidad.setText("1");
            paises.getSelectionModel().select("Venezuela");
            cantidad.setText("1");
            academico.setText("");
            industrial.setText("");
            
        contador=3;
        */
        
    }    
}
