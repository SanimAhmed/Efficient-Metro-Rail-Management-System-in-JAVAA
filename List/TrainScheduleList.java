package List;
import Entity.TrainSchedule;
public class TrainScheduleList {
    private TrainSchedule[] schedules;
    private int numSchedules;

    public TrainScheduleList(int capacity) {
        schedules = new TrainSchedule[capacity];
        numSchedules = 0;
    }

    public void addSchedule(TrainSchedule schedule) {
        if (numSchedules < schedules.length) {
            schedules[numSchedules] = schedule;
            numSchedules++;
        } else {
            System.out.println("Schedule list is full.");
        }
    }

    public void removeSchedule(TrainSchedule schedule) {
        for (int i = 0; i < numSchedules; i++) {
            if (schedules[i] == schedule) {
                schedules[i] = null;
                for (int j = i; j < numSchedules - 1; j++) {
                    schedules[j] = schedules[j + 1];
                }
                schedules[numSchedules - 1] = null;
                numSchedules--;
                break;
            }
        }
    }

    public TrainSchedule getScheduleByTrainName(String trainName) {
        for (int i = 0; i < numSchedules; i++) {
            if (schedules[i].getTrainName().equals(trainName)) {
                return schedules[i];
            }
        }
        return null;
    }

    public TrainSchedule[] getAllSchedules() {
        TrainSchedule[] allSchedules = new TrainSchedule[numSchedules];
        for (int i = 0; i < numSchedules; i++) {
            allSchedules[i] = schedules[i];
        }
        return allSchedules;
    }
}