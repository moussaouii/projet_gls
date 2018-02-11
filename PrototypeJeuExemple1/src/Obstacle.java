/*Les classes Obstacle, Mobile et Projectile sont abstraite
* En effet elles sont destiné à être hérité, les classes ainsi formé donnant des
* modèles d'Obstacle/Mobile/Projectile réutilisables, et leurs
* instances sont les entitées effectives de la vague.
*/

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Obstacle extends AbstractLanceur{

    public Obstacle(Case caseCampement) {
        super(caseCampement);
    }

    public abstract int getCout();

    public final boolean doitEtreAffiche(Partie p){
        return true;
    }

    public final Lanceur getCible(int index, Partie p){
        // TODO adapter pour ne pas tirer en boucle sur la même cible dans un même tour ??
        VagueMobiles vague = p.getVagueCourante();
        List<? extends Lanceur> cibles = vague.getMobiles();
        return this.getCible(index, p, cibles);
    }

    public final void runTour(Partie p){
        this.resetEnergieTour();

        // TODO stratégie de tir plus évoluée
        for (int i = 0; i < getNumProjectileModele(); i++) {
            Lanceur cible;
            // SI des cibles sont dispo, essayer de lancer jusqu'a ce que cela echoue (i.e quand il n'y aura plus assez d'energie)
            while ((cible = getCible(i, p)) != null && lancerProjectile(i, cible)) {}
        }
        int k = 0;
        List<Projectile> projectiles = getProjectilesLancees();
        while (k < projectiles.size()) {
            if (projectiles.get(k).doitEtreAffiche(p)) {
                projectiles.get(k).runTour(p);
                k++;
            } else {
                projectiles.remove(k);
            }
        }
    }
}
