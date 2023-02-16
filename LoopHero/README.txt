Loop Hero

Réalisé par François Gabriel et Bleuse Anthonin

Principe du jeu :

	Le héros doit réaliser 6 tours afin de gagner la partie.
	Chaque jour des monstres apparaissent sur des tuiles et différent effet sont réalisé
	Quand le héros arrive sur une tuile alors il combat les monstres présents sur cette ligne.
	Tué des monstres permet d’obtenir différents objets et des cartes, ces cartes peuvent être posés soit autour du chemin, sur le chemin, ou dans le paysage.  Les effets des cartes sont inspirés du jeu de base.

Information à connaitre afin de jouer dans de bonnes conditions :
	Les différentes touches de jeu :
	
	S -> passage en mode planification
	D -> reprise du temps
	E -> équipe l'équipement sélectionné
	I -> Charge une partie enregistrée
	O -> Sauvegarde la partie en cours

	Flèche de gauche -> vitesse du jeu en x1
	Flèche du bas -> vitesse du jeu en x2
	Flèche de droite -> vitesse du jeu en x3


Propriété :
	Les propriétés sont des interfaces donnant aux objets différentes fonction ou servant juste a les regroupés
		-Drawable : permet de dessiner un objet
		-Dropable : permet de rassembler les Drops lors des combats
		-Selectable : permet de sélectionner l'objet


Les tuiles :
	Les tuiles sont réparties en 3 type différent :
		-Paths(le chemin)
		-BorderPath(la bordure de chemin)
		-LandScape(le paysage)
	Les types sont tous des sous types de Cell.
	Les Cell implémente Drawable (une fonction draw permettant de les dessiner)


Les cartes :
	Il existe un seul objet cartes qui contient a chaque fois la tuile contenu

	Implémente Drawable et Selectable et Dropables

	Oblivion est un sous type de cartes permettant de supprimer une tuile posée précédemment


L'équipement :
	Un équipement est composé d'une rareté, d'une partie d'équipement (sword, shield, armor, helmet...) et de statistiques

Les parties d'équipement :
	Implémente Drawable et Selectable


Les entités :
	Le héros et les monstres sont tous des sous type d'Entity
	Les entités implémentent Drawable


