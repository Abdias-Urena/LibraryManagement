/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Book;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Fisic book test.
 *
 * @author Abdias
 */
public class FisicBookTest {

    /**
     * Test fisic book with availability and usability constructor and getters.
     */
    @Test
    public void testFisicBookWithAvailabilityAndUsabilityConstructorAndGetters() {
        FisicBook fisicBook = new FisicBook(true, true);

        assertTrue(fisicBook.isIsAvailable());
        assertTrue(fisicBook.isIsUsable());
    }

    /**
     * Test fisic book full constructor and getters.
     */
    @Test
    public void testFisicBookFullConstructorAndGetters() {
        FisicBook fisicBook = new FisicBook(
            true,
            false,
            "Alice Johnson",
            "Mystery",
            "2023-10-05",
            "Hardcover",
            "Mystery Novel"
                ,"y"
        );

        assertEquals("Alice Johnson", fisicBook.getAuthor());
        assertEquals("Mystery", fisicBook.getCategory());
        assertEquals("2023-10-05", fisicBook.getPublicationDate());
        assertEquals("Hardcover", fisicBook.getReproduction());
        assertEquals("Mystery Novel", fisicBook.getTitle());

        assertTrue(fisicBook.isIsAvailable());
        assertFalse(fisicBook.isIsUsable());
    }

    /**
     * Test fisic book availability and usability setters.
     */
    @Test
    public void testFisicBookAvailabilityAndUsabilitySetters() {
        FisicBook fisicBook = new FisicBook(true, true);

        fisicBook.setIsAvailable(false);
        fisicBook.setIsUsable(true);

        assertFalse(fisicBook.isIsAvailable());
        assertTrue(fisicBook.isIsUsable());
    }

    /**
     * Test fisic book to string.
     */
    @Test
    public void testFisicBookToString() {
        FisicBook fisicBook = new FisicBook(true, true);

        String expectedToString = "FisicBook{isAvailable=true, isUsable=true}";
        assertEquals(expectedToString, fisicBook.toString());
    }
}
