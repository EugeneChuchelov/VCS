package PO63.Chuchelov.wdad.data.managers;

import PO63.Chuchelov.wdad.data.managers.appConfigClasses.Appconfig;
import PO63.Chuchelov.wdad.data.managers.appConfigClasses.Bindedobject;
import PO63.Chuchelov.wdad.data.managers.appConfigClasses.Client;
import PO63.Chuchelov.wdad.data.managers.appConfigClasses.Registry;
import PO63.Chuchelov.wdad.data.managers.appConfigClasses.Rmi;
import PO63.Chuchelov.wdad.data.managers.appConfigClasses.Server;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;
import java.util.Properties;

public class PreferencesManager {
    private static PreferencesManager instance;
    private Appconfig appconfig;
    private Properties properties = new Properties();

    private PreferencesManager(){
        appconfig = Unmarshal();

        properties.put("appconfig.rmi.server.registry.createregistry",
                appconfig.getRmi().getServer().getRegistry().getCreateregistry());
        properties.put("appconfig.rmi.server.registry.registryaddress",
                appconfig.getRmi().getServer().getRegistry().getRegistryaddress());
        properties.put("appconfig.rmi.server.registry.registryport",
                String.valueOf(appconfig.getRmi().getServer().getRegistry().getRegistryport()));
        properties.put("appconfig.rmi.client.policypath", appconfig.getRmi().getClient().getPolicypath());
        properties.put("appconfig.rmi.client.usecodebaseonly", appconfig.getRmi().getClient().getUsecodebaseonly());
        properties.put("appconfig.rmi.classprovider", appconfig.getRmi().getClassprovider());

    }

    public static PreferencesManager getInstance(){
        if(instance == null){
            instance = new PreferencesManager();
        }
        return instance;
    }

    private void MarshalProperties(){
        appconfig.getRmi().getServer().getRegistry().setCreateregistry(
                properties.getProperty("appconfig.rmi.server.registry.createregistry"));
        appconfig.getRmi().getServer().getRegistry().setRegistryaddress(
                properties.getProperty("appconfig.rmi.server.registry.registryaddress"));
        appconfig.getRmi().getServer().getRegistry().setRegistryport(
                Integer.parseInt(properties.getProperty("appconfig.rmi.server.registry.registryport")));
        appconfig.getRmi().getClient().setPolicypath(
                properties.getProperty("appconfig.rmi.client.policypath"));
        appconfig.getRmi().getClient().setUsecodebaseonly(
                properties.getProperty("appconfig.rmi.client.usecodebaseonly"));
        appconfig.getRmi().setClassprovider(
                properties.getProperty("appconfig.rmi.classprovider"));
        Marshal();
    }

    @Deprecated
    public Appconfig getAppConfig(){
        return appconfig;
    }

    @Deprecated
    public void setAppConfig(Appconfig appconfig){
        Marshal();
    }

    @Deprecated
    public Rmi getRmi(){
        return appconfig.getRmi();
    }

    @Deprecated
    public void setRmi(Rmi rmi){
        appconfig.setRmi(rmi);
        Marshal();
    }

    @Deprecated
    public Server getServer(){
        return appconfig.getRmi().getServer();
    }

    @Deprecated
    public void setServer(Server server){
        appconfig.getRmi().setServer(server);
        Marshal();
    }

    @Deprecated
    public Registry getRegistry(){
        return appconfig.getRmi().getServer().getRegistry();
    }

    @Deprecated
    public void setRegistry(Registry registry){
        appconfig.getRmi().getServer().setRegistry(registry);
        Marshal();
    }

    @Deprecated
    public Client getClient(){
        return appconfig.getRmi().getClient();
    }

    @Deprecated
    public void setClient(Client client){
        appconfig.getRmi().setClient(client);
        Marshal();
    }

    @Deprecated
    public String getCreateRegistry(){
        return appconfig.getRmi().getServer().getRegistry().getCreateregistry();
    }

    @Deprecated
    public void setCreateRegistry(String createregistry){
        appconfig.getRmi().getServer().getRegistry().setCreateregistry(createregistry);
        Marshal();
    }

    @Deprecated
    public String getRegistryAddress(){
        return appconfig.getRmi().getServer().getRegistry().getRegistryaddress();
    }

    @Deprecated
    public void setRegistryAddress(String registryaddress){
        appconfig.getRmi().getServer().getRegistry().setRegistryaddress(registryaddress);
        Marshal();
    }

    @Deprecated
    public int getRegistryPort(){
        return appconfig.getRmi().getServer().getRegistry().getRegistryport();
    }

    @Deprecated
    public void setRegistryPort(int registryport){
        appconfig.getRmi().getServer().getRegistry().setRegistryport(registryport);
        Marshal();
    }

    @Deprecated
    public List<Bindedobject> getBindedObject(){
        return appconfig.getRmi().getServer().getBindedobject();
    }

    @Deprecated
    public void setBindedObject(List<Bindedobject> bindedobject){
        appconfig.getRmi().getServer().setBindedobject(bindedobject);
        Marshal();
    }

    @Deprecated
    public String getPolicyPath(){
        return appconfig.getRmi().getClient().getPolicypath();
    }

    @Deprecated
    public void setPolicyPath(String policypath){
        appconfig.getRmi().getClient().setPolicypath(policypath);
        Marshal();
    }

    @Deprecated
    public String getUseCodeBaseOnly(){
        return appconfig.getRmi().getClient().getUsecodebaseonly();
    }

    @Deprecated
    public void setUseCodeBaseOnly(String usecodebaseonly){
        appconfig.getRmi().getClient().setUsecodebaseonly(usecodebaseonly);
        Marshal();
    }

    @Deprecated
    public String getClassProvider(){
        return appconfig.getRmi().getClassprovider();
    }

    @Deprecated
    public void setClassProvider(String classprovider){
        appconfig.getRmi().setClassprovider(classprovider);
        Marshal();
    }

    public void setProperty(String key, String value){
        properties.replace(key, value);
        MarshalProperties();
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }
    public void setProperties(Properties prop){
        for(Object key : prop.keySet()){
            properties.replace((String)key, (String)prop.get(key));
        }
    }
    public Properties getProperties(){
        return properties;
    }
    public void addBindedObject(String name, String className){
        appconfig.getRmi().getServer().addBindedObject(name, className);
        Marshal();
    }
    public void removeBindedObject(String name){
        appconfig.getRmi().getServer().removeBindedObject(name);
        Marshal();
    }

    private void Marshal(){
        try {
            JAXBContext context = JAXBContext.newInstance(Appconfig.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            
            OutputStream os = new FileOutputStream("src/PO63.Chuchelov.wdad/resources.configuration/appconfig.xml");
            marshaller.marshal(appconfig, os);
            os.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    private Appconfig Unmarshal(){
        Appconfig appconfig = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Appconfig.class);
            InputStream is = new FileInputStream("src/PO63.Chuchelov.wdad/resources.configuration/appconfig.xml");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            appconfig = (Appconfig) unmarshaller.unmarshal(is);
            is.close();
        }
        catch (JAXBException e) {e.printStackTrace();}
        catch (FileNotFoundException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}

        return appconfig;
    }
}
