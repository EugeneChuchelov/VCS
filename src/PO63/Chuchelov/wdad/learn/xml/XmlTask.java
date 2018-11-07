package PO63.Chuchelov.wdad.learn.xml;

import java.io.*;

import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Organization;

import javax.xml.bind.*;


public class XmlTask {
    private Organization organization;
    private String filepath;

    public XmlTask(String filepath) {
        this.filepath = filepath;
        System.setProperty("javax.xml.accessExternalDTD", "all");
        Unmarshal();
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

    public int salaryAverage(){
        int sum = 0;
        int count = 0;

        for (Department department : organization.getDepartment() ){
            sum += department.getSalaryTotal();
            count += department.getSize();
        }

        return sum/count;
    }

    public int salaryAverage(String departmentName){
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

    public void setJobTitile(String firstName, String secondName, JobtitleEnum newJobTitle){
        int index;

        for (Department department : organization.getDepartment() ){
            index = department.findEmployee(firstName, secondName);
            if(index != -1){
                department.getEmployee().get(index).setJobtitle(newJobTitle);
            }
        }

        Marshal(organization);
    }

    public void setSalary(String firstName, String secondName, int newSalary){
        int index;

        for (Department department : organization.getDepartment() ){
            index = department.findEmployee(firstName, secondName);
            if(index != -1){
                department.getEmployee().get(index).setSalary(newSalary);
            }
        }

        Marshal(organization);
    }

    public void fireEmployee(String firstName, String secondName){
        int index;

        for (Department department : organization.getDepartment() ){
            index = department.findEmployee(firstName, secondName);
            if(index != -1){
                department.getEmployee().remove(index);
            }
        }

        Marshal(organization);
    }
}
