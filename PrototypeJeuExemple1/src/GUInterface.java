import javax.swing.*;
import java.awt.*;

public class GUInterface {
    /**Mémorise la taille courante des cases du terrain*/
    private int currentCaseScale;
    /**Fenetre principale de l'application*/
    private JFrame fenetre;

    /**
     * Zone d'affichage du terrain
     */
    private JLayeredPane CouchesArea;
    private JPanel backgroundArea;
    private JComponent obstacleArea;
    private JComponent mobileArea;
    private JComponent forgroundArea;
    /**Contient tous les composant de l'affichage du terrain*/
    private JComponent[][][] terrainLabels;
    private JLabel statJoueurLabel;
    private ObstacleSelecteur obstacleSelecteur;
    private Dimension terrainSize;
    private Dimension fullSize;

    private GraphicalWorker gw;

    public GUInterface(Jeu j) {
        System.out.println("Initialisation affichage graphique");
        this.currentCaseScale = 0;

        // Initialisationd le la fenêtre principale
        this.fenetre = new JFrame("Tower defense enigme");
        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.fenetre.setVisible(true);

        terrainSize = Toolkit.getDefaultToolkit().getScreenSize();
        terrainSize = new Dimension((int)Math.round(terrainSize.width*0.5), (int)Math.round(terrainSize.height * 0.5));
        fullSize = new Dimension(terrainSize.width + 200, terrainSize.height + 100);
        this.fenetre.setPreferredSize(fullSize);


        JPanel mainArea = new JPanel();
        BorderLayout mainLayout = new BorderLayout();
        this.fenetre.getContentPane().add(mainArea);
        mainArea.setPreferredSize(fullSize);
        mainArea.setLayout(mainLayout);
        Container mainContainer = mainArea;

        // Ajout d'un JLayerPAne à plusieurs couches (pour superposer joueur, terrain et interface)
        CouchesArea = new JLayeredPane();
        CouchesArea.setPreferredSize(terrainSize);
        mainContainer.add(CouchesArea, BorderLayout.CENTER);
        statJoueurLabel = new JLabel();
        statJoueurLabel.setPreferredSize(new Dimension(100, 50));
        statJoueurLabel.setText("  Vie restantes :                  Energie :");
        mainContainer.add(statJoueurLabel, BorderLayout.NORTH);
        obstacleSelecteur =  new ObstacleSelecteur();
        mainContainer.add(obstacleSelecteur, BorderLayout.EAST);


        // Ajouter la couche avant pour les Projectiles
        this.forgroundArea = new JLabel();
        CouchesArea.add(this.forgroundArea, 0);


        this.mobileArea =  new JLabel();
        CouchesArea.add(this.mobileArea, 1);

        this.obstacleArea = new JLabel();
        CouchesArea.add(this.obstacleArea, 2);


        // Ajouter la couche de fond, le terrain
        this.backgroundArea = new JPanel();
        this.backgroundArea.setBackground(new Color(0, 0, 0));
        //this.backgroundArea.setOpaque(true);
        //this.backgroundArea.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        CouchesArea.add(backgroundArea, 3);

        this.fenetre.pack();
        System.out.println("Init Done");

        System.out.println("Lancement du jeu sur le thread de travail");
        gw = new GraphicalWorker(j, this);
        gw.execute();
    }
    public void afficherTerrain(Partie p){
        VagueMobiles v = p.getVagueCourante();
        if (v==null) return;

        Terrain terr = p.getTerrain();

        // Calculer la taille du terrain
        Dimension afficheurSize =  this.fenetre.getSize();
        // cas particulier de la taille null a l'initialisation
        if (afficheurSize.getHeight() < 100 || afficheurSize.getWidth() < 100) {
            afficheurSize = terrainSize;
        }
        System.out.println("taille terrain ("+afficheurSize.getWidth()+", "+afficheurSize.getHeight()+")");

        // Calculer la taille des cases du terrain
        this.currentCaseScale =  (int)Math.round(Math.min(
                afficheurSize.getHeight() / terr.getNbLignes(),
                afficheurSize.getWidth() / terr.getNbColonnes()
        )*0.9);
        System.out.println("taille case "+currentCaseScale);

        afficheurSize = new Dimension(this.currentCaseScale * terr.getNbColonnes(),this.currentCaseScale * terr.getNbLignes());
        CouchesArea.setSize(afficheurSize);
        // reinitialiser et redimensionner le gridlayout de la fenêtre
        Container backgroundContainer = this.backgroundArea;
        backgroundContainer.removeAll();
        backgroundContainer.setLayout(new GridLayout(terr.getNbLignes(), terr.getNbColonnes(), 0, 0));
        this.backgroundArea.setSize(afficheurSize);

        Container obstacleContainer = this.obstacleArea;
        obstacleContainer.removeAll();
        obstacleContainer.setLayout(new GridLayout(terr.getNbLignes(), terr.getNbColonnes(), 0, 0));
        this.obstacleArea.setSize(afficheurSize);

        Container mobileContainer = this.mobileArea;
        mobileContainer.removeAll();
        mobileContainer.setLayout(new GridLayout(terr.getNbLignes(), terr.getNbColonnes(), 0, 0));
        this.mobileArea.setSize(afficheurSize);

        Container forgroundContainer = this.forgroundArea;
        forgroundContainer.removeAll();
        forgroundContainer.setLayout(new GridLayout(terr.getNbLignes(), terr.getNbColonnes(), 0, 0));
        this.forgroundArea.setSize(afficheurSize);

        this.terrainLabels = new JComponent[terr.getNbLignes()][terr.getNbColonnes()][4];
        for (int x = 0; x < terr.getNbLignes(); x++){
            for (int y = 0; y < terr.getNbColonnes(); y++) {

                if (terr.getCases()[x][y].getNatureTerrain() instanceof Campement) {
                    this.terrainLabels[x][y][1] = new CampementButton(x, y, p, obstacleSelecteur, gw);

                } else {
                    this.terrainLabels[x][y][1] = new JLabel();
                }
                this.terrainLabels[x][y][0] = new JLabel();
                this.terrainLabels[x][y][2] = new JLabel();
                this.terrainLabels[x][y][3] = new JLabel();
                this.setCaseImage(x, y, 0, terr.getCases()[x][y].getNatureTerrain().getRepresentationGraphique(p.getVagueCourante().getTourVague()));

                backgroundContainer.add(this.terrainLabels[x][y][0]);
                obstacleContainer.add(this.terrainLabels[x][y][1]);
                mobileContainer.add(this.terrainLabels[x][y][2]);
                forgroundContainer.add(this.terrainLabels[x][y][3]);
            }
        }
        obstacleSelecteur.setSelectionnable(p.modelesObstacles);
    }

    public void afficherObstacles(Partie p){
        VagueMobiles v = p.getVagueCourante();
        if (v==null) return;

        java.util.List<Obstacle> obs = p.getObstaclesReeles();
        for (Obstacle o : obs){
            this.setCaseImage(o.getPosition().getX(), o.getPosition().getY(), 1, o.getRepresentationGraphique(v.getTourVague()));
        }
        /*for (int x = 0; x<this.terrainLabels.length; x++){
            for (int y =0; y< this.terrainLabels[x].length; y++){
                setCaseImage(x, y, 1, new ImageIcon("./ressources/emptyTexture.png"));
            }
        }
        for (Obstacle o : obs){
            if (o.doitEtreAffiche(p)) {
                setCaseImage(o.getPosition().getX(), o.getPosition().getY(), 1, o.getRepresentationGraphique(p.getVagueCourante().getTourVague()));
            }
        }*/
    }

    public void afficherMobile(Partie p){
        VagueMobiles v = p.getVagueCourante();
        if (v==null) return;

        java.util.List<Mobile> mobs = p.getVagueCourante().getMobiles();

        for (JComponent[][] ligne : this.terrainLabels){
            for (JComponent[] caseLayers : ligne){
                caseLayers[2].setVisible(false);
            }
        }
        for (Mobile m : mobs){
            if (m.doitEtreAffiche(p)) {
                setCaseImage(m.getPosition().getX(), m.getPosition().getY(), 2, m.getRepresentationGraphique(p.getVagueCourante().getTourVague()));
            }
        }
        updateEnergieVie(p);
    }
    public void afficherProjectiles(Partie p){
        VagueMobiles v = p.getVagueCourante();
        if (v==null) return;

        java.util.List<Obstacle> obs = p.getVagueCourante().getObstacles();
        for (JComponent[][] ligne : this.terrainLabels){
            for (JComponent[] caseLayers : ligne){
                caseLayers[3].setVisible(false);
            }
        }
        for (Obstacle o : obs){
            for (Projectile proj : o.getProjectilesLancees()){
                if (proj.doitEtreAffiche(p)){
                    setCaseImage(proj.getPosition().getX(), proj.getPosition().getY(), 3, proj.getRepresentationGraphique(p.getVagueCourante().getTourVague()));
                }
            }
        }
    }

    private void setCaseImage(int x, int y, int z, ImageIcon icone){
        //System.out.println("Set case "+x+","+y+":"+z);
        if (this.terrainLabels[x][y][z] instanceof JLabel) {
            ((JLabel)(this.terrainLabels[x][y][z])).setIcon(new ImageIcon(icone
                    .getImage().getScaledInstance(this.currentCaseScale, this.currentCaseScale, Image.SCALE_DEFAULT)));
        } else {
            ((JButton)(this.terrainLabels[x][y][z])).setIcon(new ImageIcon(icone
                    .getImage().getScaledInstance(this.currentCaseScale, this.currentCaseScale, Image.SCALE_DEFAULT)));
        }
        this.terrainLabels[x][y][z].setVisible(true);
    }

    public void updateEnergieVie(Partie p){
        statJoueurLabel.setText("  Vie restantes : "+p.getVie()+"                Energie : "+p.getEnergieCouranteJoueur());
    }
}
