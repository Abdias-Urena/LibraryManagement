/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Person;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class StudentTest {
    
    @Test
    public void testGetCareer() {
        Student student = new Student("Computer Science", true);
        assertEquals("Computer Science", student.getCareer());
    }

    @Test
    public void testSetCareer() {
        Student student = new Student("Computer Science", true);
        student.setCareer("Electrical Engineering");
        assertEquals("Electrical Engineering", student.getCareer());
    }

    @Test
    public void testIsGrant() {
        Student student = new Student("Computer Science", true);
        assertTrue(student.isGrant());
    }

    @Test
    public void testSetGrant() {
        Student student = new Student("Computer Science", true);
        student.setGrant(false);
        assertFalse(student.isGrant());
    }

    @Test
    public void testToString() {
        Student student = new Student("Computer Science", true);
        String expected = "Student{career=Computer Science, grant=true}";
        assertEquals(expected, student.toString());
    }
}
