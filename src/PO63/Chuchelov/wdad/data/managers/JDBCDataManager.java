package PO63.Chuchelov.wdad.data.managers;

import PO63.Chuchelov.wdad.data.storage.DataSourceFactory;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Jobtitle;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;
import PO63.Chuchelov.wdad.utils.PreferencesManagerConstants;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JDBCDataManager implements DataManager {
    private DataSource dataSource;

    public JDBCDataManager() {
        dataSource = DataSourceFactory.createDataSource();
        PreferencesManager pm = PreferencesManager.getInstance();
    }

    @Override
    public int salaryAverage() throws RemoteException {
        String query = "SELECT AVG(salary) FROM employees";
        return getSalary(query);
    }

    @Override
    public int salaryAverage(String departmentName) throws RemoteException {
        String query = "SELECT AVG(salary) FROM `employees` " +
                "INNER JOIN departments ON employees.departments_id = departments.id " +
                "WHERE departments.name = ?";
        //todo the same as:
        PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
        statement.setString(1, departmentName);

        return getSalary(query);
    }

    private int getSalary(String query){
        ResultSet rs;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){
            rs = stmt.executeQuery(query);
            rs.next();
            return (int) Double.parseDouble(rs.getString("AVG(salary)"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setJobTitle(Employee employee, JobtitleEnum newJobTitle) throws RemoteException {
        String query = "UPDATE employees SET jobtitles_id = (SELECT id FROM jobtitles " +
                                                            "WHERE name = \""+newJobTitle.name()+"\") " +
                "WHERE employees.first_name = \""+employee.getFirstname()+"\" " +
                "AND employees.second_name = \""+employee.getSecondname()+"\"";
        //todo Join-ишь employees и jobtitles по jobtitles_id и просто в Where еще условие WHERE jobtitles.name = ?
        update(query);
    }

    @Override
    public void setSalary(Employee employee, int newSalary) throws RemoteException {
        String query = "UPDATE employees SET salary = " + newSalary +
                "WHERE employees.first_name = \"" + employee.getFirstname()+"\" " +
                "AND employees.second_name = \""+employee.getSecondname()+"\"";
        update(query);
    }

    @Override
    public void fireEmployee(Employee employee) throws RemoteException {
        String query = "DELETE FROM employees " +
                "WHERE employees.first_name = \"" + employee.getFirstname()+"\" " +
                "AND employees.second_name = \""+employee.getSecondname()+"\"";
        update(query);
    }

    @Override
    public void add(Department department) throws RemoteException {
        String query = "INSERT INTO `departments` (`id`, `name`) " +
                "VALUES (NULL, '"+department.getName()+"')";
        update(query);
        for(Employee employee : department.getEmployee()){
            add(employee, department.getName());
        }
    }

    private void add(Employee employee, String dep){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder("INSERT INTO `employees` (`id`, `first_name`, `second_name`, `hire_date`, `salary`, `jobtitles_id`, `departments_id`) VALUES ");
        sb.append('(').append("NULL").append(",");
        sb.append('\'').append(employee.getFirstname()).append("\',");
        sb.append('\'').append(employee.getSecondname()).append("\',");
        sb.append('\'').append(ft.format(employee.getHiredate())).append("\',");
        sb.append('\'').append(employee.getSalary()).append("\',");
        sb.append('\'').append(getId(employee.getJobtitle())).append("\',");
        sb.append('\'').append(getId(dep)).append("\')");

        update(sb.toString());
    }

    private int getId(JobtitleEnum jobtitle){
        String query = "SELECT id FROM jobtitles\n" +
                "WHERE name = \""+jobtitle.toString()+"\"";
        return getInt(query);
    }

    private int getId(String name){
        String query = "SELECT id FROM departments\n" +
                "WHERE name = \""+name+"\"";
        return getInt(query);
    }

    private int getInt(String query) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){
            ResultSet rs;
            rs = stmt.executeQuery(query);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void update(String query){
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){
             stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployee(String firstName, String secondName) throws RemoteException {
        String query = "SELECT hire_date, salary, jobtitles.name " +
                "FROM employees OUTER JOIN jobtitles ON employees.jobtitles_id = jobtitles.id " +
                "WHERE first_name = \"" + firstName + "\" AND second_name = \"" + secondName + "\"";
        ResultSet rs;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){
            rs = stmt.executeQuery(query);
            rs.next();
            Date hireDate = rs.getDate("hire_date");
            int salary = (int) Double.parseDouble(rs.getString("salary"));
            JobtitleEnum jobtitle = JobtitleEnum.valueOf(rs.getString("name"));
            return new Employee(firstName, secondName, hireDate, salary, jobtitle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
