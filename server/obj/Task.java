package server.obj;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Task extends Remote {

    void run() throws RemoteException;

    Point getPointToDo() throws RemoteException;

    int getDivergence() throws RemoteException;
}