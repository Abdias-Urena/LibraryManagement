
package Loan;
/**
 * La clase `Loan` representa un préstamo de un libro o un dispositivo a un usuario. Incluye
 * información como la fecha de vencimiento, el ID único, la fecha del préstamo y el usuario al que se
 * le ha prestado el artículo.
 *
 * Esta clase implementa las interfaces `LoanBook` y `LoanDevice`, proporcionando métodos
 * para administrar los préstamos de libros y dispositivos.
 */
import Book.Book;
import Connection.DatabaseConnection;
import Device.Device;
import Interface.LoanBook;
import Interface.LoanDevice;
import Notification.Notification;
import Person.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * The type Loan.
 */
public class Loan implements LoanBook, LoanDevice {

    private String expirationDate;

    private String id;

    private String loanDate;

    private User user;

    /**
     * Constructor por defecto para la clase `Loan`.
     */
    public Loan() {
    }

    /**
     * Constructor parametrizado para la clase `Loan`.
     *
     * @param expirationDate La fecha de vencimiento del préstamo.
     * @param id             El ID único del préstamo.
     * @param loanDate       La fecha en la que se realizó el préstamo.
     * @param user           El usuario al que se le prestó el artículo.
     */
    public Loan(String expirationDate, String id, String loanDate, User user) {
        this.expirationDate = expirationDate;
        this.id = id;
        this.loanDate = loanDate;
        this.user = user;
    }

    /**
     * Obtiene la fecha de vencimiento del préstamo.
     *
     * @return La fecha de vencimiento.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Establece la fecha de vencimiento del préstamo.
     *
     * @param expirationDate La nueva fecha de vencimiento.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Obtiene el ID único del préstamo.
     *
     * @return El ID único.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID único del préstamo.
     *
     * @param id El nuevo ID único.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha en la que se realizó el préstamo.
     *
     * @return La fecha del préstamo.
     */
    public String getLoanDate() {
        return loanDate;
    }

    /**
     * Establece la fecha en la que se realizó el préstamo.
     *
     * @param loanDate La nueva fecha del préstamo.
     */
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Obtiene el usuario al que se le prestó el artículo.
     *
     * @return El usuario.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario al que se le prestó el artículo.
     *
     * @param user El nuevo usuario.
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Book addBook(Book book) {
        Notification noti = new Notification();
        try {
            DatabaseConnection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("SELECT NUMBER_BOOK FROM BOOK WHERE TITLE = ?");
            preparedStatement.setString(1, book.getTitle());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                String number_book = rs.getString("NUMBER_BOOK");
                System.out.println(number_book);
                preparedStatement= connection.getConnection().prepareStatement("INSERT INTO LOAN (EXPIRATION_DATE, LOAN_DATE, ID, NUMBER_BOOK) VALUES (?,?,?,?)");
                preparedStatement.setDate(1, java.sql.Date.valueOf(LocalDate.parse(expirationDate)));
                preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.parse(loanDate)));
                preparedStatement.setString(3, user.getId());
                preparedStatement.setString(4, number_book);

                int row = preparedStatement.executeUpdate();
                System.out.println("Rows affected: "+row);
                if(row == 1){
                    System.out.println("Datos insertados");
                    preparedStatement= connection.getConnection().prepareStatement("UPDATE BOOK SET IS_AVAILABLE = ? WHERE NUMBER_BOOK = ?");
                    preparedStatement.setString(1,"N");
                    preparedStatement.setString(2,number_book);
                    row = preparedStatement.executeUpdate();
                    if (row == 1){
                        noti.modifyNotification("Prestamo registrado", "El prestamo se ha registrado con éxito", "/Images/success.png");
                        System.out.println("Datos actulizados"+row);
                    }else{
                        noti.modifyNotification("Error", "Error al registrar el prestamo", "/Images/error.png");
                    }
                }
            }

        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
        return book;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Book deleteBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Book modifyUrlBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Device loanDevice(Device device) {
        Notification noti = new Notification();

        try {
            DatabaseConnection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement("SELECT NUMBER_DEVICE FROM DEVICE WHERE ID =?");
            preparedStatement.setString(1, device.getId());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                String number_device = rs.getString("NUMBER_DEVICE");
                System.out.println(number_device);
                preparedStatement= connection.getConnection().prepareStatement("INSERT INTO LOAN (EXPIRATION_DATE, LOAN_DATE, ID, NUMBER_DEVICE) VALUES (?,?,?,?)");
                preparedStatement.setDate(1, java.sql.Date.valueOf(LocalDate.parse(expirationDate)));
                preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.parse(loanDate)));
                preparedStatement.setString(3, user.getId());
                preparedStatement.setString(4, number_device);
                int row = preparedStatement.executeUpdate();
                System.out.println("Rows affected: "+row);
                if(row == 1){
                    System.out.println("Datos insertados");
                    preparedStatement= connection.getConnection().prepareStatement("UPDATE DEVICE SET IS_AVAILABLE=? WHERE ID=?");
                    preparedStatement.setString(1,"N");
                    preparedStatement.setString(2,device.getId());
                    row = preparedStatement.executeUpdate();
                    if (row == 1){
                        noti.modifyNotification("Prestamo registrado", "El prestamo se ha registrado con éxito", "/Images/success.png");
                        System.out.println("Datos actulizados"+row);
                    }else{
                        noti.modifyNotification("Error", "Error al registrar el prestamo", "/Images/error.png");
                    }
                }
            }

        }catch (SQLException s){
            System.out.println(s.getErrorCode());
        }
        return device;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Device returnDevice(Device device) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Device> obtainDeviceAvailable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Loan{" + "expirationDate=" + expirationDate + ", id=" + id + ", loanDate=" + loanDate + ", user=" + user + '}';
    }
}
