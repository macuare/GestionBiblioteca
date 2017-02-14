/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AN
 */
public class Registro_Pdf {
    private Generador_Cota gc;
    private Mensajes mensaje;
    
    public Registro_Pdf() {
        gc = new Generador_Cota();
        mensaje = new Mensajes();
    }
    
    
    public void crear_documento(Paragraph contenido, String orientacion) throws DocumentException, FileNotFoundException{
        Document d;
        //CREANDO EL DOCUMENTO
        if(orientacion.equalsIgnoreCase("vertical")){
            d = new Document(PageSize.LETTER);//hoja tamaño carta
        }else{
            d = new Document(PageSize.LETTER.rotate());//hoja tamaño carta
        }
        //CREANDO LA INSTANCIA PARA ALMACENAR EL FLUJO DE INFORMACION
            File archivo = mensaje.guardar_archivo(new String[]{"*.pdf"});        
        
        if(archivo!=null){
            //String direccion = mensaje.guardar_archivo(new String[]{"*.pdf"}).getAbsolutePath();
            System.out.println("direccion: "+archivo.getPath());        
            
            System.out.println("CREANDO EL DOCUMENTO");
            PdfWriter pw = PdfWriter.getInstance(d, new FileOutputStream(archivo));              
            //ABRIENDO EL DOCUMENTO
                d.open();
            //AGREGANDO CONTENIDO AL PDF. CUERPO DEL PDF
            //d.add(new Paragraph("NUEVO PDF"));
                d.add(contenido);        
            //CERRANDO EL DOCUMENTO PDF
                d.close();
                mensaje.notificacion("EL LISTADO DE LAS ETIQUETAS FUE CREADO CON EXITO...!!!\n  RUTA: "+archivo.getPath());
        }else{
            System.out.println("CANCELANDO DOCUMENTO");
            contenido = new Paragraph();
            mensaje.notificacion("LA CREACIÓN DEL ARCHIVO FUE CANCELADO...!!!");
        }
    }
    
    
    public Paragraph crear_etiquetas(){
          Paragraph parrafo = new Paragraph();
          DecimalFormat df = new DecimalFormat("000");
                  
          
        try {             
            Image escudo = Image.getInstance(getClass().getResource("/IMAGENES/unefa_escudo.jpg"));
            escudo.scaleToFit(30.0f, 30.0f);
            
            //CAPA 0:
            
            
            
            
            //parrafo.add(new Chunk(escudo, 0, -15,true)); //capa 1
           // parrafo.add(new Chunk(escudo, 50, -15,true)); //capa 1
           //parrafo.add(new Chunk(escudo, 100, -15,true)); //capa 1
            
             Paragraph titulo = new Paragraph("BIBLIOTECA CARIBAY",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK)); 
             parrafo.add(titulo);
            
             Paragraph cota = new Paragraph("     "+"000-C485-ej100",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLDITALIC, BaseColor.BLACK)); 
             parrafo.add(cota);
             
             Paragraph pie = new Paragraph("NÚCLEO ARAGUA - EXTENSIÓN CAGUA",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK)); 
             parrafo.add(pie);
          
            
            
        } catch (BadElementException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return parrafo;
    
    }
    
    private Image manipulador_imagen(java.awt.Image imagen){
        BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        
        
        g2d.drawImage(imagen, 0, 0, null);
        
        //horizontal
        g2d.drawLine(10, 10, 30, 10);
        g2d.drawLine(10, 30, 30, 30);
        
        //vertical
        g2d.drawLine(10, 10, 10, 30);
        g2d.drawLine(30, 10, 30, 30);
        
        g2d.dispose();
        
        Image ima = null;
        try {
            ima = com.itextpdf.text.Image.getInstance(imagen,null);
        } catch (BadElementException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return ima;
    }
    
    
    private Image codigo_QR(String cota){
        Image qrmatrix = null;
        
            BarcodeQRCode qr = new BarcodeQRCode(cota, 40, 40, null);
            
        try {
           
            qrmatrix = qr.getImage();
        } catch (BadElementException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
           
       
    return qrmatrix;
    }
    
    
    public PdfPTable creando_etiqueta(String cota) {
        PdfPTable tabla = new PdfPTable(3);
        
        
        try {            
            
            Image escudo = Image.getInstance(getClass().getResource("/IMAGENES/unefa_escudo.jpg"));
            //escudo.scaleToFit(45.0f, 45.0f);
            escudo.scaleToFit(50.0f, 60.0f);
            //escudo.scaleToFit(40.0f, 50.0f);//experimental
            
            
            try {
                
                tabla.setTotalWidth(183.3f);
                tabla.setLockedWidth(true);
     //           tabla.setWidths(new float[]{0.7f,2.3f});
                //tabla.setWidths(new float[]{2.3f,0.7f});
                
                //tabla.setWidths(new float[]{1.15f,1.15f,0.7f});//original
                tabla.setWidths(new float[]{1.15f,1.15f,0.7f});//experimental
                
                
                PdfPCell celda = new PdfPCell(); 
                
                /*//primer elemento:imagen escudo
                celda = new PdfPCell(escudo);
                //celda.setRowspan(3);
                celda.setRowspan(5);
                celda.setPadding(1);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                //celda.setBorder(PdfPCell.NO_BORDER);
                tabla.addCell(celda);
                */
                //segundo elemento: cabecera
                celda = new PdfPCell(new Phrase("BIBLIOTECA CARIBAY",FontFactory.getFont(FontFactory.TIMES_ROMAN, 6, Font.BOLD, BaseColor.BLACK)));
                celda.setFixedHeight(10.0f);           
                celda.setColspan(2);                                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                //celda.setBorder(PdfPCell.NO_BORDER);
                tabla.addCell(celda);
                
                
                 //primer elemento:imagen escudo
                celda = new PdfPCell(escudo);
                //celda.setRowspan(3);
                celda.setRowspan(5);
                celda.setPadding(1);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                //celda.setBorder(PdfPCell.NO_BORDER);
                tabla.addCell(celda);
                              
                /*//primer elemento:IMAGEN QR
                celda = new PdfPCell(this.codigo_QR(cota));                
                celda.setRowspan(3);
                celda.setPadding(1);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
                //celda.setBorder(PdfPCell.NO_BORDER);
                tabla.addCell(celda);
                */
                
                //tercer elemento: cota
                
                celda = new PdfPCell(new Phrase(cota.split("-")[0],FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
                celda.setFixedHeight(15.0f);                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);                
                celda.setBorder(PdfPCell.NO_BORDER);
                celda.setColspan(2);//experimental
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Phrase(cota.split("-")[1],FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
                celda.setFixedHeight(15.0f);                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);                
                celda.setBorder(PdfPCell.NO_BORDER);
                celda.setColspan(2);//experimental
                tabla.addCell(celda);
                
                celda = new PdfPCell(new Phrase(cota.split("-")[2],FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK)));
                celda.setFixedHeight(15.0f);                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);                
                celda.setBorder(PdfPCell.NO_BORDER);
                celda.setColspan(2);//experimental
                tabla.addCell(celda);
                
                
                
                
                //cuarto elemento: pie
                celda = new PdfPCell(new Phrase("NÚCLEO ARAGUA - EXTENSIÓN CAGUA",FontFactory.getFont(FontFactory.TIMES_ROMAN, 6, Font.BOLD, BaseColor.BLACK)));
                celda.setFixedHeight(10.0f);
                celda.setColspan(2);
                //celda.setColspan(3);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setVerticalAlignment(Element.ALIGN_MIDDLE);                
                //celda.setBorder(PdfPCell.NO_BORDER);
                tabla.addCell(celda);
                
                
               
                
                
            } catch (DocumentException ex) {
                Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        } catch (BadElementException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tabla;
    }
    
    
    public PdfPTable cuadricula(LinkedList<String> cotas){
        
        PdfPTable ref = new PdfPTable(3);
        ref.setTotalWidth(550.0f);
        ref.setLockedWidth(true);
        
        for(int i=0; i<cotas.size(); i=i+2){//recorriendo las cotas almacenadas y la cantidad de cada una
            String cota = cotas.get(i);
            int cantidad = Integer.valueOf(cotas.get(i+1));
            for(int c=1;c<=cantidad;c++){//creando la cantidad de etiquetas por libro de acuerdo a su existencia
                ref.addCell(this.creando_etiqueta(cota));
            }
        }//fin recorrido
        return ref;
    }
    
    
    
    
    
    
    /**Este metodo permite la creacion de las cotas de interes....Tanto libros como tesis independientementes */
    public void crear_etiquetas_cotas(String tipo){
      //  Barra_Progreso bp = new Barra_Progreso(100.0,-1.0, "PROCESANDO...", "GENERANDO ETIQUETAS COTAS");
      //  bp.segundo_plano();
        
        Hilo h = new Hilo();
        h.setInformacion("ANALIZANDO....!!!");
        h.setValor(-1);
        h.ejecutar_segundo_plano();
        
        System.out.println("FASE 1");
        h.setInformacion("RECOLECTANDO INFORMACIÓN...!!!");
        h.setValor(10);
        LinkedList<String> aux = gc.recolectar_informacion(tipo);
        h.setValor(20);
        
        System.out.println("FASE 2");
        h.setInformacion("ORDENANDO Y AJUSTANDO INFORMACIÓN...!!!");
        h.setValor(40);
        PdfPTable tabla = this.cuadricula(aux); 
        //bp.setActual(40.0);
        Paragraph parrafo = new Paragraph();                     
        parrafo.add(tabla); 
        //bp.setActual(60);
        h.setValor(60);
        System.out.println("FASE 3");
        
        try {
            h.setInformacion("CREANDO ARCHIVO PDF...!!!");
            h.setValor(80);
            this.crear_documento(parrafo,"vertical"); 
            
            h.setValor(100);
            System.out.println("TERMINADO");
            
        } catch (DocumentException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Registro_Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    }
    
    
    
    
    
    public void listado_libros(LinkedList<String> informacion) throws DocumentException, FileNotFoundException{
         Paragraph parrafo = new Paragraph();
          DecimalFormat df = new DecimalFormat("000");
          
         
          
          PdfPTable tabla = new PdfPTable(3);
            tabla.setTotalWidth(700.3f);
                tabla.setLockedWidth(true);
   
                tabla.setWidths(new float[]{70f,600f,40f});
                
                PdfPCell celda = new PdfPCell(); 
               
                celda = new PdfPCell(new Phrase("-------",FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
          
                celda = new PdfPCell(new Phrase("DESCRIPCIÓN",FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
     
                celda = new PdfPCell(new Phrase("-------",FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
                
                celda = new PdfPCell(new Phrase("   ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                celda = new PdfPCell(new Phrase("   ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                celda = new PdfPCell(new Phrase("   ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
          
                
                
                for(int i=0; i<informacion.size(); i=i+6){//recorriendo todos los elementos
                    

                    if(informacion.get(i).equalsIgnoreCase("COTA")){
                        celda = new PdfPCell(new Phrase(informacion.get(i),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                        celda = new PdfPCell(new Phrase("\n "+informacion.get(i+1)+"\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);                    
                        celda = new PdfPCell(new Phrase(informacion.get(i+2),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                    }else{   
                        celda = new PdfPCell(new Phrase(informacion.get(i),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
                        celda = new PdfPCell(new Phrase(informacion.get(i+1)+"\n Autores: "+informacion.get(i+3)+". \n  Editorial: "+informacion.get(i+4)+". Páginas:"+informacion.get(i+5),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);                    
                        celda = new PdfPCell(new Phrase(informacion.get(i+2),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
                        
                    }
                
                }
                
                parrafo.add(tabla);
                
           
                 this.crear_documento(parrafo,"horizontal");
            
    }
    
    
    
    
    public void listado_tesis_pasantias(LinkedList<String> informacion) throws DocumentException, FileNotFoundException{
         Paragraph parrafo = new Paragraph();
          DecimalFormat df = new DecimalFormat("000");
          
         
          
          PdfPTable tabla = new PdfPTable(3);
            tabla.setTotalWidth(700.3f);
                tabla.setLockedWidth(true);
   
                tabla.setWidths(new float[]{70f,600f,40f});
                
                PdfPCell celda = new PdfPCell(); 
               
                celda = new PdfPCell(new Phrase("-------",FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
          
                celda = new PdfPCell(new Phrase("DESCRIPCIÓN",FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
     
                celda = new PdfPCell(new Phrase("-------",FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));                
                celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
                
                celda = new PdfPCell(new Phrase("   ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                celda = new PdfPCell(new Phrase("   ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                celda = new PdfPCell(new Phrase("   ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
          
                
                
                for(int i=0; i<informacion.size(); i=i+6){//recorriendo todos los elementos
                    

                    if(informacion.get(i).equalsIgnoreCase("COTA")){
                        celda = new PdfPCell(new Phrase(informacion.get(i),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                        celda = new PdfPCell(new Phrase("\n "+informacion.get(i+1)+"\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);                    
                        celda = new PdfPCell(new Phrase(informacion.get(i+2),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); celda.setBorder(PdfPCell.NO_BORDER); tabla.addCell(celda);
                    }else{   
                        celda = new PdfPCell(new Phrase(informacion.get(i),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
                        celda = new PdfPCell(new Phrase(informacion.get(i+1)+"\n Autores: "+informacion.get(i+3)+". \n  Tutor Académico: "+informacion.get(i+4)+". Tutor Industrial:"+informacion.get(i+5),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_JUSTIFIED); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);                    
                        celda = new PdfPCell(new Phrase(informacion.get(i+2),FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK)));                
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER); celda.setVerticalAlignment(Element.ALIGN_MIDDLE); tabla.addCell(celda);
                        
                    }
                
                }
                
                parrafo.add(tabla);
                
           
                 this.crear_documento(parrafo,"horizontal");
            
    }
    
    
    
    
    
    
    
}//fin de la clase
