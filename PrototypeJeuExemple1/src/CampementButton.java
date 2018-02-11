import org.omg.CORBA.SystemException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;


public class CampementButton extends JButton {
    private int posX;
    private int posY;
    private Partie p;
    private ObstacleSelecteur os;
    private GraphicalWorker gw;

    public CampementButton(final int x, final int y, final Partie p, ObstacleSelecteur obsSel, GraphicalWorker gworker) {
        posX = x;
        posY = y;
        this.p = p;
        this.os = obsSel;
        this.setContentAreaFilled(false);
        this.gw = gworker;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Campement clicked");
                for (Obstacle o : p.getObstaclesReeles()){
                    if (o.getPosition().getX() == posX && o.getPosition().getY() == posY){
                        System.out.println("Campement déjà occupé");
                        return;
                    }
                }
                Obstacle newObs = os.getSelectedObstacle(p.getTerrain().getCases()[x][y]);
                if (newObs == null) {
                    System.out.println("Selectionner un modele de tour d'abords");
                } else if(newObs.getCout() > p.getEnergieCouranteJoueur()){
                    System.out.println("Pas suffisament d'energie");
                }else {
                    System.out.println("Placement d'un nouvelle obstacle");
                    p.setEnergieCouranteJoueur(p.getEnergieCouranteJoueur() - newObs.getCout());
                    p.getObstaclesReeles().add(newObs);
                    gw.update(UpdateType.OBSTACLE, p);
                }
            }
        });

    }

}
