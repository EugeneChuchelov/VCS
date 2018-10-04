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
    //todo Appconfig appconfig
    private PreferencesManager(){
        //todo one time unmurshal
    }

    public static PreferencesManager getInstance(){
        if(instance == null){
            instance = new PreferencesManager();
        }
        return instance;
    }

    public Appconfig getAppconfing(){
        return Unmarshal();
    }

    public void setAppconfig(Appconfig appconfig){
        Marshal(appconfig);
    }

    public Rmi getRmi(){
        return Unmarshal().getRmi();
    }

    public void setRmi(Rmi rmi){
        Appconfig appconfig = Unmarshal();
        appconfig.setRmi(rmi);
        Marshal(appconfig);
    }

    public Server getServer(){
        return Unmarshal().getRmi().getServer();
    }

    public void setServer(Server server){
        Appconfig appconfig = Unmarshal();
        appconfig.getRmi().setServer(server);
        Marshal(appconfig);
    }

    public Registry getRegistry(){
        Appconfig appconfig = Unmarshal();
        return (Registry)appconfig.getRmi().getServer().getRegistryOrBindedobject().get(0);
    }

    public void setRegistry(Registry registry){
        Appconfig appconfig = Unmarshal();
        Registry reg = (Registry)appconfig.getRmi().getServer().getRegistryOrBindedobject().get(0);
        reg = registry;
        Marshal(appconfig);
    }

    public Client getClient(){
        return Unmarshal().getRmi().getClient();
    }

    public void setClient(Client client){
        Appconfig appconfig = Unmarshal();
        appconfig.getRmi().setClient(client);
        Marshal(appconfig);
    }

    public String getCreateregistry(){
        Registry reg = (Registry) Unmarshal().getRmi().getServer().getRegistryOrBindedobject().get(0);
        return reg.getCreateregistry();
    }

    public void setCreateregistry(String createregistry){
        Appconfig appconfig = Unmarshal();
        appconfig.getRmi().getServer().getRegistryOrBindedobject().set(0, createregistry);
        Marshal(appconfig);
    }

    public String getRegistryaddress(){
        Registry reg = (Registry) Unmarshal().getRmi().getServer().getRegistryOrBindedobject().get(0);
        return reg.getRegistryaddress();
    }

    public void setRegistryaddress(String registryaddress){
        Appconfig appconfig = Unmarshal();
        Registry reg = (Registry) appconfig.getRmi().getServer().getRegistryOrBindedobject().get(0);
        reg.setRegistryaddress(registryaddress);
        Marshal(appconfig);
    }

    public int getRegistryport(){
        Registry reg = (Registry) Unmarshal().getRmi().getServer().getRegistryOrBindedobject().get(0);
        return reg.getRegistryport();
    }

    public void setRegistryport(int registryport){
        Appconfig appconfig = Unmarshal();
        Registry reg = (Registry) appconfig.getRmi().getServer().getRegistryOrBindedobject().get(0);
        reg.setRegistryport(registryport);
        Marshal(appconfig);
    }

    public Bindedobject getBindedobject(){
        return (Bindedobject) Unmarshal().getRmi().getServer().getRegistryOrBindedobject().get(1);
    }

    public void setBindedobject(Bindedobject bindedobject){
        Appconfig appconfig = Unmarshal();
        appconfig.getRmi().getServer().getRegistryOrBindedobject().set(1, bindedobject);
        Marshal(appconfig);
    }

    public String getpolicypath(){
        return Unmarshal().getRmi().getClient().getPolicypath();
    }

    public void setpolicypath(String policypath){
        Appconfig appconfig = Unmarshal();
        appconfig.getRmi().getClient().setPolicypath(policypath);
        Marshal(appconfig);
    }

    public String getusecodebaseonly(){
        return Unmarshal().getRmi().getClient().getUsecodebaseonly();
    }

    public void setusecodebaseonly(String usecodebaseonly){
        Appconfig appconfig = Unmarshal();
        appconfig.getRmi().getClient().setUsecodebaseonly(usecodebaseonly);
        Marshal(appconfig);
    }

    public String getclassprovider(){
        return Unmarshal().getRmi().getClassprovider();
    }

    public void setclassprovider(String classprovider){
        Appconfig appconfig = Unmarshal();
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
