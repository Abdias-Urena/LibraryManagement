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

/**
 * The type Dashboard controller.
 */
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

    private Tab tabLibros, tabEquipos, tabLoanLibros, tabReports, tabLoanDevices, tabUsers,tabTeachers ,tabTickets;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Abrir libros.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Abrir equipos.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Abrir prestamos equipos.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Abrir prestamos libros.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Abrir profesores.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void abrirProfesores(ActionEvent event) throws IOException {
        if (tabTeachers == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TeacherTable.fxml")));
            tabTeachers = new Tab("Profesores", ap);

            tabTeachers.setGraphic(FontIcon.of(new FontIcon("fa-address-card-o").getIconCode(), 20, Color.valueOf("#fff")));
            tabTeachers.setClosable(true);
            tabTeachers.setOnClosed(event1 -> {

                tabTeachers = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabTeachers);

        }
        tabPane.getSelectionModel().select(tabTeachers);
    }

    /**
     * Abrir usuarios.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void abrirUsuarios(ActionEvent event) throws IOException {
        if (tabUsers == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/StudentTable.fxml")));
            tabUsers = new Tab("Estudiantes", ap);

            tabUsers.setGraphic(FontIcon.of(new FontIcon("fa-address-card-o").getIconCode(), 20, Color.valueOf("#fff")));
            tabUsers.setClosable(true);
            tabUsers.setOnClosed(event1 -> {

                tabUsers = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabUsers);

        }
        tabPane.getSelectionModel().select(tabUsers);
    }

    /**
     * Mostrar reportes.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Mostrar tablas tickets.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void mostrarTablasTickets(ActionEvent event) throws IOException {
        if (tabTickets == null) {
            AnchorPane ap = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TicketsTable.fxml")));
            tabTickets = new Tab("Multas", ap);

            tabTickets.setGraphic(FontIcon.of(new FontIcon("fa-laptop").getIconCode(), 20, Color.valueOf("#fff")));
            tabTickets.setClosable(true);
            tabTickets.setOnClosed(event1 -> {

                tabTickets = null;
            });
            //tabPane.setStyle("-fx-background-color:red");
            tabPane.getTabs().add(tabTickets);

        }
        tabPane.getSelectionModel().select(tabTickets);
    }

}
