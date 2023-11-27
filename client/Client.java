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
            final String ip = "172.31.18.165";
            final String localIp = "localhost";
            Registry registry = LocateRegistry.getRegistry(ip, Registry.REGISTRY_PORT);
            Mandelbrot bafOfTask = (Mandelbrot) registry.lookup("Mandelbrot");

            String[] cmd = {"/bin/sh", "-c", "echo $PPID"};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            java.util.Scanner s = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
            System.out.println("Lancement du client avec PID: " + (s.hasNext() ? s.next() : "N/A"));

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
