package PO63.Chuchelov.wdad.learn.xml;

import java.io.*;

import XMLClasses.*;

import javax.xml.bind.*;


public class XmlTask {
    //private Organization organization;
    private String filepath;

    public XmlTask(String filepath) {
        this.filepath = filepath;
        System.setProperty("javax.xml.accessExternalDTD", "all");
    }

    private void Marshal(Organization organization){
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new FileOutputStream(filepath);
            marshaller.marshal(organization, os);
            os.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    private Organization Unmarshal(){
        Organization organization = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class);
            InputStream is = new FileInputStream(filepath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            organization = (Organization) unmarshaller.unmarshal(is);
            is.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        return organization;
    }

    public int salaryAverage(){
        Organization organization = Unmarshal();

        int sum = 0;
        int count = 0;
        Salary salary;

        for (Department department : organization.getDepartment() ){
            //TODO IT HAVE TO BE in Department methods - size & salaryTotal
            for(Employee employee : department.getEmployee()){
                salary = (Salary) employee.getHiredateOrSalaryOrJobtitle().get(1);
                sum += salary.getvalue();
                count++;
            }
        }

        return sum/count;
    }

    public int salaryAverage(String departmentName){
        Organization organization = Unmarshal();

        int sum = 0;
        int count = 0;
        Salary salary;

        for (Department department : organization.getDepartment() ){
            if(department.getName().equals(departmentName)){
                //TODO IT HAVE TO BE in Department methods - size & salaryTotal
                for(Employee employee : department.getEmployee()){
                    salary = (Salary) employee.getHiredateOrSalaryOrJobtitle().get(1);
                    sum += salary.getvalue();
                    count++;
                }
                break;
            }
        }

        return sum/count;
    }

    public void setJobTitile(String firstName, String secondName, String newJobTitle){
        Organization organization = Unmarshal();
        Jobtitle jobtitle;

        outerCycle: for (Department department : organization.getDepartment() ){
            //TODO IT HAVE TO BE in Department methods - boolean hasEmployee(sname, fname) | findEmployee(sname, fname), setJobtitle()
            for(Employee employee : department.getEmployee()){
                if(employee.getFirstname().equals(firstName) &&
                        employee.getSecondname().equals(secondName)){
                    jobtitle = (Jobtitle) employee.getHiredateOrSalaryOrJobtitle().get(2);
                    jobtitle.setValue(newJobTitle);
                    break outerCycle;
                }
            }
        }

        Marshal(organization);
    }

    public void setSalary(String firstName, String secondName, int newSalary){
        Organization organization = Unmarshal();
        Salary salary;

        outerCycle: for (Department department : organization.getDepartment() ){
            //TODO IT HAVE TO BE in Department methods
            for(Employee employee : department.getEmployee()){
                if(employee.getFirstname().equals(firstName) &&
                        employee.getSecondname().equals(secondName)){
                    salary = (Salary) employee.getHiredateOrSalaryOrJobtitle().get(2);
                    salary.setvalue(newSalary);
                    break outerCycle;
                }
            }
        }

        Marshal(organization);
    }

    public void fireEmployee(String firstName, String secondName){
        Organization organization = Unmarshal();

        outerCycle: for (Department department : organization.getDepartment() ){
            //TODO IT HAVE TO BE in Department methods
            for(Employee employee : department.getEmployee()){
                if(employee.getFirstname().equals(firstName) &&
                        employee.getSecondname().equals(secondName)){
                    department.getEmployee().remove(employee);
                    break outerCycle;
                }
            }
        }

        Marshal(organization);
    }
}
