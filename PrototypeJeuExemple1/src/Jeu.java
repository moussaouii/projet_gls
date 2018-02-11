
import java.util.List;

public abstract class Jeu {
    protected String name;
    protected List<Partie> parties;
    protected int tourCourant;
    protected int partieCourrante;


    public void runFullJeu(GraphicalWorker gw){
        partieCourrante = 0;
        for (Partie p : parties){
            p.runPartie(0, gw);
            partieCourrante ++;
        }


    }
}
