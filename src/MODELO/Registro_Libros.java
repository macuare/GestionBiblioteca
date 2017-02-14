/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 *
 * @author AURORA
 */
public class Registro_Libros {

    private String categoria,titulo,autores,editorial,edicion,isbn, 
                   indice,paginas,pais,cota,cantidad,nucleo_extension, academico, industrial,
                   fecha_ingresado, foto;
    
    
    
    public Registro_Libros() {
    }

    public Registro_Libros(String categoria, String titulo, String autores, String editorial, String edicion, String isbn, String indice, String paginas, String pais, String cota, String cantidad, String nucleo_extension, String academico, String industrial, String foto) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.edicion = edicion;
        this.isbn = isbn;
        this.indice = indice;
        this.paginas = paginas;
        this.pais = pais;
        this.cota = cota;
        this.cantidad = cantidad;
        this.nucleo_extension = nucleo_extension;
        this.academico = academico;
        this.industrial = industrial;
        this.foto = foto;
    }

    
    
    
    
    
    
    
    public String crear_cota(){
        this.analisis_deway();System.out.println("ETAPA1");
        this.analisis_cutter();System.out.println("ETAPA2");
        this.cargar_cotas_guardadas();System.out.println("ETAPA3");
        this.analisis_ejemplares();System.out.println("ETAPA4");
        
        
        System.out.println("LA COTA ES: "+cota);
      
        return cota;
    }
    
    
    private LinkedList<String> cargar_categorias(){
         LinkedList<String> dewey = new LinkedList();
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
        
        try {             
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.dewey order by codigos;");
              while(resultado.next()){
                  dewey.add(resultado.getString("codigos")+" - "+resultado.getString("categorias"));                               
              }
              
          sentencia.close();    
          resultado.close();    
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        return dewey;
    
    }
    
    private LinkedList<String> cargar_editoriales(){
         LinkedList<String> editoriales = new LinkedList();
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
        
        try {             
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros GROUP BY editorial ORDER BY editorial;");
              while(resultado.next()){
                  editoriales.add(resultado.getString("editorial"));                               
              }
              
             sentencia.close();
             resultado.close();
             
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        
        editoriales.add("NO EXISTE");
        
        return editoriales;
    
    }
    
    public void pre_carga_categorias(ComboBox categorias){
        LinkedList<String> registros = this.cargar_categorias();
        
        ObservableList<Object> elementos = FXCollections.observableArrayList(registros.toArray());        
        categorias.setItems(elementos);//pre cargando las categorias
        categorias.getSelectionModel().selectFirst();
        
        this.seleccion_categorias(registros);
        
    }
    
     public void pre_carga_eitoriales(ComboBox editoriales){
        ObservableList<Object> elementos = FXCollections.observableArrayList(this.cargar_editoriales().toArray());
        editoriales.setItems(elementos);//pre cargando las categorias
        editoriales.getSelectionModel().selectFirst();
    }
     
    public void pre_carga_paises(ComboBox paises){
        
        ObservableList<String> elementos = FXCollections.observableArrayList("Afganistan", "Africa del Sur", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda", "Antillas Holandesas", "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarusia", "Belgica", "Belice", "Benin", "Bermudas", "Bolivia", "Bosnia", "Botswana", "Brasil", "Brunei Darussulam", "Bulgaria", "Burkina Faso", "Burundi", "Butan", "Camboya", "Camerun", "Canada", "Cape Verde", "Chad", "Chile", "China", "Chipre", "Colombia", "Comoros", "Congo", "Corea del Norte", "Corea del Sur", "Costa de Marfíl", "Costa Rica", "Croasia", "Cuba", "Dinamarca", "Djibouti", "Dominica", "Ecuador", "Egipto", "El Salvador", "Emiratos Arabes Unidos", "Eritrea", "Eslovenia", "España", "Estados Unidos", "Estonia", "Etiopia", "Fiji", "Filipinas", "Finlandia", "Francia", "Gabon", "Gambia", "Georgia", "Ghana", "Granada", "Grecia", "Groenlandia", "Guadalupe", "Guam", "Guatemala", "Guayana Francesa", "Guerney", "Guinea", "Guinea-Bissau", "Guinea Equatorial", "Guyana", "Haiti", "Holanda", "Honduras", "Hong Kong", "Hungria", "India", "Indonesia", "Irak", "Iran", "Irlanda", "Islandia", "Islas Caiman", "Islas Faroe", "Islas Malvinas", "Islas Marshall", "Islas Solomon", "Islas Virgenes Britanicas", "Islas Virgenes (U.S.)", "Israel", "Italia", "Jamaica", "Japon", "Jersey", "JJordania", "Kazakhstan", "Kenia", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lesotho", "Libano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo", "Macao", "Macedonia", "Madagascar", "Malasia", "Malawi", "Maldivas", "Mali", "Malta", "Marruecos", "Martinica", "Mauricio", "Mauritania", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Mozambique", "Myanmar (Burma)", "Namibia", "Nepal", "Nicaragua", "Niger", "Nigeria", "Noruega", "Nueva Caledonia", "Nueva Zealandia", "Oman", "Pakistan", "Palestina", "Panama", "Papua Nueva Guinea", "Paraguay", "Peru", "Polinesia Francesa", "Polonia", "Portugal", "Puerto Rico", "Qatar", "Reino Unido", "Republica Centroafricana", "Republica Checa", "Republica Democratica del Congo", "Republica Dominicana", "Republica Eslovaca", "Reunion", "Ruanda", "Rumania", "Rusia", "Sahara", "Samoa", "San Cristobal-Nevis (St. Kitts)", "San Marino", "San Vincente y las Granadinas", "Santa Helena", "Santa Lucia", "Santa Sede (Vaticano)", "Sao Tome & Principe", "Senegal", "Seychelles", "Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka (Ceilan)", "Sudan", "Suecia", "Suiza", "Sur Africa", "Surinam", "Swaziland", "Tailandia", "Taiwan", "Tajikistan", "Tanzania", "Timor Oriental", "Togo", "Tokelau", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkmenistan", "Turquia", "Ucrania", "Uganda", "Union Europea", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe");
        paises.setItems(elementos);//pre cargando las categorias
        paises.getSelectionModel().selectFirst();
        
   } 
     
    
     public void nueva_editorial(ComboBox editoriales){
        
      if(editoriales.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("NO EXISTE")){
        String nuevo = "";
        ObservableList<Object> elementos = editoriales.getItems();        
        
        nuevo = new Mensajes().texto_entrada("ESCRIBA LA EDITORIAL QUE DESEA AGREGAR...!!!");
        
        if(nuevo.equalsIgnoreCase("nada") || nuevo.isEmpty()){//no se agrego nada
            editoriales.setItems(elementos);//todo queda sin modificacion
        }else{//se agrego algo nuevo
           
            elementos.add(elementos.size()-1, nuevo.toUpperCase());//se agrega el nuevo elemento
            editoriales.setItems(elementos);//cargando las categorias
        }
        
      }          
        
    }
    
    public void analisis_deway(){
        cota = categoria.substring(0, 3);    
    }    
    
    private LinkedList<String> cargar_tabla_cutter(){
      LinkedList<String> tabla = new LinkedList();
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
        
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.cutter order by textos;");
              while(resultado.next()){
                  tabla.add(resultado.getString("textos")); tabla.add(String.valueOf(resultado.getInt("numeros")));              
              }
              
              System.out.println("TABLA CUTTER CARGADA: "+(tabla.size()/2));
              
              sentencia.close();
              resultado.close();
              
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        return tabla;
    }
    
    public void analisis_cutter(){
        //se supone que el cutter se cargo desde la base de datos ya ordenado por orden alfabetico
        LinkedList<String> cutter = this.cargar_tabla_cutter();//0=letras, 1=numero
        String coincidencia="";
        String texto = autores;//analizar cuando el libro no tenga autores, queda el titulo y luego la editorial
        
        texto = this.revision_acentos(texto).toLowerCase();
        
        for(int i=0; i<cutter.size(); i=i+2){//recorriendo la tabla del cutter        
            if(texto.toLowerCase().startsWith(cutter.get(i).toLowerCase())){//verificando coincidencia con las primeras letras
                coincidencia = "-"+texto.substring(0, 1).toUpperCase()+cutter.get(i+1);//guardando
                //System.out.println("Coincidencia: "+coincidencia);
            }        
        }//fin recorrido de la tabla cutter
      cota = cota.concat(coincidencia);
    }
    
    private LinkedList<String> cargar_cotas_guardadas(){
        LinkedList<String> almacenadas = new LinkedList();
        //cargar las cotas almacenadas en la base de datos con coincidencia de deway y cutter. para poder colocar el numero de ejemplar
        //deben estar ordenadas
        
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
        
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT cota FROM biblioteca.libros WHERE cota REGEXP '"+cota.split("-")[0] +"' ORDER BY cota;"); //solo se toman las cotas que inicien con la misma categoria para reducir la busqueda de comparacion
              while(resultado.next()){
                  almacenadas.add(resultado.getString("cota")); 
              }
              
              sentencia.close();
              resultado.close();
              
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        return almacenadas;
    }
    
    public void analisis_ejemplares(){
    
     if(cota.split("-").length>=2 && cota.split("-").length<3){   //verificando que a esta altura la cota tenga al menos (dewey-cutter)
        LinkedList<String> guardadas = this.cargar_cotas_guardadas();
         int numeracion = 1;
         String ejemplar = "ej"+numeracion;
         String supuesto = cota+"-"+ejemplar;
         
         boolean coincidencia = true;
         
         //System.out.println("ejemplares 1: "+supuesto);
         while(coincidencia){//revision infinita
             
                    if(guardadas.isEmpty()){//si el vector esta vacio debe guardar el registro porque es el primero
                        cota = supuesto;
                        break;//se rompe el ciclo y se deja preestablecida la cota supuesta como cota final

                    }else{//pero si no tiene que hacer el analisis de comparacion

                           for(int i=0; i<guardadas.size(); i++){//recorriendo las cotas guardadas

                               if(supuesto.equalsIgnoreCase(guardadas.get(i))){   //revisando si la cota que se quiere colocar ya existe              
                                   coincidencia = true; //System.out.println("Cota repetida. Incrementando ej");
                                   numeracion = numeracion + 1;//si la cota existe con el ejemplar se aumenta una unidad sucesivamente
                                   ejemplar = "ej"+numeracion;
                                   supuesto = cota+"-"+ejemplar; 
                                   break;//se rompe el ciclo for
                               }else{
                                   coincidencia = false;                
                               }        

                           }//fin recorriendo las cotas guardadas


       //System.out.println("coincidencia= "+coincidencia+" ejemplares 2 "+cota);
                       //si despues de revisar todas las cotas guardadas no hubo problema de duplicidad se almacena la cota definitiva
                       if(coincidencia == false){
                          cota = supuesto;
                          break; //se rompe el while
                       }
                   }
                    
                      // System.out.println("revisando coincidencias: "+coincidencia+"    buscando: "+supuesto);
          }//fin revision infinita
     }
    
    }
    
    
    
    
    private boolean nuevo_libro(){
       boolean procesado = false;
          
        if(cota.split("-").length>=3 && cota.split("-").length<4){//analizando que la cota este correcta al menos en parametros (dewey-cutter-ejemplar)
           
            String x = new Mensajes().confirmacion("DESEA GUARDAR LA INFORMACIÓN DE ESTE LIBRO...!!!"); 
            if(x!=null && (x.equalsIgnoreCase("nada") || x.equalsIgnoreCase("cancelar"))){}else{
            Connection con = new Conexion_bd().getConexion();      
            Statement sentencia = null;


             try {

                   sentencia = con.createStatement();
                   sentencia.execute("INSERT INTO biblioteca.libros VALUES("
                                     +"'"+cota+"',"
                                     +"'"+titulo.toUpperCase()+"',"
                                     +"'"+autores.toUpperCase()+"',"
                                     +"'"+academico.toUpperCase()+"',"
                                     +"'"+industrial.toUpperCase()+"',"
                                     +"'"+editorial.toUpperCase()+"',"
                                     +""+edicion.toUpperCase()+","
                                     +"'"+isbn.toUpperCase()+"',"
                                     +"'"+indice+"',"
                                     +"'"+paginas.toUpperCase()+"',"
                                     +"'"+pais.toUpperCase()+"',"
                                     +""+cantidad.toUpperCase()+","
                                     +"'"+nucleo_extension.toUpperCase()+"', "
                                     +"DATE_FORMAT(NOW(),'%d/%m/%Y - %r')"
                                     + ");");

                   this.guardar_imagen_BD(foto);

                   System.out.println("EL LIBRO HA SIDO REGISTRADO CON EXITO...!!!");

                   sentencia.close();
                   procesado = true;
             
             } catch (SQLException ex) {
                 new Mensajes().error("ERROR: \n"+ex.getMessage());
                 Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
                 try {
                     con.close();
                 } catch (SQLException ex) {
                     new Mensajes().error("ERROR: \n"+ex.getMessage());
                     Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
                 }

             }
        
        }
            
            
            
        }else{
            
            new Mensajes().error("ERROR: \n La cota esta mal construida debido a que los parametros colocados en el formulario estan incorrectos o faltan registros\nPor lo tanto no se podran procesar los datos ingresados...!!!");
        }
        
        return procesado;
     
    }
    
    
    
    public void guardar_informacion_libro(ObservableList<Modelo_Inventario> datos, TableView<Modelo_Inventario> inventario, ComboBox ca, TextArea ti, TextField au, ComboBox ed, TextField cion, TextField is, TextArea ind, TextField pa, ComboBox pis,TextField cot,TextField cant, TextField aca, TextField indus,ImageView fot, String rut){
    
        if(this.nuevo_libro()){//si la informacion fue procesada se procede a refrescar los libros guardados
            new Mensajes().notificacion("LA INFORMACIÓN DEL LIBRO FUE GUARDADA CON EXITO...!!!\nACTUALIZANDO TABLA DE INVENTARIO");
           // this.cargando_libros(datos, inventario);
            this.limpiar(ca, ti, au, ed, cion, is, ind, pa, pis,cot,cant,aca,indus,fot,rut);
        }else{
            System.out.println("INFORMACIÓN NO PROCESADA...!!!");
        }    
    
    }
    
    public String cargar_foto(ImageView foto, String ruta){
        String direccion = ruta;
        File archivo = new Mensajes().buscar_archivo();
        if(archivo!=null){     
            direccion = archivo.getPath();
            System.out.println("RUTA: "+archivo.getPath());
            Image imagen = new Image("file:/"+archivo.getPath());
            foto.setImage(imagen);
            
        } else{
            System.out.println("LA RUTA NO PUEDE SER LEIDA");
            
        }
        return direccion;
    }
    
private WritableImage recomponer_imagen(BufferedImage imagen){
        
        int ancho = imagen.getWidth();
        int alto = imagen.getHeight();
    
     //se supone que la imagen en el buffer ya esta redimensionada
       WritableImage nueva_imagen = new WritableImage(ancho, alto);       
       PixelWriter pw = nueva_imagen.getPixelWriter();
       
       for(int fila=0; fila<alto; fila++){//
           for(int columna=0; columna<ancho; columna++){              
               pw.setArgb(columna, fila, imagen.getRGB(columna, fila));           
           }       
       }
       
       imagen.flush();//limpiando el buffer
       
       
      
       return nueva_imagen;
}



    
 public void guardar_imagen_BD(String archivo){

     if(archivo!=null){
            Connection con = new Conexion_bd().getConexion();

                   File imagen = null;
                   PreparedStatement preparado = null;
                   FileInputStream fis = null;
                   String nombre_archivo=null;

           try {

                   imagen =  new File(archivo);

                   fis = new FileInputStream(imagen);
                   preparado = con.prepareStatement("INSERT INTO biblioteca.libros_fotos VALUES (?,?)");
                   preparado.setString(1, cota);
                   preparado.setBinaryStream(2, fis,(int)imagen.length());
                   preparado.executeUpdate();

                   preparado.clearParameters();
                   preparado.close();
                   
               } catch (SQLException ex) {
                   new Mensajes().error("ERROR: \n"+ex.getMessage());
                   Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
               } catch (FileNotFoundException ex) {
                   new Mensajes().error("ERROR: \n"+ex.getMessage());
                   Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
               }finally{
                   try {
                        con.close();
                        fis.close();

                   } catch (SQLException ex) {
                       new Mensajes().error("ERROR: \n"+ex.getMessage());
                       Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       new Mensajes().error("ERROR: \n"+ex.getMessage());
                       Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
                   }


               }
     }

}


public ImageView recuperar_imagen_BD(String cota, int x, int y){
    Connection con = new Conexion_bd().getConexion();
    Statement sentencia = null;
    ResultSet resultado = null;
    InputStream imagen = null;
    ImageView foto = new ImageView(new Image(getClass().getResourceAsStream("/IMAGENES/sin_foto.jpg")));//imagen por defecto

    try {

            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT imagen FROM biblioteca.libros_fotos WHERE cota='" + cota + "';");
            if (resultado.next()) {
                imagen = resultado.getBinaryStream("imagen");               
                //decodificado = ImageIO.read(imagen);
                //ImageIcon pic = new ImageIcon(decodificado);
                
                //foto = new ImageView(new Image(imagen));
                Image im = this.recomponer_imagen(new Procesamiento().redimensionar_imagenes(imagen, x, y));
                
               foto.setImage(im);
               

            } else {                
              //  System.out.println("NO SE ENCONTRO LA IMAGEN");
            }


            sentencia.close();
            resultado.close();

    }catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
                if(imagen!=null) imagen.close();
                
            } catch (IOException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    
return foto;

}   
    
 
private String identificador_categoria(LinkedList<String> dewey,String cota){
    String informacion = null;
    
    for(int i=0; i<dewey.size();i++){
        if(cota.split("-")[0].equalsIgnoreCase(dewey.get(i).split(" - ")[0])){//iguales
                informacion = dewey.get(i).split(" - ")[1].toUpperCase();
        }
    
    }
   
    
return informacion;
}


    
public void cargando_libros(ObservableList<Modelo_Inventario> datos, TableView<Modelo_Inventario> inventario){
    Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       LinkedList<String> dewey = this.cargar_categorias();
       if(datos.size()>0) datos.clear();
        
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros ORDER BY cota ;"); 
              while(resultado.next()){
                 //paso 1: se llena el panel
                  Etiquetas eti = new Etiquetas();
                       eti.Etiquetas_inventario(resultado.getString("titulo"),
                                                resultado.getString("autores"),
                                                this.identificador_categoria(dewey, resultado.getString("cota")),//extraer solo la categoria
                                                resultado.getString("editorial"),
                                                resultado.getString("edicion"),
                                                resultado.getString("isbn"),
                                                resultado.getString("pais"),
                                                resultado.getString("paginas"),
                                                resultado.getString("cantidad"),
                                                resultado.getString("academico"),
                                                resultado.getString("industrial")
                                                );                  
                 //paso 2: se usa la clase para llenar la tabla
                  datos.add(new Modelo_Inventario(resultado.getString("cota"),eti.etiqueta_inventario(this.recuperar_imagen_BD(resultado.getString("cota"),70,80).getImage())));
                  
              }
    
              //paso 3: despues de llenar el arreglo se agrega la informacion a la tabla
              inventario.setItems(datos);
              sentencia.close();
              resultado.close();
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }

}    
    

public void limpiar(ComboBox categorias, TextArea titulo, TextField autores,
                    ComboBox editorial, TextField edicion, TextField isbn, 
                    TextArea indice, TextField paginas, ComboBox paises, 
                    TextField n_cota, TextField cantidad, TextField academico,TextField industrial, ImageView foto, String ruta){
    
    
    categorias.getSelectionModel().selectFirst();
    editorial.getSelectionModel().selectFirst();
    paises.getSelectionModel().selectFirst();
    
    titulo.setText("");
    autores.setText("");
    edicion.setText("");
    isbn.setText("");
    indice.setText("");
    paginas.setText("");
    n_cota.setText("");
    cantidad.setText("");
    academico.setText("--");
    industrial.setText("--");
    foto.setImage(new Image(getClass().getResourceAsStream("/IMAGENES/sin_foto.jpg")));
    ruta = "";
}
    
   
private void actualizar(String cota, int cantidad){
     Connection con = new Conexion_bd().getConexion();      
     Statement sentencia = null;
     ResultSet resultado = null;
     int existentes = 0;
     
             try {
                    
                   sentencia = con.createStatement();
                   resultado = sentencia.executeQuery("SELECT cantidad FROM biblioteca.libros WHERE cota = '"+cota+"' ;");
                   if(resultado.next()){
                       existentes = resultado.getInt("cantidad");
                   }else{
                       existentes = 0;
                   }
                   
                   sentencia.close();
                   resultado.close();
                   
                   sentencia = con.createStatement();
                   sentencia.execute("UPDATE biblioteca.libros SET cantidad ="+(existentes+cantidad)+" WHERE cota = '"+cota+"' ;");
                   
                   new Mensajes().notificacion("LA INFORMACIÓN HA SIDO ACTUALIZADA CON EXITO...!!!\nCantidad Previa: "+existentes+"\nCantidad añadida: "+cantidad+"\nTotal="+(existentes+cantidad)+"\nCota: "+cota);
                   

                   sentencia.close();
             
             } catch (SQLException ex) {
                 new Mensajes().error("ERROR: \n"+ex.getMessage());
                 Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
                 try {
                     con.close();
                 } catch (SQLException ex) {
                     new Mensajes().error("ERROR: \n"+ex.getMessage());
                     Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
                 }

             }
}

private boolean analisis_texto_numero(String numero){
  boolean paso = false;
  
  if(numero.length()<=7){//menor a 9.999.999, siete cifras maximo
        for(int n=0; n<numero.length(); n++){//recorriendo la secuencia de caracteres una a una
          if("0123456789".contains(String.valueOf(numero.charAt(n)))){    
              paso = true;
          }else{
              paso = false;
              break;
          }
        }//fin recorrido de secuencia de caracteres
  }else {//se excedio de la cantidad maxima
      paso = false;
  }
  return paso;
}

private boolean añadir_literatura(String cota){
    Mensajes mensaje = new Mensajes();
    boolean añadido = false;
    
    if(mensaje.confirmacion("Desea incrementar la cantidad existentes del libro :"+cota+"?").equalsIgnoreCase("aceptar")){
        String x = mensaje.texto_entrada("Por Favor, Ingrese la cantida a añadir!");
        if(x!=null || x!="nada"){//en caso de colocar algo se verifica que cumpla con la parte numerica
                if(this.analisis_texto_numero(x)){//se analiza si todos los elementos ingresados on numericos y no excede las cifras maximas permitidas
                    this.actualizar(cota, Integer.valueOf(x));
                    añadido = true;
                }else{
                    añadido = false;
                    mensaje.error("No se pudo procesar ya que la información ingresada no es numérica o la cifra es muy grande, por favor revise.\nProceso Cancelado...!!!");
                }
        }else{
            añadido = false;
            mensaje.error("Formato no Reconocido.\nProceso Cancelado...!!!");
        }    
        
    }else{
        añadido = false;
       mensaje.notificacion("Proceso Cancelado...!!!");
    }

return añadido;
}

public void incrementar_literatura(String cota, ObservableList<Modelo_Inventario> datos, TableView<Modelo_Inventario> inventario){
    if(this.añadir_literatura(cota)){//solo se actualizara la tabla si la informacion fue añadida
        this.cargando_libros(datos, inventario);
    }
}




    public void seleccion_categorias(final LinkedList<String> elementos){
        
        
        //PASO 1:
         final Stage stage = new Stage();
               stage.setTitle("VISTA RÁPIDA DE CATEGORÍAS");

/* <children>
    <Pane layoutX="50.0" layoutY="50.0" prefHeight="600.0" prefWidth="400.0">
      <children>
        <TextField layoutX="7.0" layoutY="575.0" prefWidth="386.0" text="TextField" />
        <TreeView layoutX="7.0" layoutY="7.0" prefHeight="561.0" prefWidth="386.0" />
      </children>
    </Pane>
  </children>*/
     
       TreeItem<String> raiz = new TreeItem<String>("CATEGORIAS");
               
       TreeItem<String> nivel_1 = null;
       
       
       
       for(int i=0; i<elementos.size(); i++){
           if((i%10)<=0){//si la division es exacta es una sub categoria
               nivel_1 = new TreeItem<String>(elementos.get(i));
               raiz.getChildren().add(nivel_1);
           }else{//sino es un elemento de la sub categoria
               TreeItem<String> actual = new TreeItem<String>(elementos.get(i));
               nivel_1.getChildren().add(actual);
           }
           
       }
       
       final TreeView<String> arbol = new TreeView<String>(raiz);  
       arbol.setPrefWidth(386.0); arbol.setPrefHeight(561.0);
       arbol.setLayoutX(7.0);arbol.setLayoutY(7.0);
       //arbol.setPrefSize(400.0, 576.0);
      
       
       //PASO 4
       
       
       final TextField texto = new TextField();
       texto.setPromptText("Busqueda Rápida");
       texto.setTooltip(new Tooltip("Escriba aqui la palabra o palabras\npara hacer una busqueda rápida\nentre las categorias.\nSi son varias separelas con comas(,)\nEJM: filosofia,proyectos,esquemas\n y luego presione 'ENTER'"));
       texto.setLayoutX(7.0);texto.setLayoutY(575.0);
       texto.setPrefWidth(386.0);
       texto.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //System.out.println("codigo: "+event.getCode());
                if(event.getCode().toString().equalsIgnoreCase("ENTER")){
                    new Registro_Libros().busqueda_interna(elementos, texto.getText());
                }
            }
       });
               
        //PASO 5
        Pane panel = new Pane();
        panel.setPrefHeight(600.0);
        panel.setPrefWidth(400.0);
      //  panel.setMinSize(300.0, 500.0);
      //  panel.setMaxSize(400.0, 600.0);
        panel.getChildren().addAll(arbol, texto);
        
    
        //PASO 6
        final Scene escena = new Scene(panel);        
    //  stage.setMaxHeight(601.0); stage.setMaxWidth(401.0);
        stage.setScene(escena);
        stage.setResizable(false);
        stage.show();
    
        
        
        
        
        
    }

public void busqueda_interna(LinkedList<String> categorias, String texto){
      String resultado = "";
      Mensajes m = new Mensajes();
      
        if(texto!=null && texto.length()>0){
            
            String[] aux = null;
            LinkedList<String> coincidencia = new LinkedList<String>();

            //ETAPA 1: IDENTIFICANDO SI EL TEXTO CONTIENE VARIAS PALABRAS o SOLO UNA
           if(texto.contains(",")){//si contiene alguna coma entonces son varias frases, y se busca por las mismas
              aux = texto.split(",");                            
           }else{//sino solo se toma la unica palabra colocada
              aux = new String[]{texto};          
           }

           //ETAPA 2: VERIFICANDO COINCIDENCIA. PALABRA vs CATEGORIAS 
           for(int x=0; x<aux.length;x++){//recorriendo palabras de interes
            for(int i=0; i<categorias.size(); i++){//recorriendo todas las categorias
                if(categorias.get(i).toLowerCase().contains(aux[x].toLowerCase())){//si la categoria contiene la palabra de interes, se almacena
                    coincidencia.add(categorias.get(i));//se guarda todo aquel relacionado de alguna manera
                }
            }//fin recorrido todas las categorias
           }//fin recorrido palabras de interes
           
           //ETAPA 3: CONCATENANDO TODO EL CONTENIDO de coincidencia
           for(int c=0; c<coincidencia.size(); c++){
               resultado = resultado.concat(coincidencia.get(c)+"\n");
           }           
       

       //ETAPA 4: PRESENTANDO RESULTADOS 
            
            if(resultado.length()<=1){
                m.notificacion("Disculpe, pero no hubo coincidencia...!!!");
            }else{
                m.detalles("COINCIDENCIAS:\n"+resultado);   
            }  
        }else{
            m.notificacion("Disculpe, pero debe colocar alguna palabra de interes...!!!");
        }
        
}
    
private String revision_acentos(String texto){
    String [] vocales_acentos = {"Á","É","Í","Ó","Ú"};
    String [] vocales_normales = {"A","E","I","O","U"};
    
    String trozo = texto.substring(0, 3);//las primeras tres letras
    
    for(int i=0; i<vocales_acentos.length; i++){//recorriendo las vocales
        if(trozo.contains(vocales_acentos[i])){//si tiene una de estas vocales con acentos
             texto = texto.replace(vocales_acentos[i],vocales_normales[i]);             
        }
    }

return texto;
}    
    
public LinkedList<String> temporal(){
     LinkedList<String> registrados = new LinkedList<String>();
            Connection con = new Conexion_bd().getConexion();
            Statement sentencia = null;
            ResultSet resultado = null;
            
        try {
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM biblioteca.tesis WHERE categoria REGEXP '042';");
            while(resultado.next()){                
                registrados.add(resultado.getString("titulo"));
                registrados.add(resultado.getString("año"));
                registrados.add(resultado.getString("autor"));
            }
            
            sentencia.close();
            resultado.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
         try {
             con.close();
         } catch (SQLException ex) {
             Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
                
    return registrados;    
}
    
}//FIN DE LA CLASE
