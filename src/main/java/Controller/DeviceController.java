package Controller;

import Connection.DatabaseConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import java.util.Objects;
import java.util.ResourceBundle;

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

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getConnection();
    ObservableList<Device> DeviceList = FXCollections.observableArrayList();

    @FXML
    void borrarEquipo(ActionEvent event) {

    }

    @FXML
    void buscarEquipo(KeyEvent event) {

    }

    @FXML
    void editarEquipo(ActionEvent event) {

    }

    @FXML
    void listarEquipos(ActionEvent event) {
        UpdateTable();
    }

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

    public void UpdateTable() {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("available"));
        collTipo.setCellValueFactory(new PropertyValueFactory<>("type"));
        collID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collCharge.setCellValueFactory(new PropertyValueFactory<>("haveCharger"));
        DeviceList = getDeviceList();
        tablaEquipos.setItems(DeviceList);
    }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
}