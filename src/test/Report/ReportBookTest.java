/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Report;

import Book.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Report book test.
 *
 * @author Abdias
 */
public class ReportBookTest {

    /**
     * Test get book.
     */
    @Test
    public void testGetBook() {
        Book book = new Book("John Doe", "Fiction", "2023-09-30", "Hardcover", "Sample Title","y");
        ReportBook reportBook = new ReportBook(book, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        assertEquals(book, reportBook.getBook());
    }

    /**
     * Test set book.
     */
    @Test
    public void testSetBook() {
        Book book = new Book("John Doe", "Fiction", "2023-09-30", "Hardcover", "Sample Title","y");
        ReportBook reportBook = new ReportBook(book, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        Book newBook = new Book("New book","Action","2023-09-12","Hard","SampleTitle","y");
        reportBook.setBook(newBook);
        
        assertEquals(newBook, reportBook.getBook());
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        Book book = new Book("John Doe", "Fiction", "2023-09-30", "Hardcover", "Sample Title","y");
        ReportBook reportBook = new ReportBook(book, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        String expected = "ReportBook{book=" + book + '}';
        assertEquals(expected, reportBook.toString());
    }

    /*@Test
    public void testGenerateReport() {
    }*/
}
