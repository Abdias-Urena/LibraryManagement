package Controller;

import Connection.DatabaseConnection;
import Notification.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.event.*;
import Person.*;

import javax.swing.*;


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
    private JFXComboBox<String> JComboBox;

    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXTextField JTextId;
    Notification noti = new Notification();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillCombox();
    }

    void fillCombox() {
        ObservableList<String> list_combox = FXCollections.observableArrayList();
        list_combox.add("Sin beca");
        list_combox.add("BLFZC1");
        list_combox.add("BLFZC2");
        list_combox.add("BLFZC3");
        list_combox.add("BLFZC4");
        list_combox.add("BLFZC Lejana");
        list_combox.add("Beca Omar Dengo");
        JComboBox.setItems(list_combox);
    }

    @FXML
    void saveStudent(ActionEvent event) {
        System.out.println("Hola");
        String grantType = JComboBox.getValue();
        if (!grantType.equals("Sin beca") || grantType != null) {
            Student user = new Student(JTextAddress.getText(), JTextEmail.getText(), JTextTelephone.getText(),
                    JTextId.getText(), JTextSurname.getText(), JTextName.getText(),
                    JTextCareer.getText(), true);
            if (isSave(user)) {
                noti.modifyNotification("Estudiante registrado", "El estudiante se ha registrado con éxito", "/Images/success.png");

            }else{
                noti.modifyNotification("Error", "Error al registrar el estudiante", "/Images/error.png");
            }
            clean();

        }
    }

    boolean isSave(Student user) {
        System.out.println(user.getId());
        try {
            if (user.isGrant()) {//Si tiene beca se pone "Y"
                DatabaseConnection connection = DatabaseConnection.getInstance();
                PreparedStatement preparedStatement = connection.getConnection().prepareStatement("INSERT INTO USER (ID, NAME, LAST_NAME, ADDRESS, EMAIL, PHONE_NUMBER, ROL, CAREER, `GRANT`) VALUES (?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getLastname());
                preparedStatement.setString(4, user.getAddress());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getPhoneNumber());
                preparedStatement.setString(7, "S");
                preparedStatement.setString(8, user.getCareer());
                preparedStatement.setString(9, "Y");
                int row = preparedStatement.executeUpdate();
                System.out.println(row);
                if (row > 0) {
                    preparedStatement.close();
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return false;
    }

    public void clean() {
        JTextName.setText("");
        JTextSurname.setText("");
        JTextAddress.setText("");
        JTextEmail.setText("");
        JTextTelephone.setText("");
        JTextCareer.setText("");
        JComboBox.setValue("");
    }

}
