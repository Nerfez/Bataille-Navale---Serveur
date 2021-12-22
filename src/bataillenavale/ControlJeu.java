package bataillenavale;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 *
 * @author Zefren
 */
public class ControlJeu extends JPanel implements ActionListener {

    // Lien de terminaison d'association
    private ClientHandler clientSock1;

    // Attributs
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 60;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static int DELAY = 75;

    private int tabMissileX[] = new int[100];
    private int tabMissileY[] = new int[100];
    private int compteurMissile = 0;

    // bateau de 2
    int bateau1X, bateau1X2;
    int bateau1Y, bateau1Y2;

    // bateau de 3
    int bateau2X, bateau2X2, bateau2X3;
    int bateau2Y, bateau2Y2, bateau2Y3;

    int bateau3X, bateau3X2, bateau3X3;
    int bateau3Y, bateau3Y2, bateau3Y3;

    // bateau de 4
    int bateau4X, bateau4X2, bateau4X3, bateau4X4;
    int bateau4Y, bateau4Y2, bateau4Y3, bateau4Y4;

    // bateau de 5
    int bateau5X, bateau5X2, bateau5X3, bateau5X4, bateau5X5;
    int bateau5Y, bateau5Y2, bateau5Y3, bateau5Y4, bateau5Y5;

    // On place les bateaux dans un tableau
    int TableauBateaux_Horizontaux[] = new int[17];
    int TableauBateaux_Verticaux[] = new int[17];

    // missiles envoyés
    int Missiles_Horizontaux[] = new int[17];
    int Missiles_Verticaux[] = new int[17];

    int compteurMissilesTouchés = 0;

    boolean running = false;
    static boolean gameOn = false;
    Timer timer;
    Random random;
    boolean text = true;

    private boolean bateau2enVie = true, bateau3BenVie = true, bateau3AenVie = true, bateau4enVie = true, bateau5enVie = true;

    ControlJeu() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        startGame();

    }

    public void startGame() {
        newBateaux();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * On dessine les lignes du plateau avec un fond noir
     * ainsi que les bateaux par des cercles rouges
     * @param g
     */
    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            g.setColor(Color.red);
            // bateau 2
            g.fillOval(bateau1X, bateau1Y, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau1X2, bateau1Y2, UNIT_SIZE, UNIT_SIZE);

            // bateau 3
            g.fillOval(bateau2X, bateau2Y, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau2X2, bateau2Y2, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau2X3, bateau2Y3, UNIT_SIZE, UNIT_SIZE);

            g.fillOval(bateau3X, bateau3Y, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau3X2, bateau3Y2, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau3X3, bateau3Y3, UNIT_SIZE, UNIT_SIZE);

            // bateau 4
            g.fillOval(bateau4X, bateau4Y, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau4X2, bateau4Y2, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau4X3, bateau4Y3, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau4X4, bateau4Y4, UNIT_SIZE, UNIT_SIZE);

            // bateau 5
            g.fillOval(bateau5X, bateau5Y, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau5X2, bateau5Y2, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau5X3, bateau5Y3, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau5X4, bateau5Y4, UNIT_SIZE, UNIT_SIZE);
            g.fillOval(bateau5X5, bateau5Y5, UNIT_SIZE, UNIT_SIZE);

        }
    }

    // Méthodes permettant la création de bateaux de tailles spécifiques
    
    public void Créer_Bateau_2_Horizontal() {
        bateau1X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau1Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau1X2 = (bateau1X + UNIT_SIZE);
        bateau1Y2 = (bateau1Y);
    }

    public void Créer_Bateau_3_a_Horizontal() {
        bateau2X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau2Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau2X2 = (bateau2X + UNIT_SIZE);
        bateau2Y2 = (bateau2Y);

        bateau2X3 = (bateau2X2 + UNIT_SIZE);
        bateau2Y3 = (bateau2Y);
    }

    public void Créer_Bateau_3_b_Horizontal() {
        bateau3X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau3Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau3X2 = (bateau3X + UNIT_SIZE);
        bateau3Y2 = (bateau3Y);

        bateau3X3 = (bateau3X2 + UNIT_SIZE);
        bateau3Y3 = (bateau3Y);
    }

    public void Créer_Bateau_4_Horizontal() {
        bateau4X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau4Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau4X2 = (bateau4X + UNIT_SIZE);
        bateau4Y2 = (bateau4Y);

        bateau4X3 = (bateau4X2 + UNIT_SIZE);
        bateau4Y3 = (bateau4Y);

        bateau4X4 = (bateau4X3 + UNIT_SIZE);
        bateau4Y4 = (bateau4Y);
    }

    public void Créer_Bateau_5_Horizontal() {
        bateau5X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau5Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau5X2 = (bateau5X + UNIT_SIZE);
        bateau5Y2 = (bateau5Y);

        bateau5X3 = (bateau5X2 + UNIT_SIZE);
        bateau5Y3 = (bateau5Y);

        bateau5X4 = (bateau5X3 + UNIT_SIZE);
        bateau5Y4 = (bateau5Y);

        bateau5X5 = (bateau5X4 + UNIT_SIZE);
        bateau5Y5 = (bateau5Y);
    }

    public void Créer_Bateau_2_Vertical() {
        bateau1X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau1Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau1X2 = (bateau1X);
        bateau1Y2 = (bateau1Y + UNIT_SIZE);
    }

    public void Créer_Bateau_3_a_Vertical() {
        bateau2X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau2Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau2X2 = (bateau2X);
        bateau2Y2 = (bateau2Y + UNIT_SIZE);

        bateau2X3 = (bateau2X2);
        bateau2Y3 = (bateau2Y2 + UNIT_SIZE);
    }

    public void Créer_Bateau_3_b_Vertical() {
        bateau3X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau3Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau3X2 = (bateau3X);
        bateau3Y2 = (bateau3Y + UNIT_SIZE);

        bateau3X3 = (bateau3X2);
        bateau3Y3 = (bateau3Y2 + UNIT_SIZE);
    }

    public void Créer_Bateau_4_Vertical() {
        bateau4X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau4Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau4X2 = (bateau4X);
        bateau4Y2 = (bateau4Y + UNIT_SIZE);

        bateau4X3 = (bateau4X2);
        bateau4Y3 = (bateau4Y2 + UNIT_SIZE);

        bateau4X4 = (bateau4X3);
        bateau4Y4 = (bateau4Y3 + UNIT_SIZE);
    }

    public void Créer_Bateau_5_Vertical() {
        bateau5X = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        bateau5Y = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

        bateau5X2 = (bateau5X);
        bateau5Y2 = (bateau5Y + UNIT_SIZE);

        bateau5X3 = (bateau5X2);
        bateau5Y3 = (bateau5Y2 + UNIT_SIZE);

        bateau5X4 = (bateau5X3);
        bateau5Y4 = (bateau5Y3 + UNIT_SIZE);

        bateau5X5 = (bateau5X4);
        bateau5Y5 = (bateau5Y4 + UNIT_SIZE);
    }

    /**
     * Création de manière aléatoire de nos
     * bateaux compris dans notre plateau
     * de jeu et sans possibilités de se toucher
     */
    public void newBateaux() {

        while (checkCollisions() == true) {
            do {

                // bateau 2
                int DirectionBateau = random.nextInt(2) + 1;

                //Horizontal
                if (DirectionBateau == 1) {
                    Créer_Bateau_2_Horizontal();
                }

                // Vertical
                if (DirectionBateau == 2) {
                    Créer_Bateau_2_Vertical();
                }

                // bateau 3 - a 
                DirectionBateau = random.nextInt(2) + 1;

                //Horizontal
                if (DirectionBateau == 1) {
                    Créer_Bateau_3_a_Horizontal();
                }

                // Vertical
                if (DirectionBateau == 2) {
                    Créer_Bateau_3_a_Vertical();
                }

                // bateau 3 - b
                DirectionBateau = random.nextInt(2) + 1;

                //Horizontal
                if (DirectionBateau == 1) {
                    Créer_Bateau_3_b_Horizontal();
                }

                // Vertical
                if (DirectionBateau == 2) {
                    Créer_Bateau_3_b_Vertical();
                }

                // bateau 4
                DirectionBateau = random.nextInt(2) + 1;

                //Horizontal
                if (DirectionBateau == 1) {
                    Créer_Bateau_4_Horizontal();
                }

                // Vertical
                if (DirectionBateau == 2) {
                    Créer_Bateau_4_Vertical();
                }

                // bateau 5
                DirectionBateau = random.nextInt(2) + 1;

                //Horizontal
                if (DirectionBateau == 1) {
                    Créer_Bateau_5_Horizontal();
                }

                // Vertical
                if (DirectionBateau == 2) {
                    Créer_Bateau_5_Vertical();
                }
            } while (bateau1X2 >= 575 || bateau2X3 >= 575 || bateau3X3 >= 575 || bateau4X4 >= 575 || bateau5X5 >= 575 || bateau1Y2 >= 575 || bateau2Y3 >= 575 || bateau3Y3 >= 575
                    || bateau4Y4 >= 575 || bateau5Y5 >= 575);
        }
    }

    /**
     * Scrutation collisions entre bateaux
     * @return
     */
    public boolean checkCollisions() {
        boolean validite = false;

        // Correspond au bateau 2
        TableauBateaux_Horizontaux[0] = bateau1X;
        TableauBateaux_Horizontaux[1] = bateau1X2;
        TableauBateaux_Verticaux[0] = bateau1Y;
        TableauBateaux_Verticaux[1] = bateau1Y2;

        // Correspond au bateau 3 - a
        TableauBateaux_Horizontaux[2] = bateau2X;
        TableauBateaux_Horizontaux[3] = bateau2X2;
        TableauBateaux_Horizontaux[4] = bateau2X3;
        TableauBateaux_Verticaux[2] = bateau2Y;
        TableauBateaux_Verticaux[3] = bateau2Y2;
        TableauBateaux_Verticaux[4] = bateau2Y3;

        // Correspond au bateau 3 - b
        TableauBateaux_Horizontaux[5] = bateau3X;
        TableauBateaux_Horizontaux[6] = bateau3X2;
        TableauBateaux_Horizontaux[7] = bateau3X3;
        TableauBateaux_Verticaux[5] = bateau3Y;
        TableauBateaux_Verticaux[6] = bateau3Y2;
        TableauBateaux_Verticaux[7] = bateau3Y3;

        // Correspond au bateau 4
        TableauBateaux_Horizontaux[8] = bateau4X;
        TableauBateaux_Horizontaux[9] = bateau4X2;
        TableauBateaux_Horizontaux[10] = bateau4X3;
        TableauBateaux_Horizontaux[11] = bateau4X4;
        TableauBateaux_Verticaux[8] = bateau4Y;
        TableauBateaux_Verticaux[9] = bateau4Y2;
        TableauBateaux_Verticaux[10] = bateau4Y3;
        TableauBateaux_Verticaux[11] = bateau4Y4;

        // Correspond au bateau 5
        TableauBateaux_Horizontaux[12] = bateau5X;
        TableauBateaux_Horizontaux[13] = bateau5X2;
        TableauBateaux_Horizontaux[14] = bateau5X3;
        TableauBateaux_Horizontaux[15] = bateau5X4;
        TableauBateaux_Horizontaux[16] = bateau5X5;
        TableauBateaux_Verticaux[12] = bateau5Y;
        TableauBateaux_Verticaux[13] = bateau5Y2;
        TableauBateaux_Verticaux[14] = bateau5Y3;
        TableauBateaux_Verticaux[15] = bateau5Y4;
        TableauBateaux_Verticaux[16] = bateau5Y5;

        // Scrutation bateau 
        for (int i = 0; i < TableauBateaux_Verticaux.length; i++) {
            for (int j = 0; j < TableauBateaux_Verticaux.length; j++) {
                if (TableauBateaux_Horizontaux[i] == TableauBateaux_Horizontaux[j] && TableauBateaux_Verticaux[i] == TableauBateaux_Verticaux[j] && j != i) {
                    validite = true;
                }
            }
        }

        return validite;

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (running) {
            checkCollisions();
        }
        repaint();

    }

    /**
     * On enregistre les coordoonnées du missile envoyé
     * et on vérifie qu'il ait touché ou non
     * ainsi que les bateaux coulés ou non
     * @param missileX
     * @param missileY
     */
    public void setMissileEnvoi(int missileX, int missileY) {
        this.tabMissileX[compteurMissile] = missileX * UNIT_SIZE;
        this.tabMissileY[compteurMissile] = missileY * UNIT_SIZE;
        boolean validite = checkCollisionsMissile(this.tabMissileX[compteurMissile], this.tabMissileY[compteurMissile]);
        if (validite == true) {
            clientSock1.ECRIRE("green");
        } else if (validite == false) {
            clientSock1.ECRIRE("red");
        }
        checkBateauxTués();
        compteurMissile++;

    }

    /**
     * Scrutation missile touché ?
     *
     * @param MissileX
     * @param MissileY
     * @return
     */
    public boolean checkCollisionsMissile(int MissileX, int MissileY) {

        boolean validite = false;

        // Scrutation bateau 
        for (int i = 0; i < TableauBateaux_Verticaux.length; i++) {
            if (MissileX == TableauBateaux_Horizontaux[i] && MissileY == TableauBateaux_Verticaux[i]) {
                validite = true;
                Missiles_Horizontaux[compteurMissilesTouchés] = MissileX;
                Missiles_Verticaux[compteurMissilesTouchés] = MissileY;
                compteurMissilesTouchés++;
            }
        }

        return validite;
    }

    /**
     * Scrutation des bateaux coulés
     */
    public void checkBateauxTués() {

        for (int i = 0; i < compteurMissilesTouchés; i++) {
            for (int j = 0; j < compteurMissilesTouchés; j++) {
                if (TableauBateaux_Horizontaux[0] == Missiles_Horizontaux[j] && TableauBateaux_Horizontaux[1] == Missiles_Horizontaux[i]
                        && TableauBateaux_Verticaux[0] == Missiles_Verticaux[j] && TableauBateaux_Verticaux[1] == Missiles_Verticaux[i]
                        && bateau2enVie == true && i != j) {
                    clientSock1.ECRIRE("bateau:2");
                    bateau2enVie = false;
                }
            }
        }   // fin scrutation bateau 2

        for (int i = 0; i < compteurMissilesTouchés; i++) {
            for (int k = 0; k < compteurMissilesTouchés; k++) {
                for (int j = 0; j < compteurMissilesTouchés; j++) {
                    if (TableauBateaux_Horizontaux[2] == Missiles_Horizontaux[j] && TableauBateaux_Horizontaux[3] == Missiles_Horizontaux[i]
                            && TableauBateaux_Horizontaux[4] == Missiles_Horizontaux[k] && TableauBateaux_Verticaux[2] == Missiles_Verticaux[j]
                            && TableauBateaux_Verticaux[3] == Missiles_Verticaux[i] && TableauBateaux_Verticaux[4] == Missiles_Verticaux[k]
                            && bateau3AenVie == true && i != j && i != k && j != k) {
                        clientSock1.ECRIRE("bateau:3a");
                        bateau3AenVie = false;
                    }
                }
            }
        }   // fin scrutation bateau 3 - a

        for (int i = 0; i < compteurMissilesTouchés; i++) {
            for (int k = 0; k < compteurMissilesTouchés; k++) {
                for (int j = 0; j < compteurMissilesTouchés; j++) {
                    if (TableauBateaux_Horizontaux[5] == Missiles_Horizontaux[j] && TableauBateaux_Horizontaux[6] == Missiles_Horizontaux[i]
                            && TableauBateaux_Horizontaux[7] == Missiles_Horizontaux[k] && TableauBateaux_Verticaux[5] == Missiles_Verticaux[j]
                            && TableauBateaux_Verticaux[6] == Missiles_Verticaux[i] && TableauBateaux_Verticaux[7] == Missiles_Verticaux[k]
                            && bateau3BenVie == true && i != j && i != k && j != k) {
                        clientSock1.ECRIRE("bateau:3b");
                        bateau3BenVie = false;
                    }
                }
            }
        }   // fin scrutation bateau 3 - b

        for (int i = 0; i < compteurMissilesTouchés; i++) {
            for (int l = 0; l < compteurMissilesTouchés; l++) {
                for (int k = 0; k < compteurMissilesTouchés; k++) {
                    for (int j = 0; j < compteurMissilesTouchés; j++) {
                        if (TableauBateaux_Horizontaux[8] == Missiles_Horizontaux[j] && TableauBateaux_Horizontaux[9] == Missiles_Horizontaux[i]
                                && TableauBateaux_Horizontaux[10] == Missiles_Horizontaux[l] && TableauBateaux_Horizontaux[11] == Missiles_Horizontaux[k]
                                && TableauBateaux_Verticaux[8] == Missiles_Verticaux[j] && TableauBateaux_Verticaux[9] == Missiles_Verticaux[i]
                                && TableauBateaux_Verticaux[10] == Missiles_Verticaux[l] && TableauBateaux_Verticaux[11] == Missiles_Verticaux[k]
                                && bateau4enVie == true && i != j && i != k && j != k && l != i && l != j && l != k) {
                            clientSock1.ECRIRE("bateau:4");
                            bateau4enVie = false;
                        }
                    }
                }
            }
        }   // fin scrutation bateau 4

        for (int i = 0; i < compteurMissilesTouchés; i++) {
            for (int t = 0; t < compteurMissilesTouchés; t++) {
                for (int l = 0; l < compteurMissilesTouchés; l++) {
                    for (int k = 0; k < compteurMissilesTouchés; k++) {
                        for (int j = 0; j < compteurMissilesTouchés; j++) {
                            if (TableauBateaux_Horizontaux[12] == Missiles_Horizontaux[j] && TableauBateaux_Horizontaux[13] == Missiles_Horizontaux[i]
                                    && TableauBateaux_Horizontaux[14] == Missiles_Horizontaux[l] && TableauBateaux_Horizontaux[15] == Missiles_Horizontaux[k]
                                    && TableauBateaux_Horizontaux[16] == Missiles_Horizontaux[t] && TableauBateaux_Verticaux[12] == Missiles_Verticaux[j]
                                    && TableauBateaux_Verticaux[13] == Missiles_Verticaux[i] && TableauBateaux_Verticaux[14] == Missiles_Verticaux[l]
                                    && TableauBateaux_Verticaux[15] == Missiles_Verticaux[k] && TableauBateaux_Verticaux[16] == Missiles_Verticaux[t]
                                    && bateau5enVie == true && i != j && i != k && j != k && l != i && l != j && l != k && t != k && t != j && t != l && t != i) {
                                clientSock1.ECRIRE("bateau:5");
                                bateau5enVie = false;
                            }
                        }
                    }
                }
            }
        }   // fin scrutation bateau 5

    }

    // référence sur notre lien
    public void setClient(ClientHandler clientSock1) {
        this.clientSock1 = clientSock1;
    }

}
