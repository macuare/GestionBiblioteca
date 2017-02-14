/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author AURORA
 */
public class Registro_Mensual {
    
    private Mensajes m;

    public Registro_Mensual() {
        m = new Mensajes();
    }
    
    
    
    
    
    
    /**Metodo que muestra todos los libros prestados: cota, f_salida, f_entrega, tipo, estado, responsable, cedula */
     public LinkedList<String> libros_prestados(){   
       
       
       LinkedList<String> prestados = new LinkedList();
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       
        
        try {             // WHERE estado='ACTIVO' OR estado='MANTENIMIENTO' OR estado='MOROSO'
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.prestamos;");
              while(resultado.next()){
                  prestados.add(resultado.getString("cota")); //0
                  prestados.add(resultado.getString("f_salida"));//1
                  prestados.add(resultado.getString("f_entrega"));//2
                  prestados.add(resultado.getString("tipo"));//3
                  prestados.add(resultado.getString("estado"));//4
                  prestados.add(resultado.getString("responsable"));//5
                  prestados.add(resultado.getString("cedula"));//6
              }
              
              
          sentencia.close();    
          resultado.close();    
          
        } catch (SQLException ex) {
            m.error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                m.error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Libros.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        return prestados;
    
    }
    
     
     private boolean dominio_seleccion(String actual, String fsalida, String fentrega){
         boolean agregar = false;
     
         //14/04/2013 - 11:47:12 AM
         if(actual.split("/")[2].equalsIgnoreCase(fsalida.split(" - ")[0].split("/")[2])){//que sean del mismo año
             int mes_actual = Integer.valueOf(actual.split("/")[1]);
             int mes_salida = Integer.valueOf(fsalida.split(" - ")[0].split("/")[1]);
             int mes_entrega = Integer.valueOf(fentrega.split(" - ")[0].split("/")[1]);
             
           //  System.out.println(mes_actual+">="+mes_salida+" || "+mes_actual+"<="+mes_entrega);
             
             if(mes_salida>=mes_actual  || mes_actual<=mes_entrega){
                 agregar = true;                 
             }else{
                 agregar = false;
             }
             
         }
         return agregar;
     }
    
     
     
     
     public void libros_del_mes(ObservableList<Modelo_Mensual> actual, TableView<Modelo_Mensual> mensual){
         Calendar cal = Calendar.getInstance();
         DecimalFormat df = new DecimalFormat("00");
         String fecha_actual = df.format(cal.get(Calendar.DAY_OF_MONTH))+"/"+df.format(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR);
         actual.clear();
         LinkedList<String> datos = this.libros_prestados();
         
         for(int i=0; i<datos.size(); i=i+7){
           //  System.out.println("datos: "+datos.get(i+6)+" - "+datos.get(i)+" - "+datos.get(i+4)+" - "+datos.get(i+1)+" - "+datos.get(i+2));
             if(this.dominio_seleccion(fecha_actual, datos.get(i+1), datos.get(i+2))){
                actual.add(new Modelo_Mensual(datos.get(i+6), datos.get(i), datos.get(i+4), datos.get(i+1), datos.get(i+2)));         
             }
         }
         
         mensual.setItems(actual);
         
     
     }
    
    
    
    
}//fin de la clase
