package PO63.Chuchelov.wdad.learn.rmi;

import PO63.Chuchelov.wdad.data.managers.PreferencesManager;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.REGISTRYADDRESS;
import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.REGISTRYPORT;

public class Client {
    public static void main(String[] args) {
        PreferencesManager pm = PreferencesManager.getInstance();

        Department dept = new Department();
        dept.setName("Another one");
        System.setSecurityManager(new SecurityManager());

        try {
            Registry registry = LocateRegistry.getRegistry(pm.getProperty(REGISTRYADDRESS),
                    Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            XmlDataManager dataManager = (XmlDataManager) registry.lookup(
                    pm.getBindedObjectName("PO63.Chuchelov.wdad.learn.rmi.XmlDataManager"));

            System.out.println(dataManager.salaryAverage());
            System.out.println(dataManager.salaryAverage("Managing"));
            dataManager.setJobTitle(dataManager.getEmployee("Igor", "Teleastrov"), JobtitleEnum.head);
            dataManager.setSalary(dataManager.getEmployee("Igor", "Teleastrov"), 54000);
            System.out.println(dataManager.salaryAverage("Managing"));
            Employee emp = dataManager.getEmployee("Igor", "Teleastrov");
            dataManager.fireEmployee(dataManager.getEmployee("Igor", "Teleastrov"));
            dept.getEmployee().add(emp);
            dataManager.add(dept);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
