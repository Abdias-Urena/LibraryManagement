package Person;

/**
 * La clase `Teacher` representa a un profesor que hereda de la clase `User`.
 * Incluye un atributo específico para el departamento al que pertenece.
 */
public class Teacher extends User {

    private String departament;

    /**
     * Constructor por defecto para la clase `Teacher`.
     */
    public Teacher() {
    }

    /**
     * Constructor parametrizado para la clase `Teacher`.
     *
     * @param departament El departamento al que pertenece el profesor.
     */
    public Teacher(String departament) {
        this.departament = departament;
    }

    /**
     * Obtiene el departamento al que pertenece el profesor.
     *
     * @return El departamento.
     */
    public String getDepartament() {
        return departament;
    }

    /**
     * Establece el departamento al que pertenece el profesor.
     *
     * @param departament El nuevo departamento.
     */
    public void setDepartament(String departament) {
        this.departament = departament;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Teacher{" + "departament=" + departament + '}';
    }
}
