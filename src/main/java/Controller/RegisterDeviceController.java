/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author Abdias
 */
public class RegisterDeviceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXComboBox<?> comboBoxTipo;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private CheckComboBox<?> checkComboBox;
    @FXML
    private JFXTextField textMarca;
    @FXML
    private JFXButton btnRegresar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardar(ActionEvent event) {
    }
    
}
