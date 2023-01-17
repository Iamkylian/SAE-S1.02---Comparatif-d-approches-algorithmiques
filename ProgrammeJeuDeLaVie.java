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
                ProgrammeMatriceEntier.setElement(grilleCourante, i, j, 0);
            }
        }
    }

    public int nbVoisinsVivants(int i, int j) throws Exception {
        int nbVoisins = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x != i || y != j) {
                    if (ProgrammeMatriceEntier.getElement(grilleCourante, x, y) == 1) {
                        nbVoisins++;
                    }
                }
            }
        }
        return nbVoisins;
    }

    public boolean seraVivante(int i, int j) throws Exception {
        int nbVoisins = nbVoisinsVivants(i, j);
        if (ProgrammeMatriceEntier.getElement(grilleCourante, i, j) == 1) {
            if (nbVoisins == 2 || nbVoisins == 3) {
                return true;
            }
        } else {
            if (nbVoisins == 3) {
                return true;
            }
        }
        return false;
    }

    public void calculerGenerationSuivante() throws Exception {
        MatriceEntier grilleSuivante = new MatriceEntier(taille + 2, taille + 2);
        for (int i = 0; i < taille + 2; i++) {
            for (int j = 0; j < taille + 2; j++) {
                if (seraVivante(i, j)) {
                    ProgrammeMatriceEntier.setElement(grilleSuivante, i, j, 1);
                } else {
                    ProgrammeMatriceEntier.setElement(grilleSuivante, i, j, 0);
                }
            }
        }
        historiqueGrilles[generation] = grilleCourante;
        grilleCourante = grilleSuivante;
    }

    public boolean grilleConnue() throws Exception {
        for (int i = 0; i < generation; i++) {
            boolean identique = true;
            for (int j = 0; j < taille + 2; j++) {
                for (int k = 0; k < taille + 2; k++) {
                    if (ProgrammeMatriceEntier.getElement(historiqueGrilles[i], j, k) != ProgrammeMatriceEntier
                            .getElement(grilleCourante, j, k)) {
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisir la taille de la grille : ");
        int taille = sc.nextInt();
        JeuDeLaVie grilleVie = new JeuDeLaVie(taille);
        // Boucle pour jouer le jeu de la vie
        while (grilleVie.generation < 10) {
            grilleVie.calculerGenerationSuivante();
            if (grilleVie.grilleConnue()) {
                System.out.println("La même grille est apparue, arrêt des générations.");
                break;
            }
            grilleVie.generation++;
        }

        // Production du fichier HTML
        produireFichierHTML();
    }

}