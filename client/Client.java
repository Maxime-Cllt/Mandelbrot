package client;

import server.obj.Mandelbrot;
import server.obj.Task;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe Client qui va se connecter au serveur et récupérer les données à traiter
 * ex: java client.Client
 */
public class Client {

    private static final AtomicBoolean run = new AtomicBoolean(true);

    public static void main(String[] args) {

        try {
            // On récupère la liste des données à traiter
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Mandelbrot bafOfTask = (Mandelbrot) registry.lookup("Mandelbrot");

            String[] cmd = {"/bin/sh", "-c", "echo $PPID"};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
            String pid = s.hasNext() ? s.next() : "N/A";
            System.out.println("Lancement du client avec PID: " + pid);

            Task task;
            while (run.get()) {
                task = bafOfTask.getTask();
                // Si il y a une tache a execute on la traite sinon, on attend
                if (task != null) {
                    task.run();
                    bafOfTask.addResult(task);
                } else {
                    Thread.sleep(1000);
                }
            }
        } catch (java.rmi.ConnectException c) {
            run.set(false);
            System.out.println("\u001B[31mLe serveur a mis fin à la connection\u001B[0m");
            System.exit(0);
        } catch (Exception e) {
            run.set(false);
            e.printStackTrace();
            System.exit(0);
        }
    }
}
