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

public class PreferencesManager {
    private static PreferencesManager instance;
    //todo DONE Appconfig appconfig
    private Appconfig appconfig;
    private PreferencesManager(){
        appconfig = Unmarshal();
        //todo DONE one time unmurshal
    }

    public static PreferencesManager getInstance(){
        if(instance == null){
            instance = new PreferencesManager();
        }
        return instance;
    }

    public Appconfig getAppConfig(){
        return appconfig;
    }

    public void setAppConfig(Appconfig appconfig){
        Marshal(appconfig);
    }

    public Rmi getRmi(){
        return appconfig.getRmi();
    }

    public void setRmi(Rmi rmi){
        appconfig.setRmi(rmi);
        Marshal(appconfig);
    }

    public Server getServer(){
        return appconfig.getRmi().getServer();
    }

    public void setServer(Server server){
        appconfig.getRmi().setServer(server);
        Marshal(appconfig);
    }

    public Registry getRegistry(){
        return appconfig.getRmi().getServer().getRegistry();
    }

    public void setRegistry(Registry registry){
        appconfig.getRmi().getServer().setRegistry(registry);
        Marshal(appconfig);
    }

    public Client getClient(){
        return appconfig.getRmi().getClient();
    }

    public void setClient(Client client){
        appconfig.getRmi().setClient(client);
        Marshal(appconfig);
    }

    public String getCreateRegistry(){
        return appconfig.getRmi().getServer().getRegistry().getCreateregistry();
    }

    public void setCreateRegistry(String createregistry){
        appconfig.getRmi().getServer().getRegistry().setCreateregistry(createregistry);
        Marshal(appconfig);
    }

    public String getRegistryAddress(){
        return appconfig.getRmi().getServer().getRegistry().getRegistryaddress();
    }

    public void setRegistryAddress(String registryaddress){
        appconfig.getRmi().getServer().getRegistry().setRegistryaddress(registryaddress);
        Marshal(appconfig);
    }

    public int getRegistryPort(){
        return appconfig.getRmi().getServer().getRegistry().getRegistryport();
    }

    public void setRegistryPort(int registryport){
        appconfig.getRmi().getServer().getRegistry().setRegistryport(registryport);
        Marshal(appconfig);
    }

    public Bindedobject getBindedObject(){
        return appconfig.getRmi().getServer().getBindedobject();
    }

    public void setBindedObject(Bindedobject bindedobject){
        appconfig.getRmi().getServer().setBindedobject(bindedobject);
        Marshal(appconfig);
    }

    public String getPolicyPath(){
        return appconfig.getRmi().getClient().getPolicypath();
    }

    public void setPolicyPath(String policypath){
        appconfig.getRmi().getClient().setPolicypath(policypath);
        Marshal(appconfig);
    }

    public String getUseCodeBaseOnly(){
        return appconfig.getRmi().getClient().getUsecodebaseonly();
    }

    public void setUseCodeBaseOnly(String usecodebaseonly){
        appconfig.getRmi().getClient().setUsecodebaseonly(usecodebaseonly);
        Marshal(appconfig);
    }

    public String getClassProvider(){
        return appconfig.getRmi().getClassprovider();
    }

    public void setClassProvider(String classprovider){
        appconfig.getRmi().setClassprovider(classprovider);
        Marshal(appconfig);
    }

    private void Marshal(Appconfig appconfig){
        try {
            JAXBContext context = JAXBContext.newInstance(Appconfig.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
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
