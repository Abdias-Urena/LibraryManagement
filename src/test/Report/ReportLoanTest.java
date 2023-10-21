/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Report;

import Loan.Loan;
import Person.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Report loan test.
 *
 * @author Abdias
 */
public class ReportLoanTest {

    /**
     * Test get loan.
     */
    @Test
    public void testGetLoan() {
        
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        
        Loan loan = new Loan("2023-10-15", "12345", "2023-10-01", user);
        
        ReportLoan reportLoan = new ReportLoan(loan, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        assertEquals(loan, reportLoan.getLoan());
    }

    /**
     * Test set loan.
     */
    @Test
    public void testSetLoan() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");

        
        Loan loan = new Loan("2023-10-15", "12345", "2023-10-01", user);
        ReportLoan reportLoan = new ReportLoan(loan, "2023-09-30", "Sample report description", "Sample Report", "PDF");

        Loan newLoan = new Loan("2023-10-12","1222","2023-12-12",user);
        reportLoan.setLoan(newLoan);
        
        assertEquals(newLoan, reportLoan.getLoan());
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");

        
        Loan loan = new Loan("2023-10-15", "12345", "2023-10-01", user);
        ReportLoan reportLoan = new ReportLoan(loan, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        
        String expected = "ReportLoan{loan=" + loan + '}';
        assertEquals(expected, reportLoan.toString());
    }

//    @Test
//    public void testGenerateReport() {
//    }
}
