# Projet de GLS groupe G34

### Remarques :
- Pour ne pas avoir de merdes avec eclipse je propose que dès qu'eclipse nous demande un nom pour quoi que ce soit, on met le nom en minuscule.

### Planning :
Mercredi 6 décembre 2017 :
>- [X] concevoir le contenu du modèle (metamodèle généré pas à rendre ?)
>  - [ ] **E1** : Moteur de jeu donc OK
>  - [X] **E2** : RQ : j'ai ajouté les partie, je pense que c'est bon mais a confirmer.
>  - [X] **E3** : RQ : Le nom ne devrait pas plutôt être défini dans Element directement?
>  - [X] **E4** :
>  - [X] **E5** : RQ : j'ai corrigé: une partie n'a pas un texte decrivant les vagues mais directement une liste de vague
>  - [X] **E6** :
>  - [X] **E7** :
>  - [X] **E8** :
>  - [X] **E9** :
>  - [ ] **E10** : OCL donc OK
>  - [ ] **E11** : OCL donc OK
>  - [X] **E12** :
>  - [X] **E13** :
>  - [X] **E14** :
>  - [X] **E15** :
>  - [ ] **E16** : Moteur de jeu donc OK
>  - [X] **E17** :
>  - [X] **E18** :
>  - [X] **E19** : + OCL donc OK
>  - [X] **E20** : RQ : J'ai ajouté dans les mobile/Objstacle un champ qui défini les caractéristique des projectiles lancés
>  - [X] **E21** :  
>  - [X] **E22** : RQ : oublié, j'ai ajouté un champ energie
>  - [X] **E23** :
>  - [X] **E24** :
>  - [X] **E25** :
>  - [X] **E26** :
>- [X] **__D1__** : syntaxe textuelle `game.xtext`
>- [X] **__D2__** : image `game.png`

Mercredi 13 décembre 2017 :
> - [X] **__D3__** : exemple de la section 1.1 ecrit avec notre syntaxe text `enigme.td`
> - [X] **__D4__** : traduction manuelle en langage de prog `enigme.java`

Mercredi 20 décembre 2017:
> - [ ] **__D5__** : D'autres exemples suplémentaires modèle de jeux.
>   - [ ] Exemples. (_TODO Lister les exemples ici!_)
>   - [ ] Intérêts. (quel élément réalisé cet exemple illustre t'il?)
> - [x] **__D6__** : Contraintes OCL (format CompleteOCL) `game.ocl`
>   - [ ] Contraintes (_TODO Lister les contraintes ici!_)
>     - [x] Contrainte de base des attribus (positivité etc.) (energie, porte, posx, posy, etc.)
>     - [x] Coordonnée case valide
>     - [x] Coordonnée case unique
>     - [x] Terrain bien formé
>     - [ ] Vague bien formé ??
>     - [x] Seuls les mobile et projectiles peuvent aller sur entré, sortie et chemin
>     - [x] Seuls les obstacle et les pojectiles peuvent aller sur les campement.
>     - [x] Seuls les projectiles peuvent aller sur le décos.
>     - [ ] Somme des volume des mobile sur une case < au volume max de la case
>     - [ ]
>   - [x] contre exemple pour chaque contrainte
> - [X] **__D7__** : Transformation modèle à modèle accéléo `game2prototype.mtl`
> - [x] **__D8__** : Editeur graphiques (Sirius)
>   - [ ] point de vue Territoire.    _`Que faut il rendre?`_
>   - [ ] point de vue Interaction.   _`Que faut il rendre?`_

Vendredi 22 décembre 2017 :
> - [X] **__D9__** : Rapport
