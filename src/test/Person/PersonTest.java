/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Person;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Person test.
 *
 * @author Abdias
 */
public class PersonTest {

    /**
     * Test get id.
     */
    @Test
    public void testGetId() {
        Person person = new Person("1", "Doe", "John");
        assertEquals("1", person.getId());
    }

    /**
     * Test set id.
     */
    @Test
    public void testSetId() {
        Person person = new Person("1", "Doe", "John");
        person.setId("2");
        assertEquals("2", person.getId());
    }

    /**
     * Test get lastname.
     */
    @Test
    public void testGetLastname() {
        Person person = new Person("1", "Doe", "John");
        assertEquals("Doe", person.getLastname());
    }

    /**
     * Test set lastname.
     */
    @Test
    public void testSetLastname() {
        Person person = new Person("1", "Doe", "John");
        person.setLastname("Smith");
        assertEquals("Smith", person.getLastname());
    }

    /**
     * Test get name.
     */
    @Test
    public void testGetName() {
        Person person = new Person("1", "Doe", "John");
        assertEquals("John", person.getName());
    }

    /**
     * Test set name.
     */
    @Test
    public void testSetName() {
        Person person = new Person("1", "Doe", "John");
        person.setName("Jane");
        assertEquals("Jane", person.getName());
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        Person person = new Person("1", "Doe", "John");
        String expected = "Person{id=1, lastname=Doe, name=John}";
        assertEquals(expected, person.toString());
    }
    
}
