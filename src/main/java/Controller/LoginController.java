/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdias
 */
public class LoginController implements Initializable {

    @FXML
    private StackPane principalLogin;
    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane signInForm;
    @FXML
    private Pane paneFondo;
    @FXML
    private Button btnEnter;
    @FXML
    private Button btnMinimize;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Enter(ActionEvent event) {
    }

    @FXML
    private void Minimize(MouseEvent event) {
    }

    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
}
