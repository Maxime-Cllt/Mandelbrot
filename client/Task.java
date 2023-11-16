package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Task extends Remote {

    void run() throws RemoteException;

    Point getPoint_a_traiter() throws RemoteException;

    int getDivergence() throws RemoteException;
}