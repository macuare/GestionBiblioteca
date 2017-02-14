/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author AN
 */
public class Registro_Nuevo_Usuario {
    private Mensajes mensaje;
    private Encriptacion encriptar;
    private int aux=0;
    
    public Registro_Nuevo_Usuario() {
        mensaje = new Mensajes();
        encriptar = new Encriptacion();
    }
    
    
    
    private void ingresar(Connection con, String cedula, String usuario, String clave, String categoria){
       PreparedStatement preparada = null;

    try {

            con.setTransactionIsolation(con.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            preparada = con.prepareStatement("INSERT INTO biblioteca.autenticacion VALUES (?,?,?,?);");

           
                preparada.clearParameters();
                
                    preparada.setString(1, cedula); //cedula
                    preparada.setString(2, usuario); //usuario
                    preparada.setString(3, clave); //clave
                    preparada.setString(4, categoria);//categoria
                    
                preparada.addBatch();
           

            preparada.clearParameters();//limpiando parametros
            preparada.executeBatch();//ejecutando lotes

            con.commit();//haciendo permanente los cambios y liberando la base de datos
            //preparada.clearBatch();//limpiando listas de sentencias
            //preparada.close();//cerrando la sentencia
            mensaje.notificacion("EL USUARIO FUE ASIGNADO CON EXITO...!!!");

        } catch (SQLException ex) {
            mensaje.error("ERROR ENVIANDO INFORMACION A BASE DE DATOS.\n"+ex.getMessage());
           
            Logger.getLogger(Registro_Nuevo_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                preparada.clearBatch(); //limpiando listas de sentencias
                preparada.close(); //cerrando la sentencia
            } catch (SQLException ex) {
                Logger.getLogger(Registro_Nuevo_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

}
    
private boolean confirmacion_clave(String clave, String repetir){
    boolean autorizado = false;
    
    if(clave.equalsIgnoreCase(repetir)){
        autorizado = true;
    }else{
        autorizado = false;        
    }
    
    return autorizado;
}    

private void intentos(){
    aux = aux + 1;
    if(aux>=3){
        mensaje.error("DISCULPE, PERO ALCANZO EL MÁXIMO DE INTENTOS PERMITIDOS.\nCERRANDO EL PROGRAMA");
        Platform.exit();
    }//despues de los tres intentos se cierra el programa automaticamente}
}    
    

public void crear_nuevo_usuario(String cedula, String usuario, String clave, String repetir, String categoria){

    if(this.confirmacion_clave(clave, repetir)){//si las claves coinciden, se procede a guardarlas
        this.ingresar(new Conexion_bd().getConexion(), cedula, usuario, encriptar.conversor(clave, "SHA-512"), categoria);
    }else{        
        this.intentos();
        mensaje.notificacion("DISCULPE PERO LAS CLAVES NO COINCIDEN, POR FAVOR REVISE...!!!\nINTENTOS: "+aux+" de 3");
    }   
    
}

private void eliminacion(String cedula){   
     Statement sentencia = null;
     Connection con = new Conexion_bd().getConexion();
     
        try {   
            sentencia = con.createStatement();
            sentencia.executeUpdate("DELETE FROM biblioteca.autenticacion WHERE cedula='"+cedula+"';");
            //System.out.println("cedula: "+cedula);
            mensaje.notificacion("EL USUARIO FUE ELIMINADO DEL SISTEMA...!!!");
            
            sentencia.close();
           
        } catch (SQLException ex) {
            mensaje.error("ERROR ENVIANDO INFORMACION A BASE DE DATOS.\n"+ex.getMessage());
            Logger.getLogger(Registro_Nuevo_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
         try {
             con.close();
         } catch (SQLException ex) {
             Logger.getLogger(Registro_Nuevo_Usuario.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    
    
}

public void eliminar_usuario(String cedula){

    if(cedula!=null && cedula.length()>1){
        this.eliminacion(cedula);
    }else{
        mensaje.notificacion("DISCULPE, PERO NO DEBE DEJAR LA CASILLA DE CEDULA VACIA...!!!");
    }

}    


    
    
}//fin de la clase
