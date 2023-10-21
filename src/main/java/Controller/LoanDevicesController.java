package Controller;

import Book.Book;
import Connection.DatabaseConnection;
import Device.Device;
import Loan.Loan;
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

/**
 * The type Loan devices controller.
 */
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
    /**
     * The Students.
     */
    ObservableList<User> students = FXCollections.observableArrayList();
    /**
     * The List combox.
     */
    ObservableList<Device> list_combox = FXCollections.observableArrayList();

    /**
     * The Connection.
     */
    DatabaseConnection connection = DatabaseConnection.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        fillComboBox();
        list_combox= getDeviceList();
    }

    /**
     * Fill combo box.
     */
    public void fillComboBox(){
        ObservableList<String> list_comboType = FXCollections.observableArrayList("Tablet","Laptop");
        cmbTipoDis.setItems(list_comboType);
    }

    /**
     * Guardar.
     *
     * @param event the event
     */
    @FXML
    void guardar(ActionEvent event) {
        User searhStudent = students.filtered(student-> student.getId().equals(textNombre.getText())).stream().findFirst().
                orElse(null);
        System.out.println(searhStudent.toString());
        Loan loan = new Loan(datePickerLimite.getValue().toString(), "1",datePickerActual.getValue().toString(),
                searhStudent);
        Device device = list_combox.get(cmbDispDis.getItems().indexOf(cmbDispDis.getValue()));
        System.out.println(device.toString());
        loan.loanDevice(device);
        clean();
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
     * Search.
     *
     * @param event the event
     */
    @FXML
    void search(KeyEvent event) {
        String search = textNombre.getText();
        UpdateTable(search);

    }

    /**
     * Update table.
     *
     * @param id the id
     */
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

    /**
     * Selection type.
     *
     * @param event the event
     */
    @FXML
    void selectionType(ActionEvent event) {
        switch (cmbTipoDis.getValue()){
            case "Tablet":
                cmbDispDis.setItems(list_combox.filtered(device -> device.getType().equals("Tablet")));
                break;
            case "Laptop":
                cmbDispDis.setItems(list_combox.filtered(device -> device.getType().equals("Laptop")));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cmbTipoDis.getValue());
        }
    }

    /**
     * Gets device list.
     *
     * @return the device list
     */
    public ObservableList<Device> getDeviceList() {
        ObservableList<Device> list_students = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("SELECT ID, TYPE, BRAND from DEVICE WHERE IS_AVAILABLE= 'Y' AND IS_USABLE='Y'");
            ResultSet rs = pst.executeQuery();
            returnDevices(list_students, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_students;
    }

    /**
     * Return devices.
     *
     * @param list the list
     * @param rs   the rs
     */
    public void returnDevices(ObservableList<Device> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            String type = rs.getString("TYPE");
            Device device;
            if(type.equals("T")){
                device=new Device(rs.getString("BRAND"), true,
                        rs.getString("ID"), true, true, "Tablet");
            }else {
                device=new Device(rs.getString("BRAND"), true,
                        rs.getString("ID"), true, true, "Laptop");
            }
            list.add(device);
            System.out.println(list.get(0).toString());
            returnDevices(list, rs);
        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
    }

    /**
     * Clean.
     */
    public void clean() {
        cmbTipoDis.setValue("");
        cmbDispDis.setValue(null);
        datePickerLimite.setValue(null);
        datePickerActual.setValue(null);
        textNombre.setText("");
    }
}
