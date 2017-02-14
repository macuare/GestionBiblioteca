/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class Generador_Cota {

    public Generador_Cota() {
    }
    
    
    public LinkedList<String> recolectar_informacion(String elemento){
        Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       LinkedList<String> cotas = new LinkedList<String>();
               
        try {
              sentencia = con.createStatement();
              
              if(elemento.equalsIgnoreCase("TESIS")){
                resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cota REGEXP '040' OR cota REGEXP '041' OR cota REGEXP '042' ORDER BY cota ;"); 
              }else{
                resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cota NOT REGEXP '040' AND cota NOT REGEXP '041' AND cota NOT REGEXP '042' ORDER BY cota;");
              }
              
              while(resultado.next()){                        
                  cotas.add(resultado.getString("cota"));
                  cotas.add(String.valueOf(resultado.getInt("cantidad")));
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

    return cotas;
    }
    
    
    
    
    
    
    
    
    
}//fin de la clase
