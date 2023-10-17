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

    private Tab tabLibros, tabEquipos, tabLoanLibros, tabReports, tabLoanDevices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void abrirLibros(ActionEvent event) throws IOException {
        if (tabLibros == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Books.fxml")));
            tabLibros = new Tab("Libros", ap);

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
        if (tabEquipos == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Device.fxml")));
            tabEquipos = new Tab("Equipos", ap);

            tabEquipos.setGraphic(FontIcon.of(new FontIcon("fa-laptop").getIconCode(), 20, Color.valueOf("#fff")));
            tabEquipos.setClosable(true);
            tabEquipos.setOnClosed(event1 -> {

                tabEquipos = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabEquipos);

        }
        tabPane.getSelectionModel().select(tabEquipos);
    }

    @FXML
    void abrirPrestamosEquipos(ActionEvent event) throws IOException {
        if (tabLoanDevices == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoanDeviceTable.fxml")));
            tabLoanDevices = new Tab("Prestamo Equipos", ap);

            tabLoanDevices.setGraphic(FontIcon.of(new FontIcon("fa-laptop").getIconCode(), 20, Color.valueOf("#fff")));
            tabLoanDevices.setClosable(true);
            tabLoanDevices.setOnClosed(event1 -> {

                tabLoanDevices = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabLoanDevices);

        }
        tabPane.getSelectionModel().select(tabLoanDevices);
    }

    @FXML
    void abrirPrestamosLibros(ActionEvent event) throws IOException {
        if (tabLoanLibros == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoanBooksTable.fxml")));
            tabLoanLibros = new Tab("Prestamo Libros", ap);

            tabLoanLibros.setGraphic(FontIcon.of(new FontIcon("fa-laptop").getIconCode(), 20, Color.valueOf("#fff")));
            tabLoanLibros.setClosable(true);
            tabLoanLibros.setOnClosed(event1 -> {

                tabLoanLibros = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabLoanLibros);

        }
        tabPane.getSelectionModel().select(tabLoanLibros);
    }

    @FXML
    void abrirProfesores(ActionEvent event) {

    }

    @FXML
    void abrirUsuarios(ActionEvent event) {

    }

    @FXML
    void mostrarReportes(ActionEvent event) throws IOException {
        if (tabReports == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/ReportTable.fxml")));
            tabReports = new Tab("Reportes", ap);

            tabReports.setGraphic(FontIcon.of(new FontIcon("fa-book").getIconCode(), 20, Color.valueOf("#fff")));
            tabReports.setClosable(true);
            tabReports.setOnClosed(event1 -> {

                tabReports = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabReports);

        }
        tabPane.getSelectionModel().select(tabReports);
    }

    @FXML
    void mostrarTablasTickets(ActionEvent event) {

    }

}
