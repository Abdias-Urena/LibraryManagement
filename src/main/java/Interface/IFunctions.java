package Interface;

import Ticket.Ticket;

import java.sql.Date;

/**
 * La interfaz `IFunctions` define un conjunto de funciones
 */
public interface IFunctions {

    /**
     * Obtiene la cantidad de días.
     *
     * @return La cantidad de días.
     */
    public Date getDays(Ticket id);
}
