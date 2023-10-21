/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import Book.Book;
import Connection.DatabaseConnection;
import javafx.scene.control.Label;
import Book.DigitalBook;
import Book.FisicBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Abdias
 */
public class RegisterBookController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField textCategory;

    @FXML
    private JFXTextField textReproduccion;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    private JFXTextField textTitulo;

    @FXML
    private JFXDatePicker fechaPicker;

    @FXML
    private JFXTextField textAutor;

    @FXML
    private JFXComboBox<String> comboBoxTipoLibro;

    @FXML
    private Label labelURL;

    @FXML
    private JFXTextField textURL;
    Book book;

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxTipoLibro.getItems().add("Digital");
        comboBoxTipoLibro.getItems().add("Fisico");
        labelURL.setVisible(false);
        textURL.setVisible(false);
    }


    @FXML
    void guardar(ActionEvent event) {
        QuerySaveData();
    }

    public Book saveDataBook() {
        if (comboBoxTipoLibro.getValue().equals("Digital")) {
            book = new DigitalBook(textURL.getText(), textAutor.getText(), textCategory.getText(), fechaPicker.getValue().toString(),
                    textReproduccion.getText(), textTitulo.getText(), "D");
        } else {
            book = new FisicBook(true, false, textAutor.getText(), textCategory.getText(), fechaPicker.getValue().toString(),
                    textReproduccion.getText(), textTitulo.getText(), "F");
        }
        return book;
    }

    public void QuerySaveData() {
        String tipoLibro = comboBoxTipoLibro.getValue();
        book = saveDataBook();
        try {
            String insertQuery = "INSERT INTO book (AUTHOR, CATEGORY, PUBLIC_DATE, " +
                    "REPRODUC, TITLE, TYPE, URL, " +
                    "IS_AVAILABLE, IS_USABLE) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getCategory());
            preparedStatement.setString(3, book.getPublicationDate());
            preparedStatement.setString(4, book.getReproduction());
            preparedStatement.setString(5, book.getTitle());

            if (tipoLibro.equals("Digital")) {
                preparedStatement.setString(6, book.getType());
                preparedStatement.setString(7, ((DigitalBook) book).getUrl());
                preparedStatement.setString(8, "Y");
                preparedStatement.setString(9, "Y");
            } else {
                preparedStatement.setString(6, book.getType());
                preparedStatement.setString(7, "N");
                preparedStatement.setString(8, "Y");
                preparedStatement.setString(9, "Y");
            }

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Datos insertados con éxito.");
            } else {
                System.out.println("Error al insertar datos.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cleanTextField();
    }

    public void cleanTextField(){
        textAutor.setText("");
        textCategory.setText("");
        textReproduccion.setText("");
        textTitulo.setText("");
        textURL.setText("");
        comboBoxTipoLibro.setValue("");
        fechaPicker.setValue(null);
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
}
