import javax.swing.*;
import java.util.List;

/**
 * En mode graphique cette classe gère le programme principale en tache de fond.
 */
class TypePartiePack{
    public UpdateType type;
    public Partie partie;

    public TypePartiePack(UpdateType type, Partie partie) {
        this.type = type;
        this.partie = partie;
    }
}

public class GraphicalWorker extends SwingWorker<Boolean,TypePartiePack>{
    private Jeu jeu;
    private GUInterface gui;

    public GraphicalWorker(Jeu j, GUInterface gui) {
        this.jeu = j;
        this.gui = gui;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        Thread.sleep(1000);
        System.out.println("background worker start");
        jeu.runFullJeu(this);
        return true;
    }

    public void update(UpdateType type, Partie p){
        this.publish(new TypePartiePack(type, p));
    }

    public void process(List<TypePartiePack> packs){
        for (TypePartiePack pack : packs) {
            switch (pack.type) {
                case TERRAIN:
                    gui.afficherTerrain(pack.partie);
                    break;
                case OBSTACLE:
                    gui.afficherObstacles(pack.partie);
                    break;
                case MOBILE:
                    gui.afficherMobile(pack.partie);
                    break;
                case PROJECTILES:
                    gui.afficherProjectiles(pack.partie);
                    break;
            }
        }
    }
}
