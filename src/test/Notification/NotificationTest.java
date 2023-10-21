package Notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    private Notification notification;

    @BeforeEach
    void setUp() {
        notification = new Notification("Test Title", "Test Text", "Test URL");
    }

    @Test
    void testGetTitle() {
        assertEquals("Test Title", notification.getTitle());
    }

    @Test
    void testSetTitle() {
        notification.setTitle("New Title");
        assertEquals("New Title", notification.getTitle());
    }

    @Test
    void testGetText() {
        assertEquals("Test Text", notification.getText());
    }

    @Test
    void testSetText() {
        notification.setText("New Text");
        assertEquals("New Text", notification.getText());
    }

    @Test
    void testGetUrl() {
        assertEquals("Test URL", notification.getUrl());
    }

    @Test
    void testSetUrl() {
        notification.setUrl("New URL");
        assertEquals("New URL", notification.getUrl());
    }

    @Test
    void testToString() {
        assertEquals("Notification{title='Test Title', text='Test Text', url='Test URL'}", notification.toString());
    }
}