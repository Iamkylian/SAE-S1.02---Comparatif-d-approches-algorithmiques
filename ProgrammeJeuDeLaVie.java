import java.util.Scanner;

public class ProgrammeJeuDeLaVie {

    public static void main(String[][] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Bienvenue dans le jeu de la vie");
            System.out.println("Veuillez saisir la taille de la matrice : ");
            int tailleX = sc.nextInt();
            int tailleY = sc.nextInt();
            try {
                JeuDeLaVie jeuV = new JeuDeLaVie(tailleX, tailleY);
                jeuV.afficher();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Fin du programme");
    }

    Scanner clavier = new Scanner(System.in);

    int tailleX = clavier.nextInt();
    int tailleY = clavier.nextInt();

    /**
     * @param tailleX : nombre de lignes
     * @param tailleY : nombre de colonnes
     * @return : l'état initial des cellules
     */
    public static int[][] etatInitial(int tailleX, int tailleY) {
        int[][] etatInitial = new int[tailleX][tailleY];
        return etatInitial;
    }

    public void afficher() {
        System.out.println("Taille X de la grille : " + tailleX);
        System.out.println("Taille Y de la grille : " + tailleY);
    }

    // La grille de cellules
    static boolean[][] grille;

    // La taille de la grille (nombre de lignes et de colonnes)
    int taille = 5;

    /**
     * Rôle : Initialise la grille avec une taille donnée et l'état initial des
     * cellules
     * 
     * 
     * @throws Exception : si les paramètres sont négatifs ou nuls
     */
    public static void creerJeuxDeLaVie(JeuDeLaVie m) throws Exception {
        // Initialise la grille avec une taille donnée et l'état initial des cellules

        if (m.tailleX == 0 || m.tailleY == 0) {
            throw new Exception("Pas de paramètres");
        }
        JeuDeLaVie jeuV = new JeuDeLaVie(10, 10);

        // Copie l'état initial dans la grille

        for (int i = 0; i < m.tailleX; i++) {

            for (int j = 0; j < m.tailleY; j++) {

                boolean[][] EtatInitial = new boolean[m.tailleX][m.tailleY];
                m.grille[i][j] = EtatInitial[i][j];
            }
        }
        jeuV.afficher();
    }

    // Met à jour l'état de chaque cellule en fonction des règles du jeu
    public static void mettreAJour(JeuDeLaVie jeu) {

        // Création d'une nouvelle grille pour stocker l'état mis à jour

        boolean[][] nouvelEtat = new boolean[jeu.tailleX][jeu.tailleY];

        // Parcours de chaque cellule de la grille actuelle

        for (int i = 0; i < jeu.tailleX; i++) {

            for (int j = 0; j < jeu.tailleY; j++) {

                // Comptage du nombre de cellules voisines vivantes

                int nbVoisinsVivants = 0;

                for (int x = -1; x <= 1; x++) {

                    for (int y = -1; y <= 1; y++) {

                        if (x == 0 && y == 0) {

                            continue; // On ne compte pas la cellule elle-même

                        }

                        int ligne = (i + x + jeu.tailleX) % jeu.tailleX;

                        int colonne = (j + y + jeu.tailleY) % jeu.tailleY;

                        if (jeu.grille[ligne][colonne]) {

                            nbVoisinsVivants++;

                        }

                    }

                }

                // Applique les règles du jeu

                if (jeu.grille[i][j] && (nbVoisinsVivants == 2 || nbVoisinsVivants == 3)) {

                    nouvelEtat[i][j] = true; // La cellule continue à vivre

                } else if (!jeu.grille[i][j] && nbVoisinsVivants == 3) {

                    nouvelEtat[i][j] = true; // La cellule naît

                } else {

                    nouvelEtat[i][j] = false; // La cellule meurt
                }
            }
        }
        // Copie le nouvel état dans la grille
        jeu.grille = nouvelEtat;
    }

    // Retourne l'état actuel de la grille
    public static boolean[][] getGrille(JeuDeLaVie jeu) {
        return grille;
    }

}