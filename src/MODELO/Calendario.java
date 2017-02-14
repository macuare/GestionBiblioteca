/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author AURORA
 */
public class Calendario {
   private String fecha_actual, fecha_seleccionada;
   private final Stage stage = new Stage(StageStyle.TRANSPARENT);
   private final TextField mes_año;// =  new TextField("");            
 
    public Calendario() {
         mes_año =  new TextField(""); 
         
         Calendar cal = Calendar.getInstance();
         fecha_actual = cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR); //solo se graba la primera vez como referencia
    }

    
     private int mes_numeros(String meses){
        int numero=0;
        if(meses.equalsIgnoreCase("ENERO")) numero = 0;
        if(meses.equalsIgnoreCase("FEBRERO")) numero = 1;
        if(meses.equalsIgnoreCase("MARZO")) numero = 2;
        if(meses.equalsIgnoreCase("ABRIL")) numero = 3;
        if(meses.equalsIgnoreCase("MAYO")) numero = 4;
        if(meses.equalsIgnoreCase("JUNIO")) numero = 5;
        if(meses.equalsIgnoreCase("JULIO")) numero = 6;
        if(meses.equalsIgnoreCase("AGOSTO")) numero = 7;
        if(meses.equalsIgnoreCase("SEPTIEMBRE")) numero = 8;
        if(meses.equalsIgnoreCase("OCTUBRE")) numero = 9;
        if(meses.equalsIgnoreCase("NOVIEMBRE")) numero = 10;
        if(meses.equalsIgnoreCase("DICIEMBRE")) numero = 11;
    
            return numero;
    }
    
     private String mes_letras(int meses){
        String nombre=null;
        if(meses==0) nombre = "ENERO";
        if(meses==1) nombre = "FEBRERO";
        if(meses==2) nombre = "MARZO";
        if(meses==3) nombre = "ABRIL";
        if(meses==4) nombre = "MAYO";
        if(meses==5) nombre = "JUNIO";
        if(meses==6) nombre = "JULIO";
        if(meses==7) nombre = "AGOSTO";
        if(meses==8) nombre = "SEPTIEMBRE";
        if(meses==9) nombre = "OCTUBRE";
        if(meses==10) nombre = "NOVIEMBRE";
        if(meses==11) nombre = "DICIEMBRE";
        
        return nombre;
    }
    
  
     private String calculo_mes_año(String texto, String accion){
         int mes = this.mes_numeros(texto.split(" - ")[0]);//traducir el mes a numero y se almacena en la variable
         int año = Integer.valueOf(texto.split(" - ")[1]);//obteniendo el año
         
         
         if(accion.equalsIgnoreCase("sumar")){
             mes = mes + 1;
             if(mes>11){ mes = 0; año = año + 1;}//si se supera el mes 12 se suma 1 año y se establece enero
         }
         
         if(accion.equalsIgnoreCase("restar")){
             mes = mes - 1;
             if(mes<0){ mes = 11; año = año - 1;}//en la resta si da negativo es porque dio el año anterior. se establece diciembre y se resta 1 año
         }
         
         return this.mes_letras(mes)+" - "+año;
     }
     
     
     
    
    private LinkedList<String> dias_semana(){
        
        LinkedList<String> dias  = new LinkedList<String>();
            dias.add("DO");
            dias.add("LU");
            dias.add("MA");
            dias.add("MI");
            dias.add("JU");
            dias.add("VI");
            dias.add("SA");
    return dias;
    }
    
    
    
    private TextField campo_texto(String texto){
            
            final TextField tf = new TextField(texto.split("/")[0]);
            tf.setAlignment(Pos.CENTER);
            tf.setEditable(false);
            tf.setPrefSize(34.0, 46.0);
          if("SEM,DO,LU,MA,MI,JU,VI,SA".contains(texto.split("/")[0])){
            tf.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"
                      + "-fx-background-radius: 50;"
                      + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                      + "-fx-text-fill: white;");
          }else{
            if(texto.equalsIgnoreCase(fecha_actual)){//si una de las fechas es igual a la actual entonces se cambia su estilo para resltar
                tf.setStyle("-fx-background-color: rgba(44, 209, 72, 0.5);"
                      + "-fx-background-radius: 50;"
                      + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                      + "-fx-text-fill: white;"); 
                tf.setTooltip(new Tooltip("ESTA ES LA FECHA ACTUAL...!!!"));
            }else{//sino sera parte de las demas
                tf.setStyle("-fx-background-color: rgba(38, 0, 253, 0.5);"
                      + "-fx-background-radius: 50;"
                      + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"
                      + "-fx-text-fill: white;");            
            }  
            
            
            //las acciones es igual para los textos relacionados con la fecha           
                tf.setOnMouseEntered(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                          tf.setScaleX(1.1);  tf.setScaleY(1.1);                   
                      }
                  });
                tf.setOnMouseExited(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                         tf.setScaleX(1.0);  tf.setScaleY(1.0); 
                      }
                  });
                
                tf.setOnMouseClicked(new EventHandler<MouseEvent>() {
                       @Override
                       public void handle(MouseEvent event) {
                          fecha_seleccionada = tf.getText()+"/"+mes_año.getText().split(" - ")[0]+"/"+mes_año.getText().split(" - ")[1]; 
                         // System.out.println("FECHA SELECCIONADA: "+tf.getText()+"/"+mes_año.getText().split(" - ")[0]+"/"+mes_año.getText().split(" - ")[1]);
                          stage.close();
                       }
                });
          }
            
            
        return tf;
    }
    
     
    
    private void fecha(GridPane malla, String meses, String años, TextField visor){
        //malla.getChildren().clear();//limpiando todo los campos de textos agregados
        Calendar c = Calendar.getInstance();       
        
        if(meses!=null && años!=null){            
            int mes = Integer.valueOf(meses);
            int año = Integer.valueOf(años);
            c.set(Calendar.MONTH,mes-1);//se establece el mes
            c.set(Calendar.YEAR, año);//se establece el año
        }else{        }
        
        
         visor.setText(this.mes_letras(c.get(Calendar.MONTH))+" - "+c.get(Calendar.YEAR));//colocando el mes y año en el visor
        
        
     //   System.out.println("año: "+c.get(Calendar.YEAR));
        
       
        
        int dm = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        int conteo_dia = 0;
        int conteo_sm = 1;
        int conteo_ds = 0;
        boolean desplazado = false;//se utiliza para hacer referencia que el mes no empieza en la primera semana sino en la segunda
        
      //  System.out.println("dias maximos: "+dm);
        
        
        for(int i=1; i<=dm; i++){//recorriendo los dias del mes
            
                c.set(Calendar.DAY_OF_MONTH, i);//estableciendo dia del mes           
                int fecha = c.get(Calendar.DAY_OF_MONTH);
                int dias = c.get(Calendar.DAY_OF_WEEK);
                int semana = c.get(Calendar.WEEK_OF_MONTH);


        //        System.out.println("ordenando: dm:"+c.get(Calendar.DAY_OF_MONTH)+" ds:"+c.get(Calendar.DAY_OF_WEEK)+" sm:"+c.get(Calendar.WEEK_OF_MONTH));

                //-----------------------
                 if(fecha==1 && dias==1 && semana==1){//mes desplazado
                     desplazado = true; //lo que se hace es colocar bien las fechas para evitar el desplazamiento de la primera fila
        //             System.out.println("Mes con desplazamiento");
                     malla.add(this.campo_texto(fecha+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)), dias, semana);
                     conteo_sm = conteo_sm + 1;
                 }else{
                //-----------------------

                        if(desplazado){//si hubo desplazamiento del mes hay que hacer las consideraciones y reordenar a 5 filas
                            if(conteo_sm == semana && dias>conteo_ds){//se hace la consideracion de colocacion de la fecha del domingo a causa de la semana
                                // malla.add(this.campo_texto(""+fecha), dias, semana-1); 
                                 malla.add(this.campo_texto(fecha+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)), dias, semana-1);
                            }else{                                   
                                // malla.add(this.campo_texto(""+fecha), dias, semana);
                                  malla.add(this.campo_texto(fecha+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)), dias, semana);
                                 conteo_sm = conteo_sm + 1;
                            }  

                        }else{//sino es un mes normal

                            if(conteo_sm == semana && dias>conteo_ds){//se hace la consideracion de colocacion de la fecha del domingo a causa de la semana
                                 //malla.add(this.campo_texto(""+fecha), dias, semana);
                                 malla.add(this.campo_texto(fecha+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)), dias, semana);
                            }else{                               
                                 //malla.add(this.campo_texto(""+fecha), dias, semana+1);
                                 malla.add(this.campo_texto(fecha+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)), dias, semana+1);
                                 conteo_sm = conteo_sm + 1;
                            }  


                        }
                //-----------------------     
                 }
                 // dm:1 ds:1 sm:1  
                  // malla.add(this.campo_texto(""+c.get(Calendar.DAY_OF_MONTH)), c.get(Calendar.DAY_OF_WEEK), c.get(Calendar.WEEK_OF_MONTH));
                   conteo_dia = fecha;//se coloca luego la fecha actual.
                   conteo_ds = dias;
                 //  System.out.println("agregando"+dia);
  
            
            
        }//fin recorrido dias del mes
        
        
       
    
    }
    
    
    public void limpiar_malla(GridPane malla){        
     // System.out.println("NODOS: "+malla.getChildren().size());
        for(int i=0; i<malla.getChildren().size(); i++){//recorriendo los nodos del panel
            Node nodo = malla.getChildren().get(i);
           // System.out.println("indice: "+i);

            if(nodo!=null){//verificando que no este vacio 
                 if("SEM,DO,LU,MA,MI,JU,VI,SA".contains(((TextField)(nodo)).getText())){//SI CONTIENE LOS DIAS DE LA SEMANA Y SEMANA IGNORAR
                     //System.out.println("contenido: "+((TextField)(nodo)).getText());
                 }else{//Sino se borra el nodo contenido
                    // System.out.println("removiendo: "+((TextField)(nodo)).getText());
                     malla.getChildren().remove(i,malla.getChildren().size());//remover segun el indice donde este
                     break;
                 }
            }
           
           
        }
      
    }
    
    /*Una vez presentado el calendario, devuleve la fecha seleccionada en el patron xx/xx/xxxx */
    public String interfaz_grafica(){
        
        //PASO 0: CREANDO EL STAGE
         
        stage.setTitle("CALENDARIO");
        
        
        //PASO 1: CREANDO EL GRIDPANE CON FILAS Y COLUMNAS
            ColumnConstraints columnas = new ColumnConstraints(35.0);//columna de 35 de ancho
            columnas.setHgrow(Priority.SOMETIMES);
            RowConstraints filas = new RowConstraints(47.0);//filas

            final GridPane malla = new GridPane();
            malla.setLayoutX(10.0); malla.setLayoutY(49.0);
            //malla.setPrefSize(272.0, 272.0);
            malla.setPrefSize(272.0, 319.0);
            //malla.setStyle("-fx-background-color: rgba(0, 100, 100, 0.1); -fx-background-radius: 10;");
            
            
            for(int i=0;i<8;i++){//agregando 8 columas
                malla.getColumnConstraints().add(columnas);
            }

            for(int i=0;i<8;i++){//agregando 6 filas
                malla.getRowConstraints().add(filas);
            }
            
            
        //PASO 2: TEXTFIELD DEL MES y AÑO  
            
            /*final TextField mes_año = new TextField("ABRIL - 2013");*/
            mes_año.setStyle("-fx-background-color:        #ecebe9,        rgba(0,0,0,0.05),        linear-gradient(#dcca8a, #c7a740),        linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),        linear-gradient(#f6ebbe, #e6c34d);    "
                     + "      -fx-background-insets: 0,9 9 8 9,9,10,11;    -fx-background-radius: 50;    -fx-padding: 10 20 10 20;    -fx-font-family: \"Helvetica\";    -fx-font-size: 12px;    -fx-text-fill: #311c09;    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
            mes_año.setAlignment(Pos.CENTER);
            mes_año.setEditable(false);
            mes_año.setPrefWidth(145.0);
            mes_año.setLayoutX(80.0); mes_año.setLayoutY(8.0);
          /*  mes_año.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                System.out.println("click");
                                            }
                                        });*/
        
         //PASO 3: CREANDO LOS BOTONES
            
            final Button atras = new Button("<<");
            atras.setLayoutX(14.0); atras.setLayoutY(14.0);
            atras.setStyle("-fx-background-color: rgb(112, 84, 16, 0.3);"
                          +"-fx-effect: dropshadow( one-pass-box , black , 10 , 0.0 , 4 , 4);");
            atras.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                   //Calendario z = new Calendario();
                                    limpiar_malla(malla);
                                    String aux = calculo_mes_año(mes_año.getText(), "restar");                                   
                                    fecha(malla,String.valueOf(mes_numeros(aux.split(" - ")[0])+1), aux.split(" - ")[1], mes_año);                                    
                                }
                              });
            atras.setOnMouseEntered(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                          atras.setScaleX(1.2);  atras.setScaleY(1.2);                   
                      }
                  });
            atras.setOnMouseExited(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                         atras.setScaleX(1.0);  atras.setScaleY(1.0); 
                      }
                  });
            
            
           final Button adelante = new Button(">>");
            adelante.setLayoutX(249.0); adelante.setLayoutY(14.0);
            adelante.setStyle("-fx-background-color: rgb(112, 84, 16, 0.3);"
                          +"-fx-effect: dropshadow( one-pass-box , black , 10 , 0.0 , 4 , 4);");
            adelante.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        //Calendario z = new Calendario();
                                        limpiar_malla(malla);
                                        String aux = calculo_mes_año(mes_año.getText(), "sumar");                                   
                                        fecha(malla,String.valueOf(mes_numeros(aux.split(" - ")[0])+1), aux.split(" - ")[1], mes_año);                                    
                                    }
                                 });
            adelante.setOnMouseEntered(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                          adelante.setScaleX(1.2);  adelante.setScaleY(1.2);                   
                      }
                  });
            adelante.setOnMouseExited(new EventHandler<MouseEvent>() {
                      @Override
                      public void handle(MouseEvent event) {
                         adelante.setScaleX(1.0);  adelante.setScaleY(1.0); 
                      }
                  });
        
            
            
        //PASO 4: AGREGANDO LA CABECERA
            
            
            malla.add(this.campo_texto("SEM"), 0, 0);
            malla.add(this.campo_texto("DO"), 1, 0);
            malla.add(this.campo_texto("LU"), 2, 0);
            malla.add(this.campo_texto("MA"), 3, 0);
            malla.add(this.campo_texto("MI"), 4, 0);
            malla.add(this.campo_texto("JU"), 5, 0);
            malla.add(this.campo_texto("VI"), 6, 0);
            malla.add(this.campo_texto("SA"), 7, 0);
          
        //PASO 5: AGREGANDO LOS DIAS Y LAS SEMANAS
            Calendar cal = Calendar.getInstance();            
            this.fecha(malla,String.valueOf(cal.get(Calendar.MONTH)+1),String.valueOf(cal.get(Calendar.YEAR)),mes_año);
            
        //PASO 6: AGREGANDO EL PANEL Y AÑADIENDO TODOS LOS ELEMENTOS
            Pane panel = new Pane();
            //panel.setPrefSize(300.0, 340.0);
            panel.setPrefSize(300.0, 387.0);
            panel.getChildren().addAll(malla,atras,adelante,mes_año);
          //  String estil = "-fx-background-color:rgba(0,0,0,0.01)";
            panel.setStyle("-fx-background-color: rgba(0, 100, 100, 0.1); -fx-background-radius: 10;");
            panel.setBlendMode(BlendMode.MULTIPLY);
           
            
        //PASO 7: MOSTRAR TODO Y ESPERAR LAS ACCIONES             
             Scene escena = new Scene(panel);        
             escena.setFill(null);//transparencia
             escena.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
             stage.initModality(Modality.APPLICATION_MODAL);
             stage.setScene(escena);
             stage.showAndWait();
            
        //PASO 8: RETURNANDO LA FECHA SELECCIONADA
            DecimalFormat df = new DecimalFormat("00");
            fecha_seleccionada =  df.format(Integer.valueOf(fecha_seleccionada.split("/")[0]))+"/"
                                 +df.format(mes_numeros(fecha_seleccionada.split("/")[1])+1)+"/"   
                                 +fecha_seleccionada.split("/")[2];             
             
            return fecha_seleccionada;
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}//fin de la clase