package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditBookController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField textReproduccion;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXTextField textTitulo;

    @FXML
    private JFXDatePicker fechaPicker;

    @FXML
    private JFXTextField textAutor;

    @FXML
    private JFXComboBox<?> comboBoxTipoLibro;

    @FXML
    private JFXTextField textCategory;

    @FXML
    private Label labelURL;

    @FXML
    private JFXTextField textURL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void chooseBook(ActionEvent event) {
        if (comboBoxTipoLibro.getValue().equals("Digital")) {
            labelURL.setVisible(true);
            textURL.setVisible(true);
        } else {
            labelURL.setVisible(false);
            textURL.setVisible(false);
        }
    }

    @FXML
    void editarBook(ActionEvent event) {

    }

}
