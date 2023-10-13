package Device;

/**
 * La clase `Device` representa un dispositivo con detalles como la marca, la
 * disponibilidad de un cargador, el identificador, la usabilidad y el tipo.
 */
public class Device {

    private String brand;

    private boolean haveCharger;

    private String id;

    private boolean isUsable;

    private String type;

    /**
     * Constructor por defecto de la clase `Device`.
     */
    public Device() {
    }

    /**
     * Constructor que inicializa una instancia de la clase `Device` con los
     * valores proporcionados.
     *
     * @param brand La marca del dispositivo.
     * @param haveCharger Indica si el dispositivo tiene un cargador.
     * @param id El identificador del dispositivo.
     * @param isUsable Indica si el dispositivo es utilizable.
     * @param type El tipo de dispositivo.
     */
    public Device(String brand, boolean haveCharger, String id, boolean isUsable, String type) {
        this.brand = brand;
        this.haveCharger = haveCharger;
        this.id = id;
        this.isUsable = isUsable;
        this.type = type;
    }

    /**
     * Obtiene la marca del dispositivo.
     *
     * @return La marca del dispositivo.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Establece la marca del dispositivo.
     *
     * @param brand La nueva marca del dispositivo.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Verifica si el dispositivo tiene un cargador.
     *
     * @return `true` si el dispositivo tiene un cargador, `false` en caso
     * contrario.
     */
    public boolean isHaveCharger() {
        return haveCharger;
    }

    /**
     * Establece la disponibilidad de un cargador para el dispositivo.
     *
     * @param haveCharger `true` si el dispositivo tiene un cargador, `false` en
     * caso contrario.
     */
    public void setHaveCharger(boolean haveCharger) {
        this.haveCharger = haveCharger;
    }

    /**
     * Obtiene el identificador del dispositivo.
     *
     * @return El identificador del dispositivo.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del dispositivo.
     *
     * @param id El nuevo identificador del dispositivo.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Verifica si el dispositivo es utilizable.
     *
     * @return `true` si el dispositivo es utilizable, `false` en caso
     * contrario.
     */
    public boolean isIsUsable() {
        return isUsable;
    }

    /**
     * Establece la usabilidad del dispositivo.
     *
     * @param isUsable `true` si el dispositivo es utilizable, `false` en caso
     * contrario.
     */
    public void setIsUsable(boolean isUsable) {
        this.isUsable = isUsable;
    }

    /**
     * Obtiene el tipo de dispositivo.
     *
     * @return El tipo de dispositivo.
     */
    public String getType() {
        return type;
    }

    /**
     * Establece el tipo de dispositivo.
     *
     * @param type El nuevo tipo de dispositivo.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Representación en cadena de la clase `Device`.
     *
     * @return Una cadena que contiene los detalles del dispositivo.
     */
    @Override
    public String toString() {
        return "Device{" + "brand=" + brand + ", haveCharger=" + haveCharger + ", id=" + id + ", isUsable=" + isUsable + ", type=" + type + '}';
    }
}
