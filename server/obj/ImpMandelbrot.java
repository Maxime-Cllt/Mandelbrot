package server.obj;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ImpMandelbrot extends UnicastRemoteObject implements Mandelbrot {

    public ArrayList<Point> dataToDo;
    public ArrayList<Task> taskDone;
    public int sizeOfTask;

    /**
     * Constructeur de la classe ImpMandelbrot
     *
     * @throws RemoteException
     */
    public ImpMandelbrot() throws RemoteException {
        super();
        dataToDo = new ArrayList<>();
        taskDone = new ArrayList<>();
        sizeOfTask = -1;
    }

    /**
     * Fonction qui ajoute une tâche à traiter
     *
     * @param point Point à ajouter à la liste des tâches à traiter (dataToDo)
     * @throws RemoteException
     */
    public void addTask(Point point) throws RemoteException {
        dataToDo.add(point);
    }

    /**
     * Fonction qui retourne une tâche à traiter
     *
     * @return Task
     * @throws RemoteException
     */
    public Task getTask() throws RemoteException {
        sizeOfTask++;
        return (dataToDo.size() > sizeOfTask) ? new ImpTask(dataToDo.get(sizeOfTask)) : null;
    }


    /**
     * Fonction qui retourne le nombre de tâches à traiter
     *
     * @param task Task à ajouter à la liste des tâches traitées
     * @throws RemoteException
     */
    public void addResult(Task task) throws RemoteException {
        taskDone.add(task);
    }

}