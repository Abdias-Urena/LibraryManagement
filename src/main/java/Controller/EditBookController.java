package Controller;

import Connection.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import Book.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditBookController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField textReproduccion;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXTextField textTitulo;

    @FXML
    private JFXDatePicker fechaPicker;

    @FXML
    private JFXTextField textAutor;

    @FXML
    private JFXComboBox<String> comboBoxTipoLibro;

    @FXML
    private JFXTextField textCategory;

    @FXML
    private Label labelURL;

    @FXML
    private JFXTextField textURL;

    private Book libroSeleccionado;

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getConnection();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelURL.setVisible(false);
        textURL.setVisible(false);
    }

    void initData(Book list) {
        libroSeleccionado = list;  // Inicializa la variable libroSeleccionado con el libro pasado como argumento
        textAutor.setText(libroSeleccionado.getAuthor());
        textCategory.setText(libroSeleccionado.getCategory());
        textTitulo.setText(libroSeleccionado.getTitle());
        fechaPicker.setValue(LocalDate.parse(libroSeleccionado.getPublicationDate()));
        comboBoxTipoLibro.getItems().addAll("Digital", "Fisico");
        textReproduccion.setText(String.valueOf(libroSeleccionado.getReproduction()));
        if (libroSeleccionado.getType().equals("D")) {
            comboBoxTipoLibro.setValue("Digital");
            labelURL.setVisible(true);
            textURL.setVisible(true);
            if (libroSeleccionado instanceof DigitalBook) {
                textURL.setText(((DigitalBook) libroSeleccionado).getUrl());
            }
        } else if (libroSeleccionado.getType().equals("F")) {
            comboBoxTipoLibro.setValue("Fisico");
        }
    }

    @FXML
    void chooseBook(ActionEvent event) {
        if (comboBoxTipoLibro.getValue().equals("Digital")) {
            labelURL.setVisible(true);
            textURL.setVisible(true);
        } else {
            labelURL.setVisible(false);
            textURL.setVisible(false);
        }
    }
    @FXML
    void editarBook(ActionEvent event){
        QueryEdit();
    }

    void QueryEdit() {
        if (comboBoxTipoLibro.getValue().equals("Digital")) {
            libroSeleccionado = new DigitalBook(textURL.getText(), textAutor.getText(), textCategory.getText(), fechaPicker.getValue().toString(),
                    textReproduccion.getText(), textTitulo.getText(), "D");
        } else {
            libroSeleccionado = new FisicBook(true, false, textAutor.getText(), textCategory.getText(), fechaPicker.getValue().toString(),
                    textReproduccion.getText(), textTitulo.getText(), "F");
        }
        try {
            PreparedStatement pst;

            // Verifica si el libro es de tipo "Digital" o "Fisico"
            if (libroSeleccionado instanceof DigitalBook) {
                DigitalBook digitalBook = (DigitalBook) libroSeleccionado;
                pst = connection.prepareStatement("UPDATE book SET TITLE = ?, AUTHOR = ?, CATEGORY = ?, PUBLIC_DATE = ?, REPRODUC = ?, TYPE = ?, URL = ? WHERE TITLE = ?");
                pst.setString(1, digitalBook.getTitle());
                pst.setString(2, digitalBook.getAuthor());
                pst.setString(3, digitalBook.getCategory());
                pst.setString(4, digitalBook.getPublicationDate());
                pst.setString(5, digitalBook.getReproduction());
                pst.setString(6, digitalBook.getType());
                pst.setString(7, digitalBook.getUrl());
                pst.setString(8, digitalBook.getTitle()); // Usamos el título como identificador
            } else {
                // Es un libro físico
                pst = connection.prepareStatement("UPDATE book SET TITLE = ?, AUTHOR = ?, CATEGORY = ?, PUBLIC_DATE = ?, REPRODUC = ?, TYPE = ? WHERE TITLE = ?");
                pst.setString(1, libroSeleccionado.getTitle());
                pst.setString(2, libroSeleccionado.getAuthor());
                pst.setString(3, libroSeleccionado.getCategory());
                pst.setString(4, libroSeleccionado.getPublicationDate());
                pst.setString(5, libroSeleccionado.getReproduction());
                pst.setString(6, libroSeleccionado.getType());
                pst.setString(7, libroSeleccionado.getTitle()); // Usamos el título como identificador
            }

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Datos insertados con éxito.");
            } else {
                System.out.println("Error al insertar datos.");
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Libro editado");
    }


}
