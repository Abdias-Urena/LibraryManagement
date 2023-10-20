package Controller;

import Connection.DatabaseConnection;
import Loan.Loan;
import Person.Student;
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
import javafx.scene.control.ButtonType;
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

public class LoanBooksTableController implements Initializable {

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
    private TableView<Loan> tablaPrestamoLibro;

    @FXML
    private TableColumn<Loan, String> colLibroPrestamo;

    @FXML
    private TableColumn<Loan, String> colFechaPrestamo;

    @FXML
    private TableColumn<Loan, String> colFechaLimite;

    @FXML
    private TableColumn<Loan, String> collPersona;

    private Stage stageLoanBookTable;

    private LoanBooksController loanBooksController;
    ObservableList<Loan> loans = FXCollections.observableArrayList();
    DatabaseConnection connection = DatabaseConnection.getInstance();
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    public void UpdateTable() {
        colLibroPrestamo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        colFechaLimite.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        collPersona.setCellValueFactory(new PropertyValueFactory<>("user"));
        loans = getLoanList();
        tablaPrestamoLibro.setItems(loans);
    }
    public void UpdateTable(String id){
        colLibroPrestamo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        colFechaLimite.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        collPersona.setCellValueFactory(new PropertyValueFactory<>("user"));

        ObservableList<Loan> filteredStudents = loans.filtered(student ->
                student.getId().toLowerCase().contains(id.toLowerCase())
        );
        tablaPrestamoLibro.setItems(filteredStudents);
    }
    public ObservableList<Loan> getLoanList() {
        ObservableList<Loan> list_loan = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("SELECT l.EXPIRATION_DATE, l.LOAN_DATE, b.TITLE, u.ID, u.NAME,u.LAST_NAME, " +
                    "u.ADDRESS,u.EMAIL, u.PHONE_NUMBER FROM LOAN l INNER JOIN BOOK b ON l.NUMBER_BOOK = b.NUMBER_BOOK " +
                    "INNER JOIN USER u ON l.ID = u.ID");
            ResultSet rs = pst.executeQuery();
            returnLoans(list_loan, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_loan;
    }
    public void returnLoans(ObservableList<Loan> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            list.add(new Loan(rs.getString("EXPIRATION_DATE"), rs.getString("TITLE"),
                    rs.getString("LOAN_DATE"), new User(rs.getString("ADDRESS"),rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"),rs.getString("ID"),rs.getString("LAST_NAME"),
                    rs.getString("NAME"))));
            System.out.println(list.get(0).toString());
            returnLoans(list, rs);
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }
    @FXML
    void borrarPrestamoLibro(ActionEvent event) {
        Loan loan = tablaPrestamoLibro.getSelectionModel().getSelectedItem();

        if (loan != null) {
            // Mostrar una confirmación al usuario
            boolean confirmacion = showConfirmationDialog("¿Estás seguro de que deseas eliminar este libro?");

            if (confirmacion) {
                // Borrar el libro de la base de datos
                boolean exito = deleteBooksFromDatabase(loan);
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

    @FXML
    void buscarPrestamoLibros(KeyEvent event) {
        String searchBook = textBuscar.getText();
        UpdateTable(searchBook);
    }



    @FXML
    void listarPrestamoLibro(ActionEvent event) {
        UpdateTable();
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

    private boolean deleteBooksFromDatabase(Loan loan) {
        int rowsAffected;
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("SELECT NUMBER_BOOK FROM BOOK WHERE TITLE = ?");
            pst.setString(1, loan.getId());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String str = rs.getString("NUMBER_BOOK");
                System.out.println(str);
                pst = connection.getConnection().prepareStatement("DELETE FROM LOAN WHERE NUMBER_BOOK = ?");
                pst.setString(1,str);
                int row = pst.executeUpdate();
                System.out.println(row);

                pst = connection.getConnection().prepareStatement("UPDATE BOOK SET IS_AVAILABLE = ?");
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

    private boolean showConfirmationDialog(String mensaje) {
        return new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK, ButtonType.CANCEL).showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

    private void showAlert(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }

}
