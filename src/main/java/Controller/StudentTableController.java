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

public class StudentTableController {

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
    private TableView<?> tablaEstudiantes;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colApellido;

    @FXML
    private TableColumn<?, ?> colDireccion;

    @FXML
    private TableColumn<?, ?> colCorreo;

    @FXML
    private TableColumn<?, ?> colTelefono;

    @FXML
    private TableColumn<?, ?> colCarrera;

    @FXML
    private TableColumn<?, ?> colbeca;

    private Stage stageEstudiante;

    private RegisterStudentController registerStudentController;
    @FXML
    void borrarEstudiante(ActionEvent event) {

    }

    @FXML
    void buscarEstudiante(KeyEvent event) {

    }

    @FXML
    void editarEstudiante(ActionEvent event) {

    }

    @FXML
    void listarEstudiantes(ActionEvent event) {

    }

    @FXML
    void nuevoEstudiante(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterStudent.fxml"));
        AnchorPane ap = loader.load();
        registerStudentController = loader.getController();
        Scene scene = new Scene(ap);
        stageEstudiante = new Stage();

        stageEstudiante.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageEstudiante.setScene(scene);
        stageEstudiante.initOwner(root.getScene().getWindow());
        stageEstudiante.initModality(Modality.WINDOW_MODAL);
        stageEstudiante.initStyle(StageStyle.DECORATED);
        stageEstudiante.setResizable(false);
        stageEstudiante.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageEstudiante.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageEstudiante.showAndWait();
    }

}
