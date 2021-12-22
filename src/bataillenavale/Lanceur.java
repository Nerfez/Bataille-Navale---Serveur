package bataillenavale;

/**
 *
 * @author Zefren
 */
public class Lanceur {

    /**
     * @param args the command line arguments
     * Initialisation de notre ihm pour lancer le serveur
     * ainsi que notre Socket serveur qui va gérer les ips
     * entrantes
     */
    public static void main(String[] args) {
        IHMServeur ihm = new IHMServeur();
        ServeurConnexion serveurConnexion = new ServeurConnexion();
        serveurConnexion.setIHM(ihm);
        ihm.setServeurConnexion(serveurConnexion);
        ihm.setVisible(true);
    }
    
}
