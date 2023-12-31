package Controller;

import Notification.Notification;
import Connection.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckComboBox;
import Device.Device;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Edit device controller.
 */
public class EditDeviceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXComboBox<String> comboBoxTipo;
    @FXML
    private JFXButton btnEdit;
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

    private Device listDevice;

    private List<String> checkedItems;
    /**
     * The Noti.
     */
    Notification noti = new Notification();

    /**
     * Edit device.
     *
     * @param event the event
     */
    @FXML
    void editDevice(ActionEvent event) {
        QueryEditDevice();
    }

    /**
     * Edit device query device.
     *
     * @return the device
     */
    public Device editDeviceQuery() {
        String id = textID.getText();
        String brand = textMarca.getText();
        String selectedType = comboBoxTipo.getValue();
        boolean selectHaveCharger = checkedItems.contains("SI");
        if (id != null && !id.isEmpty() && brand != null && !brand.isEmpty() && selectedType != null && !selectedType.isEmpty()) {
            String type = selectedType.equals("Laptop") ? "L" : "T";
            if (!selectHaveCharger) {
                return new Device(brand, false, id, true, true, type);
            } else {
                return new Device(brand, true, id, true, true, type);
            }
        } else {
            System.out.println("Por favor, complete todos los campos.");
            noti.modifyNotification("Error", "Por favor, complete todos los campos.", "/Images/error.png");
        }
        return null;
    }


    /**
     * Query edit device.
     */
    void QueryEditDevice() {
        Device device = editDeviceQuery();
        if (device != null) {
            try {
                String insertQuery = "UPDATE device SET HAVE_CHARGER = ?, ID = ?, IS_USABLE = ?, TYPE = ?, BRAND = ?, IS_AVAILABLE = ? WHERE ID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, device.isHaveCharger() ? "Y" : "N");
                preparedStatement.setString(2, device.getId());
                preparedStatement.setString(3, "Y");
                preparedStatement.setString(4, device.getType());
                preparedStatement.setString(5, device.getBrand());
                preparedStatement.setString(6, "Y");
                preparedStatement.setString(7, device.getId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Datos insertados con éxito.");
                    noti.modifyNotification("Éxito", "Datos insertados con éxito.", "/Images/success.png");
                } else {
                    System.out.println("Error al insertar datos.");
                    noti.modifyNotification("Error", "Error al insertar datos.", "/Images/error.png");
                }
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Init data.
     *
     * @param deviceList the device list
     */
    void initData(Device deviceList) {
        listDevice = deviceList;
        textMarca.setText(listDevice.getBrand());
        textID.setText(listDevice.getId());
        if (listDevice.isHaveCharger()) {
            checkComboBox.getCheckModel().check("SI");
        } else {
            checkComboBox.getCheckModel().check("NO");
        }
        comboBoxTipo.getItems().addAll("Laptop", "Tablet");
        if (listDevice.getType().equals("L")) {
            comboBoxTipo.setValue("Laptop");
        } else {
            comboBoxTipo.setValue("Tablet");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkComboBox.getItems().addAll("SI", "NO");
        checkedItems = checkComboBox.getCheckModel().getCheckedItems();
    }
}
