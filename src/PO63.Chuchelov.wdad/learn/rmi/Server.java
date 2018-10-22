package PO63.Chuchelov.wdad.learn.rmi;

import PO63.Chuchelov.wdad.data.managers.PreferencesManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.*;

public class Server {
    public static void main(String[] args) {
        //String localhost    = "127.0.0.1";
        //String RMI_HOSTNAME = "java.rmi.server.hostname";
        //System.setProperty(RMI_HOSTNAME, localhost);
        //String CODEBASE_URL = "file:///F:\\Documents\\GitHub\\starting-monkey-to-human-path\\out\\production\\starting-monkey-to-human-path\\PO63\\Chuchelov\\wdad\\learn\\";
        //String CODEBASE_URL =  "file://F:\\Documents\\GitHub\\starting-monkey-to-human-path\\out\\production\\starting-monkey-to-human-path\\PO63\\Chuchelov\\wdad\\learn\\xml\\XMLClasses";
        //System.setProperty("java.rmi.server.codebase", CODEBASE_URL);


        PreferencesManager pm = PreferencesManager.getInstance();
        System.setSecurityManager(new SecurityManager());
        try {
            XmlDataManagerImpl obj = new XmlDataManagerImpl("F:\\Documents\\GitHub\\starting-monkey-to-human-path\\src\\PO63.Chuchelov.wdad\\learn\\xml\\CF.xml");
            XmlDataManager stub = (XmlDataManager) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry;

            if(pm.getProperty(CREATEREGISTRY).equals("yes")){
                registry = LocateRegistry.createRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            } else {
                registry = LocateRegistry.getRegistry(Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            }
            registry.rebind("XmlDataManager", stub);
            /*pm.addBindedObject("XmlDataManager", "PO63.Chuchelov.wdad.learn.rmi.XmlDataManager");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }*/
            System.out.println("Server is online (probably)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
