package Devolution;

import Interface.IFunctions;
import Loan.Loan;

/**
 * La clase `Devolution` representa la devolución de un préstamo de un
 * dispositivo, con detalles como la fecha de devolución y el préstamo asociado.
 */
public class Devolution implements IFunctions {

    private String devolutionDate;

    private Loan loan;

    /**
     * Constructor por defecto de la clase `Devolution`.
     */
    public Devolution() {
    }

    /**
     * Constructor que inicializa una instancia de la clase `Devolution` con los
     * valores proporcionados.
     *
     * @param devolutionDate La fecha de devolución del préstamo.
     * @param loan El préstamo asociado a la devolución.
     */
    public Devolution(String devolutionDate, Loan loan) {
        this.devolutionDate = devolutionDate;
        this.loan = loan;
    }

    /**
     * Obtiene la fecha de devolución del préstamo.
     *
     * @return La fecha de devolución del préstamo.
     */
    public String getDevolutionDate() {
        return devolutionDate;
    }

    /**
     * Establece la fecha de devolución del préstamo.
     *
     * @param devolutionDate La nueva fecha de devolución del préstamo.
     */
    public void setDevolutionDate(String devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    /**
     * Obtiene el préstamo asociado a la devolución.
     *
     * @return El préstamo asociado a la devolución.
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Establece el préstamo asociado a la devolución.
     *
     * @param loan El nuevo préstamo asociado a la devolución.
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * Implementación de la interfaz `IFunctions` para obtener la cantidad de
     * días. Esta implementación aún no está soportada.
     *
     * @return El valor de días (aún no hecho).
     */
    @Override
    public int getDays() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Representación en cadena de la clase `Devolution`.
     *
     * @return Una cadena que contiene los detalles de la devolución.
     */
    @Override
    public String toString() {
        return "Devolution{" + "devolutionDate=" + devolutionDate + ", loan=" + loan + '}';
    }
}
