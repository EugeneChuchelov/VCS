package PO63.Chuchelov.wdad.data.storage;

import PO63.Chuchelov.wdad.data.managers.PreferencesManager;
import PO63.Chuchelov.wdad.utils.PreferencesManagerConstants;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

public class DataSourceFactory {
    private static PreferencesManager pm = PreferencesManager.getInstance();
    public static javax.sql.DataSource createDataSource(){
        StringBuilder url = new StringBuilder("jdbc:mysql://");
        url.append(pm.getProperty(PreferencesManagerConstants.HOST_NAME)).append(":");
        url.append(pm.getProperty(PreferencesManagerConstants.PORT)).append("/");
        url.append(pm.getProperty(PreferencesManagerConstants.DBNAME));
        try {
            Class.forName(pm.getProperty(PreferencesManagerConstants.CLASSNAME));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DataSource ds = null;
        try {

            String CONTEXT  = "com.sun.jndi.cosnaming.CNCtxFactory";
            String URL = "jdbc:mysql:thin:@localhost:3306:organization";
            String LOGIN    = "root";
            String PASSWORD = "";


            Properties properties = new Properties();

            properties.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT);
            properties.put(Context.PROVIDER_URL           , URL);
            properties.put(Context.SECURITY_PRINCIPAL     , LOGIN);
            properties.put(Context.SECURITY_CREDENTIALS   , PASSWORD);
            InitialContext ic = new InitialContext(properties);
            ds = (DataSource)ic.lookup(url.toString());

        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public static javax.sql.DataSource createDataSource(String className, String
            driverType, String host, int port, String dbName, String user, String password){
        StringBuilder url = new StringBuilder("jdbc:mysql://");
        url.append(host).append(":");
        url.append(port).append("/");
        url.append(dbName);
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DataSource ds = null;
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource)ic.lookup(url.toString());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
    }
}
