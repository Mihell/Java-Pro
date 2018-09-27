package TrainParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Trains trains = new Trains();
        File file = new File("filetrains.xml");
        addTrainsToXml(new Train(3, "Kyiv", "Svalyava", new Date(), Time.valueOf("18:30:00")), file);

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            trains = (Trains)unmarshaller.unmarshal(file);
        } catch(JAXBException e){
            e.printStackTrace();
        }

        Trains myTrains = findTrains(new Date(), Time.valueOf("15:00:00"), Time.valueOf("19:00:00"), trains);
        System.out.println(myTrains);
    }

    public static Trains findTrains(Date date, Time from, Time to, Trains trains){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return new Trains(trains.getTrains().stream()
                .filter(t -> (sdf.format(t.getDate()).equals(sdf.format(date)) && t.getDeparture().compareTo(from) >= 0 && t.getDeparture().compareTo(to) <= 0))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public static void addTrainsToXml(Train train, File file){
        Trains trains = new Trains();

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            trains = (Trains)unmarshaller.unmarshal(file);
            trains.addTrain(train);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(trains, file);
        } catch(JAXBException e){
            e.printStackTrace();
        }
    }
}
