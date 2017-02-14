/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author AN
 */
public class Barra_Progreso {
    private double maximo, actual;
    private String informacion, titulo;

    private ProgressBar barra;
    private Label etiqueta;

    public Barra_Progreso(double maximo, double actual, String informacion, String titulo) {
        this.maximo = maximo;
        this.actual = actual;
        this.informacion = informacion;
        this.titulo = titulo;
        //this.segundo_plano();
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
    
    
    
    
    public void interfaz_grafica(){
       
        final Stage stage = new Stage();
        stage.setTitle(titulo);
        
        barra = new ProgressBar();
        barra.setLayoutX(14.0);barra.setLayoutY(26.0);
        barra.setPrefWidth(394.0);barra.setPrefHeight(40.0);
        barra.setProgress(actual);
        
        etiqueta = new Label(informacion);
        etiqueta.setLayoutX(14.0);etiqueta.setLayoutY(84.0);
        etiqueta.setPrefWidth(394.0);
        
        
        Pane panel = new Pane();        
        panel.setPrefWidth(422.0);panel.setPrefHeight(118.0);
        
        panel.getChildren().addAll(barra,etiqueta);
        
        Scene escena = new Scene(panel);
        escena.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
        stage.setScene(escena);
        stage.show();
        
    /*    
    <Pane layoutX="89.0" layoutY="49.0" prefHeight="118.0" prefWidth="422.0">
      <children>
        <Label fx:id="informacion" layoutX="14.0" layoutY="84.0" prefWidth="394.0" text="Label" />
        <ProgressBar fx:id="barra" layoutX="14.0" layoutY="26.0" prefHeight="40.0" prefWidth="394.0000999999975" progress="0.0" />
      </children>
    </Pane>
    */
    }
    
    public void segundo_plano(){        
                this.interfaz_grafica();
                
        Task tarea = new Task() {                        
            @Override
            public void run() {
                super.run();
            //    System.out.println("CORRIENDO PROGRAMA: "+actual+" - "+maximo);
              
                 while(true){
                     
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {                 
              //                  System.out.println("calculo: "+(actual/maximo));
                                barra.setProgress((actual/maximo));
                                etiqueta.setText(informacion);                                
                            }
                        });
                        
                     double valor = (actual/maximo);  
                     System.out.println("ACTUALIZANDO VALORES ->"+actual+" - "+maximo);
                     if(valor>=1.0) break;  
                    try {
                        new Thread().sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Barra_Progreso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            
            @Override
            protected Object call() throws Exception {             
                return null;
            }
        };
        
        new Thread(tarea).start();
        
    }
    
    
    
    
}//fin de la clase
