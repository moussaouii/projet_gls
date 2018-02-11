import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ObstacleSelecteur extends JPanel {
    private ButtonGroup group;
    private List<JRadioButton> selectionButtons;
    private ObstacleClassSelecteur selectionnables;
    private JLabel titre;

    public ObstacleSelecteur(){
        super();
        this.setPreferredSize(new Dimension(150, 200)); // TODO a faire dans GUInterface
        titre = new JLabel();
        titre.setText("Selection Tour:");
        selectionButtons = new ArrayList<>();
        selectionnables = null;
        group = new ButtonGroup();

        Container thisContainer = this;
        thisContainer.add(titre);
    }

    public void setSelectionnable(ObstacleClassSelecteur selectionnables){
        this.removeAll();
        for (JRadioButton b : selectionButtons){
            group.remove(b);
        }
        this.selectionnables = selectionnables;
        selectionButtons = new ArrayList<>();
        Container thisContainer = this;
        thisContainer.add(titre);
        for (int k = 0; k<selectionnables.getSize(); k++ ){
            JRadioButton rb = new JRadioButton();
            rb.setText(selectionnables.getInstanceName(k));
            group.add(rb);
            selectionButtons.add(rb);
            thisContainer.add(rb);
        }
    }

    public Obstacle getSelectedObstacle(Case position){
        for (int k = 0; k<selectionButtons.size(); k++){
            if (selectionButtons.get(k).isSelected()){
                return selectionnables.getInstance(k, position);
            }
        }
        return null;
    }

}
