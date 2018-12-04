package PO63.Chuchelov.wdad.data.storage;

import PO63.Chuchelov.wdad.data.managers.PreferencesManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;

import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.*;

public class DataSourceFactory {
    private static PreferencesManager pm = PreferencesManager.getInstance();
    public static javax.sql.DataSource createDataSource(){
        try {
            Class.forName(pm.getProperty(CLASSNAME));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(pm.getProperty(HOST_NAME));
        ds.setPortNumber(Integer.parseInt(pm.getProperty(PORT)));
        ds.setDatabaseName(pm.getProperty(DBNAME));
        ds.setUser((pm.getProperty(USER)));
        ds.setPassword((pm.getProperty(PASS)));

        try {
            ds.setServerTimezone("UTC");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        try {

            String CONTEXT  = "com.sun.jndi.cosnaming.CNCtxFactory";
            String URL = "jdbc:mysql://127.0.0.1:3306/organization";
            String LOGIN    = "root";
            String PASSWORD = "";


            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT);
            //properties.put(Context.PROVIDER_URL           , URL);
            //properties.put(Context.SECURITY_PRINCIPAL     , LOGIN);
            //properties.put(Context.SECURITY_CREDENTIALS   , PASSWORD);
            InitialContext ic = new InitialContext(properties);

            ds = (DataSource)ic.lookup(URL.toString());

        } catch (NamingException e) {
            e.printStackTrace();
        }*/
        return ds;
    }

    public static javax.sql.DataSource createDataSource(String className, String
            driverType, String host, int port, String dbName, String user, String password){
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName(host);
        ds.setPortNumber(port);
        ds.setDatabaseName(dbName);
        ds.setUser(user);
        ds.setPassword(password);
        return ds;
    }
}
