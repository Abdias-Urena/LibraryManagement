/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdias
 */
public class RegisterBookController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField textAutor;
    @FXML
    private JFXComboBox<?> comboBox;
    @FXML
    private JFXTextField textReproduccion;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField textTitulo;
    @FXML
    private JFXDatePicker fechaPicker;
    
    @FXML
    private JFXButton btnBack;

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
    
    @FXML
    private void Back(ActionEvent event) {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
    
}
