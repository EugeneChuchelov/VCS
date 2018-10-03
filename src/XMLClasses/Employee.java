
package XMLClasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hiredateOrSalaryOrJobtitle"
})
@XmlRootElement(name = "employee")
public class Employee {

    @XmlAttribute(name = "firstname", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String firstname;
    @XmlAttribute(name = "secondname", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String secondname;
    @XmlElements({
        @XmlElement(name = "hiredate", required = true, type = Hiredate.class),
        @XmlElement(name = "salary", required = true, type = Salary.class),
        @XmlElement(name = "jobtitle", required = true, type = Jobtitle.class)
    })
    protected List<Object> hiredateOrSalaryOrJobtitle;

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

    /**
     * Gets the value of the hiredateOrSalaryOrJobtitle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hiredateOrSalaryOrJobtitle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHiredateOrSalaryOrJobtitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hiredate }
     * {@link Salary }
     * {@link Jobtitle }
     * 
     * 
     */
    public List<Object> getHiredateOrSalaryOrJobtitle() {
        if (hiredateOrSalaryOrJobtitle == null) {
            hiredateOrSalaryOrJobtitle = new ArrayList<Object>();
        }
        return this.hiredateOrSalaryOrJobtitle;
    }

}
