/*
 * toute case d’entrée est réliée à au moins une case de sortie par une séquence de cases de chemin
contiguës. Deux cases sont contiguës si elles possèdent un côté commun
* dans ce modéle c'est pas le cas
* | e |ch |
* | s | c |
* 
 */

jeu entreeLieeASortie {
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
						natureTerrain s
					}
					case c22 {
						x 2
						y 2
						natureTerrain c
					}
				}	
			}
				
	}
	parties {
		partie partie1 {
			terrain terrain1
			vagues {
				vague v1{
					energieAttribueeJoueur 3
					mobiles {
						mobile m1 M {
							ordreVague 1
							caseEntreeVague terrain1.c11
							caseSortieVague terrain1.c21
							projectilesLancees {}
							position terrain1.c11
							energie 1
						}
					}
					obstacles {
						obstacle o1 O {
							projectilesLancees {}
							position terrain1.c22
							energie 1
						}
					}
				}
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

