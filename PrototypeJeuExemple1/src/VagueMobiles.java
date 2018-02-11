import java.util.List;

public class VagueMobiles {
    private String name;
    private int energieAttribueeJoueur;
    private List<Mobile> mobiles;
    private List<Obstacle> obstacles;
    private int tourVague;

    public VagueMobiles(String name, int energieAttribueeJoueur, List<Mobile> mobiles, List<Obstacle> obstacles) {
        this.name = name;
        this.energieAttribueeJoueur = energieAttribueeJoueur;
        this.mobiles = mobiles;
        this.obstacles = obstacles;
        tourVague =0;
    }

    public int getTourVague() {
        return tourVague;
    }
    public String getName() {
        return name;
    }

    public int getEnergieAttribueeJoueur() {
        return energieAttribueeJoueur;
    }

    public List<Mobile> getMobiles() {
        return mobiles;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void runVague(Partie p, GraphicalWorker gw){
        tourVague = 0;
        // ajouter les nouveau obstacles si possible
        for (Obstacle o : getObstacles()){
            boolean spaceFree = true;
            for (Obstacle oreel : p.getObstaclesReeles()){ // TODO a optimiser
                if (oreel.getPosition() == o.getPosition()){
                    spaceFree = false;
                }
            }
            if (spaceFree) {
                p.getObstaclesReeles().add(o);
            }
        }
        // reset l'energie des obstacles
        for (Obstacle o : p.getObstaclesReeles()){
            o.setEnergieActuelle(o.getEnergieMax());
        }
        while (this.runTour(p)) {
            System.out.println("\nTour:" + tourVague + "{\n");

            if (gw != null){
                gw.update(UpdateType.MOBILE, p);
                gw.update(UpdateType.PROJECTILES, p);
            }
            System.out.println(p.toStringDisplay());

            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
            tourVague++;
            System.out.println("}");
        }
    }

    public boolean runTour(Partie p){
        boolean mobilesActif = false;
        for (Obstacle o : p.getObstaclesReeles()){
            o.runTour(p);
        }
        for (Mobile m : mobiles){
            mobilesActif =  m.runTour(p) || mobilesActif ;
        }

        return mobilesActif;
    }
}
