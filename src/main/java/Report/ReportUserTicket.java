package Report;

/**
 * La clase `ReportUserTicket` representa un informe relacionado con un usuario
 * y hereda de la clase `Report`. Incluye un atributo específico para el usuario
 * asociado.
 */
import Person.User;

/**
 * The type Report user ticket.
 */
public class ReportUserTicket extends Report {

    private User user;

    /**
     * Constructor parametrizado para la clase `ReportUserTicket`.
     *
     * @param user        El usuario asociado al informe.
     * @param dateReport  La fecha del informe.
     * @param description La descripción del informe.
     * @param title       El título del informe.
     * @param typeReport  El tipo de informe.
     */
    public ReportUserTicket(User user, String dateReport, String description, String title, String typeReport) {
        super(dateReport, description, title, typeReport);
        this.user = user;
    }

    /**
     * Obtiene el usuario asociado al informe.
     *
     * @return El usuario asociado.
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario asociado al informe.
     *
     * @param user El nuevo usuario asociado.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ReportUserTicket{" + "user=" + user + '}';
    }
}
