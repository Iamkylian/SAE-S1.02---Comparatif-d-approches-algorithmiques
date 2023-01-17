import java.util.Random;
import java.util.Scanner;

public class JeuDeLaVie {
    MatriceEntier grille;
    MatriceEntier grillePrecedente;
    int taille;
    int generation = 0;

    // Constructeur pour initialiser la grille
    public JeuDeLaVie(int taille) throws Exception {
        this.taille = taille;
        this.grille = new MatriceEntier(taille + 2, taille + 2);
        initialiserGrille();
    }

    // Fonction pour initialiser la grille avec des valeurs aléatoires
    public void initialiserGrille() {
        Random rand = new Random();
        for (int i = 0; i < taille + 2; i++) {
            for (int j = 0; j < taille + 2; j++) {
                grille.tabMat[i][j] = rand.nextInt(2);
            }
        }
    }

    // Fonction pour mettre à jour la grille en utilisant les règles du Jeu de la
    // vie
    public void mettreAJourGrille() throws Exception {
        int[][] temp = new int[taille + 2][taille + 2];
        for (int i = 1; i < taille + 1; i++) {
            for (int j = 1; j < taille + 1; j++) {
                int nbVoisins = ProgrammeMatriceEntier.somLigne(grille, i - 1)
                        + ProgrammeMatriceEntier.somLigne(grille, i + 1)
                        + ProgrammeMatriceEntier.getElement(grille, i - 1, j)
                        + ProgrammeMatriceEntier.getElement(grille, i + 1, j)
                        + ProgrammeMatriceEntier.getElement(grille, i, j - 1)
                        + ProgrammeMatriceEntier.getElement(grille, i, j + 1);
                // Mise à jour de la grille en utilisant les règles du Jeu de la vie
                if (grille.tabMat[i][j] == 0) {
                    if (nbVoisins == 3) {
                        temp[i][j] = 1;
                    } else {
                        temp[i][j] = 0;
                    }
                } else {
                    if (nbVoisins == 2 || nbVoisins == 3) {
                        temp[i][j] = 1;
                    } else {
                        temp[i][j] = 0;
                    }
                }
            }
        }
        grillePrecedente = grille;
        grille.tabMat = temp;
    }

    // Fonction pour afficher la grille
    public void afficherGrille() {
        for (int i = 0; i < taille + 2; i++) {
            for (int j = 0; j < taille + 2; j++) {
                if (grille.tabMat[i][j] == 1) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    // Fonction pour comparer les grilles
    public boolean compareGrilles() {
        for (int i = 0; i < taille + 2; i++) {
            for (int j = 0; j < taille + 2; j++) {
                if (grille.tabMat[i][j] != grillePrecedente.tabMat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public class JeuDeLaViePrincipal {
        public static void main(String[] args) throws Exception {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez la taille de la grille :");
            int taille = scan.nextInt();
            JeuDeLaVie jeu = new JeuDeLaVie(taille);
            while (true) {
                // Mise à jour de la grille
                jeu.mettreAJourGrille();
                // Affichage de la grille
                jeu.afficherGrille();
                // Incrémentation du compteur de génération
                jeu.generation++;
                Thread.sleep(1000);
                // Vérification si la grille actuelle est identique à la grille précédente
                if (jeu.grillePrecedente != null && jeu.compareGrilles()) {
                    System.out.println("La grille s'est stabilisée après " + jeu.generation + " générations.");
                    break;
                }
                // Condition pour stopper les générations après 10 générations
                if (jeu.generation == 10) {
                    System.out.println("Le jeu s'est arrêté après 10 générations.");
                    break;
                }
            }
        }
    }
}
