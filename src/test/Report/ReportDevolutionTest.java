/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Report;

import Devolution.Devolution;
import Loan.Loan;
import Person.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class ReportDevolutionTest {
    
     @Test
    public void testGetDevolution() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        
        Devolution devolution = new Devolution("2023-10-15", loan);
        ReportDevolution reportDevolution = new ReportDevolution(devolution, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        assertEquals(devolution, reportDevolution.getDevolution());
    }

    @Test
    public void testSetDevolution() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        
        Devolution devolution = new Devolution("2023-10-15", loan);
        
        ReportDevolution reportDevolution = new ReportDevolution(devolution, "2023-09-30", "Sample report description", "Sample Report", "PDF");

        Devolution newDevolution = new Devolution("2023-9-12", loan);
        reportDevolution.setDevolution(newDevolution);
        
        assertEquals(newDevolution, reportDevolution.getDevolution());
    }

    @Test
    public void testToString() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        
        Devolution devolution = new Devolution("2023-10-15", loan);
        ReportDevolution reportDevolution = new ReportDevolution(devolution, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        
        String expected = "ReportDevolution{devolution=" + devolution + '}';
        assertEquals(expected, reportDevolution.toString());
    }

    /*@Test
    public void testGenerateReport() {
    }*/
    
}
