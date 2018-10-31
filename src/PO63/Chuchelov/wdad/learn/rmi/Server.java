package PO63.Chuchelov.wdad.learn.rmi;

import PO63.Chuchelov.wdad.data.managers.PreferencesManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.*;

public class Server {
    static final String FILEPATH = "F:\\Documents\\GitHub\\starting-monkey-to-human-path\\src\\PO63\\Chuchelov\\wdad\\learn\\xml\\CF.xml";
    static final String SECURITY_FILE_PATH = "F:\\Documents\\GitHub\\starting-monkey-to-human-path\\src\\PO63\\Chuchelov\\wdad\\learn\\rmi\\policy";
    static final String APPCONFIG_PATH = "F:\\Documents\\GitHub\\starting-monkey-to-human-path\\src\\" +
            "PO63\\Chuchelov\\wdad\\resources\\configuration\\appconfig.xml";
    static final String CLASS_NAME = "PO63.Chuchelov.wdad.learn.rmi.XmlDataManager";
    public static void main(String[] args) {
        PreferencesManager pm = PreferencesManager.getInstance(APPCONFIG_PATH);
        System.setProperty("java.security.policy", pm.getProperty(POLICYPATH));
        System.setSecurityManager(new SecurityManager());
        try {
            XmlDataManagerImpl obj = new XmlDataManagerImpl(FILEPATH); //todo fix ALL pathos
            XmlDataManager stub = (XmlDataManager) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry;

            if(pm.getProperty(CREATEREGISTRY).equals("yes")){
                registry = LocateRegistry.createRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            } else {
                registry = LocateRegistry.getRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            }
            registry.bind("XmlDataManager", obj);
            if(pm.getBindedObjectName(CLASS_NAME) == null){
                pm.addBindedObject("XmlDataManager", CLASS_NAME);
            }
            System.out.println("Server is online (probably)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
