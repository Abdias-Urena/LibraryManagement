/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Devolution;

import Loan.Loan;
import Person.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class DevolutionTest {
    
     @Test
    public void testConstructorAndGetters() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);

        assertEquals("2023-10-15", devolution.getDevolutionDate());
        assertEquals(loan, devolution.getLoan());
    }

    @Test
    public void testSetters() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Devolution devolution = new Devolution();

        devolution.setDevolutionDate("2023-10-20");
        Loan loan = new Loan("Alice Johnson", "Another Book", "2023-09-30", user);
        devolution.setLoan(loan);

        assertEquals("2023-10-20", devolution.getDevolutionDate());
        assertEquals(loan, devolution.getLoan());
    }

    @Test
    public void testToString() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("Bob Smith", "Science Book", "2023-09-25", user);

        Devolution devolution = new Devolution("2023-10-05", loan);

        String expectedToString = "Devolution{devolutionDate=2023-10-05, loan=" + loan.toString() + "}";
        assertEquals(expectedToString, devolution.toString());
    }
}
