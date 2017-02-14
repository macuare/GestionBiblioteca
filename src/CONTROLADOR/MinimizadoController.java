/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CONTROLADOR;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ANDY
 */
public class MinimizadoController implements Initializable {

    
     @FXML
    private Button Regresar;


    @FXML
    void MAXIMIZAR(ActionEvent event) {
        
            Stage st = (Stage) Regresar.getScene().getWindow();
            st.close();
            
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
