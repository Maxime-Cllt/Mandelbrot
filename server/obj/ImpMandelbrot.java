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

    public int getMax() throws RemoteException {
        int max = 0;
        for (Task task : taskDone) {
            if (task.getDivergence() > max) {
                max = task.getDivergence();
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


//    public static void calculComplexite() throws IOException {
//        ArrayList<Integer> div = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            div.add(0);
//        }
//        for (Task t : bag.taskDone) {
//            div.set(t.getDivergence(), div.get(t.getDivergence()) + 1);
//        }
//        int j = 0;
//        // écrire dans un .csv
//        File csv = new File("Complexite.csv");
//        FileWriter fw = new FileWriter(csv);
//        for (Integer i : div) {
//            String line = "\"" +
//                    j +
//                    "\"" +
//                    ";" +
//                    "\"" +
//                    i +
//                    "\"" +
//                    ";" +
//                    "\n";
//            //System.out.println("nb de points avec une divergence de " + j + " est de " + i);
//            j++;
//            fw.write(line);
//        }
//        fw.close();
//    }


}