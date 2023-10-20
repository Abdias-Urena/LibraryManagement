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
import Ticket.Ticket;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class TicketsTableController implements Initializable {

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
    private TableView<Ticket> tablaMulta;

    @FXML
    private TableColumn<Ticket, String> colUsuario;

    @FXML
    private TableColumn<Ticket, String> colDescripcion;

    @FXML
    private TableColumn<Ticket, String> colPrecioMulta;


    private Stage stageMulta;

    private TicketsController ticketsController;

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getConnection();

    ObservableList<Ticket> tickets = FXCollections.observableArrayList();

    @FXML
    void borrarMulta(ActionEvent event) {
        Ticket libroSeleccionado = tablaMulta.getSelectionModel().getSelectedItem();

        if (libroSeleccionado != null) {
            boolean confirm = showConfirmationDialog("¿Estás seguro de que deseas eliminar este libro?");

            if (confirm) {
                boolean success = deleteTicketFromDatabase(libroSeleccionado);

                if (success) {
                    UpdateTable();
                    showAlert("Libro eliminado correctamente.");
                } else {
                    showAlert("Error al eliminar el libro.");
                }
            }
        } else {
            showAlert("Por favor, selecciona un libro para eliminar.");
        }
    }

    public void UpdateTable(String id){
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("ticket"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrecioMulta.setCellValueFactory(new PropertyValueFactory<>("priceTicket"));
        ObservableList<Ticket> filteredTickets = tickets.filtered(ticket ->
                ticket.getTicket().toLowerCase().contains(id.toLowerCase()) ||
                        ticket.getDescription().toLowerCase().contains(id.toLowerCase()) ||
                        String.valueOf(ticket.getPriceTicket()).contains(id)
        );
        tablaMulta.setItems(filteredTickets);
    }

    @FXML
    void buscarMulta(KeyEvent event) {
        UpdateTable(textBuscar.getText());
    }

    @FXML
    void listarMulta(ActionEvent event) {
        UpdateTable();
    }


    public ObservableList<Ticket> getTicketList() {
        ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT u.ID, t.DESCRIPTION, t.PRICE_TICKET FROM USER u " +
                    "INNER JOIN TICKET t" +
                    " ON u.ID = t.ID");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                ticketList.add(new Ticket(rs.getString("DESCRIPTION"), rs.getInt("PRICE_TICKET"), rs.getString("u.ID")));

            }
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    private void UpdateTable() {
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("ticket"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrecioMulta.setCellValueFactory(new PropertyValueFactory<>("priceTicket"));
        tickets = getTicketList();
        tablaMulta.setItems(tickets);
    }

    @FXML
    void nuevaMulta(ActionEvent event) throws IOException {
        root.setEffect(new GaussianBlur(10.0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Tickets.fxml"));
        AnchorPane ap = loader.load();
        ticketsController = loader.getController();
        Scene scene = new Scene(ap);
        stageMulta = new Stage();

        stageMulta.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/task-list.png"))));
        stageMulta.setScene(scene);
        stageMulta.initOwner(root.getScene().getWindow());
        stageMulta.initModality(Modality.WINDOW_MODAL);
        stageMulta.initStyle(StageStyle.DECORATED);
        stageMulta.setResizable(false);
        stageMulta.setOnCloseRequest((WindowEvent e) -> {

            root.setEffect(null);
        });
        stageMulta.setOnHidden((WindowEvent e) -> {
            root.setEffect(null);
        });

        stageMulta.showAndWait();
    }

    private boolean deleteTicketFromDatabase(Ticket ticket) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM TICKET WHERE DESCRIPTION = ? AND ID = ?");
            pst.setString(1, ticket.getDescription());
            pst.setString(2, ticket.getTicket()); // Asume que hay un campo "ID" en la tabla que identifica de manera única cada libro.
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }
}
