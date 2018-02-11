jeu exemple1 {
	elementsCommuns {
		decoration montagne
		chemin route{ 
			energieNecessaireMobile 1
			volumeMaxMobile 1
		}
		campement garage
		sortie s
		entree e 
		modele projectile P {
			portee 1
			masse 1
			vitesse 1
			qteEnergie 1
		}
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
				nbLignes 3 
				nbColonnes 5 
				cases {
					case c11 {
						x 1
						y 1
						natureTerrain montagne
					}
					case c12 {
						x 1
						y 2
						natureTerrain montagne
					}
					case c13 {
						x 1
						y 3
						natureTerrain montagne
					}
					case c14 {
						x 1
						y 4
						natureTerrain route
					}
					case c15 {
						x 1
						y 5
						natureTerrain s
					}
					case c21 {
						x 2
						y 1
						natureTerrain e
					}
					case c22 {
						x 2
						y 2
						natureTerrain route
					}
					case c23 {
						x 2
						y 3
						natureTerrain route
					}
					case c24 {
						x 2
						y 4
						natureTerrain route
					}
					case c25 {
						x 2
						y 5
						natureTerrain garage
					}
					case c31 {
						x 3
						y 1
						natureTerrain garage
					}
					case c32 {
						x 3
						y 2
						natureTerrain garage
					}
					case c33 {
						x 3
						y 3
						natureTerrain garage
					}
					case c34 {
						x 3
						y 4
						natureTerrain route
					}
					case c35 {
						x 3
						y 5
						natureTerrain s
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
							caseEntreeVague terrain1.c21
							caseSortieVague terrain1.c15
							projectilesLancees {}
							position terrain1.c21
							energie 1
						}
						mobile m2 M{
							ordreVague 2 
							caseEntreeVague terrain1.c21
							caseSortieVague terrain1.c15
							projectilesLancees {}
							position terrain1.c21
							energie 1
						}
						mobile m3 M{
							ordreVague 3 
							caseEntreeVague terrain1.c21
							caseSortieVague terrain1.c35
							projectilesLancees {}
							position terrain1.c21
							energie 1
						}
					}
					obstacles {
						
					}
				}
			}
			niveaux {
				niveau niveau1 {
					dureePauseVagueMobile 1
					energieJoueur 2
					nbLimiteMobiles 2
				}
			}
		}
	}
}
