package Report;

/**
 * La clase `ReportDevolution` representa un informe relacionado con una
 * devolución y hereda de la clase `Report`. Incluye un atributo específico para
 * la devolución asociada.
 */
import Devolution.Devolution;

public class ReportDevolution extends Report {

    private Devolution devolution;

    /**
     * Constructor por defecto para la clase `ReportDevolution`.
     */
    public ReportDevolution() {
    }

    /**
     * Constructor parametrizado para la clase `ReportDevolution`.
     *
     * @param devolution La devolución asociada al informe.
     */
    public ReportDevolution(Devolution devolution) {
        this.devolution = devolution;
    }

    /**
     * Constructor parametrizado para la clase `ReportDevolution` que también
     * recibe información de la clase base `Report`.
     *
     * @param devolution La devolución asociada al informe.
     * @param dateReport La fecha del informe.
     * @param description La descripción del informe.
     * @param title El título del informe.
     * @param typeReport El tipo de informe.
     */
    public ReportDevolution(Devolution devolution, String dateReport, String description, String title, String typeReport) {
        super(dateReport, description, title, typeReport);
        this.devolution = devolution;
    }

    /**
     * Obtiene la devolución asociada al informe.
     *
     * @return La devolución asociada.
     */
    public Devolution getDevolution() {
        return devolution;
    }

    /**
     * Establece la devolución asociada al informe.
     *
     * @param devolution La nueva devolución asociada.
     */
    public void setDevolution(Devolution devolution) {
        this.devolution = devolution;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ReportDevolution{" + "devolution=" + devolution + '}';
    }
}
