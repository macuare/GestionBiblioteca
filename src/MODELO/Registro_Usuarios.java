/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 *
 * @author AURORA
 */
public class Registro_Usuarios {

    private String cedula, nombres, apellidos, telf_celular, telf_habitacion, correo, direccion, institucion, estado, carrera;
    
    public Registro_Usuarios() {
        
    }

    public Registro_Usuarios(String cedula, String nombres, String apellidos, String telf_celular, String telf_habitacion, String correo, String direccion, String institucion, String estado, String carrera) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telf_celular = telf_celular;
        this.telf_habitacion = telf_habitacion;
        this.correo = correo;
        this.direccion = direccion;
        this.institucion = institucion;
        this.estado = estado;
        this.carrera = carrera;
    }

   
    
    public void guardar_registros_usuario(){
       
       if(new Mensajes().confirmacion("Desea guardar los registros ingresados?").equalsIgnoreCase("aceptar")){ 
        
        Connection con = new Conexion_bd().getConexion();
        PreparedStatement preparado = null;
                
           try {
               
                   preparado = con.prepareStatement("INSERT INTO biblioteca.usuarios VALUES (?,?,?,?,?,?,?,?,?,DATE_FORMAT(NOW(),'%d/%m/%Y - %r'),?)");
                   preparado.setString(1, cedula);
                   preparado.setString(2, nombres.toUpperCase());
                   preparado.setString(3, apellidos.toUpperCase());
                   preparado.setString(4, telf_celular);
                   preparado.setString(5, telf_habitacion);
                   preparado.setString(6, correo);
                   preparado.setString(7, direccion.toUpperCase());
                   preparado.setString(8, institucion.toUpperCase());
                   preparado.setString(9, estado.toUpperCase());
                   preparado.setString(10, carrera.toUpperCase());
                   
                   
                   
                   
                   preparado.executeUpdate();

                   preparado.clearParameters();
                   preparado.close();
                   
                   new Mensajes().notificacion("LA INFORMACIÓN HA SIDO GUARDADA CON EXITO...!!!");
                   
                   
               } catch (SQLException ex) {
                   new Mensajes().error("ERROR: \n"+ex.getMessage());
                   Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
               } finally{
                   try {
                        con.close();
                       
                   } catch (SQLException ex) {
                       new Mensajes().error("ERROR: \n"+ex.getMessage());
                       Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
                   } 
               }
       }else{
       
           new Mensajes().notificacion("Proceso de Registro ha sido cancelado...!!!");
       }
    
    
    }
    
    private LinkedList<String> cargar_instituciones(){
         LinkedList<String> aux = new LinkedList();
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
        
        try {             
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.usuarios GROUP BY institucion ORDER BY institucion;");
              while(resultado.next()){
                  aux.add(resultado.getString("institucion"));                               
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
        
        
        
        aux.add("NO EXISTE");
        
        return aux;
    
    }
    
    
    public void pre_carga_instituciones(ComboBox instituciones){
        ObservableList<Object> elementos = FXCollections.observableArrayList(this.cargar_instituciones().toArray());
        instituciones.setItems(elementos);//pre cargando las categorias
        instituciones.getSelectionModel().selectFirst();
    }
    
    private LinkedList<String> cargar_carreras(){
         LinkedList<String> aux = new LinkedList();
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
        
        try {             
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.usuarios GROUP BY carrera ORDER BY carrera;");
              while(resultado.next()){
                  aux.add(resultado.getString("carrera"));                               
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
        
        
        
        aux.add("NO EXISTE");
        
        return aux;
    
    }
    
    
    public void pre_carga_carreras(ComboBox carreras){
        ObservableList<Object> elementos = FXCollections.observableArrayList(this.cargar_carreras().toArray());
        carreras.setItems(elementos);//pre cargando las categorias
        carreras.getSelectionModel().selectFirst();
    }
    
    
    
    public void nueva_institucion(ComboBox instituciones){
        
        if(instituciones.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("NO EXISTE") ){
        String nuevo = "";
        ObservableList<Object> elementos = instituciones.getItems();        
        
        nuevo = new Mensajes().texto_entrada("ESCRIBA LA INSTITUCIÓN QUE DESEA AGREGAR...!!!");
        
        if(nuevo.equalsIgnoreCase("nada") || nuevo.isEmpty()){//no se agrego nada
            instituciones.setItems(elementos);//todo queda sin modificacion
        }else{//se agrego algo nuevo
           
            elementos.add(elementos.size()-1, nuevo.toUpperCase());//se agrega el nuevo elemento
            instituciones.setItems(elementos);//cargando las categorias
        }
        
      }
    
    
    }
    
    public void nueva_carrera(ComboBox carrera){
        
        if(carrera.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("NO EXISTE") ){
        String nuevo = "";
        ObservableList<Object> elementos = carrera.getItems();        
        
        nuevo = new Mensajes().texto_entrada("ESCRIBA LA CARRERA QUE DESEA AGREGAR...!!!");
        
        if(nuevo.equalsIgnoreCase("nada") || nuevo.isEmpty()){//no se agrego nada
            carrera.setItems(elementos);//todo queda sin modificacion
        }else{//se agrego algo nuevo
           
            elementos.add(elementos.size()-1, nuevo.toUpperCase());//se agrega el nuevo elemento
            carrera.setItems(elementos);//cargando las categorias
        }
        
      }
    
    
    }
    
    public void cargando_usuarios(ObservableList<Modelo_Inventario> datos, TableView<Modelo_Inventario> todos){
    Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
      
       if(datos.size()>0) datos.clear();
        
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.usuarios ORDER BY cedula DESC;");
              while(resultado.next()){
                 //paso 1: se llena el panel
                  
                  
                  Etiquetas eti = new Etiquetas();
                         eti.Etiquetas_usuarios(resultado.getString("nombres"),
                                                resultado.getString("apellidos"),
                                                resultado.getString("telf_cel"),
                                                resultado.getString("telf_hab"),
                                                resultado.getString("correo"),
                                                resultado.getString("direccion"),
                                                resultado.getString("institucion"),
                                                resultado.getString("estado"),
                                                resultado.getString("f_registro"));                  
                 //paso 2: se usa la clase para llenar la tabla
          //        datos.add(new Modelo_Usuarios(resultado.getString("cedula"),eti.etiqueta_usuarios(null))));
                  
              }
              
              //paso 3: despues de llenar el arreglo se agrega la informacion a la tabla
              todos.setItems(datos);
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
    
    
public void todos_usuarios(ObservableList<Modelo_Usuarios> datos, TableView<Modelo_Usuarios> todos){
    Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
      
       if(datos.size()>0) datos.clear();
        
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.usuarios ORDER BY cedula;");
              while(resultado.next()){
                 //paso 1: se llena el panel
                  datos.add(new Modelo_Usuarios(resultado.getString("estado"),
                                                resultado.getString("cedula"),
                                                resultado.getString("nombres"),
                                                resultado.getString("apellidos"),
                                                resultado.getString("telf_cel")+", "+
                                                resultado.getString("telf_hab"),
                                                resultado.getString("correo"),
                                                resultado.getString("institucion"),
                                                resultado.getString("carrera")
                                                ));
           
              }
              
              //paso 3: despues de llenar el arreglo se agrega la informacion a la tabla
              todos.setItems(datos);
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
    
    
 public void actualizar_estado_usuario(String cedula, String estado){
       Connection con = new Conexion_bd().getConexion();
       Statement sentencia = null;     
       
        try { 
            //System.out.println("revision: "+cedula+" - "+estado);
            sentencia = con.createStatement();
            sentencia.executeUpdate("UPDATE biblioteca.usuarios SET estado='"+estado+"' WHERE cedula='"+cedula+"';");
            sentencia.close();            
            new Mensajes().notificacion("MODIFICACIÓN REALIZADA CON ÉXITO...!!!");            
            
        } catch (SQLException ex) {
            new Mensajes().error("ERROR CON LA BASE DE DATOS "+ex.getMessage());            
            Logger.getLogger(Registro_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           try {
               con.close();
           } catch (SQLException ex) {
               Logger.getLogger(Registro_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        
 }   
    
    
    
    
    
    
    
    
    
    
}//fin de la clase
