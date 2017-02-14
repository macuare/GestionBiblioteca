/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author AURORA
 */
public class Modelo_Vigentes {
    
    private String v_cedulas, v_cotas, v_estado, v_fsalida, v_fentrega;

    public Modelo_Vigentes(String v_cedulas, String v_cotas, String v_estado, String v_fsalida, String v_fentrega) {
        this.v_cedulas = v_cedulas;
        this.v_cotas = v_cotas;
        this.v_estado = v_estado;
        this.v_fsalida = v_fsalida;
        this.v_fentrega = v_fentrega;
    }

    public String getV_cedulas() {
        return v_cedulas;
    }

    public void setV_cedulas(String v_cedulas) {
        this.v_cedulas = v_cedulas;
    }

    public String getV_cotas() {
        return v_cotas;
    }

    public void setV_cotas(String v_cotas) {
        this.v_cotas = v_cotas;
    }

    public String getV_estado() {
        return v_estado;
    }

    public void setV_estado(String v_estado) {
        this.v_estado = v_estado;
    }

    public String getV_fsalida() {
        return v_fsalida;
    }

    public void setV_fsalida(String v_fsalida) {
        this.v_fsalida = v_fsalida;
    }

    public String getV_fentrega() {
        return v_fentrega;
    }

    public void setV_fentrega(String v_fentrega) {
        this.v_fentrega = v_fentrega;
    }
    
    
    
    
}//fin de la clase
