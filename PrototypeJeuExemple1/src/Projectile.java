/*Les classes Obstacle, Mobile et Projectile sont abstraite
* En effet elles sont destiné à être hérité, les classes ainsi formé donnant des
* modèles d'Obstacle/Mobile/Projectile réutilisables, et leurs
* instances sont les entitées effectives de la vague.
*/
public abstract class Projectile implements Deplacable {

    private Case position;
    private Lanceur cible;
    private int tourFin; // Enregistre le tour où ce projectil a été détruit ou -1 sinon

    public Projectile(Case position, Lanceur cible) {
        this.position = position;
        this.cible = cible;
        tourFin = ((cible != null && position != null)? -1 : -3);
    }

    public int getTourFin() {
        return tourFin;
    }
    public final Case getPosition() {
        return position;
    }
    public final Lanceur getCible() {
        return cible;
    }
    public final int getVolume() {
        return 0;
    }
    public final boolean doitEtreAffiche(Partie p){
        return tourFin == -1 || p.getVagueCourante().getTourVague() < tourFin+1;
    }

    public abstract int getPortee();

    /**
     * La masse représente la quantité d'energie necessaire au lanceur pour créer le projectile
     * @return la masse du projectile.
     */
    public abstract int getMasse();

    /**
     * La quantité d'energie représente la quantité de dégat causé par le projectile a la cible.
     * @return la qtantité d'enrgie du projectile
     */
    public abstract int getQteEnergie();

    public final void runTour(Partie p){
        if (tourFin == -3) throw new RuntimeException("Les modele de projectiles ne doivent pas être executées.");

        int cx = cible.getPosition().getX();
        int cy = cible.getPosition().getY();
        int x = position.getX();
        int y = position.getY();
        if (x!=cx){
            if (x > cx) {
                position = p.getTerrain().getCases()[x-1][y];
            }else {
                position = p.getTerrain().getCases()[x+1][y];
            }
        }else if(y !=cy){
            if (y > cy) {
                position = p.getTerrain().getCases()[x][y-1];
            }else {
                position = p.getTerrain().getCases()[x][y+1];
            }
        }
        if (position.getX()==cx && position.getY()==cy){
            int turn = p.getVagueCourante().getTourVague();
            if (cible.getTourMort() == -1) {
                cible.endommager(getQteEnergie(), turn);
                if (cible.getTourMort() != -1) {
                    p.setEnergieCouranteJoueur(p.getEnergieCouranteJoueur() + cible.getEnergieMax());
                }
            }
            tourFin = turn;
        }
    }


}
