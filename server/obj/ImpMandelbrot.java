package server.obj;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;


public class ImpMandelbrot extends UnicastRemoteObject implements Mandelbrot {

    public LinkedList<Point> dataToDo;
    public LinkedList<Task> taskDone;
    public int sizeOfTask;

    /**
     * Constructeur de la classe ImpMandelbrot
     *
     * @throws RemoteException
     */
    public ImpMandelbrot() throws RemoteException {
        super();
        dataToDo = new LinkedList<>();
        taskDone = new LinkedList<>();
        sizeOfTask = 0;
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

    public int getMax() throws RemoteException {
        int max = 0;
        for (Point point : dataToDo) {
            if (point.getDivergence() > max) {
                max = point.getDivergence();
            }
        }
        return max;
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