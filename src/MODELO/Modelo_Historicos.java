/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author AURORA
 */
public class Modelo_Historicos {
    
    private String h_cedulas, h_cotas, h_estado, h_fsalida, h_fentrega;

    public Modelo_Historicos(String h_cedulas, String h_cotas, String h_estado, String h_fsalida, String h_fentrega) {
        this.h_cedulas = h_cedulas;
        this.h_cotas = h_cotas;
        this.h_estado = h_estado;
        this.h_fsalida = h_fsalida;
        this.h_fentrega = h_fentrega;
    }

    public String getH_cedulas() {
        return h_cedulas;
    }

    public void setH_cedulas(String h_cedulas) {
        this.h_cedulas = h_cedulas;
    }

    public String getH_cotas() {
        return h_cotas;
    }

    public void setH_cotas(String h_cotas) {
        this.h_cotas = h_cotas;
    }

    public String getH_estado() {
        return h_estado;
    }

    public void setH_estado(String h_estado) {
        this.h_estado = h_estado;
    }

    public String getH_fsalida() {
        return h_fsalida;
    }

    public void setH_fsalida(String h_fsalida) {
        this.h_fsalida = h_fsalida;
    }

    public String getH_fentrega() {
        return h_fentrega;
    }

    public void setH_fentrega(String h_fentrega) {
        this.h_fentrega = h_fentrega;
    }
    
    
}//fin de la clase
