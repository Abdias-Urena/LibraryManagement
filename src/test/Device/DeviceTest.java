/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Device;

import Device.Device;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class DeviceTest {
    
     @Test
    public void testConstructorAndGetters() {
        Device device = new Device("Samsung", true, "12345", true, "Phone");

        assertEquals("Samsung", device.getBrand());
        assertTrue(device.isHaveCharger());
        assertEquals("12345", device.getId());
        assertTrue(device.isIsUsable());
        assertEquals("Phone", device.getType());
    }

    @Test
    public void testSetters() {
        Device device = new Device();

        device.setBrand("Apple");
        device.setHaveCharger(false);
        device.setId("67890");
        device.setIsUsable(true);
        device.setType("Tablet");

        assertEquals("Apple", device.getBrand());
        assertFalse(device.isHaveCharger());
        assertEquals("67890", device.getId());
        assertTrue(device.isIsUsable());
        assertEquals("Tablet", device.getType());
    }

    @Test
    public void testToString() {
        Device device = new Device("Sony", true, "54321", false, "Laptop");

        String expectedToString = "Device{brand=Sony, haveCharger=true, id=54321, isUsable=false, type=Laptop}";
        assertEquals(expectedToString, device.toString());
    }
    
}
