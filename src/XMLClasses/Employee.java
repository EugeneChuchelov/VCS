
package XMLClasses;

import PO63.Chuchelov.wdad.learn.xml.DateAdapter;

import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "hiredate",
        "salary",
        "jobtitle"
})
@XmlRootElement(name = "employee")
public class Employee {

    @XmlAttribute(name = "firstname", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String firstname;
    @XmlAttribute(name = "secondname", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String secondname;
    //todo change to:

    @XmlElement(name = "hiredate", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    //@XmlSchemaType(name = "Date")

    protected Date hiredate;
    @XmlElement(name = "salary", required = true)
    protected int salary;
    @XmlElement(name = "jobtitle", required = true)
    protected Jobtitle jobtitle;

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public JobtitleEnum getJobtitle() {
        return jobtitle.getValue();
    }

    public void setJobtitle(JobtitleEnum jobtitle) {
        this.jobtitle.setValue(jobtitle);
    }

    /**
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Gets the value of the secondname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondname() {
        return secondname;
    }

    /**
     * Sets the value of the secondname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondname(String value) {
        this.secondname = value;
    }
}
