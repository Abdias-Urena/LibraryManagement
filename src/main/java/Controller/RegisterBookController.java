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
    private JFXComboBox<?> comboBoxCategoria;

    @FXML
    private JFXTextField textReproduccion;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXTextField textTitulo;

    @FXML
    private JFXDatePicker fechaPicker;

    @FXML
    private JFXTextField textAutor;

    @FXML
    private JFXComboBox<?> comboBoxTipoLibro;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void guardar(ActionEvent event) {

    }
}
