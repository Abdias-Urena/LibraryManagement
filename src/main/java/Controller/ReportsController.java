package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<?> cmbReportes;

    @FXML
    private JFXTextField textTittle;

    @FXML
    private JFXDatePicker DatePicker;

    @FXML
    private JFXTextArea textAreaDescri;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    void guardar(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
