package Controller;

import Book.*;
import Connection.DatabaseConnection;
import Interface.LoanBook;
import Loan.Loan;
import Notification.Notification;
import Person.Student;
import Person.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.input.KeyEvent;

import javafx.util.StringConverter;

/**
 * The type Loan books controller.
 */
public class LoanBooksController implements Initializable {


    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXButton btnRegresarP;

    @FXML
    private JFXComboBox<String> comboBoxLibros;

    @FXML
    private JFXDatePicker datePickerLimite;

    @FXML
    private JFXDatePicker datePickerActual;

    @FXML
    private JFXTextField textNombre;

    @FXML
    private TableView<User> tablaEstudiantes;

    @FXML
    private TableColumn<User, String> colNombre;

    @FXML
    private TableColumn<User, String> colApellido;

    @FXML
    private TableColumn<User, String> colDireccion;

    @FXML
    private TableColumn<User, String> colCorreo;

    @FXML
    private TableColumn<User, String> colTelefono;


    /**
     * The Students.
     */
    ObservableList<User> students = FXCollections.observableArrayList();

    /**
     * The Connection.
     */
    DatabaseConnection connection = DatabaseConnection.getInstance();

    /**
     * The List combox.
     */
    ObservableList<Book> list_combox = FXCollections.observableArrayList();
    /**
     * The Noti.
     */
    Notification noti = new Notification();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        fillCombox();
    }

    /**
     * Guardar.
     *
     * @param event the event
     */
    @FXML
    void guardar(ActionEvent event) {
        User searhStudent = students.filtered(student -> student.getId().equals(textNombre.getText())).stream().findFirst().
                orElse(null);
        System.out.println(searhStudent.toString());
        Loan loan = new Loan(datePickerLimite.getValue().toString(), "1", datePickerActual.getValue().toString(),
                searhStudent);
        Book book = list_combox.get(comboBoxLibros.getItems().indexOf(comboBoxLibros.getValue()));
        System.out.println(book.toString());
        loan.addBook(book);
        clean();
    }

    /**
     * Fill combox.
     */
    void fillCombox() {
        list_combox = getBookList();
        ObservableList<String> list = FXCollections.observableArrayList();
        setListOfCombox(list, list_combox, list_combox.size() - 1);
        comboBoxLibros.setItems(list);
    }

    /**
     * Sets list of combox.
     *
     * @param list        the list
     * @param list_combox the list combox
     * @param index       the index
     */
    public void setListOfCombox(ObservableList<String> list, ObservableList<Book> list_combox, int index) {
        if (index < 0) {
            return;
        }
        list.add(list_combox.get(index).getTitle());
        setListOfCombox(list, list_combox, index - 1);
    }

    /**
     * Gets book list.
     *
     * @return the book list
     */
    public ObservableList<Book> getBookList() {
        ObservableList<Book> list_books = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("select AUTHOR,CATEGORY,PUBLIC_DATE,REPRODUC" +
                    ",TITLE,IS_AVAILABLE, IS_USABLE from Book WHERE TYPE ='F' AND IS_AVAILABLE= 'Y' AND IS_USABLE='Y'");
            ResultSet rs = pst.executeQuery();
            returnBooks(list_books, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_books;
    }

    /**
     * Gets student list.
     *
     * @return the student list
     */
    public ObservableList<User> getStudentList() {
        ObservableList<User> list_students = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("select ID,NAME,LAST_NAME,ADDRESS,EMAIL,PHONE_NUMBER from USER");
            ResultSet rs = pst.executeQuery();
            returnStudents(list_students, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_students;
    }

    /**
     * Return students.
     *
     * @param list the list
     * @param rs   the rs
     */
    public void returnStudents(ObservableList<User> list, ResultSet rs) {
        try {
            if (!rs.next()) {
                return;
            }
            list.add(new User(rs.getString("ADDRESS"), rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"), rs.getString("ID"),
                    rs.getString("LAST_NAME"), rs.getString("NAME")));
            System.out.println(list.get(0).toString());
            returnStudents(list, rs);
        } catch (SQLException s) {
            System.out.println(s.getErrorCode());
        }
    }

    /**
     * Return books.
     *
     * @param list the list
     * @param rs   the rs
     */
    public void returnBooks(ObservableList<Book> list, ResultSet rs) {
        try {
            if (!rs.next()) {
                return;
            }
            list.add(new FisicBook(true, false, rs.getString("AUTHOR"), rs.getString("CATEGORY"),
                    rs.getString("PUBLIC_DATE"), rs.getString("REPRODUC"),
                    rs.getString("TITLE"), "Fisico"));
            System.out.println(list.get(0).toString());
            returnBooks(list, rs);
        } catch (SQLException s) {
            System.out.println(s.getErrorCode());
        }
    }


    /**
     * Update table.
     */
    public void UpdateTable() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        students = getStudentList();
        tablaEstudiantes.setItems(students);
    }

    /**
     * Update table.
     *
     * @param id the id
     */
    public void UpdateTable(String id) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        ObservableList<User> filteredStudents = students.filtered(student ->
                student.getId().toLowerCase().contains(id.toLowerCase()) ||
                        student.getLastname().toLowerCase().contains(id.toLowerCase()) ||
                        student.getName().toLowerCase().contains(id.toLowerCase())
        );
        tablaEstudiantes.setItems(filteredStudents);
    }

    /**
     * Regresar p.
     *
     * @param event the event
     */
    @FXML
    void regresarP(ActionEvent event) {

    }

    /**
     * Clean.
     */
    public void clean() {
        comboBoxLibros.setValue("");
        datePickerLimite.setValue(null);
        datePickerActual.setValue(null);
        textNombre.setText("");
    }


    /**
     * Search.
     *
     * @param event the event
     */
    @FXML
    void search(KeyEvent event) {
        String id = textNombre.getText();
        UpdateTable(id);
    }
}
