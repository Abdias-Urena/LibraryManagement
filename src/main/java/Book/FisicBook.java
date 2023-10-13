package Book;

/**
 * La clase `FisicBook` es una subclase de `Book` que representa un libro físico
 * con detalles adicionales como su disponibilidad y usabilidad.
 */
public class FisicBook extends Book {

    private boolean isAvailable;

    private boolean isUsable;

    /**
     * Constructor de la clase `FisicBook` que inicializa la disponibilidad y
     * usabilidad del libro físico.
     *
     * @param isAvailable Indica si el libro físico está disponible para
     * préstamo.
     * @param isUsable Indica si el libro físico está en condiciones de ser
     * utilizado.
     */
    public FisicBook(boolean isAvailable, boolean isUsable) {
        this.isAvailable = isAvailable;
        this.isUsable = isUsable;
    }

    /**
     * Constructor de la clase `FisicBook` que inicializa la disponibilidad y
     * usabilidad del libro físico, así como otros detalles del libro.
     *
     * @param isAvailable Indica si el libro físico está disponible para
     * préstamo.
     * @param isUsable Indica si el libro físico está en condiciones de ser
     * utilizado.
     * @param author El autor del libro físico.
     * @param category La categoría del libro físico.
     * @param publicationDate La fecha de publicación del libro físico.
     * @param reproduction La información sobre la reproducción del libro
     * físico.
     * @param title El título del libro físico.
     */
    public FisicBook(boolean isAvailable, boolean isUsable, String author, String category, String publicationDate, String reproduction, String title) {
        super(author, category, publicationDate, reproduction, title);
        this.isAvailable = isAvailable;
        this.isUsable = isUsable;
    }

    /**
     * Verifica si el libro físico está disponible para préstamo.
     *
     * @return `true` si el libro físico está disponible, `false` en caso
     * contrario.
     */
    public boolean isIsAvailable() {
        return isAvailable;
    }

    /**
     * Establece la disponibilidad del libro físico.
     *
     * @param isAvailable `true` si el libro físico está disponible, `false` en
     * caso contrario.
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * Verifica si el libro físico está en condiciones de ser utilizado.
     *
     * @return `true` si el libro físico es utilizable, `false` en caso
     * contrario.
     */
    public boolean isIsUsable() {
        return isUsable;
    }

    /**
     * Establece la usabilidad del libro físico.
     *
     * @param isUsable `true` si el libro físico es utilizable, `false` en caso
     * contrario.
     */
    public void setIsUsable(boolean isUsable) {
        this.isUsable = isUsable;
    }

    /**
     * Representación en cadena de la clase `FisicBook`.
     *
     * @return Una cadena que contiene detalles sobre la disponibilidad y
     * usabilidad del libro físico.
     */
    @Override
    public String toString() {
        return "FisicBook{" + "isAvailable=" + isAvailable + ", isUsable=" + isUsable + '}';
    }
}
