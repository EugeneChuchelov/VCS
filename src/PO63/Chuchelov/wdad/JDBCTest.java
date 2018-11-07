package PO63.Chuchelov.wdad;

import PO63.Chuchelov.wdad.data.managers.DataManager;
import PO63.Chuchelov.wdad.data.managers.JDBCDataManager;

import java.rmi.RemoteException;

public class JDBCTest {
    public static void main(String[] args) throws RemoteException {
        DataManager dm = new JDBCDataManager();
        System.out.println(dm.salaryAverage());
    }
}
