package Book;

/**
 * La clase `DigitalBook` es una subclase de `Book` que representa un libro
 * digital con detalles adicionales como una URL de acceso.
 */
public class DigitalBook extends Book {

    private String url;

    /**
     * Constructor de la clase `DigitalBook` que inicializa la URL de acceso.
     *
     * @param url La URL de acceso al libro digital.
     */
    public DigitalBook(String url) {
        this.url = url;
    }

    /**
     * Constructor de la clase `DigitalBook` que inicializa la URL de acceso y
     * otros detalles del libro.
     *
     * @param url La URL de acceso al libro digital.
     * @param author El autor del libro digital.
     * @param category La categoría del libro digital.
     * @param publicationDate La fecha de publicación del libro digital.
     * @param reproduction La información sobre la reproducción del libro
     * digital.
     * @param title El título del libro digital.
     */
    public DigitalBook(String url, String author, String category, String publicationDate, String reproduction, String title, String type) {
        super(author, category, publicationDate, reproduction, title, type);
        this.url = url;
    }

    /**
     * Obtiene la URL de acceso al libro digital.
     *
     * @return La URL de acceso al libro digital.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL de acceso al libro digital.
     *
     * @param url La nueva URL de acceso al libro digital.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Representación en cadena de la clase `DigitalBook`.
     *
     * @return Una cadena que contiene la URL de acceso al libro digital.
     */
    @Override
    public String toString() {
        return "DigitalBook{" + "url=" + url + '}';
    }
}
