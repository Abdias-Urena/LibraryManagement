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
import org.junit.jupiter.api.BeforeEach;

/**
 * The type Device test.
 *
 * @author Abdias
 */
public class DeviceTest {

    private Device device;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        // Crear una instancia de Device antes de cada prueba
        device = new Device("Samsung", true, "12345", true, true, "Smartphone");
    }

    /**
     * Test get brand.
     */
    @Test
    void testGetBrand() {
        assertEquals("Samsung", device.getBrand());
    }

    /**
     * Test set brand.
     */
    @Test
    void testSetBrand() {
        device.setBrand("Apple");
        assertEquals("Apple", device.getBrand());
    }

    /**
     * Test is have charger.
     */
    @Test
    void testIsHaveCharger() {
        assertTrue(device.isHaveCharger());
    }

    /**
     * Test set have charger.
     */
    @Test
    void testSetHaveCharger() {
        device.setHaveCharger(false);
        assertFalse(device.isHaveCharger());
    }

    /**
     * Test get id.
     */
    @Test
    void testGetId() {
        assertEquals("12345", device.getId());
    }

    /**
     * Test set id.
     */
    @Test
    void testSetId() {
        device.setId("54321");
        assertEquals("54321", device.getId());
    }

    /**
     * Test is usable.
     */
    @Test
    void testIsUsable() {
        assertTrue(device.isUsable());
    }

    /**
     * Test set is usable.
     */
    @Test
    void testSetIsUsable() {
        device.setIsUsable(false);
        assertFalse(device.isUsable());
    }

    /**
     * Test get type.
     */
    @Test
    void testGetType() {
        assertEquals("Smartphone", device.getType());
    }

    /**
     * Test set type.
     */
    @Test
    void testSetType() {
        device.setType("Tablet");
        assertEquals("Tablet", device.getType());
    }

    /**
     * Test to string.
     */
    @Test
    void testToString() {
        assertEquals("Samsung 12345", device.toString());
    }

}
