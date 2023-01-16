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

    public void afficher() {
        System.out.println("Taille X: " + tailleX);
        System.out.println("Taille Y: " + tailleY);
    }
}
