
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
import Device.Device;
import Interface.LoanBook;
import Interface.LoanDevice;
import Person.User;
import java.util.List;

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
        throw new UnsupportedOperationException("Not supported yet.");
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
    public Device loanDevice(User user, Device device) {
        throw new UnsupportedOperationException("Not supported yet.");
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
