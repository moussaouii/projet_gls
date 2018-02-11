import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/*Les classes Obstacle, Mobile et Projectile sont abstraite
* En effet elles sont destiné à être hérité, les classes ainsi formé donnant des
* modèles d'Obstacle/Mobile/Projectile réutilisables, et leurs
* instances sont les entitées effectives de la vague.
*/
public abstract class Mobile extends AbstractLanceur implements Deplacable {
	
	private ArrayList<Case> liDejaViste;
	private ArrayList<Case> liDejaVisteChoixMultiple;
	private int numPasVersChoixMultiple;
	
	
    private Case caseEntreeVague;
	private Case caseSortieVague;
	private int ordreVague;
	private int nbDepalcementCeTour; // enregistre le nombre de mouvement que ce mobile a éffectué dans le tour actuel.

    public Mobile( Case caseEntreeVague, Case caseSortieVague, int ordreVague) {
        super(caseEntreeVague);
        this.caseEntreeVague = caseEntreeVague;
        this.caseSortieVague = caseSortieVague;
        this.ordreVague = ordreVague;
    	liDejaViste=new ArrayList<Case>();
    	liDejaVisteChoixMultiple=new ArrayList<Case>();
    	numPasVersChoixMultiple=0;
        nbDepalcementCeTour = 0;
    }

    public final Case getCaseEntreeVague() {
        return caseEntreeVague;
    }

    public final Case getCaseSortieVague() {
        return caseSortieVague;
    }

    public final int getOrdreVague() {
        return ordreVague;
    }

    public final void setPosition(Case position) {
        this.position = position;
    }

    public final boolean doitEtreAffiche(Partie p){
        return (this.getOrdreVague() <= p.getVagueCourante().getTourVague() &&
                !(position == caseSortieVague) && (getTourMort() == -1 || getTourMort()+2>p.getVagueCourante().getTourVague()));
    }

    public final Lanceur getCible(int index, Partie p){
        VagueMobiles vague = p.getVagueCourante();
        List<Obstacle> cibles = vague.getObstacles();
        return getCible(index, p, cibles);
    }
    
    
    public final boolean dajaViste(Case candidat) {
    	boolean dejaV=false;
    	for(Case c:this.liDejaViste) {
    		if(candidat.equals(c)) {
    			dejaV=true;
    			break;
    		}
    	}
    	return dejaV;
    	
    }
    
    public final Case getNextPosition(Partie p){
    	boolean energieNeSuffitPas=true;
    	boolean PasDeCasedeTypeChemin=true;
    	Case nextPosition=null;
    	Case droite=p.getTerrain().getCase(position.getX(),position.getY() + 1);
    	Case gauche=p.getTerrain().getCase(position.getX(),position.getY() - 1);
    	Case haut=p.getTerrain().getCase(position.getX()-1,position.getY());
    	Case bas=p.getTerrain().getCase(position.getX()+1,position.getY());
		ArrayList<Case> positionsAcote=new ArrayList<Case>();
		if(gauche!=null) {
			positionsAcote.add(gauche);
		}
		if(droite!=null) {
			positionsAcote.add(droite);
		}
		if(bas!=null) {
			positionsAcote.add(bas);
		}
		if(haut!=null) {
			positionsAcote.add(haut);
		}
		
    	if (anyEqSortie(positionsAcote))
    	{
    		
    		for(Case c:positionsAcote) {
    			if(c.equals(caseSortieVague)) {
    				
    				nextPosition=c;
    			}
    			
    		}
    		
    			
    			
    			
    	}else {
    		

    		ArrayList<Case> positionsCandidat=new ArrayList<Case>();
    		
    		int energieNecessaire;
    		for(Case c:positionsAcote) {
    			if(c.getNatureTerrain() instanceof Chemin) {
    				energieNecessaire = ((Chemin) (c.getNatureTerrain())).getEnergieNecessaireMobile();
                    if (energieNecessaire <= getEnergieTour() && ((Chemin) (c.getNatureTerrain())).isVolumeAcceptabe(getVolume())) {
                    	
                    	if(!this.dajaViste(c))
                    	{
                    		positionsCandidat.add(c);
                    	}
                    	energieNeSuffitPas=false;
                    }
                    PasDeCasedeTypeChemin=false;
    			}
    		}
    		if(!positionsCandidat.isEmpty()) {
    			//on prend un par hasard

    			int choix=(int)( Math.random()*( positionsCandidat.size()  )  );
    			nextPosition=positionsCandidat.get(choix);
    			this.liDejaViste.add(nextPosition);
    			if(positionsCandidat.size()>1) {
    				this.liDejaVisteChoixMultiple.add(nextPosition);
    			}
    		}else {
    			
    			if (PasDeCasedeTypeChemin) {
					throw new RuntimeException("terrain Mal forme");
				}else {
					if (energieNeSuffitPas) {
						//se ploque
						nextPosition=null;
					}else {
						if(liDejaViste.isEmpty()) {
		    				//cas impossible
							throw new RuntimeException("cas impossible");
		    			}else {
		    				Case c=this.liDejaViste.get(liDejaViste.size()-1-this.numPasVersChoixMultiple);
		    				energieNecessaire = ((Chemin) (c.getNatureTerrain())).getEnergieNecessaireMobile();
		    				if(!this.liDejaVisteChoixMultiple.isEmpty()) {
			    				if(c.equals(this.liDejaVisteChoixMultiple.get(this.liDejaVisteChoixMultiple.size()-1))) {
			    					//on est dans la case de choix multiple

			    					
			    					if (energieNecessaire <= getEnergieTour()&& ((Chemin) (c.getNatureTerrain())).isVolumeAcceptabe(getVolume())) {
			    						nextPosition=c;
				    					this.numPasVersChoixMultiple=0;
				    					this.liDejaVisteChoixMultiple.remove(this.liDejaVisteChoixMultiple.size()-1);
			    					}else {
			    						//se ploque
			    						nextPosition=null;
			    					}
			    				}else {
			    					if (energieNecessaire <= getEnergieTour()&& ((Chemin) (c.getNatureTerrain())).isVolumeAcceptabe(getVolume())) {
			    						nextPosition=c;
			    						this.numPasVersChoixMultiple++;
			    					}else {
			    						//se ploque
			    						nextPosition=null;
			    					}
			    					
			    				}
		    				}else {
			    				//cas impossible
								throw new RuntimeException("terrain Mal forme2");
		    				}

		    			}
		    			
					}
				}
    		}	
    	}
    	
    	return nextPosition;
    	
    	
    }

    private boolean anyEqSortie(ArrayList<Case> positionsAcote) {
		// TODO Auto-generated method stub

    	Boolean exist=false;
    	for(Case c:positionsAcote) {
    		if(c.equals(caseSortieVague)) {
    			exist=true;
    		}
    	}
		return exist;
	}

	@SuppressWarnings("unused")
	public final boolean runTour(Partie p) {
    	
    	
    	
        /** renvoi faux si le mobile est sur le terrain, et vrai s'il n'y est pas encore entrée on déjà sortie*/

        if (this.getOrdreVague() >= p.getVagueCourante().getTourVague()) {
            // le mobile n'est pas encore sur le terrain
            return true;
        }
        if (position == caseSortieVague || getEnergieActuelle() == 0) {
            // le mobile est déjà sortie du terrain ou est mort
            return doitEtreAffiche(p);
        }
        this.resetEnergieTour();
        nbDepalcementCeTour = 0;

        // TODO path finding + stratégie d'attaque plus évoluée.
        //System.out.println("Mobile en ("+position.getX()+", "+position.getY()+")");
        
        
        boolean continueMoving=true;
        do {
        	Case nextPosition =getNextPosition(p);
        	
        	if(nextPosition != null) {
        		
        		 this.position = nextPosition;
        		 if(position.getNatureTerrain() instanceof Chemin) {
                     int energieNecessaire = ((Chemin) (position.getNatureTerrain())).getEnergieNecessaireMobile();
                     this.consommerEnergieTour(energieNecessaire);
        		 }

                 nbDepalcementCeTour ++;
                 continueMoving = nbDepalcementCeTour < this.getVitesse();
        	}else {
        		continueMoving=false;
        	}
  
            
        } while (!(position.equals(caseSortieVague)) && continueMoving);
       
        // retirer une vie si on atteint la sortie
        if (position.equals(caseSortieVague)) {
            p.setVie(p.getVie() - 1);
            System.out.println("Une vie perdue, reste "+p.getVie()+" vie(s).");
        }

        // Utiliser l'energie restante pour attaquer
        for (int i = 0; i < getNumProjectileModele(); i++) {
            Lanceur cible;
            // SI des cibles sont dispo, essayer de lancer jusqu'a ce que cela echoue (i.e quand il n'y aura plus assez d'energie)
            while ((cible = getCible(i, p)) != null && lancerProjectile(i, cible)) {}
        }
        for (Projectile proj : getProjectilesLancees()){
            proj.runTour(p);
        }
        return true;
    }
}
