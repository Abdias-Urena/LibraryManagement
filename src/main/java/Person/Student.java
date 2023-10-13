package Person;

/**
 * La clase `Student` representa a un estudiante que hereda de la clase `User`.
 * Incluye atributos específicos como la carrera que sigue y si tiene una beca o
 * no.
 */
public class Student extends User {

    private String career;

    private boolean grant;

    /**
     * Constructor por defecto para la clase `Student`.
     */
    public Student() {
    }

    /**
     * Constructor parametrizado para la clase `Student`.
     *
     * @param career La carrera que sigue el estudiante.
     * @param grant Indica si el estudiante tiene una beca.
     */
    public Student(String career, boolean grant) {
        this.career = career;
        this.grant = grant;
    }

    /**
     * Obtiene la carrera que sigue el estudiante.
     *
     * @return La carrera.
     */
    public String getCareer() {
        return career;
    }

    /**
     * Establece la carrera que sigue el estudiante.
     *
     * @param career La nueva carrera.
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * Verifica si el estudiante tiene una beca.
     *
     * @return `true` si el estudiante tiene una beca, `false` en caso
     * contrario.
     */
    public boolean isGrant() {
        return grant;
    }

    /**
     * Establece si el estudiante tiene una beca.
     *
     * @param grant `true` si el estudiante tiene una beca, `false` en caso
     * contrario.
     */
    public void setGrant(boolean grant) {
        this.grant = grant;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Student{" + "career=" + career + ", grant=" + grant + '}';
    }
}
