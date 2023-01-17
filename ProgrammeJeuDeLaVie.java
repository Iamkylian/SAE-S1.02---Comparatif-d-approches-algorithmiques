import java.util.Scanner;
import java.util.Random;

public class ProgrammeJeuDeLaVie {

    int taille;
    int generation;
    MatriceEntier grilleCourante;
    MatriceEntier[] historiqueGrilles;

    public ProgrammeJeuDeLaVie(int taille) throws Exception {
        this.taille = taille;
        this.generation = 0;
        this.grilleCourante = new MatriceEntier(taille + 2, taille + 2);
        this.historiqueGrilles = new MatriceEntier[10];

        // Initialisation de la grille de jeu de la vie
        for (int i = 0; i < taille + 2; i++) {
            for (int j = 0; j < taille + 2; j++) {
                grilleCourante.tabMat[i][j] = 0;
            }
        }
    }

    public static int nbVoisinsVivants(int i, int j) throws Exception {
        int nbVoisins = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x != i || y != j) {
                    if (grilleCourante.tabMat[x][y] == 1) {
                        nbVoisins++;
                    }
                }
            }
        }
        return nbVoisins;
    }

    public static boolean estVivante(int i, int j) throws Exception {
        return grilleCourante.tabMat[i][j] == 1;
    }

    public static boolean seraVivante(int i, int j) throws Exception {
        int n = nbVoisinsVivants(i, j);
        boolean estVivante = estVivante(i, j);
        if (estVivante) {
            return (n == 2 || n == 3);
        } else {
            return (n == 3);
        }
    }

    public static void calculerGenerationSuivante() throws Exception {
        MatriceEntier temp = new MatriceEntier(taille + 2, taille + 2);
        for (int i = 0; i < taille + 2; i++) {
            for (int j = 0; j < taille + 2; j++) {
                if (seraVivante(i, j)) {
                    temp.tabMat[i][j] = 1;
                } else {
                    temp.tabMat[i][j] = 0;
                }
            }
        }
        grilleCourante = temp;
        historiqueGrilles[generation] = temp;
    }

    public static boolean grilleConnue() throws Exception {
        for (int i = 0; i < generation; i++) {
            boolean identique = true;
            for (int j = 0; j < taille + 2; j++) {
                for (int k = 0; k < taille + 2; k++) {
                    if (grilleCourante.tabMat[j][k] != historiqueGrilles[i].tabMat[j][k]) {
                        identique = false;
                        break;
                    }
                }
                if (!identique) {
                    break;
                }
            }
            if (identique) {
                return true;
            }
        }
        return false;
    }

    public static void produireFichierHTML() {
        // Code pour produire un fichier HTML contenant les différentes générations
    }

    public static void main(String[] args) throws Exception {
        // Demande à l'utilisateur de saisir la taille de la grille
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez la taille de la grille : ");
        int taille = scanner.nextInt();

        // Initialisation de la grille de jeu de la vie
        ProgrammeJeuDeLaVie jeu = new ProgrammeJeuDeLaVie(taille);

        // Boucle pour calculer les générations suivantes
        while (true) {
            calculerGenerationSuivante();
            generation++;
            // Condition pour stopper les générations si la grille courante est identique à
            // une grille précédente
            if (grilleConnue()) {
                System.out.println("La grille s'est stabilisée après " + generation + " générations.");
                break;
            }
            // Condition pour stopper les générations après 10 générations
            if (generation == 10) {
                System.out.println("Le jeu s'est arrêté après 10 générations.");
                break;
            }
        }
        // Appel de la fonction pour produire un fichier HTML
        produireFichierHTML();
    }

}
