package Entity;
/*public class Ticket {
    private int ticketID;
    private String ticketType;
    private int distance;
    private double price;

    public Ticket(int ticketID, String ticketType, int distance) {
        this.ticketID = ticketID;
        this.ticketType = ticketType;
        this.distance = distance;
        this.price = calculatePrice(distance);
    }

    public int getTicketID() {
        return ticketID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getDistance() {
        return distance;
    }

    public double getPrice() {
        return price;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        this.price = calculatePrice(distance);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double calculatePrice(int distance) {
        double basePrice = 100;
        double pricePerKm = 2.5;
        return basePrice + (distance * pricePerKm);
    }

    public void showDetails() {
        System.out.println("Ticket ID: " + ticketID);
        System.out.println("Ticket Type: " + ticketType);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Price: " + price);
    }
}*/

public class Ticket {
    private final int ticketID;
    private String ticketType;
    private int distance;
    private double price;

    public Ticket(int ticketID, String ticketType, int distance) {
        this.ticketID = ticketID;
        this.ticketType = ticketType;
        this.distance = distance;
        this.price = calculatePrice(distance);
    }

    public int getTicketID() {
        return ticketID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getDistance() {
        return distance;
    }

    public double getPrice() {
        return price;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        this.price = calculatePrice(distance);
    }

    private double calculatePrice(int distance) {
        double basePrice = 100;
        double pricePerKm = 2.5;
        return basePrice + (distance * pricePerKm);
    }

    public void showDetails() {
        System.out.println("Ticket ID: " + ticketID);
        System.out.println("Ticket Type: " + ticketType);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Price: " + price+ " TK");
    }
}

