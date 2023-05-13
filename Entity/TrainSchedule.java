package Entity;
public class TrainSchedule {
    private String trainName;
    private String trainType;
    private String departureTime;
    private String arrivalTime;
    public TrainSchedule(String trainName, String trainType, String departureTime, String arrivalTime) {
        this.trainName = trainName;
        this.trainType = trainType;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    public String getTrainName() {
        return trainName;
    }
    
    public String getTrainType() {
        return trainType;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    
    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }
    
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public void displaySchedule() {
        System.out.println("Train Name: " + trainName);
        System.out.println("Train Type: " + trainType);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Arrival Time: " + arrivalTime);
    }
}