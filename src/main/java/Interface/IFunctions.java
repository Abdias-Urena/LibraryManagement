package Interface;

import Ticket.Ticket;

/**
 * La interfaz `IFunctions` define un conjunto de funciones
 */
public interface IFunctions {

    /**
     * Obtiene la cantidad de días.
     *
     * @return La cantidad de días.
     */
    public Ticket getDays(Ticket ticket);
}
