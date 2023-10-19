package Controller;

import Connection.DatabaseConnection;
import Device.Device;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;

public class LoanDevicesController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<String> cmbTipoDis;

    @FXML
    private JFXTextField textNombre;

    @FXML
    private JFXDatePicker datePickerActual;

    @FXML
    private JFXDatePicker datePickerLimite;

    @FXML
    private JFXComboBox<Device> cmbDispDis;

    @FXML
    private JFXButton btnGuardar;
    @FXML
    private TableView<User> tablaEstudiantes;

    @FXML
    private TableColumn<User, ?> colNombre;

    @FXML
    private TableColumn<User, ?> colApellido;

    @FXML
    private TableColumn<User, ?> colDireccion;

    @FXML
    private TableColumn<User, ?> colCorreo;

    @FXML
    private TableColumn<User, ?> colTelefono;
    ObservableList<User> students = FXCollections.observableArrayList();

    DatabaseConnection connection = DatabaseConnection.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        fillComboBox();
    }
    public void fillComboBox(){
        ObservableList<String> list_comboType = FXCollections.observableArrayList("Tablet","Laptop");
        cmbTipoDis.setItems(list_comboType);
    }

    @FXML
    void guardar(ActionEvent event) {

    }

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
    public void returnStudents(ObservableList<User> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            list.add(new User(rs.getString("ADDRESS"), rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"), rs.getString("ID"),
                    rs.getString("LAST_NAME"), rs.getString("NAME")));
            System.out.println(list.get(0).toString());
            returnStudents(list, rs);
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }
    public void UpdateTable() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        students = getStudentList();
        tablaEstudiantes.setItems(students);
    }
    @FXML
    void search(KeyEvent event) {
        String search = textNombre.getText();
        UpdateTable(search);

    }
    public void UpdateTable(String id){
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
    @FXML
    void selectionType(ActionEvent event) {
        switch (cmbTipoDis.getValue()){
            case "Tablet":

                break;
            case "Laptop":

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cmbTipoDis.getValue());
        }
    }
    public void fillDisBox(){

    }
    public ObservableList<Device> getDeviceList() {
        ObservableList<Device> list_students = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("select HAVE from DEVICE");
            ResultSet rs = pst.executeQuery();
            returnDevices(list_students, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_students;
    }
    public void returnDevices(ObservableList<Device> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            list.add(new Device(rs.getString("ADDRESS"), rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"), rs.getString("ID"),
                    rs.getString("LAST_NAME"), rs.getString("NAME")));
            System.out.println(list.get(0).toString());
            returnDevices(list, rs);
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }
}
