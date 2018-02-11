import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLanceur implements Lanceur {
    protected Case position;
    private int energieActuelle;
    private int energieTour;
    private List<Projectile> projectilesLancees;
    private int tourMort; // Enregistre le tour où ce mobil est mort ou -1 s'il n'est pas encore mort


    public AbstractLanceur(Case position) {
        this.position = position;
        this.energieActuelle = getEnergieMax();
        this.energieTour = getEnergieMax();
        projectilesLancees = new ArrayList<>();
        tourMort = -1;
    }

    public int getTourMort() {
        return tourMort;
    }

    @Override
    public final int getEnergieTour(){
        return energieTour;
    }

    @Override
    public final Case getPosition() {
        return position;
    }

    @Override
    public final void endommager(int degats, int tourCourant) {
        if (tourMort == -1) {
            energieActuelle = Math.max(0, energieActuelle - degats);
            if (energieActuelle == 0) {
                tourMort = tourCourant;
            }
        } else {
            // TODO attaque d'un lanceur déjà mort!
        }
    }

    @Override
    public final int getEnergieActuelle() {
        return energieActuelle;
    }

    @Override
    public final void setEnergieActuelle(int energieActuelle) {
        this.energieActuelle = energieActuelle;
    }

    @Override
    public final void consommerEnergieTour(int energieConsommee) {
        this.energieTour -= energieConsommee;
    }
    public final void resetEnergieTour(){
        this.energieTour = energieActuelle;
    }


    @Override
    public final List<Projectile> getProjectilesLancees() {
        return projectilesLancees;
    }

    @Override
    public final boolean lancerProjectile(int index, Lanceur cible) {
        Projectile proj = getModeleProjectil(index, getPosition(), cible);
        if (getEnergieTour() >= proj.getMasse()) {
            projectilesLancees.add(proj);
            consommerEnergieTour(proj.getMasse());
            return true;
        } else {
            return false;
        }
    }

    public Lanceur getCible(int index, Partie p, List<? extends Lanceur> cibles){
        Projectile projModel = getModeleProjectil(index, null,null);
        // TODO améliorer pour gérer plusieurs modèles de projectiles
        if (projModel == null || getEnergieTour() < projModel.getQteEnergie()){
            return null;
        }
        Lanceur bestCible = null;
        int bestDistance = 1000000;
        for (Lanceur l: cibles){
            if (l.doitEtreAffiche(p) && l.getTourMort() == -1) {// Ne tirer que sur les mob présents et vivants
                int d = Math.abs(l.getPosition().getX() - this.getPosition().getX())
                        + Math.abs(l.getPosition().getY() - this.getPosition().getY());
                if (d <= projModel.getPortee() && Tactique.select(l, bestCible, getTactique(), d, bestDistance)) {
                    bestCible = l;
                    bestDistance = d;
                }
            }
        }
        return bestCible;
    }

}
