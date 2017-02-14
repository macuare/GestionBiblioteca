/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 *
 * @author AURORA
 */
public class Mensajes {

    public Mensajes() {
    }
    
    
    
  
    
    
    public void notificacion(String mensaje){
        
        
        //PASO 1:
         final Stage stage = new Stage(StageStyle.UNDECORATED);
               stage.setTitle("NOTIFICACIÓN");
               
               
        
        //PASO 2:
        ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream("/IMAGENES/animal_notificador.jpg")));
        imagen.setPreserveRatio(false);
        imagen.setFitHeight(250.0);
        imagen.setFitWidth(400.0);
        imagen.setPickOnBounds(true);
        
        //PASO 3:
        TextArea area = new TextArea(mensaje);
        area.setWrapText(true);
        area.setEditable(false);
        area.setLayoutX(14.0); area.setLayoutY(145.0);
        area.setPrefWidth(372.0);
        area.setPrefHeight(64.0);
        area.setStyle("-fx-background-color: rgba(5, 95, 255, 0.1); -fx-background-radius: 10;");
                
        //PASO 4:
        final Button boton = new Button("ACEPTAR");
        boton.setLayoutX(161.0); boton.setLayoutY(216.0);
        boton.setPrefHeight(26.0); boton.setPrefWidth(92.0);
        boton.setStyle("-fx-background-color:linear-gradient(#f0ff35, #a9ff00),radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);-fx-background-radius: 6, 5;-fx-background-insets: 0, 1;-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );-fx-text-fill: #395306;");
                boton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.hide();
                    }
                });
                
                boton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        boton.setScaleX(1.2f);
                        boton.setScaleY(1.2f);
                    }
                });
                
                boton.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){ 
                            boton.setScaleX(1.0f);
                            boton.setScaleY(1.0f);                    
                    }
                });
        
        //PASO 5
        Pane panel = new Pane();
        panel.setPrefHeight(250.0);
        panel.setPrefWidth(400.0);       
        panel.getChildren().addAll(imagen,area,boton);
        
    
        //PASO 6
        Scene escena = new Scene(panel);        
        stage.initModality(Modality.APPLICATION_MODAL);
        escena.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
        stage.setScene(escena);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), panel);
                        fadeTransition.setFromValue(0.0f);
                        fadeTransition.setToValue(1.0f);
                        fadeTransition.play();
        stage.showAndWait();
        
        
    
    }
    
    /**SALIDAS: nada, aceptar, cancelar */
    public String confirmacion(String mensaje){
         String respuesta = "nada";
            
                 //PASO 1:
                 final Stage stage = new Stage(StageStyle.TRANSPARENT);
                 stage.setTitle("CONFIRMACIÓN");
               
        
                //PASO 2:
                ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream("/IMAGENES/confirmacion.jpg")));
                imagen.setPreserveRatio(false);
                imagen.setFitHeight(250.0);
                imagen.setFitWidth(400.0);
                imagen.setPickOnBounds(true);

                //PASO 3:
                TextArea area = new TextArea(mensaje);
                area.setWrapText(true);
                area.setEditable(false);
                area.setLayoutX(177.0); area.setLayoutY(28.0);
                area.setPrefWidth(220.0);
                area.setPrefHeight(135.0);
                area.setStyle("-fx-background-color: rgba(251, 204,45, 0.6);-fx-background-radius: 20;-fx-font-weight: bold;-fx-font-size: 14px;");

                //PASO 4:
                
                final Button boton = new Button("ACEPTAR");
                boton.setLayoutX(51.0); boton.setLayoutY(209.0);
                boton.setPrefHeight(37.0); boton.setPrefWidth(129.0);
                boton.setStyle("-fx-background-color:linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44),linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));-fx-background-radius: 30;-fx-background-insets: 0,1,2,3,0;-fx-text-fill: #654b00;-fx-font-weight: bold;-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
                        boton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                boton.setId("aceptar");
                                stage.hide();
                                
                            }
                        });

                        boton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                boton.setScaleX(1.2f);
                                boton.setScaleY(1.2f);
                            }
                        });

                        boton.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event){ 
                                    boton.setScaleX(1.0f);
                                    boton.setScaleY(1.0f);                    
                            }
                        });
                    
                final Button boton2 = new Button("CANCELAR");
                boton2.setLayoutX(216.0); boton2.setLayoutY(209.0);
                boton2.setPrefHeight(37.0); boton2.setPrefWidth(129.0);
                boton2.setStyle("-fx-background-color:linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44),linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));-fx-background-radius: 30;-fx-background-insets: 0,1,2,3,0;-fx-text-fill: #654b00;-fx-font-weight: bold;-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
                        boton2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                boton.setId("cancelar");
                                stage.hide();
                            }
                        });

                        boton2.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                boton2.setScaleX(1.2f);
                                boton2.setScaleY(1.2f);
                            }
                        });

                        boton2.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event){ 
                                    boton2.setScaleX(1.0f);
                                    boton2.setScaleY(1.0f);                    
                            }
                        });      
                        

                //PASO 5
                Pane panel = new Pane();
                panel.setPrefHeight(250.0);
                panel.setPrefWidth(400.0);       
                panel.getChildren().addAll(imagen,area,boton,boton2);


                //PASO 6
                Scene escena = new Scene(panel);        
                stage.initModality(Modality.APPLICATION_MODAL);
                escena.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
                stage.setScene(escena);
                stage.showAndWait();
    
     
    
        if(boton.getId()!=null && boton.getId().equalsIgnoreCase("aceptar")) respuesta = "aceptar";
            if(boton2.getId()!=null && boton2.getId().equalsIgnoreCase("cancelar")) respuesta = "cancelar";
            
        return respuesta;
    }
    
    
    
    public File buscar_archivo(){
    
        FileChooser selector = new FileChooser();
        LinkedList<String> lista = new LinkedList<String>();
        lista.add("*.png");
        lista.add("*.jpg");        
        selector.getExtensionFilters().add(new FileChooser.ExtensionFilter("Solo imagenes", lista));
    
        File archivo = selector.showOpenDialog(null);
        
        return archivo;
    }
    /**Este metodo se encarga de tomar la direccion donde se puede guardar los archivos.</br>
     se debe colocar las extensiones de interes ejm: "*.png","*.jpg"
     */
    public File guardar_archivo(String[] extensiones){
    
        FileChooser selector = new FileChooser();
        //LinkedList<String> lista = new LinkedList<String>();
        selector.setTitle("GUARDAR ARCHIVO");
        //lista.add("*.png");
        //lista.add("*.jpg");
        //selector.getExtensionFilters().add(new FileChooser.ExtensionFilter("", lista));
        selector.getExtensionFilters().add(new FileChooser.ExtensionFilter("Tipos de Archivos",extensiones));           
        File archivo = selector.showSaveDialog(null);
        
        archivo = this.analisis_ruta(selector, archivo);
        
        return archivo;
    }
    
    public File analisis_ruta(FileChooser guardar, File archivo){
        boolean correcto = false;
      if(archivo!=null){  
        for(int i=0; i<guardar.getExtensionFilters().size(); i++){//recorriendo los filtros apilados
            List<String> extensiones = guardar.getExtensionFilters().get(i).getExtensions();
            for(int f=0; f<extensiones.size(); f++){//recorriendo las extensiones de cada filtro
                if(archivo.getAbsolutePath().contains(extensiones.get(f).substring(2))){//verificando que la ruta contenga el archivo con su extension y que no solo sea una extension el que este como nombre. analisando esto (archivo.extension)
                    correcto = true;
                    break;
                }else{
                    correcto = false;
                }                
            }
            if(correcto) break;//se rompe el ciclo exterior
        }
        
        //despues del analisis se toma las conclusiones
        if(correcto){
            
        }else{
           archivo = new File(archivo.getPath().concat("."+guardar.getExtensionFilters().get(0).getExtensions().get(0).substring(2)));//se crea un archivo por defecto y extension
        }
     }
        return archivo;
    }
    
    
    
     public String texto_entrada(String mensaje){
         String respuesta = "nada";
            
                 //PASO 1:
                 final Stage stage = new Stage(StageStyle.TRANSPARENT);
                 stage.setTitle("CONFIRMACIÓN");
               
        
                //PASO 2:
                ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream("/IMAGENES/confirmacion.jpg")));
                imagen.setPreserveRatio(false);
                imagen.setFitHeight(250.0);
                imagen.setFitWidth(400.0);
                imagen.setPickOnBounds(true);

                //PASO 3:
                TextArea area = new TextArea(mensaje);
                area.setWrapText(true);
                area.setEditable(false);
                area.setLayoutX(14.0); area.setLayoutY(28.0);
                area.setPrefWidth(372.0);
                area.setPrefHeight(60.0);
                area.setStyle("-fx-background-color: rgba(251, 204,45, 0.6);-fx-background-radius: 20;-fx-font-weight: bold;-fx-font-size: 14px;");

                TextField entrada = new TextField();
                entrada.setPromptText("Escriba aquí");
                entrada.setLayoutX(51.0); entrada.setLayoutY(114.0);
                entrada.setPrefHeight(37.0); entrada.setPrefWidth(294.0);
                   
                
                //PASO 4:
                
                final Button boton = new Button("ACEPTAR");
                boton.setLayoutX(51.0); boton.setLayoutY(209.0);
                boton.setPrefHeight(37.0); boton.setPrefWidth(129.0);
                boton.setStyle("-fx-background-color:linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44),linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));-fx-background-radius: 30;-fx-background-insets: 0,1,2,3,0;-fx-text-fill: #654b00;-fx-font-weight: bold;-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
                        boton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {                                
                                stage.hide();
                                
                            }
                        });

                        boton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                boton.setScaleX(1.2f);
                                boton.setScaleY(1.2f);
                            }
                        });

                        boton.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event){ 
                                    boton.setScaleX(1.0f);
                                    boton.setScaleY(1.0f);                    
                            }
                        });
                    
                final Button boton2 = new Button("CANCELAR");
                boton2.setLayoutX(216.0); boton2.setLayoutY(209.0);
                boton2.setPrefHeight(37.0); boton2.setPrefWidth(129.0);
                boton2.setStyle("-fx-background-color:linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44),linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));-fx-background-radius: 30;-fx-background-insets: 0,1,2,3,0;-fx-text-fill: #654b00;-fx-font-weight: bold;-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
                        boton2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {                                
                                stage.hide();
                            }
                        });

                        boton2.setOnMouseEntered(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                boton2.setScaleX(1.2f);
                                boton2.setScaleY(1.2f);
                            }
                        });

                        boton2.setOnMouseExited(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event){ 
                                    boton2.setScaleX(1.0f);
                                    boton2.setScaleY(1.0f);                    
                            }
                        });      
                        

                //PASO 5
                Pane panel = new Pane();
                panel.setPrefHeight(250.0);
                panel.setPrefWidth(400.0);       
                panel.getChildren().addAll(imagen,area, entrada,boton,boton2);


                //PASO 6
                Scene escena = new Scene(panel);        
                stage.initModality(Modality.APPLICATION_MODAL);
                escena.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
                stage.setScene(escena);
                stage.showAndWait();
    
     if(entrada.getText().equalsIgnoreCase("Escriba aquí")){
         respuesta = "nada";
     }else{
         respuesta = entrada.getText();
     }
    
            
        return respuesta;
    }
    
    
    
    public void error(String mensaje){
        
        
        //PASO 1:
         final Stage stage = new Stage(StageStyle.UNDECORATED);
               stage.setTitle("ERROR");
               
               
        
        //PASO 2:
        ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream("/IMAGENES/error.jpg")));
        imagen.setPreserveRatio(false);
        imagen.setFitHeight(250.0);
        imagen.setFitWidth(400.0);
        imagen.setPickOnBounds(true);
        
        //PASO 3:
        TextArea area = new TextArea(mensaje);
        area.setWrapText(true);
        area.setEditable(false);
        area.setLayoutX(7.0); area.setLayoutY(7.0);
        area.setPrefWidth(386.0);
        area.setPrefHeight(236.0);
        area.setStyle("-fx-background-color: rgba(251, 204,45, 0.3); -fx-background-radius: 20;&#10;    -fx-font-weight: bold;&#10;    -fx-font-size: 14px;");
  //
    
        //PASO 4:
        final Button boton = new Button("ACEPTAR");
        boton.setLayoutX(136.0); boton.setLayoutY(199.0);
        boton.setPrefHeight(37.0); boton.setPrefWidth(129.0);
        boton.setStyle("-fx-background-color:linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44),linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));-fx-background-radius: 30;-fx-background-insets: 0,1,2,3,0;-fx-text-fill: #654b00;-fx-font-weight: bold;-fx-font-size: 14px;-fx-padding: 10 20 10 20;");
                boton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.hide();
                    }
                });
                
                boton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        boton.setScaleX(1.2f);
                        boton.setScaleY(1.2f);
                    }
                });
                
                boton.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){ 
                            boton.setScaleX(1.0f);
                            boton.setScaleY(1.0f);                    
                    }
                });
        
        //PASO 5
        Pane panel = new Pane();
        panel.setPrefHeight(250.0);
        panel.setPrefWidth(400.0);       
        panel.getChildren().addAll(imagen,area,boton);
        
    
        //PASO 6
        Scene escena = new Scene(panel);        
        stage.initModality(Modality.APPLICATION_MODAL);
        escena.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
        stage.setScene(escena);
        stage.showAndWait();
    
    }
    
    
     public void detalles(String mensaje){
        
        
        //PASO 1:
         final Stage stage = new Stage(StageStyle.UNDECORATED);
               stage.setTitle("DETALLES");
               
               
        
        //PASO 2:
        ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream("/IMAGENES/detalles.jpg")));
        imagen.setPreserveRatio(false);
        imagen.setFitHeight(250.0);
        imagen.setFitWidth(400.0);
        imagen.setPickOnBounds(true);
        
        //PASO 3:
        TextArea area = new TextArea(mensaje);
        area.setWrapText(true);
        area.setEditable(false);
        area.setLayoutX(7.0); area.setLayoutY(7.0);
        area.setPrefWidth(386.0);
        area.setPrefHeight(236.0);
        area.setStyle("-fx-background-color: rgba(251, 204,45, 0.3); -fx-background-radius: 20;&#10;    -fx-font-weight: bold;&#10;    -fx-font-size: 14px;&#10;  -fx-text-fill: white;");
  //
    
        //PASO 4:
        final Button boton = new Button("ACEPTAR");
        //boton.setLayoutX(136.0); boton.setLayoutY(199.0);
        boton.setLayoutX(250.0); boton.setLayoutY(199.0);
        boton.setPrefHeight(37.0); boton.setPrefWidth(129.0);
        boton.setStyle("-fx-background-color:linear-gradient(#ffd65b, #e68400),linear-gradient(#ffef84, #f2ba44),linear-gradient(#ffea6a, #efaa22),linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));-fx-background-radius: 30;-fx-background-insets: 0,1,2,3,0;-fx-text-fill: #654b00;-fx-font-weight: bold;-fx-font-size: 12px;-fx-padding: 6 12 6 12;");
                boton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.hide();
                    }
                });
                
                boton.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        boton.setScaleX(1.2f);
                        boton.setScaleY(1.2f);
                    }
                });
                
                boton.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){ 
                            boton.setScaleX(1.0f);
                            boton.setScaleY(1.0f);                    
                    }
                });
        
        //PASO 5
        Pane panel = new Pane();
        panel.setPrefHeight(250.0);
        panel.setPrefWidth(400.0);       
        panel.getChildren().addAll(imagen,area,boton);
        
    
        //PASO 6
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
    
    }
    
    
    
    
}//fin de la clase
