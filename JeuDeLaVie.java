public class JeuDeLaVie{
    int nbL;
    int nbC;

    JeuDeLaVie() throws Exception {
        this.nbL = 0;
        this.nbC = 0;
        if (this.nbL == 0 || this.nbC == 0) {
            throw new Exception("Pas de paramètres");
        }
    }
        

    JeuDeLaVie(int pfNbLignes, int pfNbColonnes) throws Exception {
        if ((pfNbLignes <= 0) || (pfNbColonnes <= 0)) {
            throw new Exception("Un des paramètres est vide ou négatif");
        }
        this.nbL = pfNbLignes;
        this.nbC = pfNbColonnes;
    }

    public void afficher() {
        System.out.println("Nombre de lignes: " + this.nbL);
        System.out.println("Nombre de colonnes: " + this.nbC);
    }
}