package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuArchivo;

    @FXML
    private MenuItem menuConf;

    @FXML
    private MenuItem menuOrden;

    @FXML
    private MenuItem menuSalir;

    @FXML
    private Menu menuInventario;

    @FXML
    private MenuItem menuVerMaterial;

    @FXML
    private MenuItem menuVerMantenimiento;

    @FXML
    private Menu menuOrdenes;

    @FXML
    private MenuItem menuBorrador;

    @FXML
    private MenuItem menuPresentada;

    @FXML
    private MenuItem menuAceptada;

    @FXML
    private MenuItem menuProceso;

    @FXML
    private MenuItem menuFinalizada;

    @FXML
    private Menu menuUsuarios;

    @FXML
    private MenuItem verUsuarios;

    @FXML
    private Menu menuClientes;

    @FXML
    private MenuItem verClientes;

    @FXML
    private JFXTabPane tabPane;

    @FXML
    void abrirConfiguracion(ActionEvent event) {

    }

    @FXML
    void mostrarClientes(ActionEvent event) {

    }

    @FXML
    void mostrarMantenimiento(ActionEvent event) {

    }

    @FXML
    void mostrarTablaUsuarios(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {

    }

    @FXML
    void tablaMateriales(ActionEvent event) {

    }

    @FXML
    void verAceptada(ActionEvent event) {

    }

    @FXML
    void verBorrador(ActionEvent event) {

    }

    @FXML
    void verFinalizada(ActionEvent event) {

    }

    @FXML
    void verOrdenPrincipal(ActionEvent event) {

    }

    @FXML
    void verPresentada(ActionEvent event) {

    }

    @FXML
    void verProceso(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
