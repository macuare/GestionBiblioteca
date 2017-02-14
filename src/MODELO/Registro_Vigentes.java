/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author AURORA
 */
public class Registro_Vigentes {
    private Mensajes m;
    private Registro_Mensual rm;
    private Registro_Prestamos rp;
    
    public Registro_Vigentes() {
        m = new Mensajes();
        rm = new Registro_Mensual();
        rp = new Registro_Prestamos();
    }
    
    
     public void prestamos_vigentes(ObservableList<Modelo_Vigentes> mdatos, TableView<Modelo_Vigentes> vigentes){
    
     
         mdatos.clear();
         LinkedList<String> datos = rm.libros_prestados();
         String cota,fsalida,fentrega,tipo,estado,responsable,cedula;
         
         for(int i=0; i<datos.size(); i=i+7){
                //  System.out.println("datos: "+datos.get(i+6)+" - "+datos.get(i)+" - "+datos.get(i+4)+" - "+datos.get(i+1)+" - "+datos.get(i+2));
                cota = datos.get(i);
                fsalida = datos.get(i+1);
                fentrega = datos.get(i+2);
                tipo = datos.get(i+3);
                estado = datos.get(i+4);
                responsable = datos.get(i+5);
                cedula = datos.get(i+6);

             //   System.out.println("ANTES--->ESTADO: "+estado+" - ENTREGA:"+fentrega);
              if(estado.equalsIgnoreCase("ACTIVO")){
                if(rp.entrega_vs_prestamo(fentrega.substring(0, 10))){//si esta moroso se cambia el estado del prestamo a moroso
                  rp.actualizar_estado(cedula,cota,"MOROSO",fentrega);
               //    System.out.println("CAMBIANDO ESTADO");
                  estado = "MOROSO";
                }  
              }
              //  System.out.println("DESPUES--->ESTADO: "+estado+" - ENTREGA:"+fentrega);
                
              if(estado.equalsIgnoreCase("ACTIVO") || estado.equalsIgnoreCase("MANTENIMIENTO") || estado.equalsIgnoreCase("MOROSO")){
                   mdatos.add(new Modelo_Vigentes(cedula, cota, estado, fsalida, fentrega));         
              }
         
         }
         
         vigentes.setItems(mdatos);
      
    }
    
        
    
    
    
    
}//fin de la clase
