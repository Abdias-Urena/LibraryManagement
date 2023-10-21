package Controller;


import Connection.DatabaseConnection;
import Device.Device;
import Loan.Loan;
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
import javafx.scene.control.Alert;
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
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * The type Loan device table controller.
 */
public class LoanDeviceTableController implements Initializable {

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
    private TableView<Loan> tablaPrestamoEquipo;

    @FXML
    private TableColumn<Loan, String> colDispos;

    @FXML
    private TableColumn<Loan, String> colFechaPrestamo;

    @FXML
    private TableColumn<Loan, String> collFechaLimite;

    @FXML
    private TableColumn<Loan, String> collPersona;

    private Stage stageLoanDeviceTable;

    private LoanDevicesController loanDevicesController;
    /**
     * The Loans.
     */
    ObservableList<Loan> loans = FXCollections.observableArrayList();
    /**
     * The Connection.
     */
    DatabaseConnection connection = DatabaseConnection.getInstance();
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    /**
     * Update table.
     */
    public void UpdateTable() {
        colDispos.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        collFechaLimite.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        collPersona.setCellValueFactory(new PropertyValueFactory<>("user"));
        loans = getLoanList();
        tablaPrestamoEquipo.setItems(loans);
    }

    /**
     * Update table.
     *
     * @param id the id
     */
    public void UpdateTable(String id){
        colDispos.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        collFechaLimite.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        collPersona.setCellValueFactory(new PropertyValueFactory<>("user"));

        ObservableList<Loan> filteredStudents = loans.filtered(student ->
                student.getId().toLowerCase().contains(id.toLowerCase()) || student.getUser().getId().
                        contains(id.toLowerCase())||student.getUser().getLastname().
                        contains(id.toLowerCase())
        );
        tablaPrestamoEquipo.setItems(filteredStudents);
    }

    /**
     * Gets loan list.
     *
     * @return the loan list
     */
    public ObservableList<Loan> getLoanList() {
        ObservableList<Loan> list_loan = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("SELECT l.EXPIRATION_DATE, l.LOAN_DATE, d.TYPE, d.BRAND, d.ID, u.ID, u.NAME,u.LAST_NAME, " +
                    "u.ADDRESS,u.EMAIL, u.PHONE_NUMBER FROM LOAN l INNER JOIN DEVICE d ON l.NUMBER_DEVICE = d.NUMBER_DEVICE " +
                    "INNER JOIN USER u ON l.ID = u.ID");
            ResultSet rs = pst.executeQuery();
            returnLoans(list_loan, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_loan;
    }

    /**
     * Return loans.
     *
     * @param list the list
     * @param rs   the rs
     */
    public void returnLoans(ObservableList<Loan> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            String type = rs.getString("TYPE").equals("T") ? "Tablet" : "Laptop";
            type+= " "+ rs.getString("BRAND") +" "+ rs.getString("d.ID");
            list.add(new Loan(rs.getString("EXPIRATION_DATE"), type,
                    rs.getString("LOAN_DATE"), new User(rs.getString("ADDRESS"),rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"),rs.getString("u.ID"),rs.getString("LAST_NAME"),
                    rs.getString("NAME"))));
            System.out.println(list.get(0).toString());
            returnLoans(list, rs);
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }

    /**
     * Borrar prestamo equipo.
     *
     * @param event the event
     */
    @FXML
    void borrarPrestamoEquipo(ActionEvent event) {
        Loan loan = tablaPrestamoEquipo.getSelectionModel().getSelectedItem();

        if (loan != null) {
            // Mostrar una confirmación al usuario
            boolean confirmacion = showConfirmationDialog("¿Estás seguro de que deseas eliminar este libro?");

            if (confirmacion) {
                // Borrar el libro de la base de datos
                boolean exito = deleteDeviceFromDatabase(loan);
                System.out.println("sdfsdfadf");

                if (exito) {
                    // Actualizar la tabla después de borrar
                    UpdateTable();
                } else {
                    // Mostrar un mensaje de error si la eliminación falla
                    showAlert("Error al eliminar el libro.");
                }
            }
        } else {
            showAlert("Por favor, selecciona un libro para eliminar.");
        }
    }
    private boolean deleteDeviceFromDatabase(Loan loan) {
        String []id = loan.getId().split(" ");
        System.out.println(id[2]);
        int rowsAffected;
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("SELECT NUMBER_DEVICE FROM DEVICE WHERE ID = ?");
            pst.setString(1, id[2]);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String str = rs.getString("NUMBER_DEVICE");
                System.out.println(str);
                pst = connection.getConnection().prepareStatement("DELETE FROM LOAN WHERE NUMBER_DEVICE = ?");
                pst.setString(1,str);
                int row = pst.executeUpdate();
                System.out.println(row);

                pst = connection.getConnection().prepareStatement("UPDATE DEVICE SET IS_AVAILABLE = ?");
                pst.setString(1,"Y");
                rowsAffected = pst.executeUpdate();
                System.out.println("Hola");
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Buscar prestamo equipos.
     *
     * @param event the event
     */
    @FXML
    void buscarPrestamoEquipos(KeyEvent event) {
        String search = textBuscar.getText();
        UpdateTable(search);
    }

    /**
     * Editar prestamo equipo.
     *
     * @param event the event
     */
    @FXML
    void editarPrestamoEquipo(ActionEvent event) {

    }

    /**
     * Listar prestamo equipos.
     *
     * @param event the event
     */
    @FXML
    void listarPrestamoEquipos(ActionEvent event) {
        UpdateTable();
    }

    /**
     * Nuevo prestamo equipo.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void nuevoPrestamoEquipo(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoanDevice.fxml"));
        AnchorPane ap = loader.load();
        loanDevicesController = loader.getController();
        Scene scene = new Scene(ap);
        stageLoanDeviceTable = new Stage();

        stageLoanDeviceTable.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageLoanDeviceTable.setScene(scene);
        stageLoanDeviceTable.initOwner(root.getScene().getWindow());
        stageLoanDeviceTable.initModality(Modality.WINDOW_MODAL);
        stageLoanDeviceTable.initStyle(StageStyle.DECORATED);
        stageLoanDeviceTable.setResizable(false);
        stageLoanDeviceTable.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageLoanDeviceTable.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageLoanDeviceTable.showAndWait();
    }
    private boolean showConfirmationDialog(String mensaje) {
        return new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK, ButtonType.CANCEL).showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

    private void showAlert(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }

}