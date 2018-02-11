import java.util.ArrayList;
import java.util.List;

public class Partie {
    private String name;
    private Terrain terrain;
    private List<VagueMobiles> vagues;
    private List<Niveau> niveaux;
    private int vagueCourante;
    private int energieCouranteJoueur;
    private int vie;
    protected ObstacleClassSelecteur modelesObstacles;
    protected List<Obstacle> obstaclesReeles;

    public Partie(String name, Terrain terrain, List<VagueMobiles> vagues, List<Niveau> niveaux, ObstacleClassSelecteur modelesObstacles) {
        this.name = name;
        this.terrain = terrain;
        this.vagues = vagues;
        this.niveaux = niveaux;
        this.vagueCourante = 0;
        energieCouranteJoueur = 0;
        vie = 1;
        this.modelesObstacles = modelesObstacles;
        obstaclesReeles = new ArrayList<>(); // TODO
    }

    public int getVie() {
        return vie;
    }

    public ObstacleClassSelecteur getModelesObstacles() {
        return modelesObstacles;
    }

    public List<Obstacle> getObstaclesReeles() {
        return obstaclesReeles;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getEnergieCouranteJoueur() {
        return energieCouranteJoueur;
    }

    public void setEnergieCouranteJoueur(int energieCouranteJoueur) {
        this.energieCouranteJoueur = energieCouranteJoueur;
    }

    public String getName() {
        return name;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public List<VagueMobiles> getVagues() {
        return vagues;
    }

    public List<Niveau> getNiveaux() {
        return niveaux;
    }

    public VagueMobiles getVagueCourante() {
        if (vagueCourante >= vagues.size()) {
            return null;
        }
        return vagues.get(vagueCourante);
    }



    public void runPartie(int indiceNiveau, GraphicalWorker gw) {
        if (indiceNiveau >= niveaux.size() || indiceNiveau < 0){
            throw new RuntimeException("Niveau invalide");
        }

        if (gw != null){
            gw.update(UpdateType.TERRAIN, this);
        }

        Niveau niv = niveaux.get(indiceNiveau);
        energieCouranteJoueur = niv.getEnergieJoueur();
        vagueCourante = 0;
        vie = niv.getNbLimiteMobile();
        while(vie>=0 && vagueCourante < getVagues().size()){
            if (gw != null){
                gw.update(UpdateType.OBSTACLE, this);
            }
            VagueMobiles v = getVagueCourante();
            v.runVague(this, gw);
            if (vie>=0) {
                energieCouranteJoueur += v.getEnergieAttribueeJoueur();
                try {
                    Thread.sleep(niv.getDureePauseVagueMobile());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                vagueCourante++;
            }
        }
        if (vie <= 0){
            System.out.println("Partie perdue");
            // TODO amméliorer le remonté d'info en retournant des boolean plutôt que void
        } else {
            System.out.println("partie Gagnée");
        }
    }

    public String toStringDisplay(){
        // TODO represente la partie a afficher en console
        String[] lignes = new String[this.getTerrain().getNbLignes()];
        for (int x = 0; x<this.getTerrain().getNbLignes(); x++){
            lignes[x] = "";
            for (int y = 0; y<this.getTerrain().getNbColonnes(); y++){
                lignes[x] = lignes[x] + this.getTerrain().getCases()[x][y].getNatureTerrain().getRepresentationConsole();
            }
        }
        for (Obstacle o : getObstaclesReeles()){
            Case c = o.getPosition();
            lignes[c.getX()] = lignes[c.getX()].substring(0,c.getY())
                    + o.getRepresentationConsole()
                    + lignes[c.getX()].substring(c.getY() + 1);
            for (Projectile p : o.getProjectilesLancees()){
                if (p.doitEtreAffiche(this)) {
                    Case cp = p.getPosition();
                    lignes[cp.getX()] = lignes[cp.getX()].substring(0, cp.getY())
                            + p.getRepresentationConsole()
                            + lignes[cp.getX()].substring(cp.getY() + 1);
                }
            }
        }
        for (Mobile m : this.getVagues().get(vagueCourante).getMobiles()){
            Case c = m.getPosition();
            if (m.doitEtreAffiche(this)) {
                lignes[c.getX()] = lignes[c.getX()].substring(0, c.getY())
                        + m.getRepresentationConsole()
                        + lignes[c.getX()].substring(c.getY() + 1);
            }
            for (Projectile p : m.getProjectilesLancees()){
                if (p.doitEtreAffiche(this)) {
                    Case cp = p.getPosition();
                    lignes[cp.getX()] = lignes[cp.getX()].substring(0, cp.getY())
                            + p.getRepresentationConsole()
                            + lignes[cp.getX()].substring(cp.getY() + 1);
                }
            }
        }

        String result = "";
        for (String s : lignes){
            result = result + s + "\n";
        }
        return result;
    }



}
