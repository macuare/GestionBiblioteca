/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

/**
 *
 * @author AURORA
 */
public class Modelo_Mensual {
    
    private String cedulas, cotas, estado, fsalida, fentrega;

    public Modelo_Mensual(String cedulas, String cotas, String estado, String fsalida, String fentrega) {
        this.cedulas = cedulas;
        this.cotas = cotas;
        this.estado = estado;
        this.fsalida = fsalida;
        this.fentrega = fentrega;
    }

    public String getCedulas() {
        return cedulas;
    }

    public void setCedulas(String cedulas) {
        this.cedulas = cedulas;
    }

    public String getCotas() {
        return cotas;
    }

    public void setCotas(String cotas) {
        this.cotas = cotas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFsalida() {
        return fsalida;
    }

    public void setFsalida(String fsalida) {
        this.fsalida = fsalida;
    }

    public String getFentrega() {
        return fentrega;
    }

    public void setFentrga(String fentrega) {
        this.fentrega = fentrega;
    }
    
    
    
    
}//fin de la clase
