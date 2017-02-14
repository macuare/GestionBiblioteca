/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca2013;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author AURORA
 */
public class BIBLIOTECA2013 extends Application {
    
    @Override
    public void start(final Stage stage) throws Exception {
      //  Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Principal.fxml"));        
        Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Autenticacion.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                                            @Override
                                         public void handle(MouseEvent event) {
                                          stage.setX(event.getScreenX());
                                          stage.setY(event.getScreenY());
                                         }
                                        });
        scene.setFill(null);
        Image imagen = new Image(getClass().getResourceAsStream("/IMAGENES/escudo.PNG"));
        stage.getIcons().add(imagen);
        stage.setTitle("AUTENTICACIÓN DEL SISTEMA DE BIBLIOTECA");
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
