/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MODELO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDY
 */
public class Conexion_SMS {
 
 
    public Conexion_SMS() {
    }

    
    public void enviar_sms(String ip, String puerto, String telefono ,String mensaje, int retardo){
        try {
            //URL url = new URL("http://192.168.30.101:9090/sendsms?phone="+telefono+"&text=Biblioteca%20Caribay%20Estan%20en%20la%20lista%20de%20morosos.%20Entregar%20los%20libros.&password=andy");
           Thread.sleep(retardo);//retardo
           //telefono = "04124636211";
          // ip = "192.168.1.111";
            URL url = new URL("http://"+ip+":"+puerto+"/sendsms?phone="+telefono+"&text="+mensaje+"&password=andy");
            URLConnection con = url.openConnection();
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            
            String linea;
            while ((linea = in.readLine()) != null) {
                System.out.println(linea);
            } } catch (MalformedURLException ex) {
            Logger.getLogger(Conexion_SMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexion_SMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Conexion_SMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
   /* public static void main(String[] args){
        Conexion_SMS csms = new Conexion_SMS();
        LinkedList<String> morosos = new LinkedList<>();
                morosos.add("04142971913");
                morosos.add("04143476826");
                morosos.add("04129408777");
                morosos.add("04121475396");
                morosos.add("04243406031");
                morosos.add("04168163687");
                morosos.add("04262325794");
                morosos.add("04168098810");
                morosos.add("04144889360");
                morosos.add("04124936493");
                morosos.add("04128987728");
                morosos.add("04128931681");
                morosos.add("04163464142");
                morosos.add("04127798599");
                morosos.add("04265332698");
                morosos.add("04128807748");

             for(int i=0; i<morosos.size(); i++){
            try {
                System.out.println("ENVIANDO MENSAJE A: "+morosos.get(i));
                csms.enviar_sms(morosos.get(i));
                Thread.sleep(1000);
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Conexion_SMS.class.getName()).log(Level.SEVERE, null, ex);
            }
             
             }   
        
       
        
    
    
    
    
    }*/
    
    
    
    
    
    
}
