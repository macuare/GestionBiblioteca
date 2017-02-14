/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//DESARROLLADO POR EL ING CUSATTI ANDY
package MODELO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class Conexion_bd {
  
	//DESARROLLADO POR EL ING CUSATTI ANDY

    private String usuario,clave;
    private String confirmacion;
    private static String direccion;

    public Conexion_bd(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
            }

  public Conexion_bd() {
        
    }

    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public static String getDireccion() {
        return direccion;
    }

    public static void setDireccion(String direccion) {
        Conexion_bd.direccion = direccion;
    }

   

public String direccion_ip_red(){

        String ip, nueva_ip="";
        String r[]= new String[]{};
        try {
            ip=InetAddress.getLocalHost().getHostAddress();
            System.out.println("direccion: "+ip);

                    ip=ip.replace(".", ":");
                  //  System.out.println("modificado: "+ip);
                    r=ip.split(":");//dividiendo por punto cada octeto
                  //  System.out.println("divisiones: "+r.length);
                    for(int i=0; i<r.length; i++){//recorriendo la estructura de la ip
                    //    System.out.println("split: "+r[i]);
                            if(i==3){//analizando el ultimo octeto
                               // System.out.println("ultimo octeto: "+r[i]);
                                if(r[i].equalsIgnoreCase("100") || r[i].equalsIgnoreCase("1"))nueva_ip="localhost";//si la ip de la maquina termina en 70 es porque es el servidor y se trabaja en localhost
                                else nueva_ip=nueva_ip.concat("100");//el ultimo octeto que corresponde al servidor. y se conecta en modo remoto
                            }else{
                                nueva_ip=nueva_ip.concat(r[i]+".");//de aqui se obtienen los primeros tres octetos

                            }
                    }//fin recorrido estructura ip

                    System.out.println("vieja_ip: "+ip+"   nueva_ip: "+nueva_ip);


        } catch (UnknownHostException ex) {
            Logger.getLogger(Conexion_bd.class.getName()).log(Level.SEVERE, null, ex);
        }
return nueva_ip;
    }



   public Connection getConexion() {
        Connection conexion = null;
         String url;
        	try {
            this.setConfirmacion("false");
			Class.forName("com.mysql.jdbc.Driver");                        
                        usuario = "jdbc:mysql://200.150.168.180"+"trxxxdssed";
                        clave = "++--dd-d";
 
//UNEFA CAGUA MARACAY
//System.out.println(this.getDireccion());
     if(this.getDireccion().equalsIgnoreCase("localhost")){//se conecta en modo local
            url = "jdbc:mysql://localhost"; //personal
            conexion = DriverManager.getConnection(url,"root","clave");//conexion normal andy
          }else{//se conecta en modo remoto

            url="jdbc:mysql://"+this.getDireccion(); //control de estudio cagua
            conexion = DriverManager.getConnection(url,"usuarioRemoto","clave");//conexion Servidor IBM  mi maquina - cagua - maracay
         

       }
  


            this.setConfirmacion("true");
           

            
        } catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                new JFrame(),e.getLocalizedMessage()+
                "Base de Datos no encontrada",
                "ADVERTENCIA",
                JOptionPane.INFORMATION_MESSAGE);

            System.out.println(e.getLocalizedMessage()+"Base de Datos no encontrada");

	}

        return conexion;
    }

   
    
        }//fin de la clase