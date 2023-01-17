
public class JeuDeLaVie {

    int taille;
        int generation;
        MatriceEntier grilleCourante;
        MatriceEntier[] historiqueGrilles;
    
    public JeuDeLaVie(int taille) throws Exception {
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

    
}
