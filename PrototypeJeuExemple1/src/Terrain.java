import java.util.List;

public class Terrain {
    private String name;
    private int nbLignes;
    private int nbColonnes;
    private Case[][] cases;

    public Terrain(String name, int nbLignes, int nbColonnes, Case[][] cases) {
        this.name = name;
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.cases = cases;
    }

    public String getName() {
        return name;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public Case getCase(int i,int j) {
        if(i<0 || j<0  || i>=nbLignes || j>=nbColonnes) {
        	return null;
        }else {
        	return cases[i][j];
        }
    }
    public Case[][] getCases() {
        return cases;
    }
}
