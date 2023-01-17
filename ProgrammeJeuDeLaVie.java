public class ProgrammeJeuDeLaVie {
    public static void main(String[] args) {
        try {
            JeuDeLaVie jeuV = new JeuDeLaVie(10, 10);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ProgrammeJeuDeLaVie(int tailleX, int tailleY) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
    }

    int tailleX;
    int tailleY;

    

    public static creerJeuxDeLaVie() {
        JeuDeLaVie jeuV = new JeuDeLaVie(10, 10);
        jeuV.afficher();
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
    public static void JeuDeLaVie(int taille, boolean[][] etatInitial) {
        JeuxDeLaVie grille = new boolean[taille][taille];

        // Copie l'état initial dans la grille
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j] = etatInitial[i][j];
            }
        }
    }
}
