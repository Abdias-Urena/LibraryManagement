package Interface;

import Book.Book;

/**
 * La interfaz `LoanBook` define un conjunto de operaciones para gestionar el
 * préstamo, eliminación y modificación de libros.
 */
public interface LoanBook {

    /**
     * Agrega un libro a la colección.
     *
     * @param book El libro que se agregará a la colección.
     * @return El libro agregado.
     */
    public Book addBook(Book book);

    /**
     * Elimina un libro de la colección.
     *
     * @param book El libro que se eliminará de la colección.
     * @return El libro eliminado.
     */
    public Book deleteBook(Book book);

    /**
     * Modifica la URL de un libro.
     *
     * @param book El libro cuya URL se modificará.
     * @return El libro con la URL modificada.
     */
    public Book modifyUrlBook(Book book);
}
