package PO63.Chuchelov.wdad;

import PO63.Chuchelov.wdad.data.managers.DataManager;
import PO63.Chuchelov.wdad.data.managers.JDBCDataManager;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class JDBCTest {
    public static void main(String[] args) throws RemoteException, ParseException {
        DataManager dm = new JDBCDataManager();
        //System.out.println(dm.salaryAverage());
        //System.out.println(dm.salaryAverage("First Engineering"));
        Employee f = dm.getEmployee("Igor", "Teleastrov");
        dm.setJobTitle(f, JobtitleEnum.head);
        //dm.fireEmployee(f);

        /*
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        Date f = ft.parse("11-05-1211");
        Date s = ft.parse("29-04-1015");
        System.out.println(ft.format(f));
        Employee first = new Employee("Svetlogor", "Dianatov", f, 125000, JobtitleEnum.manager);
        Employee second = new Employee("Yaropolk", "Remnev", s, 201000, JobtitleEnum.manager);
        ArrayList<Employee> fds = new ArrayList<>();
        fds.add(first);
        fds.add(second);
        Department dep = new Department("Defencing", fds);
        dm.add(dep);
        */
    }
}
