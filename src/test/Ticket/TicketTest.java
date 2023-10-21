/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Ticket;

import Loan.Loan;
import Person.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Ticket test.
 *
 * @author Abdias
 */
public class TicketTest {

    private Ticket ticket;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        // Crear una instancia de Ticket antes de cada prueba
        ticket = new Ticket("Descripción del ticket", 100, "T123");
    }

    /**
     * Test get description.
     */
    @Test
    void testGetDescription() {
        assertEquals("Descripción del ticket", ticket.getDescription());
    }

    /**
     * Test set description.
     */
    @Test
    void testSetDescription() {
        ticket.setDescription("Nueva descripción");
        assertEquals("Nueva descripción", ticket.getDescription());
    }

    /**
     * Test get price ticket.
     */
    @Test
    void testGetPriceTicket() {
        assertEquals(100, ticket.getPriceTicket());
    }

    /**
     * Test set price ticket.
     */
    @Test
    void testSetPriceTicket() {
        ticket.setPriceTicket(200);
        assertEquals(200, ticket.getPriceTicket());
    }

    /**
     * Test get ticket.
     */
    @Test
    void testGetTicket() {
        assertEquals("T123", ticket.getTicket());
    }

    /**
     * Test set ticket.
     */
    @Test
    void testSetTicket() {
        ticket.setTicket("T456");
        assertEquals("T456", ticket.getTicket());
    }
    
}
