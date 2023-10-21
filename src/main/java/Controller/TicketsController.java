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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;


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
        if (verifyHasLoan()) {
            ticket = new Ticket(textAreaDesc.getText(), 0, cmbUser.getValue());
            LocalDate days = ticket.getDays(ticket).toLocalDate();
            int price = ticket.calculatePrice(days);
            labelPrice.setText(String.valueOf(price));
        }else {
            System.out.println("No se puede calcular el precio de un ticket sin una devolución asociada");
            labelPrice.setText("0");
        }
    }


    @FXML
    void guardarTicket(ActionEvent event) {
        QuerySaveData();
    }

    public Ticket saveData() {
        ticket = new Ticket(textAreaDesc.getText(), Integer.parseInt(labelPrice.getText()), cmbUser.getValue());
        return ticket;
    }

    public boolean verifyHasLoan() {
        ticket = new Ticket(textAreaDesc.getText(), 0, cmbUser.getValue());
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM loan WHERE ID = ?");
            pst.setString(1, ticket.getTicket());
            ResultSet rs = pst.executeQuery();
            boolean hasActiveLoan = rs.next();
            pst.close();
            return hasActiveLoan;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void QuerySaveData() {
        if (!labelPrice.getText().equals("0") && verifyHasLoan()) {
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
        } else {
            System.out.println("No se puede guardar un ticket con precio 0");
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
