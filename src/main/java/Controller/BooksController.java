package Controller;

import Book.Book;
import Book.FisicBook;
import Connection.DatabaseConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class BooksController implements Initializable {

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
    private TableView<Book> tablaLibros;

    @FXML
    private TableColumn<Book,String> colAutor;

    @FXML
    private TableColumn<Book,String> colCategoria;

    @FXML
    private TableColumn<Book,String> colPublicacion;

    @FXML
    private TableColumn<Book,String> colReproduccion;

    @FXML
    private TableColumn<Book,String> colTitle;

    @FXML
    private TableColumn<Book,String> colTipo;

    private Stage stageLibros;

    private RegisterBookController registerBookController;

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getConnection();

    ObservableList<Book> BookList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    @FXML
    void buscarLibro(KeyEvent event) {

    }


    public ObservableList<Book> getBooksList() {

        ObservableList<Book> BookList = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.prepareStatement("select AUTHOR,CATEGORY,PUBLIC_DATE,REPRODUC,TITLE,TYPE from book");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BookList.add(new Book(rs.getString("AUTHOR"), rs.getString("CATEGORY"), rs.getString("PUBLIC_DATE"), rs.getString("REPRODUC"), rs.getString("TITLE"),rs.getString("TYPE")));
            }
        } catch (Exception e) {
        }
        return BookList;
    }


    public void UpdateTable() {
        colAutor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        colReproduccion.setCellValueFactory(new PropertyValueFactory<>("reproduction"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("type"));
        BookList = getBooksList();
        tablaLibros.setItems(BookList);
    }

    @FXML
    void nuevoLibro(ActionEvent event) throws IOException {

        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterBook.fxml"));
        AnchorPane ap = loader.load();
        registerBookController = loader.getController();
        Scene scene = new Scene(ap);
        stageLibros = new Stage();

        stageLibros.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageLibros.setScene(scene);
        stageLibros.initOwner(root.getScene().getWindow());
        stageLibros.initModality(Modality.WINDOW_MODAL);
        stageLibros.initStyle(StageStyle.DECORATED);
        stageLibros.setResizable(false);
        stageLibros.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageLibros.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageLibros.showAndWait();
    }


    @FXML
    void editarLibro(ActionEvent event) throws IOException {
        Book libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditBook.fxml"));
        AnchorPane ap = loader.load();
        registerBookController = loader.getController();
        Scene scene = new Scene(ap);
        stageLibros = new Stage();

        stageLibros.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageLibros.setScene(scene);
        stageLibros.initOwner(root.getScene().getWindow());
        stageLibros.initModality(Modality.WINDOW_MODAL);
        stageLibros.initStyle(StageStyle.DECORATED);
        stageLibros.setResizable(false);
        stageLibros.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageLibros.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageLibros.showAndWait();
    }

    @FXML
    void borrarLibro(ActionEvent event) {

    }
}
