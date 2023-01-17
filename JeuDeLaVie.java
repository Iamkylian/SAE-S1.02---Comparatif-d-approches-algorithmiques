public class JeuDeLaVie{
    int tailleX;
    int tailleY;

    JeuDeLaVie() throws Exception {
        this.tailleX = 0;
        this.tailleY = 0;
        if (this.tailleX == 0 || this.tailleY == 0) {
            throw new Exception("Pas de paramètres");
        }
    }
        

    /**
     * @param tailleX : nombre de lignes
     * @param tailleY : nombre de colonnes
     * @throws Exception : si les paramètres sont négatifs ou nuls
     */
    JeuDeLaVie(int tailleX, int tailleY) throws Exception {
        if ((tailleX <= 0) || (tailleY <= 0)) {
            throw new Exception("Un des paramètres est vide ou négatif");
        }
        this.tailleX = tailleX;
        this.tailleY = tailleY;
    }

    public void afficher() {
        System.out.println("Nombre de lignes: " + this.tailleX);
        System.out.println("Nombre de colonnes: " + this.tailleY);
    }
}