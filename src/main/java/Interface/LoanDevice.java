package Interface;

import Device.Device;
import Person.User;
import java.util.List;

/**
 * La interfaz `LoanDevice` define un conjunto de operaciones para gestionar el
 * préstamo y devolución de dispositivos, así como para obtener una lista de
 * dispositivos disponibles.
 */
public interface LoanDevice {

    /**
     * Realiza un préstamo de un dispositivo a un usuario.
     *
     *
     * @param device El dispositivo que se prestará.
     * @return El dispositivo prestado.
     */
    public Device loanDevice(Device device);

    /**
     * Devuelve un dispositivo prestado.
     *
     * @param device El dispositivo que se devolverá.
     * @return El dispositivo devuelto.
     */
    public Device returnDevice(Device device);

    /**
     * Obtiene una lista de dispositivos disponibles para préstamo.
     *
     * @return Una lista de dispositivos disponibles.
     */
    public List<Device> obtainDeviceAvailable();
}
