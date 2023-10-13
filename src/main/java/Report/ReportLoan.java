package Report;

/**
 * La clase `ReportLoan` representa un informe relacionado con un préstamo y
 * hereda de la clase `Report`. Incluye un atributo específico para el préstamo
 * asociado.
 */
import Loan.Loan;

public class ReportLoan extends Report {

    private Loan loan;

    /**
     * Constructor por defecto para la clase `ReportLoan`.
     */
    public ReportLoan() {
    }

    /**
     * Constructor parametrizado para la clase `ReportLoan`.
     *
     * @param loan El préstamo asociado al informe.
     */
    public ReportLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * Constructor parametrizado para la clase `ReportLoan` que también recibe
     * información de la clase base `Report`.
     *
     * @param loan El préstamo asociado al informe.
     * @param dateReport La fecha del informe.
     * @param description La descripción del informe.
     * @param title El título del informe.
     * @param typeReport El tipo de informe.
     */
    public ReportLoan(Loan loan, String dateReport, String description, String title, String typeReport) {
        super(dateReport, description, title, typeReport);
        this.loan = loan;
    }

    /**
     * Obtiene el préstamo asociado al informe.
     *
     * @return El préstamo asociado.
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Establece el préstamo asociado al informe.
     *
     * @param loan El nuevo préstamo asociado.
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateReport() {
        throw new UnsupportedOperationException("Not supported yet..");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ReportLoan{" + "loan=" + loan + '}';
    }
}
