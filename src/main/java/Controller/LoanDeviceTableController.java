package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.Objects;

public class LoanDeviceTableController {

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
    private TableView<?> tablaPrestamoEquipo;

    @FXML
    private TableColumn<?, ?> colTipoDispos;

    @FXML
    private TableColumn<?, ?> colDispos;

    @FXML
    private TableColumn<?, ?> colFechaPrestamo;

    @FXML
    private TableColumn<?, ?> collFechaLimite;

    @FXML
    private TableColumn<?, ?> collPersona;

    private Stage stageLoanDeviceTable;

    private LoanDevicesController loanDevicesController;
    @FXML
    void borrarPrestamoEquipo(ActionEvent event) {

    }

    @FXML
    void buscarPrestamoEquipos(KeyEvent event) {

    }

    @FXML
    void editarPrestamoEquipo(ActionEvent event) {

    }

    @FXML
    void listarPrestamoEquipos(ActionEvent event) {

    }

    @FXML
    void nuevoPrestamoEquipo(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoanDevice.fxml"));
        AnchorPane ap = loader.load();
        loanDevicesController = loader.getController();
        Scene scene = new Scene(ap);
        stageLoanDeviceTable = new Stage();

        stageLoanDeviceTable.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageLoanDeviceTable.setScene(scene);
        stageLoanDeviceTable.initOwner(root.getScene().getWindow());
        stageLoanDeviceTable.initModality(Modality.WINDOW_MODAL);
        stageLoanDeviceTable.initStyle(StageStyle.DECORATED);
        stageLoanDeviceTable.setResizable(false);
        stageLoanDeviceTable.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageLoanDeviceTable.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageLoanDeviceTable.showAndWait();
    }

}