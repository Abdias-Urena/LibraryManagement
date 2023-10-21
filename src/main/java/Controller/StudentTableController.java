package Controller;

import Book.DigitalBook;
import Connection.DatabaseConnection;
import Person.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
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
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The type Student table controller.
 */
public class StudentTableController implements Initializable {

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
    private TableView<Student> tablaEstudiantes;

    @FXML
    private TableColumn<Student, String> colNombre;

    @FXML
    private TableColumn<Student, String> colApellido;

    @FXML
    private TableColumn<Student, String> colDireccion;

    @FXML
    private TableColumn<Student, String> colCorreo;

    @FXML
    private TableColumn<Student, String> colTelefono;

    @FXML
    private TableColumn<Student, String> colCarrera;

    @FXML
    private TableColumn<Student, String> colbeca;

    private Stage stageEstudiante;

    private RegisterStudentController registerStudentController;

    /**
     * The Students.
     */
    ObservableList<Student> students = FXCollections.observableArrayList();

    /**
     * The Connection.
     */
    DatabaseConnection connection = DatabaseConnection.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    /**
     * Gets student list.
     *
     * @return the student list
     */
    public ObservableList<Student> getStudentList() {
        ObservableList<Student> list_students = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("select ID,NAME,LAST_NAME,ADDRESS,EMAIL,PHONE_NUMBER,CAREER,`GRANT` from USER WHERE ROL = 'S'");
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
    public void returnStudents(ObservableList<Student> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            list.add(new Student(rs.getString("ADDRESS"), rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"), rs.getString("ID"),
                    rs.getString("LAST_NAME"), rs.getString("NAME"),
                    rs.getString("CAREER"), true));
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
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("career"));
        colbeca.setCellValueFactory(cellData -> {
            if (cellData.getValue().isGrant()) {
                return new SimpleStringProperty("Tiene");
            } else {
                return new SimpleStringProperty("No tiene");
            }
        });
        students = getStudentList();
        tablaEstudiantes.setItems(students);
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
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("career"));
        colbeca.setCellValueFactory(cellData -> {
            if (cellData.getValue().isGrant()) {
                return new SimpleStringProperty("Tiene");
            } else {
                return new SimpleStringProperty("No tiene");
            }
        });

        ObservableList<Student> filteredStudents = students.filtered(student ->
                student.getId().toLowerCase().contains(id.toLowerCase()) ||
                        student.getLastname().toLowerCase().contains(id.toLowerCase()) ||
                                student.getName().toLowerCase().contains(id.toLowerCase())
        );
        tablaEstudiantes.setItems(filteredStudents);
    }

    /**
     * Borrar estudiante.
     *
     * @param event the event
     */
    @FXML
    void borrarEstudiante(ActionEvent event) {

    }

    /**
     * Buscar estudiante.
     *
     * @param event the event
     */
    @FXML
    void buscarEstudiante(KeyEvent event) {
        String id = textBuscar.getText();
        UpdateTable(id);
    }

    /**
     * Editar estudiante.
     *
     * @param event the event
     */
    @FXML
    void editarEstudiante(ActionEvent event) {

    }

    /**
     * Listar estudiantes.
     *
     * @param event the event
     */
    @FXML
    void listarEstudiantes(ActionEvent event) {
        UpdateTable();
    }

    /**
     * Nuevo estudiante.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void nuevoEstudiante(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterStudent.fxml"));
        AnchorPane ap = loader.load();
        registerStudentController = loader.getController();
        Scene scene = new Scene(ap);
        stageEstudiante = new Stage();

        stageEstudiante.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageEstudiante.setScene(scene);
        stageEstudiante.initOwner(root.getScene().getWindow());
        stageEstudiante.initModality(Modality.WINDOW_MODAL);
        stageEstudiante.initStyle(StageStyle.DECORATED);
        stageEstudiante.setResizable(false);
        stageEstudiante.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageEstudiante.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageEstudiante.showAndWait();
    }

}
