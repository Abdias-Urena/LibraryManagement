package Controller;

import Book.*;
import Connection.DatabaseConnection;
import Device.Device;
import Loan.Loan;
import Person.Student;
import Person.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.dc.pr.PRError;

public class ReportsController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<String> cmbReportes;

    @FXML
    private JFXTextField textTittle;

    @FXML
    private JFXDatePicker DatePicker;

    @FXML
    private JFXTextArea textAreaDescri;

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableView<Book> tablaLibros;

    @FXML
    private TableColumn<Book, String> colAutor;

    @FXML
    private TableColumn<Book, String> colCategoria;

    @FXML
    private TableColumn<Book, String> colPublicacion;

    @FXML
    private TableColumn<Book, String> colReproduccion;

    @FXML
    private TableColumn<Book, String> colTitle;

    @FXML
    private TableColumn<Book, String> colTipo;

    @FXML
    private TableColumn<Book, String> colURL;

    @FXML
    private TableView<Device> tablaEquipos;

    @FXML
    private TableColumn<Device, String> colMarca;

    @FXML
    private TableColumn<Device, String> colDisponibilidad;

    @FXML
    private TableColumn<Device, String> collTipo;

    @FXML
    private TableColumn<Device, String> collID;

    @FXML
    private TableColumn<Device, String> collCharge;


    @FXML
    private TableView<Loan> tablaPrestamoLoan;

    @FXML
    private TableColumn<Loan, String> colLibroPrestamoLoan;

    @FXML
    private TableColumn<Loan, String> colFechaPrestamoLoan;

    @FXML
    private TableColumn<Loan, String> colFechaLimiteLoan;

    @FXML
    private TableColumn<Loan, String> collPersonaLoan;

    @FXML
    private TableView<String> tablaUsers;

    @FXML
    private TableColumn<String, String> colNombre;

    @FXML
    private TableColumn<String, String> colApellido;

    @FXML
    private TableColumn<String, String> colDireccion;

    @FXML
    private TableColumn<String, String> colCorreo;

    @FXML
    private TableColumn<String, String> colTelefono;

    @FXML
    private TableColumn<String, String> colType;

    DatabaseConnection connection = DatabaseConnection.getInstance();
    ObservableList<Loan> loans = FXCollections.observableArrayList();
    ObservableList<Book> BookList = FXCollections.observableArrayList();
    ObservableList<Device> DeviceList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillComboBox();
        UpdateTableLoan();
        UpdateTableBook();
        UpdateTableDevice();

    }
    public void fillComboBox(){
        ObservableList<String> list_type = FXCollections.observableArrayList("Usuarios con ticket","Prestamos",
                "Libros","Dispositivos");
        cmbReportes.setItems(list_type);
    }
    public void UpdateTableLoan() {
        colLibroPrestamoLoan.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaPrestamoLoan.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        colFechaLimiteLoan.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        collPersonaLoan.setCellValueFactory(new PropertyValueFactory<>("user"));
        loans = getLoanList();
        tablaPrestamoLoan.setItems(loans);
    }
    public ObservableList<Loan> getLoanList() {
        ObservableList<Loan> list_loan = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("SELECT l.EXPIRATION_DATE, l.LOAN_DATE, b.TITLE, u.ID, u.NAME,u.LAST_NAME, " +
                    "u.ADDRESS,u.EMAIL, u.PHONE_NUMBER FROM LOAN l INNER JOIN BOOK b ON l.NUMBER_BOOK = b.NUMBER_BOOK" +
                    " INNER JOIN USER u ON l.ID = u.ID");
            ResultSet rs = pst.executeQuery();
            returnLoans(list_loan, rs);
            System.out.println("sdfasdfadfa");
            pst.close();
            pst = connection.getConnection().prepareStatement("SELECT l.EXPIRATION_DATE, l.LOAN_DATE, d.TYPE,u.ID, u.NAME,u.LAST_NAME, " +
                    "u.ADDRESS,u.EMAIL, u.PHONE_NUMBER FROM LOAN l INNER JOIN DEVICE d ON l.NUMBER_DEVICE = d.NUMBER_DEVICE" +
                    " INNER JOIN USER u ON l.ID = u.ID");
            rs = pst.executeQuery();
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
            String item = (rs.getString(3).equals("T") ? "Tablet" : (rs.getString(3).equals("L")
                    ? "Laptop" : rs.getString("TITLE")));
            list.add(new Loan(rs.getString("EXPIRATION_DATE"), item,
                    rs.getString("LOAN_DATE"), new User(rs.getString("ADDRESS"),rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"),rs.getString("ID"),rs.getString("LAST_NAME"),
                    rs.getString("NAME"))));

            System.out.println(list.get(0).toString());
            returnLoans(list, rs);
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }

    public ObservableList<Book> getBooksList() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("select AUTHOR,CATEGORY,PUBLIC_DATE,REPRODUC,TITLE,TYPE,URL from book");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String type = rs.getString("TYPE");

                if ("D".equals(type)) {
                    // Es un libro digital, crea una instancia de DigitalBook
                    bookList.add(new DigitalBook(rs.getString("URL"),
                            rs.getString("AUTHOR"),
                            rs.getString("CATEGORY"),
                            rs.getString("PUBLIC_DATE"),
                            rs.getString("REPRODUC"),
                            rs.getString("TITLE"),
                            rs.getString("TYPE")));
                } else if ("F".equals(type)) {
                    // Es un libro físico, crea una instancia de Book
                    bookList.add(new Book(rs.getString("AUTHOR"),
                            rs.getString("CATEGORY"),
                            rs.getString("PUBLIC_DATE"),
                            rs.getString("REPRODUC"),
                            rs.getString("TITLE"),
                            rs.getString("TYPE")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookList;
    }


    public void UpdateTableBook() {
        colAutor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        colReproduccion.setCellValueFactory(new PropertyValueFactory<>("reproduction"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("type"));
        colURL.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof DigitalBook) {
                return new SimpleStringProperty(((DigitalBook) cellData.getValue()).getUrl());
            } else {
                return new SimpleStringProperty("");
            }
        });
        BookList = getBooksList();
        tablaLibros.setItems(BookList);
    }

    public ObservableList<Device> getDeviceList() {
        ObservableList<Device> DeviceList = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("select * from device");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DeviceList.add(new Device(
                        rs.getString("BRAND"),
                        rs.getBoolean("HAVE_CHARGER"),
                        rs.getString("ID"),
                        rs.getBoolean("IS_USABLE"),
                        rs.getBoolean("IS_AVAILABLE"),
                        rs.getString("TYPE")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DeviceList;
    }

    public void UpdateTableDevice() {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colDisponibilidad.setCellValueFactory(celldata -> {
            if (celldata.getValue().isAvailable()) {
                return new SimpleStringProperty("Disponible");
            } else return new SimpleStringProperty("No disponible");
        });
        collTipo.setCellValueFactory(new PropertyValueFactory<>("type"));
        collID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collCharge.setCellValueFactory(celldata -> {
            if (celldata.getValue().isHaveCharger()) {
                return new SimpleStringProperty("Disponible");
            } else return new SimpleStringProperty("No disponible");
        });
        DeviceList = getDeviceList();
        tablaEquipos.setItems(DeviceList);
    }


    @FXML
    void guardar(ActionEvent event) {
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("INSERT INTO REPORT(DATE_REPORT,DESCRIPTION,TITLE, TYPE_REPORT)" +
                    " VALUES(?,?,?,?)");
            preparedStatement.setDate(1, java.sql.Date.valueOf(LocalDate.parse(DatePicker.getValue().toString())));
            preparedStatement.setString(2, textAreaDescri.getText());
            preparedStatement.setString(3, textTittle.getText());
            preparedStatement.setString(4, cmbReportes.getValue());
            int row = preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }

    @FXML
    void searhType(ActionEvent event) {
        switch (cmbReportes.getValue()){
            case "Usuarios con ticket":
                tablaUsers.setVisible(true);
                tablaLibros.setVisible(false);
                tablaEquipos.setVisible(false);
                tablaPrestamoLoan.setVisible(false);
                break;
            case "Libros":
                tablaUsers.setVisible(false);
                tablaLibros.setVisible(true);
                tablaEquipos.setVisible(false);
                tablaPrestamoLoan.setVisible(false);
                break;
            case "Prestamos":
                tablaUsers.setVisible(false);
                tablaLibros.setVisible(false);
                tablaEquipos.setVisible(false);
                tablaPrestamoLoan.setVisible(true);
                break;
            case "Dispositivos":
                tablaUsers.setVisible(false);
                tablaLibros.setVisible(false);
                tablaEquipos.setVisible(true);
                tablaPrestamoLoan.setVisible(false);
                break;
            default:
                tablaUsers.setVisible(false);
                tablaLibros.setVisible(false);
                tablaEquipos.setVisible(false);
                tablaPrestamoLoan.setVisible(false);
                break;
        }

    }
}
