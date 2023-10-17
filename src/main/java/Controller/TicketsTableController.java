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

public class TicketsTableController {

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
    private TableView<?> tablaMulta;

    @FXML
    private TableColumn<?, ?> colUsuario;

    @FXML
    private TableColumn<?, ?> colDescripcion;

    @FXML
    private TableColumn<?, ?> colPrecioMulta;

    @FXML
    private TableColumn<?, ?> colTipoMulta;

    private Stage stageMulta;

    private TicketsController ticketsController;
    @FXML
    void borrarMulta(ActionEvent event) {

    }

    @FXML
    void buscarMulta(KeyEvent event) {

    }

    @FXML
    void editarMulta(ActionEvent event) {

    }

    @FXML
    void listarMulta(ActionEvent event) {

    }

    @FXML
    void nuevaMulta(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tickets.fxml"));
        AnchorPane ap = loader.load();
        ticketsController = loader.getController();
        Scene scene = new Scene(ap);
        stageMulta = new Stage();

        stageMulta.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageMulta.setScene(scene);
        stageMulta.initOwner(root.getScene().getWindow());
        stageMulta.initModality(Modality.WINDOW_MODAL);
        stageMulta.initStyle(StageStyle.DECORATED);
        stageMulta.setResizable(false);
        stageMulta.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageMulta.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageMulta.showAndWait();
    }

}
