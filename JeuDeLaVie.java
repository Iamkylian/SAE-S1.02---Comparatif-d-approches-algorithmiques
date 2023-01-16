public class JeuDeLaVie{
    int nbL;
    int nbC;

    JeuDeLaVie() throws Exception {
        throw new Exception("Pas de paramètres");
    }

    JeuDeLaVie(int pfNbLignes, int pfNbColonnes) throws Exception {
        if ((pfNbLignes <= 0) || (pfNbColonnes <= 0)) {
            throw new Exception("Un des paramètres est vide ou négatif");
        }
        this.nbL = pfNbLignes;
        this.nbC = pfNbColonnes;
    }
}