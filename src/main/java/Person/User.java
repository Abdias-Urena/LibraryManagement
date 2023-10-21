package Person;

/**
 * La clase `User` representa a un usuario que hereda de la clase `Person`.
 * Incluye atributos específicos como dirección, correo electrónico y número de
 * teléfono.
 */
public class User extends Person {

    private String address;

    private String email;

    private String phoneNumber;

    /**
     * Constructor por defecto para la clase `User`.
     */
    public User() {
    }

    /**
     * Constructor parametrizado para la clase `User`.
     *
     * @param address     La dirección del usuario.
     * @param email       El correo electrónico del usuario.
     * @param phoneNumber El número de teléfono del usuario.
     */
    public User(String address, String email, String phoneNumber) {
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructor parametrizado para la clase `User` que también recibe
     * información de la clase base `Person`.
     *
     * @param address     La dirección del usuario.
     * @param email       El correo electrónico del usuario.
     * @param phoneNumber El número de teléfono del usuario.
     * @param id          El ID único de la persona.
     * @param lastname    El apellido de la persona.
     * @param name        El nombre de la persona.
     */
    public User(String address, String email, String phoneNumber, String id, String lastname, String name) {
        super(id, lastname, name);
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Obtiene la dirección del usuario.
     *
     * @return La dirección.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la dirección del usuario.
     *
     * @param address La nueva dirección.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El nuevo correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     *
     * @return El número de teléfono.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Establece el número de teléfono del usuario.
     *
     * @param phoneNumber El nuevo número de teléfono.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName()+" "+getLastname();
    }
}
