/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author AURORA
 */
public class Etiquetas {

    private String titulo,autores,categoria,editorial,edicion,isbn,pais,paginas,cantidad,academico,industrial;
    private String nombres,apellidos,telf_cel, telf_hab, correo,direccion,institucion,estado,f_registro; 
    private String observaciones,f_entrega,f_salida,cota;

    public Etiquetas() {
    }

    public void Etiquetas_inventario(String titulo, String autores, String categoria, String editorial, String edicion, String isbn, String pais, String paginas, String cantidad, String academico, String industrial) {
        this.titulo = titulo;
        this.autores = autores;
        this.categoria = categoria;
        this.editorial = editorial;
        this.edicion = edicion;
        this.isbn = isbn;
        this.pais = pais;
        this.paginas = paginas;
        this.cantidad = cantidad;
        this.academico = academico;
        this.industrial = industrial;
    }

    public void Etiquetas_usuarios(String nombres, String apellidos, String telf_cel, String telf_hab, String correo, String direccion, String institucion, String estado, String f_registro) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telf_cel = telf_cel;
        this.telf_hab = telf_hab;
        this.correo = correo;
        this.direccion = direccion;
        this.institucion = institucion;
        this.estado = estado;
        this.f_registro = f_registro;
    }
    
     public void Etiquetas_prestado(String titulo, String cota, String f_salida, String f_entrega, String estado, String observaciones) {
        this.titulo = titulo;
        this.cota = cota;
        this.f_salida = f_salida;
        this.f_entrega = f_entrega;
        this.estado = estado;
        this.observaciones = observaciones;
    }
    
    
    
    
    public Pane etiqueta_inventario(Image foto){
    
      // FASE 1: Foto del Libro
        //ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream("/MODELO/libro_portada.jpg")));
        ImageView imagen = new ImageView(foto);
        imagen.setPreserveRatio(false);
        imagen.setFitHeight(80.0);
        imagen.setFitWidth(70.0);
        imagen.setLayoutX(2);
        imagen.setLayoutY(7);
        imagen.setPickOnBounds(true);
        
      //FASE 2: ETIQUETAS
        Label et1 = new Label("TITULO:");
        et1.setLayoutX(80.0); et1.setLayoutY(1.0); et1.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et2 = new Label("AUTORES:");
        et2.setLayoutX(80.0); et2.setLayoutY(19.0); et2.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et3 = new Label("CATEGORIA:");
        et3.setLayoutX(80.0); et3.setLayoutY(36.0); et3.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et4 = new Label("EDITORIAL:");
        et4.setLayoutX(80.0); et4.setLayoutY(54.0); et4.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et5 = new Label("EDICION:");
        et5.setLayoutX(80.0); et5.setLayoutY(72.0); et5.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et6 = new Label("ISBN:");
        et6.setLayoutX(356.0); et6.setLayoutY(54.0); et6.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et7 = new Label("PAIS:");
        et7.setLayoutX(518.0); et7.setLayoutY(53.0); et7.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et8 = new Label("PAGINAS:");
        et8.setLayoutX(183.0); et8.setLayoutY(72.0); et8.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et9 = new Label("CANTIDAD:");
        et9.setLayoutX(317.0); et9.setLayoutY(72.0); et9.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et10 = null; Label et11=null;
       if(academico.length()>2 || industrial.length()>2){
            et10 = new Label("TUTOR ACADÉMICO:");
            et10.setLayoutX(80.0); et10.setLayoutY(92.0); et10.setFont(Font.font("System", FontWeight.BOLD, 12)); 
            et11 = new Label("TUTOR INDUSTRIAL:");
            et11.setLayoutX(80.0); et11.setLayoutY(112.0); et11.setFont(Font.font("System", FontWeight.BOLD, 12)); 
       }
      //FASE 3: AGREGANDO CONTENIDO  
        Label cont1 = new Label(titulo);
        cont1.setLayoutX(134.0); cont1.setLayoutY(1.0); cont1.setPrefWidth(556.0);        
        Label cont2 = new Label(autores);
        cont2.setLayoutX(141.0); cont2.setLayoutY(19.0); cont2.setPrefWidth(542.0);
        Label cont3 = new Label(categoria);
        cont3.setLayoutX(152.0); cont3.setLayoutY(36.0); cont3.setPrefWidth(218.0);        
        Label cont4 = new Label(editorial);
        cont4.setLayoutX(153.0); cont4.setLayoutY(54.0); cont4.setPrefWidth(204.0);
        Label cont5 = new Label(edicion);
        cont5.setLayoutX(135.0); cont5.setLayoutY(72.0); cont5.setPrefWidth(35.0);        
        Label cont6 = new Label(isbn);
        cont6.setLayoutX(389.0); cont6.setLayoutY(54.0); cont6.setPrefWidth(129.0);        
        Label cont7 = new Label(pais);
        cont7.setLayoutX(550.0); cont7.setLayoutY(53.0); cont7.setPrefWidth(133.0);        
        Label cont8 = new Label(paginas);
        cont8.setLayoutX(238.0); cont8.setLayoutY(72.0); cont8.setPrefWidth(35.0);
        Label cont9 = new Label(cantidad);
        cont9.setLayoutX(384.0); cont9.setLayoutY(72.0); cont9.setPrefWidth(66.0);
        Label cont10=null; Label cont11=null;
        if(academico.length()>2 || industrial.length()>2){
            cont10 = new Label(academico);
            cont10.setLayoutX(200.0); cont10.setLayoutY(92.0); cont10.setPrefWidth(460.0);
            cont11 = new Label(industrial);
            cont11.setLayoutX(200.0); cont11.setLayoutY(112.0); cont11.setPrefWidth(460.0);
        }
      //FASE 4: CONFIGURANDO EL PANEL          
        Pane panel = new Pane();
        panel.setLayoutX(85.0); panel.setLayoutY(113.0); 
        if(academico.length()>2 || industrial.length()>2){
            panel.setPrefHeight(130.0); panel.setPrefWidth(690.0);
        }else{
            panel.setPrefHeight(93.0); panel.setPrefWidth(690.0);
        }
       // panel.setStyle("-fx-border-color:black;-fx-background-color:yellow;");  
     //   panel.setStyle("-fx-background-color:#c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);"+
       //                "-fx-background-radius: 10; -fx-background-insets: 0,1,1; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        
   /*    panel.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);"+
                      "-fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"+
                       "-fx-text-fill: #395306;);");
    
     */
       // panel.setStyle(" -fx-background-color:        #c3c4c4,        linear-gradient(#d6d6d6 50%, white 100%),        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);    -fx-background-radius: 30;    -fx-background-insets: 0,1,1;    -fx-text-fill: black;    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
       
       // panel.setStyle("-fx-background-color:       #090a0c,        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),        linear-gradient(#20262b, #191d22),        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));    -fx-background-radius: 5,4,3,5;    -fx-background-insets: 0,1,2,0;    -fx-text-fill: white;    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );    -fx-font-family: \"Arial\";    -fx-text-fill: linear-gradient(white, #d0d0d0);    -fx-font-size: 12px;    -fx-padding: 10 20 10 20;");
        
        panel.setStyle("-fx-background-color:        #3c7fb1,        linear-gradient(#fafdfe, #e8f5fc),        linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);");        
        
        
      //FASE 5: AGREGANDO TODOS LOS NODOS AL PANEL  
        
      if(academico.length()>2 || industrial.length()>2){
          panel.getChildren().addAll(
                imagen,
                et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,
                cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8,cont9,cont10,cont11
                );
        }else{
          panel.getChildren().addAll(
                imagen,
                et1,et2,et3,et4,et5,et6,et7,et8,et9,
                cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8,cont9
                );
        }  
       
        
      return panel;
        
    }
    
    
    
     public Pane etiqueta_usuarios(Image foto){
    
      // FASE 1: Foto del Libro
        //ImageView imagen = new ImageView(new Image(getClass().getResourceAsStream("/MODELO/libro_portada.jpg")));
        ImageView imagen = new ImageView(foto);
        imagen.setPreserveRatio(false);
        imagen.setFitHeight(80.0);
        imagen.setFitWidth(70.0);
        imagen.setLayoutX(2);
        imagen.setLayoutY(7);
        imagen.setPickOnBounds(true);
        
      //FASE 2: ETIQUETAS
        Label et1 = new Label("NOMBRES:");
        et1.setLayoutX(80.0); et1.setLayoutY(1.0); et1.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et2 = new Label("APELLIDOS:");
        et2.setLayoutX(80.0); et2.setLayoutY(19.0); et2.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et3 = new Label("CELULAR:");
        et3.setLayoutX(80.0); et3.setLayoutY(36.0); et3.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et4 = new Label("CASA:");
        et4.setLayoutX(80.0); et4.setLayoutY(54.0); et4.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et5 = new Label("CORREO:");
        et5.setLayoutX(80.0); et5.setLayoutY(72.0); et5.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et6 = new Label("ISBN:");
        et6.setLayoutX(356.0); et6.setLayoutY(54.0); et6.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et7 = new Label("PAIS:");
        et7.setLayoutX(518.0); et7.setLayoutY(53.0); et7.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et8 = new Label("PAGINAS:");
        et8.setLayoutX(183.0); et8.setLayoutY(72.0); et8.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        Label et9 = new Label("CANTIDAD:");
        et9.setLayoutX(317.0); et9.setLayoutY(72.0); et9.setFont(Font.font("System", FontWeight.BOLD, 12)); 
        
      //FASE 3: AGREGANDO CONTENIDO  
        Label cont1 = new Label(titulo);
        cont1.setLayoutX(134.0); cont1.setLayoutY(1.0); cont1.setPrefWidth(556.0);        
        Label cont2 = new Label(autores);
        cont2.setLayoutX(141.0); cont2.setLayoutY(19.0); cont2.setPrefWidth(542.0);
        Label cont3 = new Label(categoria);
        cont3.setLayoutX(152.0); cont3.setLayoutY(36.0); cont3.setPrefWidth(218.0);        
        Label cont4 = new Label(editorial);
        cont4.setLayoutX(153.0); cont4.setLayoutY(54.0); cont4.setPrefWidth(204.0);
        Label cont5 = new Label(edicion);
        cont5.setLayoutX(135.0); cont5.setLayoutY(72.0); cont5.setPrefWidth(35.0);        
        Label cont6 = new Label(isbn);
        cont6.setLayoutX(389.0); cont6.setLayoutY(54.0); cont6.setPrefWidth(129.0);        
        Label cont7 = new Label(pais);
        cont7.setLayoutX(550.0); cont7.setLayoutY(53.0); cont7.setPrefWidth(133.0);        
        Label cont8 = new Label(paginas);
        cont8.setLayoutX(238.0); cont8.setLayoutY(72.0); cont8.setPrefWidth(35.0);
        Label cont9 = new Label(cantidad);
        cont9.setLayoutX(384.0); cont9.setLayoutY(72.0); cont9.setPrefWidth(66.0);
       
      //FASE 4: CONFIGURANDO EL PANEL          
        Pane panel = new Pane();
        panel.setLayoutX(85.0); panel.setLayoutY(73.0); 
        panel.setPrefHeight(93.0); panel.setPrefWidth(690.0);
       // panel.setStyle("-fx-border-color:black;-fx-background-color:yellow;");  
     //   panel.setStyle("-fx-background-color:#c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);"+
       //                "-fx-background-radius: 10; -fx-background-insets: 0,1,1; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        
       panel.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);"+
                      "-fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"+
                       "-fx-text-fill: #395306;);");
    
      //FASE 5: AGREGANDO TODOS LOS NODOS AL PANEL  
        panel.getChildren().addAll(
                imagen,
                et1,et2,et3,et4,et5,et6,et7,et8,et9,
                cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8,cont9
                );
        
      return panel;
        
    }
    
    public Pane etiqueta_prestado(Image foto){
    
        ImageView imagen = new ImageView(foto);
        imagen.setPreserveRatio(false);
        imagen.setFitHeight(150.0);
        imagen.setFitWidth(150.0);
        imagen.setLayoutX(2);
        imagen.setLayoutY(5);
        imagen.setPickOnBounds(true);
    
        TextArea area = new TextArea("TÍTULO: "+titulo);
        area.setLayoutX(153.0);area.setLayoutY(4.0);
        area.setPrefSize(615.0, 48);
        area.setWrapText(true);
        area.setEditable(false);
        area.setStyle("-fx-background-color:transparent; -fx-border-color:lightblue; fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        
        Label et1 = new Label("COTA: "+cota);
        et1.setFont(Font.font("Arial", FontWeight.NORMAL, 12)); 
        et1.setLayoutX(153.0); et1.setLayoutY(56.0);
        et1.setPrefWidth(278.0);
        
        Label et2 = new Label("SALIDA: "+f_salida);
        et2.setFont(Font.font("Arial", FontWeight.NORMAL, 12)); 
        et2.setLayoutX(153.0); et2.setLayoutY(91.0);
        et2.setPrefWidth(278.0);
        
        Label et3 = new Label("ENTREGA: "+f_entrega);
        et3.setFont(Font.font("Arial", FontWeight.NORMAL, 12)); 
        et3.setLayoutX(153.0); et3.setLayoutY(128.0);
        et3.setPrefWidth(278.0);
        
        Label et4 = new Label("ESTADO: "+estado);
        et4.setFont(Font.font("Arial", FontWeight.NORMAL, 12)); 
        et4.setLayoutX(439.0); et4.setLayoutY(56.0);
        et4.setPrefWidth(329.0);
        
        TextArea area2 = new TextArea("OBSERVACIÓN: "+observaciones);
        area2.setLayoutX(438.0);area2.setLayoutY(89.0);
        area2.setPrefSize(329.0, 65);
        area2.setWrapText(true);
        area2.setEditable(false);
        area2.setStyle("-fx-background-color:transparent; -fx-border-color:lightblue; fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        
        Pane panel = new Pane();
       // panel.setLayoutX(85.0); panel.setLayoutY(73.0); 
        panel.setPrefHeight(160.0); panel.setPrefWidth(770.0);
       // panel.setStyle("-fx-border-color:black;-fx-background-color:yellow;");  
     //   panel.setStyle("-fx-background-color:#c3c4c4, linear-gradient(#d6d6d6 50%, white 100%), radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);"+
       //                "-fx-background-radius: 10; -fx-background-insets: 0,1,1; -fx-text-fill: black; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );");
        
      /* panel.setStyle("-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);"+
                      "-fx-background-radius: 6, 5; -fx-background-insets: 0, 1; -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"+
                       "-fx-text-fill: #395306;);");
    */
        
     panel.setStyle("-fx-border-color:lightblue;");
  
        panel.getChildren().addAll(
                imagen,
                et1,et2,et3,et4,
                area, area2
                );
        
      return panel;
        
    
    }
    
    
    
    
    
    
    
    
}//fin de la clase
