package Notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Notification test.
 */
class NotificationTest {

    private Notification notification;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        notification = new Notification("Test Title", "Test Text", "Test URL");
    }

    /**
     * Test get title.
     */
    @Test
    void testGetTitle() {
        assertEquals("Test Title", notification.getTitle());
    }

    /**
     * Test set title.
     */
    @Test
    void testSetTitle() {
        notification.setTitle("New Title");
        assertEquals("New Title", notification.getTitle());
    }

    /**
     * Test get text.
     */
    @Test
    void testGetText() {
        assertEquals("Test Text", notification.getText());
    }

    /**
     * Test set text.
     */
    @Test
    void testSetText() {
        notification.setText("New Text");
        assertEquals("New Text", notification.getText());
    }

    /**
     * Test get url.
     */
    @Test
    void testGetUrl() {
        assertEquals("Test URL", notification.getUrl());
    }

    /**
     * Test set url.
     */
    @Test
    void testSetUrl() {
        notification.setUrl("New URL");
        assertEquals("New URL", notification.getUrl());
    }

    /**
     * Test to string.
     */
    @Test
    void testToString() {
        assertEquals("Notification{title='Test Title', text='Test Text', url='Test URL'}", notification.toString());
    }
}