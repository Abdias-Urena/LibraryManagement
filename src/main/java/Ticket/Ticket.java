package Ticket;

import Connection.DatabaseConnection;
import Interface.IFunctions;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * La clase `Ticket` representa un ticket relacionado con una devolución.
 * Incluye atributos como la devolución asociada, descripción, precio del ticket
 * y número de ticket.
 */
public class Ticket implements IFunctions {


    private String description;

    private int priceTicket;

    private String ticket;

    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    Connection connection = databaseConnection.getConnection();

    /**
     * Constructor por defecto para la clase `Ticket`.
     */
    public Ticket() {
    }

    /**
     * Constructor parametrizado para la clase `Ticket`.
     *
     * @param description La descripción del ticket.
     * @param priceTicket El precio del ticket.
     * @param ticket      El número de ticket.
     */
    public Ticket(String description, int priceTicket, String ticket) {
        this.description = description;
        this.priceTicket = priceTicket;
        this.ticket = ticket;
    }

    public Ticket(String description, int priceTicket) {
        this.description = description;
        this.priceTicket = priceTicket;
    }

    /**
     * Obtiene la descripción del ticket.
     *
     * @return La descripción del ticket.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del ticket.
     *
     * @param description La nueva descripción del ticket.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el precio del ticket.
     *
     * @return El precio del ticket.
     */
    public int getPriceTicket() {
        return priceTicket;
    }

    /**
     * Establece el precio del ticket.
     *
     * @param priceTicket El nuevo precio del ticket.
     */
    public void setPriceTicket(int priceTicket) {
        this.priceTicket = priceTicket;
    }

    /**
     * Obtiene el número de ticket.
     *
     * @return El número de ticket.
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * Establece el número de ticket.
     *
     * @param ticket El nuevo número de ticket.
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getDays(Ticket id) {
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT EXPIRATION_DATE FROM loan WHERE ID = ?;");
            pst.setString(1, id.getTicket());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                java.sql.Date expirationDate = rs.getDate("EXPIRATION_DATE");
                pst.close();
                rs.close();
                System.out.println(expirationDate);

                return expirationDate;
            } else {
                pst.close();
                rs.close();
                throw new RuntimeException("No se encontraron resultados para el ID proporcionado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int calculatePrice(LocalDate days) {
        LocalDate fechaActual = LocalDate.now();
        long daysDifference = ChronoUnit.DAYS.between(days,fechaActual);
        System.out.println(daysDifference);
        if (daysDifference < 0) {
            return 0;
        } else {
            return (int) daysDifference * 500;
        }
    }



    @Override
    public String toString() {
        return "Ticket{" + ", description=" + description + ", priceTicket=" + priceTicket + ", ticket=" + ticket + '}';
    }
}
