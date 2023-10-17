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

public class BooksController implements Initializable {

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
    private TableView<?> tablaLibros;

    @FXML
    private TableColumn<?, ?> colAutor;

    @FXML
    private TableColumn<?, ?> colCategoria;

    @FXML
    private TableColumn<?, ?> colPublicacion;

    @FXML
    private TableColumn<?, ?> colReproduccion;

    @FXML
    private TableColumn<?, ?> colTitulo;

    @FXML
    private TableColumn<?, ?> colTipo;

    private Stage stageLibros;

    private RegisterBookController registerBookController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void buscarLibro(KeyEvent event) {

    }

    @FXML
    void listarLibros(ActionEvent event) {

    }

    @FXML
    void nuevoLibro(ActionEvent event) throws IOException {

        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterBook.fxml"));
        AnchorPane ap = loader.load();
        registerBookController = loader.getController();
        Scene scene = new Scene(ap);
        stageLibros = new Stage();

        stageLibros.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageLibros.setScene(scene);
        stageLibros.initOwner(root.getScene().getWindow());
        stageLibros.initModality(Modality.WINDOW_MODAL);
        stageLibros.initStyle(StageStyle.DECORATED);
        stageLibros.setResizable(false);
        stageLibros.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageLibros.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageLibros.showAndWait();
    }


    @FXML
    void editarLibro(ActionEvent event) {

    }

    @FXML
    void borrarLibro(ActionEvent event) {

    }
}
