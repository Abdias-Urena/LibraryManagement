/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Person;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User test.
 *
 * @author Abdias
 */
public class UserTest {

    /**
     * Test get address.
     */
    @Test
    public void testGetAddress() {
        User user = new User("123 Main St", "user@example.com", "123-456-7890");
        assertEquals("123 Main St", user.getAddress());
    }

    /**
     * Test set address.
     */
    @Test
    public void testSetAddress() {
        User user = new User("123 Main St", "user@example.com", "123-456-7890");
        user.setAddress("456 Elm St");
        assertEquals("456 Elm St", user.getAddress());
    }

    /**
     * Test get email.
     */
    @Test
    public void testGetEmail() {
        User user = new User("123 Main St", "user@example.com", "123-456-7890");
        assertEquals("user@example.com", user.getEmail());
    }

    /**
     * Test set email.
     */
    @Test
    public void testSetEmail() {
        User user = new User("123 Main St", "user@example.com", "123-456-7890");
        user.setEmail("newuser@example.com");
        assertEquals("newuser@example.com", user.getEmail());
    }

    /**
     * Test get phone number.
     */
    @Test
    public void testGetPhoneNumber() {
        User user = new User("123 Main St", "user@example.com", "123-456-7890");
        assertEquals("123-456-7890", user.getPhoneNumber());
    }

    /**
     * Test set phone number.
     */
    @Test
    public void testSetPhoneNumber() {
        User user = new User("123 Main St", "user@example.com", "123-456-7890");
        user.setPhoneNumber("987-654-3210");
        assertEquals("987-654-3210", user.getPhoneNumber());
    }

}
