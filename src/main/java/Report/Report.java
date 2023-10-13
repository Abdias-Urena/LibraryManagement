package Report;

/**
 * La clase abstracta `Report` representa un informe con atributos como fecha
 * del informe, descripción, título y tipo de informe. Esta clase proporciona la
 * estructura básica de un informe y declara un método abstracto para generar el
 * informe concreto.
 */
public abstract class Report {

    private String dateReport;

    private String description;

    private String title;

    private String typeReport;

    /**
     * Constructor por defecto para la clase abstracta `Report`.
     */
    public Report() {
    }

    /**
     * Constructor parametrizado para la clase abstracta `Report`.
     *
     * @param dateReport La fecha del informe.
     * @param description La descripción del informe.
     * @param title El título del informe.
     * @param typeReport El tipo de informe.
     */
    public Report(String dateReport, String description, String title, String typeReport) {
        this.dateReport = dateReport;
        this.description = description;
        this.title = title;
        this.typeReport = typeReport;
    }

    /**
     * Obtiene la fecha del informe.
     *
     * @return La fecha del informe.
     */
    public String getDateReport() {
        return dateReport;
    }

    /**
     * Establece la fecha del informe.
     *
     * @param dateReport La nueva fecha del informe.
     */
    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }

    /**
     * Obtiene la descripción del informe.
     *
     * @return La descripción del informe.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del informe.
     *
     * @param description La nueva descripción del informe.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el título del informe.
     *
     * @return El título del informe.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título del informe.
     *
     * @param title El nuevo título del informe.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene el tipo de informe.
     *
     * @return El tipo de informe.
     */
    public String getTypeReport() {
        return typeReport;
    }

    /**
     * Establece el tipo de informe.
     *
     * @param typeReport El nuevo tipo de informe.
     */
    public void setTypeReport(String typeReport) {
        this.typeReport = typeReport;
    }

    /**
     * Método abstracto que debe ser implementado en las clases concretas para
     * generar el informe.
     */
    public abstract void generateReport();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Report{" + "dateReport=" + dateReport + ", description=" + description + ", title=" + title + ", typeReport=" + typeReport + '}';
    }
}
