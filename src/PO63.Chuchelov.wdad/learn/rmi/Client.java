package PO63.Chuchelov.wdad.learn.rmi;

import PO63.Chuchelov.wdad.data.managers.PreferencesManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.REGISTRYADDRESS;
import static PO63.Chuchelov.wdad.utils.PreferencesManagerConstants.REGISTRYPORT;

public class Client {
    public static void main(String[] args) {
        PreferencesManager pm = PreferencesManager.getInstance();
        try {
            Registry registry = LocateRegistry.getRegistry(pm.getProperty(REGISTRYADDRESS),
                    Integer.parseInt(pm.getProperty(REGISTRYPORT)));
            XmlDataManager dataManager = (XmlDataManager) registry.lookup(
                    pm.getBindedObjectName("PO63.Chuchelov.wdad.learn.rmi.XmlDataManager"));

            System.out.println(dataManager.salaryAverage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
