package PO63.Chuchelov.wdad.learn.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {
    private static SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");

    public Date unmarshal(String v) throws Exception {
        return ft.parse(v);
    }

    public String marshal(Date v) throws Exception {
        return ft.format(v);
    }
}
