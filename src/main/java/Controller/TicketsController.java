package Controller;

import Connection.DatabaseConnection;
import Ticket.Ticket;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TicketsController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<String> cmbUser;

    @FXML
    private JFXTextArea textAreaDesc;

    @FXML
    private Label labelPrice;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXButton btnCalculate;
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getConnection();
    Ticket ticket;

    ObservableList<String> userList = getUserList();


    @FXML
    void calculatePrice(ActionEvent event) {
        labelPrice.setText("100");
    }

    @FXML
    void guardarTicket(ActionEvent event) {
        QuerySaveData();
    }

    public Ticket saveData() {
        ticket = new Ticket(textAreaDesc.getText(), Integer.parseInt(labelPrice.getText()), cmbUser.getValue());
        return ticket;
    }

    public void QuerySaveData() {
        ticket = saveData();
        try {
            PreparedStatement enableFK = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            enableFK.execute();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO ticket (DESCRIPTION,PRICE_TICKET,ID) VALUES (?,?,?)");
            pst.setString(1, ticket.getDescription());
            pst.setInt(2, ticket.getPriceTicket());
            pst.setString(3, ticket.getTicket());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Datos insertados con éxito.");
            } else {
                System.out.println("Error al insertar datos.");
            }
            PreparedStatement disableFK = connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            disableFK.execute();
            enableFK.close();
            disableFK.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getUserList() {
        ObservableList<String> userList = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT ID FROM user");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String userID = rs.getString("ID");
                userList.add(userID);
            }
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void fillComboBox() {
        userList = getUserList();
        cmbUser.setItems(userList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboBox();
    }
}
