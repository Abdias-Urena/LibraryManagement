package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
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
    private TableView<?> tablaEquipos;

    @FXML
    private TableColumn<?, ?> colMarca;

    @FXML
    private TableColumn<?, ?> colCargador;

    @FXML
    private TableColumn<?, ?> colDisponibilidad;

    @FXML
    private TableColumn<?, ?> collTipo;


    private Stage stageEquipos;

    private RegisterDeviceController registerDeviceController;
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

    }
}