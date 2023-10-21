/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Report;

import Person.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Report user ticket test.
 *
 * @author Abdias
 */
public class ReportUserTicketTest {

    /**
     * Test get user.
     */
    @Test
    public void testGetUser() {
        User user = new User("12345", "John", "Doe", "johndoe@example.com", "123-456-7890", "123 Main St");
        ReportUserTicket reportUserTicket = new ReportUserTicket(user, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        assertEquals(user, reportUserTicket.getUser());
    }

    /**
     * Test set user.
     */
    @Test
    public void testSetUser() {
        User user = new User("12345", "John", "Doe", "johndoe@example.com", "123-456-7890", "123 Main St");
        ReportUserTicket reportUserTicket = new ReportUserTicket(user, "2023-09-30", "Sample report description", "Sample Report", "PDF");

        User newUser = new User("67890", "Jane", "Smith", "janesmith@example.com", "987-654-3210", "456 Elm St");
        reportUserTicket.setUser(newUser);
        
        assertEquals(newUser, reportUserTicket.getUser());
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        User user = new User("12345", "John", "Doe", "johndoe@example.com", "123-456-7890", "123 Main St");
        ReportUserTicket reportUserTicket = new ReportUserTicket(user, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        
        String expected = "ReportUserTicket{user=" + user + '}';
        assertEquals(expected, reportUserTicket.toString());
    }

//    @Test
//    public void testGenerateReport() {
//    }
}
