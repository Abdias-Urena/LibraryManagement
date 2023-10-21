package Report;

/**
 * La clase `ReportBook` representa un informe relacionado con un libro y hereda
 * de la clase `Report`. Incluye un atributo específico para el libro asociado.
 */
import Book.Book;

/**
 * The type Report book.
 */
public class ReportBook extends Report {

    private Book book;

    /**
     * Constructor por defecto para la clase `ReportBook`.
     */
    public ReportBook() {
    }

    /**
     * Constructor parametrizado para la clase `ReportBook`.
     *
     * @param book El libro asociado al informe.
     */
    public ReportBook(Book book) {
        this.book = book;
    }

    /**
     * Constructor parametrizado para la clase `ReportBook` que también recibe
     * información de la clase base `Report`.
     *
     * @param book        El libro asociado al informe.
     * @param dateReport  La fecha del informe.
     * @param description La descripción del informe.
     * @param title       El título del informe.
     * @param typeReport  El tipo de informe.
     */
    public ReportBook(Book book, String dateReport, String description, String title, String typeReport) {
        super(dateReport, description, title, typeReport);
        this.book = book;
    }

    /**
     * Obtiene el libro asociado al informe.
     *
     * @return El libro asociado.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Establece el libro asociado al informe.
     *
     * @param book El nuevo libro asociado.
     */
    public void setBook(Book book) {
        this.book = book;
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
        return "ReportBook{" + "book=" + book + '}';
    }
}
