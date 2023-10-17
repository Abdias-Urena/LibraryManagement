package Controller;

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterStudentController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField JTextName;

    @FXML
    private JFXTextField JTextSurname;

    @FXML
    private JFXTextField JTextAddress;

    @FXML
    private JFXTextField JTextEmail;

    @FXML
    private JFXTextField JTextTelephone;

    @FXML
    private JFXTextField JTextCareer;

    @FXML
    private JFXComboBox<?> JComboBox;

    @FXML
    private JFXButton btnSave;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
