/*chaque partie doit avoir au moins une vague
 */

jeu auMoinsVague {
	elementsCommuns {
		chemin ch{ 
			energieNecessaireMobile 1
			volumeMaxMobile 1
		}
		modele projectile P {
			portee 1
			masse 1
			vitesse 1
			qteEnergie 1
		}
		entree e
		sortie s
		campement c
		decoration d
		modele obstacle O {
			energieMax 3
			tactique ViserFaible 
			modelesProjectile { P}
			cout 1
		}
		modele mobile M {
			volume 1 
			energieMax 1
			tactique ViserProche
			modelesProjectile { P}
			vitese 1
		}
		terrain terrain1 {
				nbLignes 2 
				nbColonnes 2 
				cases {
					case c11 {
						x 1
						y 1
						natureTerrain e
					}
					case c12 {
						x 1
						y 2
						natureTerrain ch
					}
					case c21 {
						x 2
						y 1
						natureTerrain c
					}
					case c22 {
						x 2
						y 2
						natureTerrain s
					}
				}	
			}
				
	}
	parties {
		partie partie1 {
			terrain terrain1
			vagues {
				
			}
			niveaux {
				niveau n1 {
					dureePauseVagueMobile 1
					energieJoueur 0
					nbLimiteMobiles 2
				}
			}
		}
	}
}
