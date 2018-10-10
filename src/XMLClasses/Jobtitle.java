package XMLClasses;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "jobtitle")
public class Jobtitle {
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
}