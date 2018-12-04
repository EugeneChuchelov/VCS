
package PO63.Chuchelov.wdad.learn.xml.XMLClasses;

import PO63.Chuchelov.wdad.learn.xml.DateAdapter;

import java.io.Serializable;
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
public class Employee implements Serializable {

    @XmlAttribute(name = "firstname", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String firstname;
    @XmlAttribute(name = "secondname", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String secondname;

    @XmlElement(name = "hiredate", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    protected Date hiredate;
    @XmlElement(name = "birthdate", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    protected Date birthdate;
    @XmlElement(name = "salary", required = true)
    protected int salary;
    @XmlElement(name = "jobtitle", required = true)
    protected Jobtitle jobtitle;


    public Employee() {
    }

    public Employee(String firstname, String secondname, Date hiredate, int salary, JobtitleEnum jobtitle) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.hiredate = hiredate;
        this.salary = salary;
        this.jobtitle = new Jobtitle(jobtitle);
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }
        if(obj instanceof Employee){
            return ((Employee) obj).firstname.equals(firstname) &&
                    ((Employee) obj).secondname.equals(secondname) &&
                     ((Employee) obj).hiredate.equals(hiredate) &&
                      ((Employee) obj).salary == salary &&
                       ((Employee) obj).jobtitle.equals(jobtitle);
        }
        return false;
    }
}
