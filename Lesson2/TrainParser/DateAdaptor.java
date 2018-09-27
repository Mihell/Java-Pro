package TrainParser;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAdaptor extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = sdf.parse(v);
        return date;
    }

    @Override
    public String marshal(Date v) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(v);
    }
}
