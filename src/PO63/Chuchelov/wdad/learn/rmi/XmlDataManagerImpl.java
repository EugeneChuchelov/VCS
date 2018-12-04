package PO63.Chuchelov.wdad.learn.rmi;

import PO63.Chuchelov.wdad.data.managers.DataManager;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Organization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XmlDataManagerImpl implements DataManager {
    private Organization organization;
    private String filepath;

    public XmlDataManagerImpl() {
        super();
    }

    public XmlDataManagerImpl(String filepath) {
        this.filepath = filepath;
        System.setProperty("javax.xml.accessExternalDTD", "all");
        Unmarshal();
    }

    @Override
    public int salaryAverage() {
        int sum = 0;
        int count = 0;

        for (Department department : organization.getDepartment() ){
            sum += department.getSalaryTotal();
            count += department.getSize();
        }

        return sum/count;
    }

    @Override
    public int salaryAverage(String departmentName) {
        int sum = 0;
        int count = 0;

        for (Department department : organization.getDepartment() ){
            if(department.getName().equals(departmentName)){
                sum += department.getSalaryTotal();
                count += department.getSize();
            }
        }

        return sum/count;
    }

    @Override
    public void setJobTitle(Employee employee, JobtitleEnum newJobTitle) {
        int index;

        for (Department department : organization.getDepartment() ){
            index = department.findEmployee(employee);
            if(index != -1){
                department.getEmployee().get(index).setJobtitle(newJobTitle);
            }
        }

        Marshal(organization);
    }

    @Override
    public void setSalary(Employee employee, int newSalary) {
        int index;
        for (Department department : organization.getDepartment() ){
            index = department.findEmployee(employee);
            if(index != -1){
                department.getEmployee().get(index).setSalary(newSalary);
                break;
            }
        }

        Marshal(organization);
    }

    @Override
    public void fireEmployee(Employee employee) {
        int index;

        for (Department department : organization.getDepartment() ){
            index = department.findEmployee(employee);
            if(index != -1){
                department.getEmployee().remove(index);
                break;
            }
        }

        Marshal(organization);
    }

    @Override
    public void add(Department department) {
        organization.getDepartment().add(department);

        Marshal(organization);
    }

    @Override
    public Employee getEmployee(String firstName, String secondName) {
        int index = 0;
        for(Department dp : organization.getDepartment()){
            index = dp.findEmployee(firstName, secondName);
            if(index != -1){
                return dp.getEmployee().get(index);
            }
        }
        return null;
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

    private void Unmarshal(){
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
    }
}
