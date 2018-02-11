import java.util.List;

public interface Lanceur extends Element{
    /**
     * Renvoi une instance de projectile.
     * @param index: int, l'indice du modele de projectile a lancer dans la liste des modele de projectile.
     * @param position : Case , la position du nouveau projectile.
     * @param cible : Lanceur, la cible du nouveau projectile.
     * @return une instance de projectile.
     */
    Projectile getModeleProjectil(int index, Case position, Lanceur cible);

    /**
     * Renvoi le nombre de modèle de projectile que gère le lanceur.
     * @return int.
     */
    int getNumProjectileModele();

    /** inflige des dégat au lanceur*/
    void endommager(int degats, int tourCourant);

    int getTourMort();

    int getEnergieMax();

    void resetEnergieTour();

    Tactique getTactique();

    int getEnergieActuelle(); // equivalent de la vie du lanceur, elle reduit après un coup

    void setEnergieActuelle(int energie);

    int getEnergieTour(); // equivalent de la mana du lanceur elle est consommée pour effectuer des actions et est initalisé au debut de chaque tour avec la valeur de getEnergieActuelle

    void consommerEnergieTour(int energieConsommee);

    Case getPosition();

    List<Projectile> getProjectilesLancees();

    Lanceur getCible(int index, Partie p);

    /**
     * Lance un projectile et consomme l'energie necessaire.
     * @param index: int, l'indice du modele de projectile a lancer dans la liste des modele de projectile.
     * @param cible: Lanceur, la cible du nouveau projectile.
     * @return true si l'opération est un succes, false sinon (exemple, energie insufffisante)
     */
    boolean lancerProjectile(int index, Lanceur cible);

}
