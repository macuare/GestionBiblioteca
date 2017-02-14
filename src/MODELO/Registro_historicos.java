/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author AURORA
 */
public class Registro_historicos {

    private Mensajes m;
    private Registro_Mensual rm;
    
    public Registro_historicos() {
        rm = new Registro_Mensual();
        m = new Mensajes();
    }
    
    
    public void historicos(ObservableList<Modelo_Historicos> total, TableView<Modelo_Historicos> historicos){
    
     
         total.clear();
         LinkedList<String> datos = rm.libros_prestados();
         
         for(int i=0; i<datos.size(); i=i+7){
             //  System.out.println("datos: "+datos.get(i+6)+" - "+datos.get(i)+" - "+datos.get(i+4)+" - "+datos.get(i+1)+" - "+datos.get(i+2));
           
                total.add(new Modelo_Historicos(datos.get(i+6), datos.get(i), datos.get(i+4), datos.get(i+1), datos.get(i+2)));         
            
         }
         
         historicos.setItems(total);
         
     
     
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}//fin de la clase
