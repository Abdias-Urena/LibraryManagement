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

public class TeacherTableController {

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
    private TableView<?> tablaProfesor;

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
    private TableColumn<?, ?> colDepartamento;

    private Stage stageProfesor;

    private RegisterTeachersController registerTeachersController;
    @FXML
    void borrarProfesor(ActionEvent event) {

    }

    @FXML
    void buscarProfesor(KeyEvent event) {

    }

    @FXML
    void editarProfesor(ActionEvent event) {

    }

    @FXML
    void listarProfesor(ActionEvent event) {

    }

    @FXML
    void nuevoProfesor(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterTeachers.fxml"));
        AnchorPane ap = loader.load();
        registerTeachersController = loader.getController();
        Scene scene = new Scene(ap);
        stageProfesor = new Stage();

        stageProfesor.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageProfesor.setScene(scene);
        stageProfesor.initOwner(root.getScene().getWindow());
        stageProfesor.initModality(Modality.WINDOW_MODAL);
        stageProfesor.initStyle(StageStyle.DECORATED);
        stageProfesor.setResizable(false);
        stageProfesor.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageProfesor.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageProfesor.showAndWait();
    }

}
