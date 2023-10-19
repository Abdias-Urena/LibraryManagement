package Controller;

import Connection.DatabaseConnection;
import Person.Student;
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
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.ResourceBundle;

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

    ObservableList<Student> students = FXCollections.observableArrayList();

    DatabaseConnection connection = DatabaseConnection.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
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


    public void UpdateTable() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("career"));
        colbeca.setCellValueFactory(new PropertyValueFactory<>("grant"));
        students = getStudentList();
        tablaEstudiantes.setItems(students);
    }
    public void UpdateTable(String id){
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("career"));
        colbeca.setCellValueFactory(new PropertyValueFactory<>("grant"));

        ObservableList<Student> filteredStudents = students.filtered(student ->
                student.getId().toLowerCase().contains(id.toLowerCase()) ||
                        student.getLastname().toLowerCase().contains(id.toLowerCase()) ||
                                student.getName().toLowerCase().contains(id.toLowerCase())
        );
        tablaEstudiantes.setItems(filteredStudents);
    }

    @FXML
    void borrarEstudiante(ActionEvent event) {

    }

    @FXML
    void buscarEstudiante(KeyEvent event) {
        String id = textBuscar.getText();
        UpdateTable(id);
    }

    @FXML
    void editarEstudiante(ActionEvent event) {

    }

    @FXML
    void listarEstudiantes(ActionEvent event) {
        UpdateTable();
    }

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
