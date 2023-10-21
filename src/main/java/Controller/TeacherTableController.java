package Controller;

import Connection.DatabaseConnection;
import Person.Student;
import Person.Teacher;
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

/**
 * The type Teacher table controller.
 */
public class TeacherTableController implements Initializable {

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
    private TableView<Teacher> tablaProfesor;

    @FXML
    private TableColumn<Teacher, String> colNombre;

    @FXML
    private TableColumn<Teacher, String> colApellido;

    @FXML
    private TableColumn<Teacher, String> colDireccion;

    @FXML
    private TableColumn<Teacher, String> colCorreo;

    @FXML
    private TableColumn<Teacher, String> colTelefono;

    @FXML
    private TableColumn<Teacher, String> colDepartamento;

    private Stage stageProfesor;

    private RegisterTeachersController registerTeachersController;
    /**
     * The Teachers.
     */
    ObservableList<Teacher> teachers = FXCollections.observableArrayList();

    /**
     * The Connection.
     */
    DatabaseConnection connection = DatabaseConnection.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    /**
     * Gets teachers list.
     *
     * @return the teachers list
     */
    public ObservableList<Teacher> getTeachersList() {
        ObservableList<Teacher> list_teachers = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.getConnection().prepareStatement("select ID,NAME,LAST_NAME,ADDRESS,EMAIL,PHONE_NUMBER,DEPARTMENT from USER WHERE ROL = 'T'");
            ResultSet rs = pst.executeQuery();
            returnStudents(list_teachers, rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list_teachers;
    }

    /**
     * Return students.
     *
     * @param list the list
     * @param rs   the rs
     */
    public void returnStudents(ObservableList<Teacher> list, ResultSet rs){
        try {
            if(!rs.next()){
                return;
            }
            list.add(new Teacher(rs.getString("ADDRESS"), rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"), rs.getString("ID"),
                    rs.getString("LAST_NAME"), rs.getString("NAME"),
                    rs.getString("DEPARTMENT")));
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
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departament"));
        teachers = getTeachersList();
        tablaProfesor.setItems(teachers);
    }

    /**
     * Update table.
     *
     * @param search the search
     */
    public void UpdateTable(String search) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departament"));

        ObservableList<Teacher> filteredTeachers = teachers.filtered(teacher ->
                teacher.getId().toLowerCase().contains(search.toLowerCase()) ||
                        teacher.getLastname().toLowerCase().contains(search.toLowerCase()) ||
                        teacher.getName().toLowerCase().contains(search.toLowerCase())
        );
        tablaProfesor.setItems(filteredTeachers);
    }

    /**
     * Borrar profesor.
     *
     * @param event the event
     */
    @FXML
    void borrarProfesor(ActionEvent event) {

    }

    /**
     * Buscar profesor.
     *
     * @param event the event
     */
    @FXML
    void buscarProfesor(KeyEvent event) {
        String search = textBuscar.getText();
        UpdateTable(search);
    }

    /**
     * Editar profesor.
     *
     * @param event the event
     */
    @FXML
    void editarProfesor(ActionEvent event) {

    }

    /**
     * Listar profesor.
     *
     * @param event the event
     */
    @FXML
    void listarProfesor(ActionEvent event) {
        UpdateTable();
    }

    /**
     * Nuevo profesor.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void nuevoProfesor(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterTeachers.fxml"));
        AnchorPane ap = loader.load();
        registerTeachersController = loader.getController();
        Scene scene = new Scene(ap);
        stageProfesor = new Stage();

        stageProfesor.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageProfesor.setScene(scene);
        stageProfesor.initOwner(root.getScene().getWindow());
        stageProfesor.initModality(Modality.WINDOW_MODAL);
        stageProfesor.initStyle(StageStyle.DECORATED);
        stageProfesor.setResizable(false);
        stageProfesor.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageProfesor.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageProfesor.showAndWait();
    }

}
