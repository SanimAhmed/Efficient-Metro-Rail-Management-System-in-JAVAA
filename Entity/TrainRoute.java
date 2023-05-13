package Entity;

public class TrainRoute {
    private String source;
    private String destination;
    private int distance;
    private int duration;
    public TrainRoute(String source, String destination, int distance, int duration) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
    }
    
    public String getSource() {
        return source;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public int getDistance() {
        return distance;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public void displayRoute() {
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Distance: " + distance + " km");
        System.out.println("Duration: " + duration + " hours");
    }
}