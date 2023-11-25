package server.obj;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mandelbrot extends Remote {

    void addTask(final Point p) throws RemoteException;

    void addResult(final Task t) throws RemoteException;

    Task getTask() throws RemoteException;
}