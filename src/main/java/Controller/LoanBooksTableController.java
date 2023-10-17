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

public class LoanBooksTableController {

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
    private TableView<?> tablaPrestamoLibro;

    @FXML
    private TableColumn<?, ?> colLibroPrestamo;

    @FXML
    private TableColumn<?, ?> colFechaPrestamo;

    @FXML
    private TableColumn<?, ?> colFechaLimite;

    @FXML
    private TableColumn<?, ?> collPersona;

    private Stage stageLoanBookTable;

    private LoanBooksController loanBooksController;
    @FXML
    void borrarPrestamoLibro(ActionEvent event) {

    }

    @FXML
    void buscarPrestamoLibros(KeyEvent event) {

    }

    @FXML
    void editarPrestamoLibro(ActionEvent event) {

    }

    @FXML
    void listarPrestamoLibro(ActionEvent event) {

    }

    @FXML
    void nuevoPrestamoLibro(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoanBooks.fxml"));
        AnchorPane ap = loader.load();
        loanBooksController = loader.getController();
        Scene scene = new Scene(ap);
        stageLoanBookTable = new Stage();

        stageLoanBookTable.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageLoanBookTable.setScene(scene);
        stageLoanBookTable.initOwner(root.getScene().getWindow());
        stageLoanBookTable.initModality(Modality.WINDOW_MODAL);
        stageLoanBookTable.initStyle(StageStyle.DECORATED);
        stageLoanBookTable.setResizable(false);
        stageLoanBookTable.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageLoanBookTable.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageLoanBookTable.showAndWait();
    }

}
