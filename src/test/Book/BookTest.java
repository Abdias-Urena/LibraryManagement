/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Book;

import Book.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class BookTest {
    
    @Test
    public void testConstructorAndGetters() {
        Book book = new Book("John Doe", "Fiction", "2023-09-30", "Hardcover", "Sample Title","y");

        assertEquals("John Doe", book.getAuthor());
        assertEquals("Fiction", book.getCategory());
        assertEquals("2023-09-30", book.getPublicationDate());
        assertEquals("Hardcover", book.getReproduction());
        assertEquals("Sample Title", book.getTitle());
    }

    @Test
    public void testSetters() {
        Book book = new Book();

        book.setAuthor("Jane Smith");
        book.setCategory("Non-fiction");
        book.setPublicationDate("2022-12-15");
        book.setReproduction("Paperback");
        book.setTitle("New Book Title");

        assertEquals("Jane Smith", book.getAuthor());
        assertEquals("Non-fiction", book.getCategory());
        assertEquals("2022-12-15", book.getPublicationDate());
        assertEquals("Paperback", book.getReproduction());
        assertEquals("New Book Title", book.getTitle());
    }

    @Test
    public void testToString() {
        Book book = new Book("Alice Johnson", "Mystery", "2023-10-05", "E-book", "Mystery Novel","y");

        String expectedToString = "Book{author=Alice Johnson, category=Mystery, publicationDate=2023-10-05, reproduction=E-book, tiltle=Mystery Novel}";
        assertEquals(expectedToString, book.toString());
    }
}
