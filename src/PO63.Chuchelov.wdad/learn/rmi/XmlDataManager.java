package PO63.Chuchelov.wdad.learn.rmi;

import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface XmlDataManager extends Remote {
    public int salaryAverage() throws RemoteException;
    public int salaryAverage(String departmentName) throws RemoteException;
    public void setJobTitile(Employee employee, JobtitleEnum newJobTitle) throws RemoteException;
    public void setSalary(Employee employee, int newSalary) throws RemoteException;
    public void fireEmployee(Employee employee) throws RemoteException;
    public void add(Department department) throws RemoteException;
}
