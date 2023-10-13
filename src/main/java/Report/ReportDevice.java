package Report;

/**
 * La clase `ReportDevice` representa un informe relacionado con un dispositivo
 * y hereda de la clase `Report`. Incluye un atributo específico para el
 * dispositivo asociado.
 */
import Device.Device;

public class ReportDevice extends Report {

    private Device device;

    /**
     * Constructor por defecto para la clase `ReportDevice`.
     */
    public ReportDevice() {
    }

    /**
     * Constructor parametrizado para la clase `ReportDevice`.
     *
     * @param device El dispositivo asociado al informe.
     */
    public ReportDevice(Device device) {
        this.device = device;
    }

    /**
     * Constructor parametrizado para la clase `ReportDevice` que también recibe
     * información de la clase base `Report`.
     *
     * @param device El dispositivo asociado al informe.
     * @param dateReport La fecha del informe.
     * @param description La descripción del informe.
     * @param title El título del informe.
     * @param typeReport El tipo de informe.
     */
    public ReportDevice(Device device, String dateReport, String description, String title, String typeReport) {
        super(dateReport, description, title, typeReport);
        this.device = device;
    }

    /**
     * Obtiene el dispositivo asociado al informe.
     *
     * @return El dispositivo asociado.
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Establece el dispositivo asociado al informe.
     *
     * @param device El nuevo dispositivo asociado.
     */
    public void setDevice(Device device) {
        this.device = device;
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
        return "ReportDevice{" + "device=" + device + '}';
    }
}
