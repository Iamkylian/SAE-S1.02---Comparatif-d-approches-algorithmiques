import java.util.Scanner;

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

    public static int nbVoisinsVivants(int i, int j, ProgrammeJeuDeLaVie grilleVie) throws Exception {
        int nbVoisins = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x != i || y != j) {
                    if (ProgrammeMatriceEntier.getElement(grilleVie.grilleCourante, x, y) == 1) {
                        nbVoisins++;
                    }
                }
            }
        }
        return nbVoisins;
    }

    public static boolean seraVivante(int i, int j, ProgrammeJeuDeLaVie grilleVie) throws Exception {
        int nbVoisins = nbVoisinsVivants(i, j, grilleVie);
        if (ProgrammeMatriceEntier.getElement(grilleVie.grilleCourante, i, j) == 1) {
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

    public static void calculerGenerationSuivante(ProgrammeJeuDeLaVie grilleVie) throws Exception {
        MatriceEntier grilleSuivante = new MatriceEntier(grilleVie.taille + 2, grilleVie.taille + 2);
        for (int i = 0; i < grilleVie.taille + 2; i++) {
            for (int j = 0; j < grilleVie.taille + 2; j++) {
                if (seraVivante(i, j, grilleVie)) {
                    ProgrammeMatriceEntier.setElement(grilleSuivante, i, j, 1);
                } else {
                    ProgrammeMatriceEntier.setElement(grilleSuivante, i, j, 0);
                }
            }
        }
        grilleVie.historiqueGrilles[grilleVie.generation] = grilleVie.grilleCourante;
        grilleVie.grilleCourante = grilleSuivante;
    }

    /**
     * @param grilleVie : la grille de jeu de la vie à tester
     * @return true si la grille courante est identique à une des grilles précédentes, false sinon  
     * @throws Exception : si la grille courante est identique à une des grilles précédentes
     */
    public static boolean grilleConnue(ProgrammeJeuDeLaVie grilleVie) throws Exception {
        for (int i = 0; i < grilleVie.generation; i++) {
            boolean identique = true;
            for (int j = 0; j < grilleVie.taille + 2; j++) {
                for (int k = 0; k < grilleVie.taille + 2; k++) {
                    if (ProgrammeMatriceEntier.getElement(grilleVie.historiqueGrilles[i], j, k) != ProgrammeMatriceEntier
                            .getElement(grilleVie.grilleCourante, j, k)) {
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

    public static void produireFichierHTML(ProgrammeJeuDeLaVie grilleVie) throws Exception {

        String ln = System.getProperty("line.separator");
        String chaine = "<table border=\"1\">" + ln;

        int i = 0;
        int j = 0;
        while (i < ProgrammeMatriceEntier.getNbLignes(grilleVie.grilleCourante)) {
            chaine = chaine + "<tr><td>";

            for (j = 0; j < ProgrammeMatriceEntier.getNbColonnes(grilleVie.grilleCourante) - 1; j++) {
                chaine = chaine + String.valueOf(grilleVie.grilleCourante.tabMat[i][j]) + "</td><td>";
            }
            chaine = chaine + String.valueOf(grilleVie.grilleCourante.tabMat[i][j]) + "</td></tr>" + ln;
            i++;
        }
        chaine = chaine + "</table>" + ln;
        System.out.println(chaine);

    }

    public static void main(String[] args) throws Exception {
        // Demande à l'utilisateur de saisir la taille de la grille
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisir la taille de la grille : ");
        int taille = sc.nextInt();
        ProgrammeJeuDeLaVie grilleVie = new ProgrammeJeuDeLaVie(taille);
        // Boucle pour jouer le jeu de la vie
        while (grilleVie.generation < 10) {
            calculerGenerationSuivante(grilleVie);
            if (grilleConnue(grilleVie)) {
                System.out.println("La même grille est apparue, arrêt des générations.");
                break;
            }
            grilleVie.generation++;
        }

        // Production du fichier HTML
        produireFichierHTML(grilleVie);

    }

}