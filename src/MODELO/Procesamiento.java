/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author AURORA
 */
public class Procesamiento {

    
    public Procesamiento() {
    
    }
    
    
    public BufferedImage redimensionar_imagenes(InputStream imagen, int x, int y){
       BufferedImage bi=null;
        
        try {
            
            //se obtiene la imagen original
            BufferedImage decodificado = ImageIO.read(imagen);
            ImageIcon pic = new ImageIcon(decodificado);
            
            
            //se crea la nueva imagen con las dimensiones de interes
            bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = bi.createGraphics();
            g2.drawImage(pic.getImage().getScaledInstance(x, y, Image.SCALE_FAST),0,0, null);
            g2.dispose();
            
            imagen.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Procesamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    
    return bi;
    }
   
    
    
    
    
    
    
    
    
    
    
    
}//fin de la clase
