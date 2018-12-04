
package PO63.Chuchelov.wdad.learn.xml.XMLClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "employee"
})
@XmlRootElement(name = "department")
public class Department implements Serializable {

    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlElement(required = true)
    protected List<Employee> employee;

    public Department() {
    }

    public Department(String name, List<Employee> employee) {
        this.name = name;
        this.employee = employee;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the employee property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employee property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployee().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Employee }
     * 
     * 
     */
    public List<Employee> getEmployee() {
        if (employee == null) {
            employee = new ArrayList<Employee>();
        }
        return this.employee;
    }

    public int getSize(){
        return getEmployee().size();
    }

    public int getSalaryTotal(){
        int salary = 0;
        for(Employee employee : getEmployee()){
            salary += employee.getSalary();
        }
        return salary;
    }

    public int findEmployee(String firstName, String secondName) {
        int index = 0;

        for(Employee employee : getEmployee()){
            if(employee.getFirstname().equals(firstName) &&
                    employee.getSecondname().equals(secondName)){
                return index;
            }
            else {
                index++;
            }
        }
        return -1;
    }

    public int findEmployee(Employee emp){
        int index = 0;

        for(Employee employee : getEmployee()){
            if(employee.equals(emp)){
                return index;
            }
            else {
                index++;
            }
        }
        return -1;
    }

}
