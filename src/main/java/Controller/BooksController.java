package Controller;

import Book.*;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The type Books controller.
 */
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

    private Stage stageLibros;

    private RegisterBookController registerBookController;

    /**
     * The Database connection.
     */
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    /**
     * The Connection.
     */
    Connection connection = databaseConnection.getConnection();

    /**
     * The Book list.
     */
    ObservableList<Book> BookList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    /**
     * Buscar libro.
     *
     * @param event the event
     */
    @FXML
    void buscarLibro(KeyEvent event) {
        UpdateTable(textBuscar.getText());
    }


    /**
     * Gets books list.
     *
     * @return the books list
     */
    public ObservableList<Book> getBooksList() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = connection.prepareStatement("select AUTHOR,CATEGORY,PUBLIC_DATE,REPRODUC,TITLE,TYPE,URL from book");
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

    /**
     * Update table.
     *
     * @param id the id
     */
    public void UpdateTable(String id) {
        colAutor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        colReproduccion.setCellValueFactory(new PropertyValueFactory<>("reproduction"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTipo.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof DigitalBook) {
                return new SimpleStringProperty("Digital");
            } else {
                return new SimpleStringProperty("Físico");
            }
        });
        colURL.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof DigitalBook) {
                return new SimpleStringProperty(((DigitalBook) cellData.getValue()).getUrl());
            } else {
                return new SimpleStringProperty("No existe");
            }
        });
        ObservableList<Book> filteredBooks = BookList.filtered(book ->
                book.getAuthor().toLowerCase().contains(id.toLowerCase()) ||
                        book.getCategory().toLowerCase().contains(id.toLowerCase()) ||
                        book.getPublicationDate().toLowerCase().contains(id.toLowerCase()) ||
                        book.getReproduction().toLowerCase().contains(id.toLowerCase()) ||
                        book.getTitle().toLowerCase().contains(id.toLowerCase()) ||
                        book.getType().toLowerCase().contains(id.toLowerCase())
        );
        tablaLibros.setItems(filteredBooks);
    }

    /**
     * Update table.
     */
    public void UpdateTable() {
        colAutor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        colReproduccion.setCellValueFactory(new PropertyValueFactory<>("reproduction"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTipo.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof DigitalBook) {
                return new SimpleStringProperty("Digital");
            } else {
                return new SimpleStringProperty("Físico");
            }
        });
        colURL.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof DigitalBook) {
                return new SimpleStringProperty(((DigitalBook) cellData.getValue()).getUrl());
            } else {
                return new SimpleStringProperty("No existe");
            }
        });
        BookList = getBooksList();
        tablaLibros.setItems(BookList);
    }

    /**
     * Nuevo libro.
     *
     * @param event the event
     * @throws IOException the io exception
     */
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

    /**
     * Listar equipos.
     *
     * @param event the event
     */
    @FXML
    void listarEquipos(ActionEvent event) {
        UpdateTable();
    }

    /**
     * Editar libro.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void editarLibro(ActionEvent event) throws IOException {
        // Obtener el libro seleccionado de la tabla
        Book libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();

        if (libroSeleccionado != null) {
            try {
                // Cargar el controlador del nuevo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditBook.fxml"));
                AnchorPane ap = loader.load();
                EditBookController editBookController = loader.getController();

                // Enviar los datos del libro seleccionado al controlador de destino
                editBookController.initData(libroSeleccionado);

                Scene scene = new Scene(ap);
                Stage stageEdicion = new Stage();
                stageEdicion.setScene(scene);
                stageEdicion.initModality(Modality.WINDOW_MODAL);
                stageEdicion.initStyle(StageStyle.DECORATED);
                stageEdicion.setResizable(false);
                stageEdicion.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Borrar libro.
     *
     * @param event the event
     */
    @FXML
    void borrarLibro(ActionEvent event) {
        Book libroSeleccionado = tablaLibros.getSelectionModel().getSelectedItem();

        if (libroSeleccionado != null) {
            boolean confirmacion = showConfirmationDialog("¿Estás seguro de que deseas eliminar este libro?");

            if (confirmacion) {
                boolean exito = deleteBookFromDatabase(libroSeleccionado);

                if (exito) {
                    UpdateTable();
                } else {
                    showAlert("Error al eliminar el libro.");
                }
            }
        } else {
            showAlert("Por favor, selecciona un libro para eliminar.");
        }
    }


    private boolean deleteBookFromDatabase(Book book) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM book WHERE TITLE = ?");
            pst.setString(1, book.getTitle()); // Asume que hay un campo "ID" en la tabla que identifica de manera única cada libro.
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showConfirmationDialog(String mensaje) {
        return new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.OK, ButtonType.CANCEL).showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

    private void showAlert(String mensaje) {
        new Alert(Alert.AlertType.INFORMATION, mensaje).showAndWait();
    }

}
