/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Book;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class DigitalBookTest {
    
    @Test
    public void testDigitalBookWithUrlConstructorAndGetters() {
        DigitalBook digitalBook = new DigitalBook("https://example.com/book");

        assertEquals("https://example.com/book", digitalBook.getUrl());
    }

    @Test
    public void testDigitalBookFullConstructorAndGetters() {
        DigitalBook digitalBook = new DigitalBook(
            "https://example.com/book",
            "Alice Johnson",
            "Mystery",
            "2023-10-05",
            "E-book",
            "Mystery Novel","y"
        );

        assertEquals("Alice Johnson", digitalBook.getAuthor());
        assertEquals("Mystery", digitalBook.getCategory());
        assertEquals("2023-10-05", digitalBook.getPublicationDate());
        assertEquals("E-book", digitalBook.getReproduction());
        assertEquals("Mystery Novel", digitalBook.getTitle());

        assertEquals("https://example.com/book", digitalBook.getUrl());
    }

    @Test
    public void testDigitalBookUrlSetter() {
        DigitalBook digitalBook = new DigitalBook("https://example.com/old-url");

        digitalBook.setUrl("https://example.com/new-url");
        assertEquals("https://example.com/new-url", digitalBook.getUrl());
    }

    @Test
    public void testDigitalBookToString() {
        DigitalBook digitalBook = new DigitalBook("https://example.com/book");

        String expectedToString = "DigitalBook{url=https://example.com/book}";
        assertEquals(expectedToString, digitalBook.toString());
    }
    
}
