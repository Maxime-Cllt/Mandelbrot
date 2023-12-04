package client;

import server.obj.Mandelbrot;
import server.obj.Task;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe Client qui va se connecter au serveur et récupérer les données à traiter
 *
 * args[0] : ip du serveur
 *
 * ex: java client.Client localhost
 */
public class Client {

    private static final AtomicBoolean run = new AtomicBoolean(true);
    private static final String localIp = "localhost";


    public static void main(String[] args) {

        try {

            ArrayList<String> arrArgs = new ArrayList<>(Arrays.asList(args));
            String ip;

            if (arrArgs.size() == 1) ip = arrArgs.get(0);
            else ip = localIp;

            // On récupère la liste des données à traiter
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
            System.err.println(c.getMessage());
            c.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            run.set(false);
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
