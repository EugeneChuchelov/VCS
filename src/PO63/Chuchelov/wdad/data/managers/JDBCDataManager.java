package PO63.Chuchelov.wdad.data.managers;

import PO63.Chuchelov.wdad.data.storage.DataSourceFactory;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;
import PO63.Chuchelov.wdad.utils.PreferencesManagerConstants;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDataManager implements DataManager {
    DataSource dataSource;
    String user;
    String pass;

    public JDBCDataManager() {
        dataSource = DataSourceFactory.createDataSource();
        PreferencesManager pm = PreferencesManager.getInstance();
        user = pm.getProperty(PreferencesManagerConstants.USER);
        pass = pm.getProperty(PreferencesManagerConstants.PASS);
    }

    @Override
    public int salaryAverage() throws RemoteException {
        Statement stmt;
        ResultSet rs;
        String query = "SELECT AVG(salary) FROM employees";
        try {
            Connection conn = dataSource.getConnection(user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            return Integer.parseInt(rs.getString(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int salaryAverage(String departmentName) throws RemoteException {
        return 0;
    }

    @Override
    public void setJobTitle(Employee employee, JobtitleEnum newJobTitle) throws RemoteException {

    }

    @Override
    public void setSalary(Employee employee, int newSalary) throws RemoteException {

    }

    @Override
    public void fireEmployee(Employee employee) throws RemoteException {

    }

    @Override
    public void add(Department department) throws RemoteException {

    }

    @Override
    public Employee getEmployee(String firstName, String secondName) throws RemoteException {
        return null;
    }
}
