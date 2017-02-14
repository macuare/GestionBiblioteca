/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MODELO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author ANDY
 */
public class Registro_Morosos {
    private Mensajes mensaje;
    private Conexion_SMS sms; 

    public Registro_Morosos() {
        mensaje = new Mensajes();
        sms = new Conexion_SMS();
    }
    
    
    
    public LinkedList<String> informacion(){
       Connection con = new Conexion_bd().getConexion();      
       LinkedList<String> estudiantes = new LinkedList<String>();
       
       Statement sentencia = null;
       ResultSet resultado = null; 
       
       try {             
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.usuarios");
              while(resultado.next()){
                  String texto = resultado.getString("telf_cel");
                  if(texto != null && texto.length() > 10){
                    estudiantes.add(texto);                 
                  }else{
                      System.out.println("Cedula: "+resultado.getString("cedula")+" --> NO TIENE NRO TELEFONICO.");
                  }
              }
              
          sentencia.close();    
          resultado.close();    
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
       
       return estudiantes;
    }
    
    public LinkedList<String> lista_morosos(){
        LinkedList<String> listado = new LinkedList<String>();
            Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
          
        try {             
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT cedula,cota ,f_entrega FROM biblioteca.prestamos WHERE estado = 'MOROSO' ORDER BY cedula;");
              //resultado = sentencia.executeQuery("SELECT cedula,cota ,f_entrega FROM biblioteca.prestamos WHERE estado = 'MOROSO' OR estado ='ACTIVO' order by cedula;");
              
              while(resultado.next()){
                  listado.add(resultado.getString("cedula"));
                  listado.add(resultado.getString("cota"));
                  listado.add(resultado.getString("f_entrega"));
              }
              
          sentencia.close();    
          resultado.close();    
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        return listado;
    }
    
    public LinkedList<String> informacion_usuario(LinkedList<String> morosos){
        LinkedList<String> datos = new LinkedList<String>();
            Connection con = new Conexion_bd().getConexion();      
            Statement sentencia = null;
            ResultSet resultado = null; 
          
        try {    
                for(int i=0; i<morosos.size(); i=i+3){//recorriendo los valores de moroso
                    sentencia = con.createStatement();
                    resultado = sentencia.executeQuery("SELECT nombres, apellidos, telf_cel FROM biblioteca.usuarios WHERE cedula='"+morosos.get(i)+"' ;");
                    if(resultado.next()){      
                       datos.add(resultado.getString("apellidos"));//agregando datos. nombre, apellidos, fecha entrega, telefono
                       datos.add(resultado.getString("telf_cel"));//agregando datos. nombre, apellidos, fecha entrega, telefono
                       datos.add(morosos.get(i+1));//cota del libro
                       datos.add(morosos.get(i+2).substring(0, 10));//agregando datos. nombre, apellidos, fecha entrega, telefono
                    }
                }//fin recorrido morosos
                    
              
          sentencia.close();    
          resultado.close();    
        } catch (SQLException ex) {
            new Mensajes().error("ERROR: \n"+ex.getMessage());
            Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                new Mensajes().error("ERROR: \n"+ex.getMessage());
                Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        return datos;
    }
    
    
    public void direccion_servidor(TextField IP_Servidor){
        try {
            IP_Servidor.setText(mensaje.texto_entrada("ESCRIBA LA DIRECCIÓN IP DEL SERVIDOR DE SMS\n ejm:"+InetAddress.getLocalHost().getHostAddress()));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void procesar_morosos(final TextField IP_Servidor, final ProgressIndicator Procesado, final TextArea Resumen){
        LinkedList<String> p1 = this.lista_morosos();
        LinkedList<String> p2 = this.informacion_usuario(p1);
        
        
        final Double factor = (p2.size()/4.0);
        
     //   Resumen.appendText("Procesando "+factor+" Registros\n\n");
        
        for(int i=0; i<p2.size(); i=i+4){ // Recorriendo usuarios
            String estudiante = p2.get(i);
            String libro = p2.get(i+2);
            String f_entrega = p2.get(i+3);
            final String telefono_remitente = p2.get(i+1);
            
            final Integer nro = new Integer((i/4)+1);
            
            
            final String mensaje = "SISTEMA AUTOMATIZADO UNEFA BIBLIOTECA CARIBAY. "
                                    + "<-MOROSO-> "
                                    + "Usuario: "+estudiante+" "
                                    + "Libro: "+libro+" - "
                                    + "Debió entregar el: "+f_entrega;
        
            System.out.println(factor+" - "+nro+" Traza > "+mensaje+" longitud: "+mensaje.length());
            sms.enviar_sms(
                            IP_Servidor.getText(),                
                            "9090",
                            telefono_remitente,
                            mensaje.replaceAll(" ", "%20"),
                            1000
                        );
        /*
           try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
            }
       */     
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {                                                
                       // System.out.println(factor+" - "+nro+" interno > "+((Double)(100.0/factor)*nro)/100);
                        Resumen.appendText(nro+") *_"+mensaje.concat("\n"));
                        Procesado.setProgress(((Double)(100.0/factor)*nro)/100.0);
                }
            });
            
        }// Fin Recorrido usuarios
    
    }
    
    public void procesar(){
        LinkedList<String> p1 = this.lista_morosos();
        LinkedList<String> p2 = this.informacion_usuario(p1);
        
        
       String direccion_ip = null;
        try {
            direccion_ip = mensaje.texto_entrada("ESCRIBA LA DIRECCIÓN IP DEL SERVIDOR DE SMS\n ejm:"+InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      /*  sms.enviar_sms(
                "192.168.30.102",
                "9090",
                "04124154116",
                "UNEFA BIBLIOTECA CARIBAY. Estudiante PARRA HERNANDEZ Edo:MOROSO Libro: 620-S314-ej1 - Debio Entregar: 10/05/2014".replaceAll(" ", "%20"),
                1000);*/
        
        
        for(int i=0; i<p2.size(); i=i+4){
            String mensaje = "UNEFA BIBLIOTECA CARIBAY. Estudiante "+p2.get(i)+" Edo:MOROSO "
                             + "Libro: "+p2.get(i+2)+" - "
                             + "Debio Entregar: "+p2.get(i+3);                        
            /*String mensaje = "UNEFA BIBLIOTECA CARIBAY. Estudiante "+p2.get(i)+",  DEBIDO A QUE LA BIBLIOTECA SERA CERRADA HASTA NUEVO AVISO"
                             +", SE LE SOLICITA HACER ENTREGA DEL LIBRO. GRACIAS.";
            */
            System.out.println("MENSAJE>"+i/4+" "+mensaje+" longitud:"+mensaje.length());
            sms.enviar_sms(
                //"192.168.30.102",
                direccion_ip,
                "9090",
                p2.get(i+1),//telefono
                mensaje.replaceAll(" ", "%20"),
                1000);
            
        }
    
       
        /*
        for(int i=0; i<500; i++){//2014
            String mensaje = "Feliz Cumpleaños MI PRINCESITA."
                             + " Felices 28. Que te sigas poniendo mas pomposa y preciosita ademas de ricota como el buen vino. entre mas añeja mejor.!!!";
            System.out.println("MENSAJE>"+i+" "+mensaje+" longitud:"+mensaje.length());
            sms.enviar_sms(
                "192.168.1.111",
                "9090",
                "04124636211",
                //"04124154116",//telefono
                mensaje.replaceAll(" ", "%20"),
                1000);
            
        }
        */
        
        
        
        p1.clear();p2.clear();
    }
    
    
    public void procesarxx(){
        LinkedList<String> estudiantes = this.informacion(); // se lista todos los estudiantes. opcional para enviar solo notifiacaciones
        String direccion_ip = null;
        try {
            direccion_ip = mensaje.texto_entrada("ESCRIBA LA DIRECCIÓN IP DEL SERVIDOR DE SMS\n ejm:"+InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Registro_Morosos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        for(int i=0; i<estudiantes.size(); i++){
            //String m = "UNEFA BIBLIOTECA CARIBAY. ahora con servicio WEB ONLINE -> "
              //               + "http://bibliocaribay.blogspot.com/";
            String m = "BIBLIOTECA CARIBAY. BUENOS DIAS, SE INFORMA QUE EL SERVICIO DE LA BIBLIOTECA, "
                    + "HA SIDO REESTABLECIDO, DISCULPEN LA DEMORA.  GRACIAS...!!!";
                    
            
            
            System.out.println("telefono: "+estudiantes.get(i)+" MENSAJE>"+i+" "+m+" longitud:"+m.length());
            
            sms.enviar_sms(
                //"192.168.30.102",
                direccion_ip,
                "9090",
                estudiantes.get(i),//telefono
                m.replaceAll(" ", "%20"),
                2000);
           
        }
        
        estudiantes.clear();
    
    }
    
    
}//fin de la clase
