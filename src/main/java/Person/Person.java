package Person;

/**
 * La clase `Person` representa a una persona con atributos como ID, apellido y
 * nombre. Puede utilizarse como una clase base para representar personas en un
 * sistema.
 */
public class Person {

    private String id;

    private String lastname;

    private String name;

    /**
     * Constructor por defecto para la clase `Person`.
     */
    public Person() {
    }

    /**
     * Constructor parametrizado para la clase `Person`.
     *
     * @param id El ID único de la persona.
     * @param lastname El apellido de la persona.
     * @param name El nombre de la persona.
     *
     */
    public Person(String id, String lastname, String name) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
    }

    /**
     * Obtiene el ID único de la persona.
     *
     * @return El ID único.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID único de la persona.
     *
     * @param id El nuevo ID único.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el apellido de la persona.
     *
     * @return El apellido.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Establece el apellido de la persona.
     *
     * @param lastname El nuevo apellido.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return El nombre.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param name El nuevo nombre.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", lastname=" + lastname + ", name=" + name + '}';
    }
}
