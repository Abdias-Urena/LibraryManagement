package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuRegistro;

    @FXML
    private MenuItem menuRegistroLibro;

    @FXML
    private MenuItem menuRegistroEquipo;

    @FXML
    private Menu menuPrestamos;

    @FXML
    private MenuItem menuPrestamosLibros;

    @FXML
    private MenuItem menuPrestamosEquipos;

    @FXML
    private Menu menuUsuarios;

    @FXML
    private MenuItem menuRegistroEst;

    @FXML
    private MenuItem menuRegistroProf;

    @FXML
    private Menu menuTickets;

    @FXML
    private MenuItem menuVerTickets;

    @FXML
    private Menu menuReportes;

    @FXML
    private MenuItem menuVerReportes;

    @FXML
    private JFXTabPane tabPane;

    private Tab tabLibros;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void abrirLibros(ActionEvent event) throws IOException {
        if (tabLibros == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Books.fxml")));
            tabLibros = new Tab("LIBROS FISICOS", ap);

            tabLibros.setGraphic(FontIcon.of(new FontIcon("fa-book").getIconCode(), 20, Color.valueOf("#fff")));
            tabLibros.setClosable(true);
            tabLibros.setOnClosed(event1 -> {

                tabLibros = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabLibros);

        }
        tabPane.getSelectionModel().select(tabLibros);
    }

    @FXML
    void abrirEquipos(ActionEvent event) throws IOException {

    }

    @FXML
    void abrirPrestamosEquipos(ActionEvent event) {

    }

    @FXML
    void abrirPrestamosLibros(ActionEvent event) {

    }

    @FXML
    void abrirProfesores(ActionEvent event) {

    }

    @FXML
    void abrirUsuarios(ActionEvent event) {

    }

    @FXML
    void mostrarReportes(ActionEvent event) {

    }

    @FXML
    void mostrarTablasTickets(ActionEvent event) {

    }

}
