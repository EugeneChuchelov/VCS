
package XMLClasses;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the XMLClasses package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: XMLClasses
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Jobtitle }
     * 
     */
    public Jobtitle createJobtitle() {
        return new Jobtitle();
    }

    /**
     * Create an instance of {@link Organization }
     * 
     */
    public Organization createOrganization() {
        return new Organization();
    }

    /**
     * Create an instance of {@link Department }
     * 
     */
    public Department createDepartment() {
        return new Department();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link Hiredate }
     * 
     */
    public Hiredate createHiredate() {
        return new Hiredate();
    }

    /**
     * Create an instance of {@link Salary }
     * 
     */
    public Salary createSalary() {
        return new Salary();
    }

}
