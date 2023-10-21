/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Connection.DatabaseConnection;
import Device.Device;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckComboBox;
import Notification.Notification;

/**
 * FXML Controller class
 *
 * @author Abdias
 */
public class RegisterDeviceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXComboBox<String> comboBoxTipo;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private CheckComboBox<String> checkComboBox;
    @FXML
    private JFXTextField textMarca;
    @FXML
    private JFXTextField textID;

    @FXML
    private Label labelID;
    @FXML
    private Label labelBrand;

    /**
     * The Database connection.
     */
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    /**
     * The Connection.
     */
    Connection connection = databaseConnection.getConnection();
    private List<String> checkedItems;
    /**
     * The Notification.
     */
    Notification notification = new Notification();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxTipo.getItems().addAll("Laptop", "Tablet");
        checkComboBox.getItems().addAll("SI", "NO");
        checkedItems = checkComboBox.getCheckModel().getCheckedItems();
    }

    @FXML
    private void guardar(ActionEvent event) {
        QuerySaveData();
    }

    /**
     * Register device device.
     *
     * @return the device
     */
    public Device registerDevice() {
        String id = textID.getText();
        String brand = textMarca.getText();
        String selectedType = comboBoxTipo.getValue();
        if (id != null && !id.isEmpty() && brand != null && !brand.isEmpty() && selectedType != null && !selectedType.isEmpty()) {
            boolean haveCharger = checkedItems.contains("SI");
            String type = selectedType.equals("Laptop") ? "L" : "T";
            return new Device(brand, haveCharger, id, true, true, type);
        } else {
            System.out.println("Por favor, complete todos los campos.");
            notification.modifyNotification("Error", "Por favor, complete todos los campos.", "/Images/error.png");
        }
        return null;
    }

    private void cleanField() {
        textID.setText("");
        textMarca.setText("");
        comboBoxTipo.setValue("");
        checkComboBox.getCheckModel().clearChecks();
    }

    private void QuerySaveData() {
        Device device = registerDevice();
        if (device != null) {
            try {
                String insertQuery = "INSERT INTO device (HAVE_CHARGER, ID, IS_USABLE, " +
                        "TYPE, BRAND,IS_AVAILABLE) " +
                        "VALUES (?, ?, ?, ?, ?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, device.isHaveCharger() ? "Y" : "N");
                preparedStatement.setString(2, device.getId());
                preparedStatement.setString(3, "Y");
                preparedStatement.setString(4, device.getType());
                preparedStatement.setString(5, device.getBrand());
                preparedStatement.setString(6, "Y");

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Datos insertados con éxito.");
                    notification.modifyNotification("Éxito", "Datos insertados con éxito.", "/Images/success.png");
                } else {
                    System.out.println("Error al insertar datos.");
                    notification.modifyNotification("Error", "Error al insertar datos.", "/Images/error.png");
                }

                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            cleanField();
        }
    }

}
