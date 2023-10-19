package Controller;

import Connection.DatabaseConnection;
import Person.Student;
import Person.Teacher;
import Person.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private JFXTextField textID;


    @FXML
    void regresarP(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void guardar(ActionEvent event) {
        Teacher user = new Teacher(textDireccion.getText(), textGmail.getText(), textNumero.getText(), textID.getText(),
                textApellido.getText(), textNombre.getText(), textDepartamento.getText());
        if(isSave(user)){
            JOptionPane.showMessageDialog(null,"Registro Exitoso");
        }
    }
    boolean isSave(Teacher user){
        System.out.println(user.getDepartament());
        try{
            DatabaseConnection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("INSERT INTO USER (ID, NAME, LAST_NAME, ADDRESS, EMAIL, PHONE_NUMBER, ROL, DEPARTMENT) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, "T");
            preparedStatement.setString(8, user.getDepartament());
            int row = preparedStatement.executeUpdate();
            connection.getConnection().close();
            System.out.println(row);
            if(row > 0){
                return true;
            }
        }catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
        return false;
    }

}
