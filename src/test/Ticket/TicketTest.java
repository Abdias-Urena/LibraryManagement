/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Ticket;

import Devolution.Devolution;
import Loan.Loan;
import Person.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class TicketTest {
    
    @Test
    public void testGetDevolution() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");
        assertEquals(devolution, ticket.getDevolution());
    }

    @Test
    public void testSetDevolution() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");

        Devolution newDevolution = new Devolution("2023-9-12",loan);
        ticket.setDevolution(newDevolution);

        assertEquals(newDevolution, ticket.getDevolution());
    }

    @Test
    public void testGetDescription() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");
        assertEquals("Sample Ticket", ticket.getDescription());
    }

    @Test
    public void testSetDescription() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");

        ticket.setDescription("Updated Ticket Description");

        assertEquals("Updated Ticket Description", ticket.getDescription());
    }

    @Test
    public void testGetPriceTicket() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");
        assertEquals(50, ticket.getPriceTicket());
    }

    @Test
    public void testSetPriceTicket() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");

        ticket.setPriceTicket(75);

        assertEquals(75, ticket.getPriceTicket());
    }

    @Test
    public void testGetTicket() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");
        assertEquals("Ticket123", ticket.getTicket());
    }

    @Test
    public void testSetTicket() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");

        ticket.setTicket("UpdatedTicket123");

        assertEquals("UpdatedTicket123", ticket.getTicket());
    }

    @Test
    public void testToString() {
        User user = new User("Rio claro", "abdias@gmail.com", "89898989", "1", "urena", "abdias");
        Loan loan = new Loan("John Doe", "The Book", "2023-10-01", user);

        Devolution devolution = new Devolution("2023-10-15", loan);
        Ticket ticket = new Ticket(devolution, "Sample Ticket", 50, "Ticket123");
        
        String expected = "Ticket{devolution=" + devolution + ", description=Sample Ticket, priceTicket=50, ticket=Ticket123}";
        assertEquals(expected, ticket.toString());
    }
    
}
