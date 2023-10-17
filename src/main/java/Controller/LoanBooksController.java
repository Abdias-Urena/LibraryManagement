package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoanBooksController implements Initializable {


    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXButton btnRegresarP;

    @FXML
    private JFXComboBox<?> comboBoxLibros;

    @FXML
    private JFXDatePicker datePickerLimite;

    @FXML
    private JFXDatePicker datePickerActual;

    @FXML
    private JFXTextField textNombre;

    @FXML
    void guardar(ActionEvent event) {

    }

    @FXML
    void regresarP(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
