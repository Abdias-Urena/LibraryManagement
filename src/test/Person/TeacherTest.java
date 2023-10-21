/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Person;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Teacher test.
 *
 * @author Abdias
 */
public class TeacherTest {

    /**
     * Test get departament.
     */
    @Test
    public void testGetDepartament() {
        Teacher teacher = new Teacher("Mathematics");
        assertEquals("Mathematics", teacher.getDepartament());
    }

    /**
     * Test set departament.
     */
    @Test
    public void testSetDepartament() {
        Teacher teacher = new Teacher("Physics");
        teacher.setDepartament("Chemistry");
        assertEquals("Chemistry", teacher.getDepartament());
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        Teacher teacher = new Teacher("History");
        String expected = "Teacher{departament=History}";
        assertEquals(expected, teacher.toString());
    }
    
}
