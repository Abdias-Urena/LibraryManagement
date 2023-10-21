package Controller;


import Book.Book;
import Connection.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import Device.Device;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The type Device controller.
 */
public class DeviceController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnListar;

    @FXML
    private JFXButton btnNuevo;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnBorrar;

    @FXML
    private JFXTextField textBuscar;

    @FXML
    private TableView<Device> tablaEquipos;

    @FXML
    private TableColumn<Device, String> colMarca;

    @FXML
    private TableColumn<Device, String> colDisponibilidad;

    @FXML
    private TableColumn<Device, String> collTipo;

    @FXML
    private TableColumn<Device, String> collID;

    @FXML
    private TableColumn<Device, String> collCharge;

    private Stage stageEquipos;


    private RegisterDeviceController registerDeviceController;

    /**
     * The Database connection.
     */
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    /**
     * The Connection.
     */
    Connection connection = databaseConnection.getConnection();
    /**
     * The Device list.
     */
    ObservableList<Device> DeviceList = FXCollections.observableArrayList();


    /**
     * Buscar equipo.
     *
     * @param event the event
     */
    @FXML
    void buscarEquipo(KeyEvent event) {
        UpdateTable(textBuscar.getText());
    }

    /**
     * Editar equipo.
     *
     * @param event the event
     */
    @FXML
    void editarEquipo(ActionEvent event) {
        // Obtener el libro seleccionado de la tabla
        Device DeviceSelect = tablaEquipos.getSelectionModel().getSelectedItem();

        if (DeviceSelect != null) {
            try {
                // Cargar el controlador del nuevo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditDevice.fxml"));
                AnchorPane ap = loader.load();
                EditDeviceController editDeviceController = loader.getController();

                editDeviceController.initData(DeviceSelect);

                Scene scene = new Scene(ap);
                Stage stageEdicion = new Stage();
                stageEdicion.setScene(scene);
                stageEdicion.initModality(Modality.WINDOW_MODAL);
                stageEdicion.initStyle(StageStyle.DECORATED);
                stageEdicion.setResizable(false);
                stageEdicion.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Listar equipos.
     *
     * @param event the event
     */
    @FXML
    void listarEquipos(ActionEvent event) {
        UpdateTable();
    }

    /**
     * Gets device list.
     *
     * @return the device list
     */
    public ObservableList<Device> getDeviceList() {
        ObservableList<Device> DeviceList = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from device");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DeviceList.add(new Device(
                        rs.getString("BRAND"),
                        rs.getBoolean("HAVE_CHARGER"),
                        rs.getString("ID"),
                        rs.getBoolean("IS_USABLE"),
                        rs.getBoolean("IS_AVAILABLE"),
                        rs.getString("TYPE")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DeviceList;
    }

    /**
     * Update table.
     *
     * @param id the id
     */
    public void UpdateTable(String id) {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colDisponibilidad.setCellValueFactory(cellData -> {
            if (cellData.getValue().isAvailable()) {
                return new SimpleStringProperty("Disponible");
            } else return new SimpleStringProperty("No disponible");
        });
        collTipo.setCellValueFactory(new PropertyValueFactory<>("type"));
        collID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collCharge.setCellValueFactory(cellData -> {
            if (cellData.getValue().isHaveCharger()) {
                return new SimpleStringProperty("Disponible");
            } else return new SimpleStringProperty("No disponible");
        });
        ObservableList<Device> filteredDevices = DeviceList.filtered(device ->
                device.getBrand().contains(id.toLowerCase()) ||
                        String.valueOf(device.isAvailable()).toLowerCase().contains(id.toLowerCase()) ||
                        device.getType().toLowerCase().contains(id.toLowerCase()) ||
                        device.getId().toLowerCase().contains(id.toLowerCase()) ||
                        String.valueOf(device.isHaveCharger()).toLowerCase().contains(id.toLowerCase())
        );
        tablaEquipos.setItems(filteredDevices);
    }


    /**
     * Update table.
     */
    public void UpdateTable() {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colDisponibilidad.setCellValueFactory(cellData -> {
            if (cellData.getValue().isAvailable()) {
                return new SimpleStringProperty("Disponible");
            } else return new SimpleStringProperty("No disponible");
        });
        collTipo.setCellValueFactory(cellData -> {
            if (cellData.getValue().getType().equals("L")) {
                return new SimpleStringProperty("Laptop");
            } else return new SimpleStringProperty("Tablet");
        });
        collID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collCharge.setCellValueFactory(cellData -> {
            if (cellData.getValue().isHaveCharger()) {
                return new SimpleStringProperty("Disponible");
            } else return new SimpleStringProperty("No disponible");
        });
        DeviceList = getDeviceList();
        tablaEquipos.setItems(DeviceList);
    }

    /**
     * Nuevo equipo.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void nuevoEquipo(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterDevice.fxml"));
        AnchorPane ap = loader.load();
        registerDeviceController = loader.getController();
        Scene scene = new Scene(ap);
        stageEquipos = new Stage();

        stageEquipos.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageEquipos.setScene(scene);
        stageEquipos.initOwner(root.getScene().getWindow());
        stageEquipos.initModality(Modality.WINDOW_MODAL);
        stageEquipos.initStyle(StageStyle.DECORATED);
        stageEquipos.setResizable(false);
        stageEquipos.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageEquipos.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageEquipos.showAndWait();
    }

    /**
     * Borrar equipo.
     *
     * @param event the event
     */
    @FXML
    void borrarEquipo(ActionEvent event) {
        Device DeviceSelect = tablaEquipos.getSelectionModel().getSelectedItem();

        if (DeviceSelect != null) {
            boolean confirm = showConfirmationDialog("¿Estás seguro de que deseas eliminar este equipo?");

            if (confirm) {
                boolean exito = deleteBookFromDatabase(DeviceSelect);

                if (exito) {
                    UpdateTable();
                } else {
                    showAlert("Error al eliminar el libro.");
                }
            }
        } else {
            showAlert("Por favor, selecciona un libro para eliminar.");
        }
    }
    private boolean deleteBookFromDatabase(Device device) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM device WHERE ID = ?");
            pst.setString(1, device.getId());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showConfirmationDialog(String mensaje) {
        return new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK, ButtonType.CANCEL).showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

    private void showAlert(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
}