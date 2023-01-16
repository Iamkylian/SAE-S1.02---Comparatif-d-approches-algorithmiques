/**
 * Indiquer le ou les numeros de TP et d'exercice.
 *
 * @martinet_leila_2A
 */
public class MatriceEntier
{
    int nbL;
    int nbC;
    int tabMat[][];

    MatriceEntier() throws Exception {
        throw new Exception("Pas de paramètres");
    }

    MatriceEntier(int pfNbLignes, int pfNbColonnes) throws Exception {
        if ((pfNbLignes <= 0) || (pfNbColonnes <= 0)) {
            throw new Exception("Un des paramètres est vide ou négatif");
        }
        this.nbL = pfNbLignes;
        this.nbC = pfNbColonnes;
        this.tabMat = new int[nbL][nbC];
    }
}

