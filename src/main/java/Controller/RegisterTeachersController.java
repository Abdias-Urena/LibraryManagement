package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class RegisterTeachersController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXButton btnRegresarP;

    @FXML
    private JFXTextField textNombre;

    @FXML
    private JFXTextField textApellido;

    @FXML
    private JFXTextField textDireccion;

    @FXML
    private JFXTextField textGmail;

    @FXML
    private JFXTextField textNumero;

    @FXML
    private JFXTextField textDepartamento;

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
