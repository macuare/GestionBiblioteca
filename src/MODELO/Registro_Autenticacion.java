/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;

/**
 *
 * @author AN
 */
public class Registro_Autenticacion {
    private Mensajes mensaje;
    private int intentos=0;
    private Encriptacion encriptar;
    public static String personal;
            
            
    public Registro_Autenticacion() {
        mensaje = new Mensajes();
        encriptar = new Encriptacion();
    }
    
    
    public Object[] verificacion(String cedula, PasswordField clave){
        boolean existe = false;
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       String ced=null, usua=null, tipo=null;
                     
        try {             
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.autenticacion WHERE cedula='"+cedula+"' AND clave='"+encriptar.conversor(clave.getText(), "SHA-512")+"';");
              //System.out.println(encriptar.conversor(clave.getText(), "SHA-512"));
              //0a41fa6fe3780c19121f36dd1a2ec8afa95d358325db2ae8ce2f21a0392a481e18c004b68d5b24df1736548a1cf5874be17d1f0039e35e2af3b75bfc9b387444
              //
              if(resultado.next()){
                  existe = true;
                  ced = resultado.getString("cedula");
                  usua = resultado.getString("usuario");
                  tipo = resultado.getString("tipo");
                  
                  personal = ced+" - "+usua;//variable estatica global
                  
              }else{
                  existe = false;                  
              }
              
             sentencia.close();
             resultado.close();
             
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Autenticacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Autenticacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        return new Object[]{existe,ced,usua,tipo};
    }
    
    
    
    public Object[] analisis(String usuario, PasswordField clave){
        
        Object[] informacion = this.verificacion(usuario, clave);
        // System.out.println("informacion: "+informacion[0]);
        
     if(String.valueOf(informacion[0]).equalsIgnoreCase("true")){            
         System.out.println("confirmacion correcta");         
     }else{         
         mensaje.notificacion("ESTIMADO USUARIO, SU AUTENTICACIÓN HA SIDO RECHAZADA.\nVERIFIQUE LOS DATOS INGRESADOS!!!"); 
         intentos = intentos + 1;
         //System.out.println("intentos: "+intentos);
         
         if(intentos >=3 ){
             mensaje.error("USUARIO, USTED YA ALCANZO EL MAXIMO DE INTENTOS.\nCERRANDO EL PROGRAMA"); 
             Platform.exit();
         }
     }
         
       return informacion;     
    }
    
    
    public void permisos(Parent root, String privilegio){
        //accediendo a los elementos contenidos en el fxml con exito        
               for(int i=0; i<root.getChildrenUnmodifiable().size(); i++){//recorriendo todos los elementos del parent
                   //System.out.println("elementos: "+root.getChildrenUnmodifiable().get(i).toString());
                   if(root.getChildrenUnmodifiable().get(i)instanceof MenuBar){
                     //  System.out.println("elemento hallado");
                       MenuBar x = (MenuBar) root.getChildrenUnmodifiable().get(i);
                       for(int c=0; c<x.getMenus().size(); c++){//recorriendo todos los elementos del menubar
                       //  System.out.println("hijos: "+x.getMenus().get(c).getId());                           
                         if(x.getMenus().get(c).getId().equalsIgnoreCase("menu_archivo")){
                             Menu m = x.getMenus().get(c);
                             for(int t=0;t<m.getItems().size();t++){//analizando los menusitems
                                 MenuItem mi = m.getItems().get(t);
                                 if(mi.getId().equalsIgnoreCase("usuario") && privilegio.equalsIgnoreCase("ADMINISTRADOR")){
                                    mi.setDisable(false);
                                    break;
                                 }
                             }
                         }
                       }
                   }
               }
        
    
    }
    
    
    
    
    
    
}//fin de la clase
