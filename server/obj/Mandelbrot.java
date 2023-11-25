package server.obj;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mandelbrot extends Remote {

    void addTask(Point p) throws RemoteException;

    void addResult(Task t) throws RemoteException;

    Task getTask() throws RemoteException;
}