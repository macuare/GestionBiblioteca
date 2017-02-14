/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class Registro_Reportes {
    
    
    public Registro_Reportes() {    
    }
    
    
    
      public void escritor_textos(String ruta, String parrafo){

        FileWriter fichero = null;
        PrintWriter pw = null;

            
                    try {
                        fichero = new FileWriter(ruta,true);
                         
                       
                        pw= new PrintWriter(fichero);                           
                        pw.println(parrafo);
                        
                      


                    } catch (IOException ex) {
                        Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{

                        if (null != fichero)
                          try {
                            fichero.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                                                    

        }// fin del metodo de escritor de textos

public void escritor_texto_mejorado(String ruta,String cabecera, String parrafo){
    File archivo = null;

    //verificando si el archivo existe
        archivo = new File(ruta);
            if(archivo.exists()){//si existe, se continua escribiendo sobre el mismo archivo
                this.escritor_textos(ruta, parrafo);
            }else{//en caso de que el archivo no exista, se crea la cabecera y se añade el primer registro
                this.escritor_textos(ruta,cabecera);//escribiendo la cabecera
                this.escritor_textos(ruta, parrafo);//escribiendo el primer parrafo
            }
}
    
    
    
    
  public void lista_libros_ordenados(Connection con){
      LinkedList<String> cotas = new LinkedList<String>();
      LinkedList<String> informacion = new LinkedList<String>();
      LinkedList<String> bloques = new LinkedList<String>();
      LinkedList<String> definitivo = new LinkedList<String>();
      
      
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            //buscando cotas actuales
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM biblioteca.dewey order by codigos;");
            while(resultado.next()){//cotas encontradas  
                cotas.add(resultado.getString("codigos"));
                cotas.add(resultado.getString("categorias"));
            }
            sentencia.close();
            resultado.close();
            
            //buscando libros por cota
            sentencia = con.createStatement();
            //resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cota ORDER BY cota;");
            resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cota NOT REGEXP '040' AND cota NOT REGEXP '041' AND cota NOT REGEXP '042' ORDER BY cota;");
            while(resultado.next()){//libros encontrados      
                informacion.add(resultado.getString("cota"));
                informacion.add(resultado.getString("titulo"));
                informacion.add(resultado.getString("edicion"));
                informacion.add(resultado.getString("autores"));
                informacion.add(resultado.getString("editorial"));
                informacion.add(resultado.getString("paginas"));
                
            }
            sentencia.close();
            resultado.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        
        //-------------------recorriendo libros de la biblioteca
        
        String descripcion = "";
        boolean coincidencia = false;
        
        
       for(int t=0; t<cotas.size(); t=t+2){//recorriendo las cotas
         //  System.out.println("COTA;"+cotas.get(t+1).toUpperCase()+";AÑO");
          // this.escritor_texto_mejorado("d://listado_libros.txt","----------;LISTADO DE LIBROS BIBLIOTECA CARIBAY;----------","COTA;"+cotas.get(t+1).toUpperCase()+";AÑO");
           descripcion = "COTA;"+cotas.get(t+1).toUpperCase()+";AÑO";
           coincidencia = false;
           
           
        for(int i=0; i<informacion.size();i=i+6){//recorriendo datos de los libros
            
            if(informacion.get(i).startsWith(cotas.get(t))){//si coinciden el codigo deway...primeros tres caracteres
               // System.out.println(informacion.get(i)+";"+informacion.get(i+1)+";"+informacion.get(i+2));
               // this.escritor_texto_mejorado("d://listado_libros.txt","----------;LISTADO DE LIBROS BIBLIOTECA CARIBAY;----------",informacion.get(i)+";"+informacion.get(i+1)+";"+informacion.get(i+2));
                bloques.add(informacion.get(i)+";"+informacion.get(i+1).trim()+";"+informacion.get(i+2)+";"+informacion.get(i+3)+";"+informacion.get(i+4).trim()+";"+informacion.get(i+5));
               // System.out.println("L:"+informacion.get(i+1).length());
                coincidencia = true;
            }
            
        }//fin recorrido de libros
        
        
                if(coincidencia){//solo si hay contenido para la area solo se hace
                  //this.escritor_texto_mejorado("d://listado_libros.txt","----------;LISTADO DE LIBROS BIBLIOTECA CARIBAY;----------",descripcion.toUpperCase());                        
                        definitivo.add("COTA");
                        definitivo.add("CATEGORÍA: "+cotas.get(t+1).toUpperCase());
                        definitivo.add("AÑO");                        
                        definitivo.add("---");
                        definitivo.add("---");
                        definitivo.add("---");    
                    
                    for(int b=0;b<bloques.size();b++){
                        definitivo.add(bloques.get(b).split(";")[0]);//cota
                        definitivo.add(bloques.get(b).split(";")[1]);//titulo
                        definitivo.add(bloques.get(b).split(";")[2]);//edicion
                        definitivo.add(bloques.get(b).split(";")[3]);//autores
                        definitivo.add(bloques.get(b).split(";")[4]);//editorial
                        definitivo.add(bloques.get(b).split(";")[5]);//paginas
                    }
                    
                   bloques.clear();
               }
               
       }//fin recorrido de cotas
       
        try {
            new Registro_Pdf().listado_libros(definitivo);
        } catch (DocumentException ex) {
            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       cotas.clear();
       informacion.clear();
       definitivo.clear();
  }
    
    
  public void lista_tesis_pasantias_ordenados(Connection con){
      LinkedList<String> cotas = new LinkedList<String>();
      LinkedList<String> informacion = new LinkedList<String>();
      LinkedList<String> bloques = new LinkedList<String>();
      LinkedList<String> definitivo = new LinkedList<String>();
      
      
        try {
            Statement sentencia = null;
            ResultSet resultado = null;
            
            //buscando cotas actuales
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM biblioteca.dewey order by codigos;");
            while(resultado.next()){//cotas encontradas  
                cotas.add(resultado.getString("codigos"));
                cotas.add(resultado.getString("categorias"));
            }
            sentencia.close();
            resultado.close();
            
            //buscando libros por cota
            sentencia = con.createStatement();
            //resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cotaORDER BY cota;");
            resultado = sentencia.executeQuery("SELECT * FROM biblioteca.libros WHERE cota REGEXP '040' OR cota REGEXP '041' OR cota REGEXP '042' ORDER BY cota;");
            while(resultado.next()){//libros encontrados      
                informacion.add(resultado.getString("cota"));
                informacion.add(resultado.getString("titulo"));
                informacion.add(resultado.getString("edicion"));
                informacion.add(resultado.getString("autores"));
                informacion.add(resultado.getString("academico"));
                informacion.add(resultado.getString("industrial"));
                
            }
            sentencia.close();
            resultado.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        
        //-------------------recorriendo libros de la biblioteca
        
        String descripcion = "";
        boolean coincidencia = false;
        
        
       for(int t=0; t<cotas.size(); t=t+2){//recorriendo las cotas
         //  System.out.println("COTA;"+cotas.get(t+1).toUpperCase()+";AÑO");
          // this.escritor_texto_mejorado("d://listado_libros.txt","----------;LISTADO DE LIBROS BIBLIOTECA CARIBAY;----------","COTA;"+cotas.get(t+1).toUpperCase()+";AÑO");
           descripcion = "COTA;"+cotas.get(t+1).toUpperCase()+";AÑO";
           coincidencia = false;
           
           
        for(int i=0; i<informacion.size();i=i+6){//recorriendo datos de los libros
            
            if(informacion.get(i).startsWith(cotas.get(t))){//si coinciden el codigo deway...primeros tres caracteres
               // System.out.println(informacion.get(i)+";"+informacion.get(i+1)+";"+informacion.get(i+2));
               // this.escritor_texto_mejorado("d://listado_libros.txt","----------;LISTADO DE LIBROS BIBLIOTECA CARIBAY;----------",informacion.get(i)+";"+informacion.get(i+1)+";"+informacion.get(i+2));
                bloques.add(informacion.get(i)+";"+informacion.get(i+1).trim()+";"+informacion.get(i+2)+";"+informacion.get(i+3)+"; "+informacion.get(i+4).trim()+"; "+informacion.get(i+5));
               // System.out.println("L:"+informacion.get(i+1).length());
                coincidencia = true;
            }
            
        }//fin recorrido de libros
        
        
                if(coincidencia){//solo si hay contenido para la area solo se hace
                  //this.escritor_texto_mejorado("d://listado_libros.txt","----------;LISTADO DE LIBROS BIBLIOTECA CARIBAY;----------",descripcion.toUpperCase());                        
                        definitivo.add("COTA");
                        definitivo.add("CATEGORÍA: "+cotas.get(t+1).toUpperCase());
                        definitivo.add("AÑO");                        
                        definitivo.add("---");
                        definitivo.add("---");
                        definitivo.add("---");    
                    
                    for(int b=0;b<bloques.size();b++){
                        definitivo.add(bloques.get(b).split(";")[0]);//cota
                        definitivo.add(bloques.get(b).split(";")[1]);//titulo
                        definitivo.add(bloques.get(b).split(";")[2]);//edicion
                        definitivo.add(bloques.get(b).split(";")[3]);//autores
                        definitivo.add(bloques.get(b).split(";")[4]);//academico
                        definitivo.add(bloques.get(b).split(";")[5]);//industrial
                    }
                    
                   bloques.clear();
               }
               
       }//fin recorrido de cotas
       
        try {
            new Registro_Pdf().listado_tesis_pasantias(definitivo);
        } catch (DocumentException ex) {
            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Registro_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       cotas.clear();
       informacion.clear();
       definitivo.clear();
  }
    
    
    
    
    
}//fin de la clase
