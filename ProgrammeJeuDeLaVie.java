import java.util.Scanner;

public class ProgrammeJeuDeLaVie {
    public static void main(String[] args) {
        try {
            JeuDeLaVie jeuV = new JeuDeLaVie(10, 10);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Scanner clavier = new Scanner(System.in);

    int tailleX = clavier.nextInt();
    int tailleY = clavier.nextInt();

    /**
     * Role :
     * 
     * @param pftailleX : nombre de lignes
     * @param pftailleY : nombre de colonnes
     */
    public ProgrammeJeuDeLaVie(int pftailleX, int pftailleY) {
        this.tailleX = pftailleX;
        this.tailleY = pftailleY;
    }

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
        System.out.println("Taille X: " + tailleX);
        System.out.println("Taille Y: " + tailleY);
    }

    // La grille de cellules
    boolean[][] grille;


    // La taille de la grille (nombre de lignes et de colonnes)
    int taille = 5;


    // Initialise la grille avec une taille donnée et l'état initial des cellules


    /**
     * Rôle : Initialise la grille avec une taille donnée et l'état initial des
     * cellules
     * 
     * @throws Exception : si les paramètres sont négatifs ou nuls
     */
    public static void creerJeuxDeLaVie(int tailleX, int tailleY) throws Exception {
        if (tailleX == 0 || tailleY == 0) {
            throw new Exception("Pas de paramètres");
        }
        JeuDeLaVie jeuV = new JeuDeLaVie(10, 10);
        // Copie l'état initial dans la grille
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = etatInitial[i][j];
            }
        }
        jeuV.afficher();
    }

    // Met à jour l'état de chaque cellule en fonction des règles du jeu
    public static void mettreAJour() {
        boolean[][] nouvelEtat = new boolean[taille][taille];

        for (int i = 0; i < taille ; i++) {
            for (int j = 0; j < taille ; j++) {
                // Compte le nombre de cellules voisines vivantes
                int voisinsVivants = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x == 0 && y == 0) {
                            continue; // On ne compte pas la cellule elle-même
                        }

                        int l = (i + x + taille) % taille;
                        int c = (j + y + taille) % taille;

                        if (grille[l][c]) {
                            voisinsVivants++;
                        }
                    }
                }

                // Applique les règles du jeu
                if (grille[i][j] && (voisinsVivants == 2 || voisinsVivants == 3)) {
                    nouvelEtat[i][j] = true; // La cellule continue à vivre
                } else if (!grille[i][j] && voisinsVivants == 3) {
                    nouvelEtat[i][j] = true; // La cellule naît
                } else {
                    nouvelEtat[i][j] = false; // La cellule meurt
                }
            }
        }

        // Copie le nouvel état dans la grille
        grille = nouvelEtat;
    }

}