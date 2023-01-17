import java.util.Scanner;

public class ProgrammeJeuDeLaVie {

    /** Rôle : permet de créer un jeu de la vie
     * @param args : tableau de chaînes de caractères composé de "0" et de "1"
     */
    public static void main(String[][] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("Bienvenue dans le jeu de la vie" + "\n" + " . . . ");

            System.out.println("Veuillez saisir la taille de la matrice : ");

            int tailleX = sc.nextInt();
            int tailleY = sc.nextInt();

            int[][] matrice = new int[tailleX][tailleY];

            for (int i = 0; i < tailleX; i++) {

                for (int j = 0; j < tailleY; j++) {

                    matrice[i][j] = sc.nextInt();
                }
            }
            try {

                JeuDeLaVie jeuV = new JeuDeLaVie(tailleX, tailleY);
                jeuV.afficher();
                creerJeuxDeLaVie(jeuV);

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        System.out.println("Fin du programme");
        sc.close();
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
    boolean[][] grille;

    // La taille de la grille (nombre de lignes et de colonnes)
    int taille = 5;


    /**
     * Rôle : Initialise la grille avec une taille donnée et l'état initial des
     * cellules
     * 
     * @param m : la grille de cellules
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
    public static boolean[][] getGrille(boolean[][] grille) {
        return grille;
    }

    public static int compterVoisins(MatriceEntier m, int i, int j) {
        int nbVoisins = 0;
        for (int x = -1; x <= 1; x++) {
           for (int y = -1; y <= 1; y++) {
              if (!(x == 0 && y == 0)) {
                 try {
                    int indiceLigne = (i + x + ProgrammeMatriceEntier.getNbLignes(m)) % ProgrammeMatriceEntier.getNbLignes(m);
                    int indiceColonne = (j + y + ProgrammeMatriceEntier.getNbColonnes(m)) % ProgrammeMatriceEntier.getNbColonnes(m);
                    nbVoisins += ProgrammeMatriceEntier.getElement(m, indiceLigne, indiceColonne);
                 } catch (Exception e) {
                    e.printStackTrace();
                 }
              }
           }
        }
        return nbVoisins;
     }
  
     public static void jouerTour(MatriceEntier m) {
         
         
        try {
           if (!ProgrammeMatriceEntier.estCarree(m)) {
              throw new Exception("La m n'est pas carrée");
           }

           int[][] MatriceSuivante = new int[ProgrammeMatriceEntier.getNbLignes(m)][ProgrammeMatriceEntier.getNbColonnes(m)];

           for (int i = 0; i < ProgrammeMatriceEntier.getNbLignes(m); i++) {

              for (int j = 0; j < ProgrammeMatriceEntier.getNbColonnes(m); j++) {

                 int nbVoisins = compterVoisins(m, i, j);

                 if (ProgrammeMatriceEntier.getElement(m, i, j) == 1) {

                    if (nbVoisins < 2 || nbVoisins > 3) {

                       MatriceSuivante[i][j] = 0;

                    } else {

                       MatriceSuivante[i][j] = 1;

                    }

                 } else {

                    if (nbVoisins == 3) {

                       MatriceSuivante[i][j] = 1;

                    } else {

                       MatriceSuivante[i][j] = 0;

                    }
                 }
              }
           }
           for (int i = 0; i < ProgrammeMatriceEntier.getNbLignes(m); i++) {

              for (int j = 0; j < ProgrammeMatriceEntier.getNbColonnes(m); j++) {

                 ProgrammeMatriceEntier.setElement(m, i, j, MatriceSuivante[i][j]);

              }
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
     }
  
     
  
}

