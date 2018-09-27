package TrainParser;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Time;

public class TimeAdaptor extends XmlAdapter<String, Time> {
    @Override
    public Time unmarshal(String v) throws Exception {
        String stringTime = v + ":00";
        Time time = Time.valueOf(stringTime);
        return time;
    }

    @Override
    public String marshal(Time v) throws Exception {
        String stringTime = v.toString();
        return stringTime.substring(0, 5);
    }
}
