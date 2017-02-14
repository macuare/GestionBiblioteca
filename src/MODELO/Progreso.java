/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author AURORA
 */
public class Progreso {
   private int maximo, minimo, actual;
   private String informacion;

  private ProgressBar barra = null;
  private Label etiqueta=null;
   
    public Progreso(int maximo, int minimo, int actual, String informacion) {
        this.maximo = maximo;
        this.minimo = minimo;
        this.actual = actual;
        this.informacion = informacion;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void grafico(){
        final Stage stage = new Stage();
        
        barra = new ProgressBar();
        barra.setProgress(-0.1);
       // barra.progressProperty().bind(actual);
        barra.setPrefSize(371.0, 42.0);
        barra.setLayoutX(11.0);barra.setLayoutY(30.0);
        
        etiqueta = new Label(informacion);
        etiqueta.setLayoutX(11.0);etiqueta.setLayoutY(96.0);
        etiqueta.setPrefWidth(371.0);
        
        Pane panel = new Pane();
        panel.setPrefSize(392.0, 149.0);
        
        panel.getChildren().addAll(barra,etiqueta);
        
        Scene escena = new Scene(panel);        
        stage.initModality(Modality.NONE);
        escena.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
        stage.setScene(escena);
        stage.show();
        
        
    }
    
    
    public void segundo_plano(){
        this.grafico();
        
        Task tarea = new Task() {

            @Override
            protected Object call() throws Exception {
                while(true){//
                    System.out.println("procesando:"+actual);
                    updateProgress(actual/maximo, maximo);
                    if(actual>=maximo) break;
                }
                return null;
                
            }
        };
        
        barra.progressProperty().bind(tarea.progressProperty()) ;
    
        
      new Thread(tarea).start();  
    
    }
    
    
    
    
    
    
    
}//fin de la clase
