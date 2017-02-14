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
public class Modelo_Usuarios {
    
    private String estado, cedula;
    private String nombres, apellidos, telefonos, correos, institucion, carrera;

    public Modelo_Usuarios(String estado, String cedula, String nombres, String apellidos, String telefonos, String correos, String institucion, String carrera) {
        this.estado = estado;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefonos = telefonos;
        this.correos = correos;
        this.institucion = institucion;
        this.carrera = carrera;
    }

    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getCorreos() {
        return correos;
    }

    public void setCorreos(String correos) {
        this.correos = correos;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

  
    
    
    
}//fin de la clase
