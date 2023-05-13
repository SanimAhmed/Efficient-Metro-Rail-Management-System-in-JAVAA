package Entity;

import List.TrainList.Schedule;

public final class Train extends Transport {

   
    private int trainID;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;

    public Train(int transportID, String transportName, String source, String destination, String departureTime, String arrivalTime) {
        super(transportID, transportName);
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    
    public int getTrainID() {
        return trainID=super.getTransportID();
    }
    // Getters and setters
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

  


    @Override
    public void start() {
        System.out.println("Train started.");
    }

    @Override
    public void stop() {
        System.out.println("Train stopped.");
    }

    public boolean equalsIgnoreCase(String searchText) {
        return false;
    }
}