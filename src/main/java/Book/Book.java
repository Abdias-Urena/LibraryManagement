package Book;

/**
 * La clase `Book` representa un libro con detalles como el autor, la categoría,
 * la fecha de publicación, la reproducción y el título.
 */
public class Book {

    private String author;

    private String category;

    private String publicationDate;

    private String reproduction;

    private String title;

    private String type;

    /**
     * Constructor por defecto de la clase `Book`.
     */
    public Book() {
    }

    /**
     * Constructor que inicializa una instancia de la clase `Book` con los
     * valores proporcionados.
     *
     * @param author          Autor del libro.
     * @param category        La categoría del libro.
     * @param publicationDate La fecha de publicación del libro.
     * @param reproduction    La información sobre la reproducción del libro.
     * @param title           El título del libro.
     * @param type            the type
     */
    public Book(String author, String category, String publicationDate, String reproduction, String title, String type) {
        this.author = author;
        this.category = category;
        this.publicationDate = publicationDate;
        this.reproduction = reproduction;
        this.title = title;
        this.type = type;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Establece el autor del libro.
     *
     * @param author Abdias
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Obtiene la categoría del libro.
     *
     * @return La categoría del libro.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece la categoría del libro.
     *
     * @param category La nueva categoría del libro.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Obtiene la fecha de publicación del libro.
     *
     * @return La fecha de publicación del libro.
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Establece la fecha de publicación del libro.
     *
     * @param publicationDate La nueva fecha de publicación del libro.
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Obtiene la información sobre la reproducción del libro.
     *
     * @return La información sobre la reproducción del libro.
     */
    public String getReproduction() {
        return reproduction;
    }

    /**
     * Establece la información sobre la reproducción del libro.
     *
     * @param reproduction La nueva información sobre la reproducción del libro.
     */
    public void setReproduction(String reproduction) {
        this.reproduction = reproduction;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del libro.
     *
     * @param title El nuevo título del libro.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Establece el tipo del libro.
     *
     * @param 'El nuevo tipo del libro'.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Representación en cadena de la clase `Book`.
     *
     * @return Una cadena que contiene los detalles del libro.
     */
    @Override
    public String toString() {
        return "Book{" + "author=" + author + ", category=" + category + ", publicationDate=" + publicationDate + ", reproduction=" + reproduction + ", tiltle=" + title + '}';
    }
}
