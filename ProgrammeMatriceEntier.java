
/**
 * Indiquer le ou les numeros de TP et d'exercice.
 *
 * @martinet_leila_2A
 */
public class ProgrammeMatriceEntier
{
    public static int getNbLignes (MatriceEntier m) {
        return m.nbL;
    }

    public static int getNbColonnes (MatriceEntier m) {
        return m.nbC;
    }

    public static int getElement (MatriceEntier m, int i, int j) throws Exception {
        if ((i < 0) || (i >= getNbLignes(m)) || (j < 0) || (j >= getNbColonnes(m))) {
            throw new Exception ("Un des paramètres de getElement est incorrect");
        }
        return m.tabMat[i][j];
    }

    public static int somLigne (MatriceEntier m, int i) throws Exception {
        if ((i < 0) || (i >= getNbLignes(m))) {
            throw new Exception ("La ligne n'existe pas dans la matrice");
        }
        int somme = 0;
        for (int j=0; j<getNbColonnes(m); j++) {
            somme = somme + m.tabMat[i][j];
        }
        return somme;
    }

    public static int somColonne (MatriceEntier m, int j) throws Exception {
        if ((j<0) || (j>= getNbColonnes(m))) {
            throw new Exception ("La colonne n'existe pas");
        }
        int somme = 0;
        for (int i=0; i<getNbLignes(m); i++) {
            somme = somme + m.tabMat[i][j];
        }
        return somme;
    }

    public static boolean estCarree (MatriceEntier m) {
        if (m.nbL == m.nbC) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean estDiagonale (MatriceEntier m) throws Exception {
        if (estCarree(m) == false) {
            throw new Exception ("La matrice n'est pas carrée");
        }
        for (int i=0; i<getNbLignes(m); i++) {
            for (int j=0; j<getNbColonnes(m); j++) {
                if (i != j && m.tabMat[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void setElement (MatriceEntier m, int i, int j, int k) throws Exception {
        if (i<0 || i>=getNbLignes(m) || j<0 || j>=getNbColonnes(m)) {
            throw new Exception ("j ou i est incorrect");
        }
        m.tabMat[i][j] = k;
    }

    public static void setPremiereDiagonale (MatriceEntier m, int n) throws Exception {
        if (estCarree(m) == false) {
            throw new Exception ("la matrice n'est pas carrée");
        }
        for (int i=0; i<getNbColonnes(m); i++) {
            m.tabMat[i][i] = n;
        }
    }

    public static void setSecondeDiagonale (MatriceEntier m, int n) throws Exception {
        if (estCarree(m) == false) {
            throw new Exception ("la matrice n'est pas carrée");
        }
        int j = getNbLignes(m);
        for (int i=0; i<getNbColonnes(m); i++) {
            m.tabMat[i][j-i-1]=n;
        }
    }

    public static void mulMatNombre (MatriceEntier m, int n) {
        for (int i=0; i<getNbLignes(m); i++) {
            for (int j=0; j<getNbColonnes(m); j++) {
                m.tabMat[i][j] = m.tabMat[i][j] * n;
            }
        }
    }

    public static String toString (MatriceEntier m) {
        String chaine = "";
        int i = 0;
        while (i<getNbLignes(m)) {
            for (int j=0; j<getNbColonnes(m); j++) {
                chaine = chaine + String.valueOf(m.tabMat[i][j]) + " ";
            }
            i++;
            chaine = chaine + System.getProperty("line.separator") ;
        }
        return chaine;
    }

    public static String toHTML (MatriceEntier m) {

        String ln = System.getProperty("line.separator");
        String chaine = "<table border=\"1\">" + ln;

        int i = 0;
        int j= 0;
        while (i<getNbLignes(m)) {
            chaine = chaine + "<tr><td>";

            for (j=0; j<getNbColonnes(m)-1; j++) {
                chaine = chaine + String.valueOf(m.tabMat[i][j]) + "</td><td>";
            }
            chaine = chaine + String.valueOf(m.tabMat[i][j]) + "</td></tr>" + ln;
            i++;
        }
        chaine = chaine + "</table>" + ln;
        return chaine;
    }
}