/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import javafx.scene.layout.Pane;

/**
 *
 * @author AURORA
 */
public class Modelo_Inventario {
    
    private String cota;
    private Pane contenedor;

    public Modelo_Inventario(String cota, Pane contenedor) {
        this.cota = cota;
        this.contenedor = contenedor;
    }

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public Pane getContenedor() {
        return contenedor;
    }

    public void setContenedor(Pane contenedor) {
        this.contenedor = contenedor;
    }
    
   
    
}//fin de la clase
