/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Tooltip;

/**
 *
 * @author AURORA
 */
public class Registro_Estadisticas {
    
    private Mensajes m;

    public Registro_Estadisticas() {
        m = new Mensajes();
    }
/*    
You can change the color of a bar using the setStyle API as follows.
e.g. if the color of the first bar in the first series of the bar chart needs to be set :-
barChart.getData().get(0).getData().get(0).getNode().setStyle("-fx-background-color: black,red; -fx-background-insets: 0, 2; -fx-background-radius: 5px; -fx-padding: 5px;");    
 */   
    
    
    
    
    public void configuracion(AreaChart<String, Number> grafica, CategoryAxis categorias, NumberAxis numeros){
        grafica.getData().clear();  
        
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        
        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));
        
        
        
        grafica.getData().add(series);
        
    
    }
    
    
    private LinkedList<String> determinar_categorias(LinkedList<String> registros,int saltos ,int agrupar_por){
        LinkedList<String> elementos = new LinkedList<String>();
        
        
        for(int i=0; i<registros.size(); i=i+saltos){
            if(elementos.isEmpty()){//como esta vacio se agrega el primer elemento
                elementos.add(registros.get(i+agrupar_por));//se guarda el elemento de interes
            }else{
                boolean existe = false;
                
                for(int r=0; r<elementos.size(); r++){//se recorre todos los elementos para comparacion 
                    if(elementos.get(r).equalsIgnoreCase(registros.get(i+agrupar_por))){                    
                        existe = true;
                        break;//ya existe el campo de interes registrado
                    }else{
                        existe = false;
                    }
                }//fin recorrido elementos
                
                if(existe==false) elementos.add(registros.get(i+agrupar_por));//se guarda el elemento de interes despues de revisar la lista y no ser encontrado
                
            }
        }//fin recorrido registros
    
    return elementos;
    }
    
    private String identificador_mes(String m){
        //System.out.println(m);
        String mes="NE";
        if(m.equalsIgnoreCase("01")) mes = "Enero";
        if(m.equalsIgnoreCase("02")) mes = "Febrero";
        if(m.equalsIgnoreCase("03")) mes = "Marzo";
        if(m.equalsIgnoreCase("04")) mes = "Abril";
        if(m.equalsIgnoreCase("05")) mes = "Mayo";
        if(m.equalsIgnoreCase("06")) mes = "Junio";
    
        if(m.equalsIgnoreCase("07")) mes = "Julio";
        if(m.equalsIgnoreCase("08")) mes = "Agosto";
        if(m.equalsIgnoreCase("09")) mes = "Septiembre";
        if(m.equalsIgnoreCase("10")) mes = "Octubre";
        if(m.equalsIgnoreCase("11")) mes = "Noviembre";
        if(m.equalsIgnoreCase("12")) mes = "Diciembre";
        
    return mes;
    }
    
    
    public void mas_prestados(AreaChart<String, Number> grafica, CategoryAxis categorias, NumberAxis numeros){
        Registro_Mensual rm = new Registro_Mensual();
        grafica.getData().clear();
        
        LinkedList<String> aux = rm.libros_prestados();//estan todos los libros prestados
        LinkedList<String> aux2 = this.determinar_categorias(aux, 7, 4);//se muestra los registros agrupados por estado
        DecimalFormat df = new DecimalFormat("00");
        LinkedList<String> datos = new LinkedList<String>();
        
        for(int m=1; m<=12; m++){//recorrido por mes
            for(int x=0; x<aux2.size(); x++){//recorriendo por agrupacion
                int contar = 0;
                for(int i=0; i<aux.size(); i=i+7){//recorriendo todos los elementos
                    if(aux2.get(x).equalsIgnoreCase(aux.get(i+4)) && df.format(m).equalsIgnoreCase(aux.get(i+1).split(" - ")[0].split("/")[1])){//solo si hay coincidencia en categoria y mes
                        contar = contar + 1;
                    }
                }//recorriendo todos los registros        
                
              datos.add(df.format(m));//agregando mes
              datos.add(aux2.get(x));//agregando categoria
              datos.add(String.valueOf(contar));//agregando cantidad
              
            }//fin recorrido por agrupacion
        }//fin recorrido por mes

        ObservableList<Series<String,Number>> actual = FXCollections.observableArrayList();
        XYChart.Series serie;
      
            for(int g=0; g<datos.size(); g=g+3){//recorriendo los datos filtrados            
                if(actual.isEmpty()){//si esta vacio se almacena el primer registro
                    serie = new XYChart.Series();
                    serie.setName(datos.get(g+1));//guardando el nombre del estado
                    serie.getData().add(new XYChart.Data(this.identificador_mes(datos.get(g)), Integer.valueOf(datos.get(g+2))));//guardando mes y cantidad                    
                    actual.add(serie);
                }else{//sino se revisa los guardados y se almacena en la serie que contiene sus registros
                    boolean existe=false;
                    for(int z=0; z<actual.size(); z++){
                        if(actual.get(z).getName().equalsIgnoreCase(datos.get(g+1))){//si coinciden los nombres
                            existe = true;
                            serie = actual.get(z);
                            serie.getData().add(new XYChart.Data(this.identificador_mes(datos.get(g)), Integer.valueOf(datos.get(g+2))));//se busca la serie de interes y se le agrega el valor                        
                          //  System.out.println("interno: "+serie.getName()+" longitud:"+serie.getData().size());
                          //  System.out.println("Longitud serie:"+serie.getData().get(1).toString());
                            break;
                        }else{
                            existe = false;
                        }                                       
                    }              
                
                    if(existe == false){//despues de revisar todas las series y esta no existe entonce se agrega
                        serie = new XYChart.Series();
                        serie.setName(datos.get(g+1));//guardando el nombre del estado
                        serie.getData().add(new XYChart.Data(this.identificador_mes(datos.get(g)), Integer.valueOf(datos.get(g+2))));//guardando mes y cantidad
                        actual.add(serie);
                    }
                
                }
                
            }//fin recorrido datos filtrados [Data[mes01,0,null], Data[mes02,0,null], Data[mes03,1,null]

        grafica.getData().addAll(actual);
    
    }
    
    public void barrasx(BarChart<String,Number> grafica_barras){
        grafica_barras.getData().clear();
        
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data("austria", 25601.34));
        series1.getData().add(new XYChart.Data("brasil", 20148.82));
        series1.getData().add(new XYChart.Data("venezuela", 10000));
        series1.getData().add(new XYChart.Data("italia", 35407.15));
        series1.getData().add(new XYChart.Data("argentina", 12000));      
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series1.getData().add(new XYChart.Data("austria", 25601.34));
        series1.getData().add(new XYChart.Data("brasil", 20148.82));
        series1.getData().add(new XYChart.Data("venezuela", 10000));
        series1.getData().add(new XYChart.Data("italia", 35407.15));
        series1.getData().add(new XYChart.Data("argentina", 12000));      
        
        grafica_barras.getData().addAll(series1,series2);
        /*//javafx8
        <BarChart fx:id="Barra_Otros" layoutX="14.0" layoutY="14.0" prefHeight="543.0" prefWidth="773.0" title="OTROS">
                          <xAxis>
                            <CategoryAxis side="BOTTOM" />
                          </xAxis>
                          <yAxis>
                            <NumberAxis side="LEFT" />
                          </yAxis>
       </BarChart>
       //javafx 2
       <BarChart fx:id="barra_estadisticas" barGap="1.0" prefHeight="526.0" prefWidth="800.0" AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="43.0">
                  <xAxis>
                    <CategoryAxis side="BOTTOM">
                      <categories>
                        <FXCollections fx:factory="observableArrayList" />
                      </categories>
                    </CategoryAxis>
                  </xAxis>
                  <yAxis>
                    <NumberAxis side="LEFT" />
                  </yAxis>
        </BarChart>
        */
    }
    
    public void barras(BarChart<String,Number> grafica_barras){
  
    
        Registro_Mensual rm = new Registro_Mensual();
        grafica_barras.getData().clear();
        
        LinkedList<String> aux = rm.libros_prestados();//estan todos los libros prestados
        LinkedList<String> aux2 = this.determinar_categorias(aux, 7, 4);//se muestra los registros agrupados por estado
        DecimalFormat df = new DecimalFormat("00");
        LinkedList<String> datos = new LinkedList<String>();
        
        
        int xmes=0;
        for(int m=1; m<=12; m++){//recorriendo los meses para saber cuantos libros prestados por mes
               for(int i=0; i<aux.size(); i=i+7){//recorriendo todos los elementos
                    if(df.format(m).equalsIgnoreCase(aux.get(i+1).split(" - ")[0].split("/")[1])){//solo si hay coincidencia del mes
                        xmes = xmes + 1;
                    }
                }//recorriendo todos los registros                 
               
             datos.add(df.format(m));//agregando mes
             datos.add("PRESTADOS");//agregando categoria
             datos.add(String.valueOf(xmes));//agregando cantidad  
             xmes=0; //reiniciar conteo por mes
        }
        
        
        
        for(int m=1; m<=12; m++){//recorrido por mes
            for(int x=0; x<aux2.size(); x++){//recorriendo por agrupacion
                int contar = 0;
                for(int i=0; i<aux.size(); i=i+7){//recorriendo todos los elementos
                    if(aux2.get(x).equalsIgnoreCase(aux.get(i+4)) && df.format(m).equalsIgnoreCase(aux.get(i+1).split(" - ")[0].split("/")[1])){//solo si hay coincidencia en categoria y mes
                        contar = contar + 1;
                    }
                }//recorriendo todos los registros        
                
              datos.add(df.format(m));//agregando mes
              datos.add(aux2.get(x));//agregando categoria
              datos.add(String.valueOf(contar));//agregando cantidad
              
            }//fin recorrido por agrupacion
        }//fin recorrido por mes
        
        
        
        ObservableList<Series<String,Number>> actual = FXCollections.observableArrayList();
        XYChart.Series<String, Number> serie;
      
            for(int g=0; g<datos.size(); g=g+3){//recorriendo los datos filtrados            
               
                
                if(actual.isEmpty()){//si esta vacio se almacena el primer registro
                    serie = new XYChart.Series();                    
                    serie.setName(datos.get(g+1));//guardando el nombre del estado
                    serie.getData().add(new XYChart.Data(this.identificador_mes(datos.get(g)), Integer.valueOf(datos.get(g+2))));//guardando mes y cantidad                    
                    
                   
                    actual.add(serie);
                }else{//sino se revisa los guardados y se almacena en la serie que contiene sus registros
                    boolean existe=false;
                    for(int z=0; z<actual.size(); z++){
                        if(actual.get(z).getName().equalsIgnoreCase(datos.get(g+1))){//si coinciden los nombres
                            existe = true;
                            serie = actual.get(z);
                            serie.getData().add(new XYChart.Data(this.identificador_mes(datos.get(g)), Integer.valueOf(datos.get(g+2))));//se busca la serie de interes y se le agrega el valor                        
                            
                            
                            //  System.out.println("interno: "+serie.getName()+" longitud:"+serie.getData().size());
                          //  System.out.println("Longitud serie:"+serie.getData().get(1).toString());
                            break;
                        }else{
                            existe = false;
                        }                                       
                    }              
                
                    if(existe == false){//despues de revisar todas las series y esta no existe entonce se agrega
                        serie = new XYChart.Series();
                        serie.setName(datos.get(g+1));//guardando el nombre del estado
                        serie.getData().add(new XYChart.Data(this.identificador_mes(datos.get(g)), Integer.valueOf(datos.get(g+2))));//guardando mes y cantidad
                      
                        
                        actual.add(serie);
                    }
                
                }
                
            }//fin recorrido datos filtrados [Data[mes01,0,null], Data[mes02,0,null], Data[mes03,1,null]
    
        
        
    grafica_barras.getData().addAll(actual);
        
    this.comentario(grafica_barras);                           
 
 
    
    }
    
    
private void comentario(BarChart<String,Number> grafica_barras){
   // grafica_barras.getData().get(0).getData().get(0).getNode();
    for(int i=0; i<grafica_barras.getData().size(); i++){
        XYChart.Series<String, Number> serie  = grafica_barras.getData().get(i);
       
        for(int s=0; s<serie.getData().size(); s++){
                Node nodo = serie.getData().get(s).getNode();
                Tooltip tt = new Tooltip("Descripcion:\n Estado: "+serie.getName()+"\n  Cantidad: "+serie.getData().get(s).getYValue());
                Tooltip.install(nodo, tt); 
       }
           
    }
            
}
    
//**Se coloca la cantidad de los top_x libros mas solicitados */
public void diez_libros_mas_solicitado(BarChart<String,Number> grafica_barras){
    Connection con = new Conexion_bd().getConexion();
    int cuenta = 0;
    LinkedList<String> informacion = new LinkedList<String>();
    
      try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            //buscando cotas actuales
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT cota, count(*) cantidad FROM biblioteca.prestamos GROUP BY cota ORDER BY cantidad DESC;");
            while(resultado.next()){//cotas encontradas  
                informacion.add(resultado.getString(1));
                informacion.add(resultado.getString(2));
                
                cuenta++;
                if(cuenta>=10) break;
            }
            
            sentencia.close();
            resultado.close();
            
            //
            grafica_barras.getData().clear();
            
            ObservableList<Series<String,Number>> actual = FXCollections.observableArrayList();
            XYChart.Series<String, Number> serie;
            
            
            for(int i=0; i<informacion.size(); i=i+2){//recorriendo cotas y cantidad
                serie = new XYChart.Series();
                serie.setName("POSICION "+((i/2)+1));//nombre de la serie
                serie.getData().add(new XYChart.Data(informacion.get(i),Integer.valueOf(informacion.get(i+1))));
                actual.add(serie);
            }
            
            
            grafica_barras.getData().addAll(actual);
            
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}










    
    
}//fin de la clase
