package Ticket;

/**
 * La clase `Ticket` representa un ticket relacionado con una devolución.
 * Incluye atributos como la devolución asociada, descripción, precio del ticket
 * y número de ticket.
 */
public class Ticket {


    private String description;

    private int priceTicket;

    private String ticket;

    /**
     * Constructor por defecto para la clase `Ticket`.
     */
    public Ticket() {
    }

    /**
     * Constructor parametrizado para la clase `Ticket`.
     *
     * @param description La descripción del ticket.
     * @param priceTicket El precio del ticket.
     * @param ticket El número de ticket.
     */
    public Ticket(String description, int priceTicket, String ticket) {
        this.description = description;
        this.priceTicket = priceTicket;
        this.ticket = ticket;
    }
    /**
     * Obtiene la descripción del ticket.
     *
     * @return La descripción del ticket.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del ticket.
     *
     * @param description La nueva descripción del ticket.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el precio del ticket.
     *
     * @return El precio del ticket.
     */
    public int getPriceTicket() {
        return priceTicket;
    }

    /**
     * Establece el precio del ticket.
     *
     * @param priceTicket El nuevo precio del ticket.
     */
    public void setPriceTicket(int priceTicket) {
        this.priceTicket = priceTicket;
    }

    /**
     * Obtiene el número de ticket.
     *
     * @return El número de ticket.
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * Establece el número de ticket.
     *
     * @param ticket El nuevo número de ticket.
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Ticket{" + ", description=" + description + ", priceTicket=" + priceTicket + ", ticket=" + ticket + '}';
    }
}
