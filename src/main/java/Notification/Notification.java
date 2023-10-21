package Notification;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * La clase `Notification` se utiliza para crear y mostrar notificaciones gráficas
 * en una aplicación JavaFX.
 */
public class Notification {

    private String title;
    private String text;
    private String url;

    /**
     * Constructor por defecto de la clase `Notification`.
     */
    public Notification() {
    }

    /**
     * Constructor de la clase `Notification` que inicializa los atributos de la notificación.
     *
     * @param title El título de la notificación.
     * @param text  El texto de la notificación.
     * @param url   La URL de la imagen a mostrar en la notificación.
     */
    public Notification(String title, String text, String url) {
        this.title = title;
        this.text = text;
        this.url = url;
    }

    /**
     * Obtiene el título de la notificación.
     *
     * @return El título de la notificación.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título de la notificación.
     *
     * @param title El nuevo título de la notificación.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el texto de la notificación.
     *
     * @return El texto de la notificación.
     */
    public String getText() {
        return text;
    }

    /**
     * Establece el texto de la notificación.
     *
     * @param text El nuevo texto de la notificación.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Obtiene la URL de la imagen de la notificación.
     *
     * @return La URL de la imagen de la notificación.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Establece la URL de la imagen de la notificación.
     *
     * @param url La nueva URL de la imagen de la notificación.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Modifica y muestra la notificación con el título, texto y URL proporcionados.
     *
     * @param title El nuevo título de la notificación.
     * @param text  El nuevo texto de la notificación.
     * @param url   La nueva URL de la imagen de la notificación.
     */
    public void modifyNotification(String title, String text, String url) {
        Image img = new Image(url);
        Notifications notification = Notifications.create();
        notification.graphic(new ImageView(img));
        notification.title(title);
        notification.text(text);
        notification.hideAfter(Duration.seconds(5));
        notification.position(Pos.TOP_RIGHT);
        notification.show();
    }

    /**
     * Representación en cadena de la clase `Notification`.
     *
     * @return Una cadena que contiene los detalles de la notificación.
     */
    @Override
    public String toString() {
        return "Notification{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
