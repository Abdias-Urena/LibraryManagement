/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Loan;

import Person.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Loan test.
 *
 * @author Abdias
 */
public class LoanTest {

    /**
     * Test constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");

        Loan loan = new Loan("2023-10-15", "12345", "2023-10-01", user);

        assertEquals("2023-10-15", loan.getExpirationDate());
        assertEquals("12345", loan.getId());
        assertEquals("2023-10-01", loan.getLoanDate());
        assertEquals(user, loan.getUser());
    }

    /**
     * Test setters.
     */
    @Test
    public void testSetters() {
        Loan loan = new Loan();

        loan.setExpirationDate("2023-10-20");
        loan.setId("67890");
        loan.setLoanDate("2023-09-30");
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        loan.setUser(user);

        assertEquals("2023-10-20", loan.getExpirationDate());
        assertEquals("67890", loan.getId());
        assertEquals("2023-09-30", loan.getLoanDate());
        assertEquals(user, loan.getUser());
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");

        Loan loan = new Loan("2023-10-05", "54321", "2023-09-25", user);

        String expectedToString = "Loan{expirationDate=2023-10-05, id=54321, loanDate=2023-09-25, user=" + user.toString() + "}";
        assertEquals(expectedToString, loan.toString());
    }

}
