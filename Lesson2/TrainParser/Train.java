package TrainParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement(name = "train")
public class Train {

    private int id;

    private String from;
    private String to;
    private Date date;
    private Time departure;


    public Train() {
    }

    public Train(int id, String from, String to, Date date, Time departure) {

        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }
    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }
    @XmlElement
    public void setDate(Date date) {
        this.date = date;
    }
    @XmlElement
    public void setDeparture(Time departure) {
        this.departure = departure;
    }


    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @XmlJavaTypeAdapter(DateAdaptor.class)
    public Date getDate() {
        return date;
    }

    @XmlJavaTypeAdapter(TimeAdaptor.class)
    public Time getDeparture() {
        return departure;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Train{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + sdf.format(date) +
                ", departure=" + departure +
                '}' + "\n";
    }
}
