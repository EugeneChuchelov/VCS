package PO63.Chuchelov.wdad.learn.xml.XMLClasses;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "jobtitle")
public class Jobtitle implements Serializable {
    @XmlAttribute(name = "value", required = true)
    //@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected JobtitleEnum value;
    /**
     * Gets the value of the value property.
     *
     * @return
     *     possible object is
     *     {@link JobtitleEnum }
     *
     */
    public JobtitleEnum getValue() {
        return value;
    }
    /**
     * Sets the value of the value property.
     *
     * @param value
     *     allowed object is
     *     {@link JobtitleEnum }
     *
     */
    public void setValue(JobtitleEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj instanceof Jobtitle){
            return ((Jobtitle) obj).value.equals(value);
        }
        return false;
    }
}