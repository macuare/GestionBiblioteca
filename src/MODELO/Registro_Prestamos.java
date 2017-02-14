/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author AURORA
 */
public class Registro_Prestamos {

   Mensajes m;
   
   
   
    public Registro_Prestamos() {
        m = new Mensajes();
    }

   
    
    
    private String[] verificar_usuario(String cedula){
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       
       String usuario = "";
       String estado ="INACTIVO";
       
                try {             

                      sentencia = con.createStatement();
                      resultado = sentencia.executeQuery("SELECT * FROM biblioteca.usuarios WHERE cedula='"+cedula+"';");
                      if(resultado.next()){
                          usuario = resultado.getString("nombres")+", "+resultado.getString("apellidos");
                          estado = resultado.getString("estado");
                      }else{
                          m.notificacion("ESTE USUARIO NO SE ENCUENTRA REGISTRADO EN EL SISTEMA...!!!");
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
                
    return new String[]{usuario,estado};    
    }
    
    public void verificacion(TextField cedula, TextField usuario, Button prestar,ObservableList<Modelo_Prestamos> datos, TableView<Modelo_Prestamos> prestados){
        
        if(cedula.getText().isEmpty()){
           m.error("DEBE COLOCAR PRIMERO LA CÉDULA...!!!");
       }else{
            String informacion[] = this.verificar_usuario(cedula.getText());
            if(informacion[0].isEmpty()){//si el usuario no existe
                
            }else{
                if(informacion[1].equalsIgnoreCase("ACTIVO")){//verificando que ademas de existir este activo el usuario para habilitar la interfaz
                    cedula.setEditable(false);
                    usuario.setText(informacion[0]);
                    prestar.setDisable(false);
                    this.libros_asignados(cedula.getText(), datos, prestados);
                }else{
                    m.notificacion("DISCULPE, PERO ESTE USUARIO SE ENCUENTRA INACTIVO...!!!\nCONSULTAR AL JEFE DE BIBLIOTECA.");
                }
            }           
       }
    }
    
    private void prestando(String cedula, String cota, String f_entrega, String tipo, String estado, String responsable){
          Connection con = new Conexion_bd().getConexion();      
                    Statement sentencia = null;

                     try {             

                           sentencia = con.createStatement();
                           sentencia.execute("INSERT INTO biblioteca.prestamos VALUES (NULL,"
                                             +"'"+cedula+"',"    
                                             +"'"+cota+"',"
                                             +"DATE_FORMAT(NOW(),'%d/%m/%Y - %r'),"
                                             +"CONCAT('"+f_entrega+" - ',DATE_FORMAT(NOW(),'%r')),"
                                             +"'"+tipo+"',"
                                             +"'"+estado+"',"
                                             +"'"+responsable+"',''"
                                             + " );");              

                              sentencia.close(); 
                           m.notificacion("EL PRESTAMO HA SIDO ALMACENADO CON EXITO...!!!");   


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
    
    }
    
    private boolean verificar_formato_fecha(String fecha){
        boolean correcto = false;
     if(fecha!=null || fecha.length()>0){
        // System.out.println("fase 1");
        if(fecha.contains("/")){//verificando que contenga el slash            
        //    System.out.println("fase 2");
          if(fecha.split("/").length>2 && fecha.split("/").length<4){  //asegurando que tenga los 3 slash
           //   System.out.println("fase 3");
            String dia = fecha.split("/")[0];
            String mes = fecha.split("/")[1];
            String año = fecha.split("/")[2];        
            
            if(año.length()>3 && año.length()<5){ //se verifica año, mes, dia
              //  System.out.println("fase 4");
                if(mes.length()>1 && mes.length()<3){
                 //   System.out.println("fase 5");
                    if(dia.length()>1 && dia.length()<3){//solo cuando se pase este analisis significa que las demas tambien por lo que se puede decir que la fecha esta correctamente escrita
                     //   System.out.println("fase 6");
                        correcto = true;
                    }
                }
            }
          }
        }
     }
        
        return correcto;
    }
    
    private void prestar_libro(String cedula, String cota, String f_entrega, String tipo, String estado, String responsable){
        cota = this.adecuar_cota(cota);//verificando la cota
        if(cota!=null){
            if(this.verificar_formato_fecha(f_entrega)){

               if((this.libros_prestados(cedula).size()/6)<3){   //solo se prestara el libro cuando el estudiante tenga menos de 3 libros y ademas el libro este en existencia
                  if(this.prestada_y_existente(cota)){             
                      this.prestando(cedula, cota, f_entrega, tipo, estado, responsable);             
                  }else{
                       m.notificacion("DISCULPE, PERO NO SE PUEDE PRESTAR EL LIBRO AL USUARIO DEBIDO A QUE:\n"                        
                                  + " YA NO HAY MAS DISPONIBLES.");

                   }
               }else{     
                   m.notificacion("DISCULPE, PERO NO SE PUEDE PRESTAR EL LIBRO AL USUARIO DEBIDO A QUE:\n"                        
                                  + " TIENE LA CANITIDAD MÁXIMA PERMITIDA.");
               }

           }else{
                   m.notificacion("DISCULPE, PERO LA FECHA ESTÁ MAL ESCRITA");      

           }
       }else{
            m.notificacion("DISCULPE, PERO ESCRIBA CORRECTAMENTE LA COTA DEL LIBRO");      
        
        }
    }
    
    
    
    
    
    public LinkedList<String> libros_prestados(String cedula){   
        
       LinkedList<String> prestados = new LinkedList();
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
        
        try {             
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.prestamos WHERE cedula='"+cedula+"' AND (estado='ACTIVO' OR estado='MANTENIMIENTO' OR estado='MOROSO');");
              while(resultado.next()){
                  prestados.add(resultado.getString("cota")); //0
                  prestados.add(resultado.getString("f_salida"));//1
                  prestados.add(resultado.getString("f_entrega"));//2
                  prestados.add(resultado.getString("tipo"));//3
                  prestados.add(resultado.getString("estado"));//4
                  prestados.add(resultado.getString("responsable"));//5                  
              }
              
              System.out.println("en mano: "+prestados.size()/6);
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
    
    
    public String adecuar_cota(String cota){
        String adaptacion=null;
      if(cota!=null){  
        if(cota.length()>1){
            String aux[] = cota.split("-");
            if(aux.length>2 && aux.length<4){//tres elementos
                adaptacion = aux[0].toUpperCase()+"-"+aux[1].toUpperCase()+"-"+aux[2].toLowerCase();//000-C117-ej1; mayuscula-mayuscula-minuscula
            }
        }
      }
      
    return adaptacion;
    }
    
    private boolean prestada_y_existente(String cota){
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       boolean prestar = false;
       int prestado = 0;
       int existente = 0;
       
        try {             
              
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.prestamos WHERE cota='"+cota+"' AND (estado='ACTIVO' OR estado='MANTENIMIENTO' OR estado='MOROSO');");
              while(resultado.next()){
                      prestado = prestado + 1;//contando la cantidad de libros prestados de la misma cota segun consideraciones      
              }
              
                sentencia.close();    
                resultado.close();    
          
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cota='"+cota+"';");
              if(resultado.next()){
                      existente = resultado.getInt("cantidad");//contando la cantidad de libros prestados de la misma cota segun consideraciones      
              }else{//es porque la cota no existe
                      m.error("DISCULPE PERO LA COTA QUE COLOCO NO EXISTE, VERIFIQUE...!!!");
                      
              }
              
                sentencia.close();    
                resultado.close();      
          
                
              if(prestado<existente){
                  prestar = true;
              }  
                
                
          
          
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
        
    return prestar;
    
    }
    
    private String estado_usuario(String cedula){
        Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       String estado = "INACTIVO";
     
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.usuarios WHERE cedula='"+cedula+"' ;");
              if(resultado.next()){
                estado = resultado.getString("estado");                  
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
        return estado;
    }
    
    public void prestar(TextField cedula, TextField cota, TextField f_entrega, ComboBox tipo, Button prestar, TextField usuario, ObservableList<Modelo_Prestamos> datos, TableView<Modelo_Prestamos> prestados){
        

            if(m.confirmacion("DESEA PRESTAR ESTE LIBRO...!!!").equalsIgnoreCase("aceptar")){
              
                
                //this.prestar_libro(cedula.getText(), cota.getText(), f_entrega.getText(), tipo.getSelectionModel().getSelectedItem().toString(), "ACTIVO", "15196976 - ANDY CUSATTI");
                this.prestar_libro(cedula.getText(), cota.getText(), f_entrega.getText(), tipo.getSelectionModel().getSelectedItem().toString(), "ACTIVO", Registro_Autenticacion.personal);
                this.libros_asignados(cedula.getText(), datos, prestados);

                if(m.confirmacion("DESEA PRESTAR OTRO LIBRO A ESTE USUARIO...!!!").equalsIgnoreCase("aceptar")){

                }else{
                    cedula.setText("");
                    cedula.setEditable(true);
                    prestar.setDisable(true);
                    cota.setText("");
                    usuario.setText("");  
                    f_entrega.setText("");
                }

            }else{        
                m.notificacion("PROCESO DE PRESTAMO CANCELADO...!!!");
            }
        
    }
    
   private String informacion_cota(String cota){
   
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       String titulo="";
     
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cota='"+cota+"' ;");
              if(resultado.next()){
                titulo = resultado.getString("titulo");                  
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

   return titulo;
   } 
    
   public void libros_asignados(String cedula, ObservableList<Modelo_Prestamos> datos, TableView<Modelo_Prestamos> prestados){
    Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
       ResultSet resultado = null; 
       String titulo="";
       datos.clear();
       
        try {
              sentencia = con.createStatement();
              resultado = sentencia.executeQuery("SELECT * FROM biblioteca.prestamos WHERE cedula='"+cedula+"' AND (estado='ACTIVO' OR estado='MANTENIMIENTO' OR estado='MOROSO');");
              while(resultado.next()){
                 //paso 1: se llena el panel
                  Etiquetas eti = new Etiquetas();
                       eti.Etiquetas_prestado(  this.informacion_cota(resultado.getString("cota")),
                                                resultado.getString("cota"),                                                
                                                resultado.getString("f_salida").substring(0, 10),
                                                resultado.getString("f_entrega").substring(0, 10),                                               
                                                resultado.getString("estado"),                                                
                                                "Este libro ha sido prestado por: "
                                                +resultado.getString("responsable")
                                                +", el día "+resultado.getString("f_salida").split(" - ")[0]
                                                +" a la hora "+resultado.getString("f_salida").split(" - ")[1]
                                                +".\n"+resultado.getString("observaciones"));                  
                       
                 //paso 2: se usa la clase para llenar la tabla
                  Pane panel = eti.etiqueta_prestado(new Registro_Libros().recuperar_imagen_BD(resultado.getString("cota"),150,150).getImage());              
                  panel = this.analisis_estado(resultado.getString("f_entrega"), cedula, resultado.getString("cota"),resultado.getString("estado") , panel);
                  datos.add(new Modelo_Prestamos(panel));
                  
              }
              
              //paso 3: despues de llenar el arreglo se agrega la informacion a la tabla
              prestados.setItems(datos);
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
    
    
   public void entregando(TextField cedula, ObservableList<Modelo_Prestamos> datos, TableView<Modelo_Prestamos> prestados){
     if(prestados.getSelectionModel().isEmpty()){//verificando que halla una celda al menos seleccionada
         m.notificacion("DISCULPE, PERO DEBE SELECCIONAR UN LIBRO PRIMERO...!!!");         
     }else{
       
       if(m.confirmacion("DESEA ENTREGAR EL LIBRO?").equalsIgnoreCase("aceptar")){
           
            String cota = "";
            ObservableList<Node> nodos = prestados.getSelectionModel().getSelectedItem().getPanel().getChildren();//reteniendo todos los elementos del panel seleccionado de la tabla
           // System.out.println("elementos: "+nodos.size());
            for(int i=0; i<nodos.size(); i++){//recorriendo todos los nodos del panel
                Node nodo = nodos.get(i);
              //  System.out.println(nodo);
                if(nodo instanceof Label){//si el nodo es un textfield se revisa su contenido 
                  //  System.out.println("encontrado texto");
                    String texto = ((Label)(nodo)).getText();
                   if(texto.startsWith("COTA:")){//si el nodo en cuestion es el que busco extraigo su contenido y dejo de revisar
                       cota = texto.split(": ")[1]; 
                       break;
                   }           
                }
            }

         //   System.out.println("cota sele:"+cota);
            if(cota.length()>0){
             this.entregar_libro(cedula.getText(), cota);
             this.libros_asignados(cedula.getText() ,datos, prestados);
            }else{
                m.error("LA COTA SELECCIONADA TIENE PROBLEMAS...!!!");
            }
       
       }else{
           m.notificacion("PROCESO DE ENTREGA CANCELADO...!!!");
       }
   }
     
   }
    
    
    
    private void entregar_libro(String cedula, String cota ){
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
      //AND (estado='ACTIVO' OR estado='MANTENIMIENTO' OR estado='MOROSO')
       
       
          try {
            
            
              sentencia = con.createStatement();
              sentencia.executeUpdate("UPDATE biblioteca.prestamos SET estado='ENTREGADO' WHERE cedula='"+cedula+"' AND cota='"+cota+"';");
              
              sentencia.close();
              
              m.notificacion("EL LIBRO "+cota+", FUE ENTREGADO SIN PROBLEMAS...!!!");
              
              
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
    
    
    public void actualizar_estado(String cedula, String cota, String estado, String entrega){
       Connection con = new Conexion_bd().getConexion();      
       Statement sentencia = null;
          try {
              sentencia = con.createStatement();
              sentencia.executeUpdate("UPDATE biblioteca.prestamos SET estado='MOROSO' WHERE cedula='"+cedula+"' AND cota='"+cota+"' AND f_entrega='"+entrega+"';");              
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
    
    
    public boolean entrega_vs_prestamo(String fentrega){
        
        Calendar cal = Calendar.getInstance();
        
        int dia_actual = cal.get(Calendar.DAY_OF_MONTH);
        int mes_actual = cal.get(Calendar.MONTH)+1;
        int año_actual = cal.get(Calendar.YEAR);
        
        int dia_entrega = Integer.valueOf(fentrega.split(" - ")[0].split("/")[0]);
        int mes_entrega = Integer.valueOf(fentrega.split(" - ")[0].split("/")[1]);
        int año_entrega = Integer.valueOf(fentrega.split(" - ")[0].split("/")[2]);
        
        boolean moroso = true;
        
            if(dia_entrega >= dia_actual && mes_entrega >= mes_actual && año_entrega < año_actual ||
               dia_entrega >= dia_actual && mes_entrega < mes_actual && año_entrega <= año_actual ||  
               dia_entrega < dia_actual && mes_entrega > mes_actual && año_entrega < año_actual   ||
               dia_entrega < dia_actual && mes_entrega <= mes_actual && año_entrega <= año_actual      
              ){ moroso = true;}else{moroso = false;}
       
       return moroso;        
    }
    
    private void informacion_estado(Pane panel){
    
         ObservableList<Node> nodos = panel.getChildren();//reteniendo todos los elementos del panel seleccionado de la tabla
           // System.out.println("elementos: "+nodos.size());
            for(int i=0; i<nodos.size(); i++){//recorriendo todos los nodos del panel
                Node nodo = nodos.get(i);
              //  System.out.println(nodo);
                if(nodo instanceof Label){//si el nodo es un textfield se revisa su contenido 
                  //  System.out.println("encontrado texto");
                    String texto = ((Label)(nodo)).getText();
                   if(texto.startsWith("ESTADO:")){//si el nodo en cuestion es el que busco extraigo su contenido y dejo de revisar
                       ((Label)(nodo)).setText("ESTADO: MOROSO");
                       break;
                   }           
                }
            }
    
    }
    
    public Pane analisis_estado(String fentrega, String cedula, String cota, String estado, Pane panel){
       if(estado.equalsIgnoreCase("ACTIVO")){ 
           System.out.println("cota: "+cota+"   estado:"+estado);
           
            if(this.entrega_vs_prestamo(fentrega.substring(0, 10))){//si esta moroso se cambia el estado del prestamo a moroso
                this.actualizar_estado(cedula,cota,"MOROSO",fentrega);
                this.informacion_estado(panel);
                 panel.setStyle("-fx-background-color: rgba(178, 44, 44, 0.3);");
            }    
       }else{//se cnsidera los otros estados
           if(estado.equalsIgnoreCase("MANTENIMIENTO")){
               panel.setStyle("-fx-background-color: rgba(255, 255, 58, 0.3);");
           }
           
           if(estado.equalsIgnoreCase("MOROSO")){
               panel.setStyle("-fx-background-color: rgba(178, 44, 44, 0.3);");
           }
       }
       
       return panel;
    }
    
    
    
    
    
    
    
    
}//fin de la clase
