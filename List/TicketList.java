package List;
import Entity.Ticket;
public class TicketList {
    private Ticket[] tickets;
    private int numTickets;

    public TicketList(int capacity) {
        tickets = new Ticket[capacity];
        numTickets = 0;
    }

    public void addTicket(Ticket ticket) {
        if (numTickets < tickets.length) {
            tickets[numTickets] = ticket;
            numTickets++;
        } else {
            System.out.println("Ticket list is full.");
        }
    }

    public void removeTicket(Ticket ticket) {
        for (int i = 0; i < numTickets; i++) {
            if (tickets[i] == ticket) {
                tickets[i] = null;
                for (int j = i; j < numTickets - 1; j++) {
                    tickets[j] = tickets[j + 1];
                }
                tickets[numTickets - 1] = null;
                numTickets--;
                break;
            }
        }
    }

    public Ticket getTicketByID(int ticketID) {
        for (int i = 0; i < numTickets; i++) {
            if (tickets[i].getTicketID() == ticketID) {
                return tickets[i];
            }
        }
        return null;
    }

    public Ticket[] getAllTickets() {
        Ticket[] allTickets = new Ticket[numTickets];
        for (int i = 0; i < numTickets; i++) {
            allTickets[i] = tickets[i];
        }
        return allTickets;
    }
}