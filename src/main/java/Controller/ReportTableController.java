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

public class ReportTableController {

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
    private TableView<?> tablaReportes;

    @FXML
    private TableColumn<?, ?> colTitulo;

    @FXML
    private TableColumn<?, ?> colDiaReporte;

    @FXML
    private TableColumn<?, ?> colDescripcion;

    @FXML
    private TableColumn<?, ?> colTipoReporte;

    private Stage stageReport;

    private ReportsController reportsController;
    @FXML
    void borrarReporte(ActionEvent event) {

    }

    @FXML
    void buscarReporte(KeyEvent event) {

    }

    @FXML
    void editarReporte(ActionEvent event) {

    }

    @FXML
    void listarReporte(ActionEvent event) {

    }

    @FXML
    void nuevoReporte(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Reports.fxml"));
        AnchorPane ap = loader.load();
        reportsController = loader.getController();
        Scene scene = new Scene(ap);
        stageReport = new Stage();

        stageReport.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageReport.setScene(scene);
        stageReport.initOwner(root.getScene().getWindow());
        stageReport.initModality(Modality.WINDOW_MODAL);
        stageReport.initStyle(StageStyle.DECORATED);
        stageReport.setResizable(false);
        stageReport.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageReport.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageReport.showAndWait();
    }

}