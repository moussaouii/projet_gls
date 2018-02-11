import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Jeu_j1 extends Jeu{
    public Jeu_j1() {
        String name = "j1";
        tourCourant = 0;
        partieCourrante = -1;
        ObstacleClassSelecteur modeleObstacles; // TODO remplacer par des objet Class

        final Decoration montagne = new Decoration("montagne", '#', null, "montagne.png");
        final Chemin route = new Chemin("route",'=',null,"route.png",1,1);
        final Campement garage = new Campement("garage", 'G', null, "garage.png");
        final Sortie s = new Sortie("s", 'S', null, "sortie.png");
        final Entree e = new Entree("e", 'E', null, "entree.png");

        final ImageIcon[] repr_P = {new ImageIcon("./ressources/P.png"), new ImageIcon("./ressources/P_dead0.png"), new ImageIcon("./ressources/P_dead1.png")};
        final ImageIcon[] repr_M = {new ImageIcon("./ressources/M.png"), new ImageIcon("./ressources/M_dead0.png"), new ImageIcon("./ressources/M_dead1.png")};
        final ImageIcon repr_O = new ImageIcon("./ressources/O.png");
        class P extends Projectile{
            public P (Case c, Lanceur l){
                super(c, l);
            }

            @Override
            public int getPortee() {
                return 1;
            }

            @Override
            public int getMasse() {
                return 1;
            }

            @Override
            public int getQteEnergie() {
                return 1;
            }

            @Override
            public int getVitesse() {
                return 1;
            }

            @Override
            public Character getRepresentationConsole() {
                return 'P';
            }

            @Override
            public ImageIcon getRepresentationGraphique(int turn) {
                if (getTourFin() == -1) {
                    return repr_P[0];

                }else{
                    return repr_P[(turn)%2 + 1];
                }
            }
        };

        class M extends Mobile{

            public M(Case caseEntreeVague, Case caseSortieVague, int ordreVague) {
                super(caseEntreeVague, caseSortieVague, ordreVague);
            }

            @Override
            public int getVolume() {
                return 1;
            }

            @Override
            public int getVitesse() {
                return 1;
            }

            @Override
            public Projectile getModeleProjectil(int index,Case p, Lanceur cible) {
                throw new RuntimeException("pas de modele correspondant à cette index:"+index);
            }

            @Override
            public int getNumProjectileModele() {
                return 0;
            }

            @Override
            public int getEnergieMax() {
                return 1;
            }

            @Override
            public Tactique getTactique() {
                return Tactique.ViserProche;
            }

            @Override
            public Character getRepresentationConsole() {
                return 'M';
            }

            @Override
            public ImageIcon getRepresentationGraphique(int turn) {
                if (getTourMort() == -1) {
                    System.out.println("repr");
                    return repr_M[0];

                }else{
                    System.out.println("repr2");
                    return repr_M[(turn - getTourMort())%2 + 1];
                }
            }
        }

        class O extends Obstacle{
            public O(Case caseCampement) {
                super(caseCampement);
            }

            @Override
            public int getCout() {
                return 1;
            }

            @Override
            public Projectile getModeleProjectil(int index, Case p, Lanceur cible) {
                if (index == 0) {
                    return new P(p, cible);
                }else{
                    throw new RuntimeException("pas de modele correspondant à cette index:"+index);
                }
            }

            @Override
            public int getNumProjectileModele() {
                return 1;
            }

            @Override
            public int getEnergieMax() {
                return 3;
            }

            @Override
            public Tactique getTactique() {
                return Tactique.ViserFaible;
            }

            @Override
            public Character getRepresentationConsole() {
                return 'O';
            }

            @Override
            public ImageIcon getRepresentationGraphique(int turn) {
                return repr_O;
            }
        }

        modeleObstacles = new ObstacleClassSelecteur() {
            @Override
            public int getSize() {
                return 1;
            }
            @Override
            public String getInstanceName(int index) {
                switch (index){
                    case 0:
                        return "O";
                    default:
                        return null;
                }
            }

            @Override
            public Obstacle getInstance(int index, Case position) {
                switch (index){
                    case 0:
                        return new O(position);
                    default:
                        return null;
                }
            }
        };

        int nbLignes = 3;
        int nbColonnes = 5;
        Case[][] arrayCase = new Case[nbLignes][nbColonnes];
        for (int x = 0; x<nbLignes;x++){
            for (int y=0; y<nbColonnes; y++){
                NatureTerrain nt = null;
                if (x == 0){
                    nt = montagne;
                }else if (x == 2) {
                    nt = garage;
                }else if (y == 0) {
                    nt = e;
                }else if (y == 4) {
                    nt = s;
                }else {
                    nt = route;
                }
                arrayCase[x][y] = new Case(x, y, nt);
            }
        }
        Terrain terrain1 = new Terrain("terrain1", nbLignes, nbColonnes, arrayCase);

        parties = new ArrayList<>();
        List<VagueMobiles> vagues_partie1 = new ArrayList<>();
        List<Mobile> mobiles_v1 = new ArrayList<>();
        mobiles_v1.add(new M( arrayCase[1][0], arrayCase[1][4], 1));
        mobiles_v1.add(new M( arrayCase[1][0], arrayCase[1][4], 2));
        mobiles_v1.add(new M( arrayCase[1][0], arrayCase[1][4], 3));
        List<Obstacle> obstacles_v1 = new ArrayList<>();
        obstacles_v1.add(new O(arrayCase[2][2]));
        VagueMobiles v1 = new VagueMobiles("v1", 0, mobiles_v1, obstacles_v1);
        vagues_partie1.add(v1);
        List<Niveau> niveau_partie1 = new ArrayList<>();
        niveau_partie1.add(new Niveau(1,0,2));
        Partie partie1 = new Partie("partie1", terrain1, vagues_partie1, niveau_partie1, modeleObstacles);
        parties.add(partie1);
    }
}
