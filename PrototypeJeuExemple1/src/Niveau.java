
public class Niveau {
    private int dureePauseVagueMobile;
    private int energieJoueur;
    private int nbLimiteMobile;

    public Niveau(int dureePauseVagueMobile, int energieJoueur, int nbLimiteMobile) {
        this.dureePauseVagueMobile = dureePauseVagueMobile;
        this.energieJoueur = energieJoueur;
        this.nbLimiteMobile = nbLimiteMobile;
    }

    public int getDureePauseVagueMobile() {
        return dureePauseVagueMobile;
    }

    public int getEnergieJoueur() {
        return energieJoueur;
    }

    public int getNbLimiteMobile() {
        return nbLimiteMobile;
    }
}
