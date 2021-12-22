package bataillenavale;

import java.net.Socket;
import javax.swing.JFrame;

/**
 *
 * @author Zefren
 */
public class Jeu extends JFrame {

    // Liens de terminaisons d'associations
    private ControlJeu controljeu;
    private ServeurConnexion serveurConnexion;
    private Socket socket1;
    private ClientHandler clientSock1;

    // attributs
    
    private int missileX, missileY;

    // Constructeur
    Jeu(ServeurConnexion serveurConnexion) {
        this.serveurConnexion = serveurConnexion;
        this.add(controljeu = new ControlJeu());
        this.setTitle("Bataille Navale - Jeu | Serveur v1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    // Méthodes

    /**
     * On enregistre les coordoonnées des missiles envoyés
     * @param reponse
     */
    public void setMissile(String reponse) {
        this.missileX = Integer.valueOf(reponse.substring(0, reponse.length() - 2));
        this.missileY = Integer.valueOf(reponse.substring(2, reponse.length()));
        controljeu.setMissileEnvoi(missileX, missileY);
    }

    /**
     * On fait connaître à notre ihm de jeu l'ip de notre client
     * afin de communiquer avec lui, si on dispose d'autres clients
     * il suffit de réitérer les lignes ci-dessous en incrémentant le
     * tableau que l'on reçoit du getSocket()
     */
    public void Initialisation() {
        socket1 = serveurConnexion.getSocket()[0];
        clientSock1 = new ClientHandler(socket1);
        controljeu.setClient(clientSock1);
    }

}
