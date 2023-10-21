package Controller;

import Connection.DatabaseConnection;
import Person.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import Report.*;

public class ReportTableController implements Initializable {

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
    private TableView<ReportBook> tablaReportes;

    @FXML
    private TableColumn<ReportBook, String> colTitulo;

    @FXML
    private TableColumn<ReportBook, String> colDiaReporte;

    @FXML
    private TableColumn<ReportBook, String> colDescripcion;

    @FXML
    private TableColumn<ReportBook, String> colTipoReporte;

    private Stage stageReport;

    private ReportsController reportsController;

    ObservableList<ReportBook> report = FXCollections.observableArrayList();

    DatabaseConnection connection = DatabaseConnection.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
    @FXML
    void borrarReporte(ActionEvent event) {

    }

    @FXML
    void buscarReporte(KeyEvent event) {
        String search = textBuscar.getText();
        UpdateTable(search);

    }

    @FXML
    void editarReporte(ActionEvent event) {

    }

    @FXML
    void listarReporte(ActionEvent event) {
        UpdateTable();
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

    public ObservableList<ReportBook> getReportList() {
        ObservableList<ReportBook> list_report = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("SELECT DATE_REPORT,DESCRIPTION" +
                    ",TITLE,TYPE_REPORT from REPORT");
            ResultSet rs = pst.executeQuery();
            returnStudents(list_report, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_report;
    }
    public void returnStudents(ObservableList<ReportBook> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            list.add(new ReportBook(null,rs.getString("DATE_REPORT"), rs.getString("DESCRIPTION"),
                    rs.getString("TITLE"), rs.getString("TYPE_REPORT")));
            returnStudents(list, rs);
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }
    public void UpdateTable() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("dateReport"));
        colDiaReporte.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTipoReporte.setCellValueFactory(new PropertyValueFactory<>("typeReport"));
        report = getReportList();
        tablaReportes.setItems(report);
    }
    public void UpdateTable(String id) {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("dateReport"));
        colDiaReporte.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTipoReporte.setCellValueFactory(new PropertyValueFactory<>("typeReport"));

        ObservableList<ReportBook> filteredStudents = report.filtered(rep ->
                rep.getTitle().toLowerCase().contains(id.toLowerCase()) ||
                        rep.getDateReport().toLowerCase().contains(id.toLowerCase()) ||
                        rep.getTypeReport().toLowerCase().contains(id.toLowerCase()) ||
                        rep.getDescription().toLowerCase().contains(id.toLowerCase())
        );
        tablaReportes.setItems(filteredStudents);
    }

}