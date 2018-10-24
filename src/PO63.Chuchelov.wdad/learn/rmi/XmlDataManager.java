package PO63.Chuchelov.wdad.learn.rmi;

import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface XmlDataManager extends Remote {
    int salaryAverage() throws RemoteException;
    int salaryAverage(String departmentName) throws RemoteException;
    void setJobTitle(Employee employee, JobtitleEnum newJobTitle) throws RemoteException;
    void setSalary(Employee employee, int newSalary) throws RemoteException;
    void fireEmployee(Employee employee) throws RemoteException;
    void add(Department department) throws RemoteException;
    Employee getEmployee(String firstName, String secondName) throws RemoteException;
}
