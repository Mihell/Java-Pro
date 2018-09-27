package TrainParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "trains")
public class Trains {
    @XmlElement(name = "train")
    private ArrayList<Train> trains = new ArrayList<>();

    public Trains() {
    }

    public Trains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void addTrain(Train train) {
        this.trains.add(train);
    }

    @Override
    public String toString() {
        return "Trains{" +
                "trains:" + "\n" + trains +
                '}';
    }
}
