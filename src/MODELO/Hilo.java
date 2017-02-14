/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author AN
 */
public class Hilo extends Task{
    private double valor,minimo,maximo;
    private String informacion;
    private boolean continuar = true;
    private ProgressBar bp;
    private Label etiqueta, avance;
    private final Stage st;
    private Mensajes mensaje;
    
    
    public Hilo() {
        valor = 0.0;
        minimo = 0.0;
        maximo = 100.0;
        mensaje = new Mensajes();
        st = new Stage();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    
    
    public void grafica(){
        try {
                       
            this.setValor(-0.1);
            
                    Parent root = FXMLLoader.load(getClass().getResource("/MODELO/barras.fxml")); 
                    Scene scene = new Scene(root);
              
               for(int i=0; i<root.getChildrenUnmodifiable().size(); i++){
                   
                   if(root.getChildrenUnmodifiable().get(i) instanceof ProgressBar){
                       bp = (ProgressBar) root.getChildrenUnmodifiable().get(i);
                   }
               
                   if(root.getChildrenUnmodifiable().get(i) instanceof Label){
                     //  System.out.println("elemento: "+root.getChildrenUnmodifiable().get(i).getId());
                       
                       if(root.getChildrenUnmodifiable().get(i).getId().equalsIgnoreCase("informacion")){
                        etiqueta = (Label) root.getChildrenUnmodifiable().get(i);
                       // System.out.println("seleccionado: informacion");
                       }
                       
                       if(root.getChildrenUnmodifiable().get(i).getId().equalsIgnoreCase("avance")){
                        avance = (Label) root.getChildrenUnmodifiable().get(i);
                        //System.out.println("seleccionado: avance");
                       }
                       
                   }
               }
                    
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          st.setX(event.getScreenX());
                                          st.setY(event.getScreenY());
                                         }
                                        });
            st.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(null);
            st.setScene(scene);
            st.show();
           
            
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    @Override
    protected Object call() throws Exception {
        //this.grafica();
        
        System.out.print("EJECUTANDO TAREA");
        
        while(continuar){                           
            Thread.sleep(100);
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                   double calculo =  valor/maximo;
                   bp.setProgress(new Double(calculo));
                   avance.setText((calculo*100)+" %");
                  
                   //System.out.println("progreso: "+bp.getProgress());
                   etiqueta.setText(informacion);
                  // System.out.println("comentario: "+informacion);
                }
            }                    
                            );
           // System.out.println("valor:maximo   "+valor+":"+maximo);
            if(valor>=maximo){ continuar = false; }
            
        }
        
        System.out.println("FIN DE LA TAREA"); 
      //  mensaje.notificacion("PROCESO FINALIZADO...!!!");
        System.out.println("cerrando la ventana");
       
        st.close();
        
        System.out.println("ventanaCERRADA"); 
        
        return null;
    }
    
    
    
    
    
    public void ejecutar_segundo_plano(){
        this.grafica();        
        new Thread(this).start();
        
    }
            
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//fin de la clase
