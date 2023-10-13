/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Report;

import Device.Device;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdias
 */
public class ReportDeviceTest {
    
     @Test
    public void testGetDevice() {
         Device device = new Device("Samsung", true, "12345", true, "Phone");
        ReportDevice reportDevice = new ReportDevice(device, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        assertEquals(device, reportDevice.getDevice());
    }

    @Test
    public void testSetDevice() {
         Device device = new Device("Samsung", true, "12345", true, "Phone");
        ReportDevice reportDevice = new ReportDevice(device, "2023-09-30", "Sample report description", "Sample Report", "PDF");

        Device newDevice = new Device("Samsung1", true, "12345", true, "Phone");
        reportDevice.setDevice(newDevice);
        
        assertEquals(newDevice, reportDevice.getDevice());
    }

    @Test
    public void testToString() {
         Device device = new Device("Samsung", true, "12345", true, "Phone");
        ReportDevice reportDevice = new ReportDevice(device, "2023-09-30", "Sample report description", "Sample Report", "PDF");
        
        String expected = "ReportDevice{device=" + device + '}';
        assertEquals(expected, reportDevice.toString());
    }

//    @Test
//    public void testGenerateReport() {
//    }
}
