package bataillenavale;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Zefren
 */
public class ServeurConnexion extends Thread {

    // Liens de terminaisons d'associations
    
    private IHMServeur ihm;
    private Jeu jeu;
    
    Socket client; 
    
    
    // attributs
    
    // ici on a un tableau de 12 connexion mais on en utilise qu'une seule
    private String[] IP = new String[12];
    private Socket[] socket = new Socket[12];
    int compteurIP = 0;
    
    // le port ne doit PAS être compris entre 0 et ~1024 /!\
    // pour éviter les confusions ouvrir un port 
    // entre 1030 - 5000 qui peut convenir
    int port = 2000;

    // Méthodes

    /**
     * On lance le serveur sur le port d'écoute 2000
     * tous les clients peuvent se connecter à l'ip du pc qui héberge
     * le serveur
     */
    
    @Override
    public void run() {
        
        ServerSocket server = null;
  
        try {
  
            // le serveur est en ecoute sur le port 2000
            server = new ServerSocket(port);
            server.setReuseAddress(true);
  
            // ici on attends 1 seule connexion client
            // mais on peut très bien augmenter, c'est d'ailleurs
            // le but d'utiliser des multithreads comme ici
            while (compteurIP!=1) {
  
                // en attente qu'un client se connecte
                client = server.accept();
  
                // On affiche qu'un nouveau client s'est connecté
                ihm.setAffichage(client + " s'est connecté !\n");
                System.out.println("New client connected"
                                   + client.getInetAddress()
                                         .getHostAddress());
                
                IP[compteurIP] = client.getInetAddress()
                                         .getHostAddress();
                
                socket[compteurIP] = client;
  
                
                ClientHandler clientSock
                    = new ClientHandler(client);
  
                clientSock.set_IP(IP[compteurIP]);
                clientSock.setJeu(jeu);
                
                // on garde plusieurs connexions en même temps
                // grâce au multithread, encore une fois
                // dans ce projet nous n'en avons pas l'utilité car
                // on a qu'un seul client
                new Thread(clientSock).start();
                compteurIP++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Accesseurs
    
    public String [] getIP(){
        return IP;
    }

    public Socket[] getSocket() {
        return socket;
    }
    
    // références sur nos liens
    
    public void setIHM(IHMServeur ihm) {
        this.ihm = ihm;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

}
