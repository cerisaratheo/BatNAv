#Bataille navale

auteurs :
- SAKER Lucas
- CERISARA Théo

## Description

Bataille navale en mode mono-joueur (le joueur joue contre lui même), en mode texte (en ligne de commande) ou graphique (le joueur joue à la souris). 

## Comment jouer

choix du mode de jeu depuis la classe principale

# Choix d'implémentation

## modes de jeu

3 modes de jeu sont implémentés:

- Console : le joueur entre les commandes au clavier dans une console texte
- ConsoleAuto : le programme joue tout seul contre lui-même. Il n'y a aucune interaction avec le joueur. Ce mode sert à tester le jeu.
- Graphique : le joueur voit la grille dans un JPanel Swing et interagit avec le jeu via la souris.

# Explication des diagrammes UML

## Diagramme de classe

Nous avons séparé la partie interface utilisateur du moteur du jeu. Pour cela nous avons créé une interface java GUI qui est implémentée par quatre classes : Console (pour le mode texte), ConsoleAuto (pour le mode texte automatique), Graphique (pour le mode graphique), et GraphiqueTest (pour le mode graphique automatique).

La classe GUI définit trois méthodes : 

- initJeu() pour initialiser la taille du bateau, le nom du joueur...
- positionnerBateau() pour permettre au joueur de poser ses bateaux
- jouer() pour tirer sur les bateaux

Le package "graphic" rassemble toutes les classes swing.

La classe abstraite bateau est implémentée par cinq classes : SousMarin, Torpilleur, Croiseur, ContreTorpilleur, PorteAvion

La classe Grille contient toutes les cases du plateau

La classe Joueur contient toutes les informations du moteur jeu : la grille, la liste des bateaux. C'est donc cette classe qu'il faut sauvegarder (sérialiser). Toutefois, pour le mode graphique il faut aussi sérialiser une deuxième grille qui n'affiche pas les bateaux mais les tirs déjà effectués. Ceci permet au joueur de ne pas voir ses bateaux lorsqu'il tire. 

La classe Jeu contient le main.

## Diagrammes de séquence

Nous avons choisi 3 scénario:

- Initialisation du jeu: un joueur pose un bateau
- Partie en cours : un joueur attaque un bateau qui ne coule pas
- Partie en cours : un joueur attaque un bateau et le coule

### Poser un bateau

- La classe Jeu crée les objets: Console, Bateaux, Grille et Joueur (les objets Case sont créés par la Grille).
- Ensuite la classe Jeu se contente de lancer la méthode "positionnerBateaux()" de la Console
- La Console s'occupe alors de demander aux joueurs les coordonnées initiales et l'orientation, mets à jour les bateaux avec ces informations
- Puis la Console récupère la case initiale, et exécute la méthode "positionnerBateau()" de la Grille
- La Grille vérifie alors que le bateau peut être posé à cet endroit, puis itère sur toutes les cases sur lesquelles le bateau doit être posé et appelle "devenirOccupee()" pour chacune.
- La Console affiche enfin la grille
