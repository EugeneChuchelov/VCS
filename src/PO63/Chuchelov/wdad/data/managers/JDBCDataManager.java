package PO63.Chuchelov.wdad.data.managers;

import PO63.Chuchelov.wdad.data.storage.DataSourceFactory;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Department;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.Employee;
import PO63.Chuchelov.wdad.learn.xml.XMLClasses.JobtitleEnum;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.sql.*;
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
        ResultSet rs;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()){
            rs = stmt.executeQuery(query);
            rs.next();
            return (int) Double.parseDouble(rs.getString("AVG(salary)"));
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public int salaryAverage(String departmentName) throws RemoteException {
        String query = "SELECT AVG(salary) FROM `employees` " +
                "INNER JOIN departments ON employees.departments_id = departments.id " +
                "WHERE departments.name = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, departmentName);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return (int) Double.parseDouble(rs.getString("AVG(salary)"));
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public void setJobTitle(Employee employee, JobtitleEnum newJobTitle) throws RemoteException {
        String query = "UPDATE employees SET jobtitles_id = (SELECT id FROM jobtitles " +
                                                            "WHERE name = ?) " +
                "WHERE employees.first_name = ? " +
                "AND employees.second_name = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, newJobTitle.name());
            statement.setString(2, employee.getFirstname());
            statement.setString(3, employee.getSecondname());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSalary(Employee employee, int newSalary) throws RemoteException {
        String query = "UPDATE employees SET salary = ? " +
                "WHERE employees.first_name = ? AND employees.second_name = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setInt(1, newSalary);
            statement.setString(2, employee.getFirstname());
            statement.setString(3, employee.getSecondname());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fireEmployee(Employee employee) throws RemoteException {
        String query = "DELETE FROM employees " +
                "WHERE employees.first_name = ? " +
                "AND employees.second_name = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, employee.getFirstname());
            statement.setString(2, employee.getSecondname());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Department department) throws RemoteException {
        String query = "INSERT INTO `departments` (`id`, `name`) " +
                "VALUES (NULL, ?)";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, department.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Employee employee : department.getEmployee()){
            add(employee, department.getName());
        }
    }

    private void add(Employee employee, String dep){
        String query = "INSERT INTO `employees` (`id`, `first_name`, `second_name`, " +
                "`birth_date`, `hire_date`, `salary`, `jobtitles_id`, `departments_id`) VALUES " +
                "(NULL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, employee.getFirstname());
            statement.setString(2, employee.getSecondname());
            statement.setDate(3, (java.sql.Date) employee.getBirthdate());
            statement.setDate(4, (java.sql.Date) employee.getHiredate());
            statement.setInt(5, employee.getSalary());
            statement.setInt(6, getId(employee.getJobtitle()));
            statement.setInt(7, getId(dep));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getId(JobtitleEnum jobtitle){
        String query = "SELECT id FROM jobtitles WHERE name = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, jobtitle.name());
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int getId(String name){
        String query = "SELECT id FROM departments WHERE name = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, name);
            statement.executeUpdate();
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Employee getEmployee(String firstName, String secondName) throws RemoteException {
        String query = "SELECT hire_date, salary, jobtitles.name " +
                "FROM employees JOIN jobtitles ON employees.jobtitles_id = jobtitles.id " +
                "WHERE employees.first_name = ? AND employees.second_name = ?";
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Date hireDate = rs.getDate("hire_date");
            int salary = (int) Double.parseDouble(rs.getString("salary"));
            JobtitleEnum jobtitle = JobtitleEnum.valueOf(rs.getString("name"));
            return new Employee(firstName, secondName, hireDate, salary, jobtitle);
        } catch (SQLException e) {
            return null;
        }
    }
}
