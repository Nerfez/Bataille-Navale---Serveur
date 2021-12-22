package bataillenavale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.in;
import static java.lang.System.out;
import java.net.Socket;
import java.util.Random;

public class ClientHandler implements Runnable {

    // Liens de terminaisons d'associations
    private final Socket clientSocket;
    private Jeu jeu;

    // attributs 
    private String message = "";
    private String IP;

    // Constructeur
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * Permet de gérer l'envoi / la reception des messages entre le serveur et
     * le client
     */
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {

            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {

                System.out.printf(" Sent from the client: %s\n", line);
                out.println(line);

                if (IP.equals("127.0.0.1")) {
                    if (line.startsWith("missile")) {
                        String reponse = line.substring(7, line.length());
                        jeu.setMissile(reponse);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void set_IP(String ip) {
        this.IP = ip;
    }

    /**
     * Permet d'écrire un message à un client
     *
     * @param message
     */
    public void ECRIRE(String message) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // référence à notre lien
    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

}
