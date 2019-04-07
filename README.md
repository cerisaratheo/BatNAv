# Bataille navale

auteurs :
- SAKER Lucas
- CERISARA Théo

Ce projet est accessible en ligne sur https://github.com/cerisaratheo/BatNAv

## Description

Bataille navale en mode mono-joueur (le joueur joue contre lui même), en mode texte (en ligne de commande) ou graphique (le joueur joue à la souris). 

## Comment compiler (et jouer)

Sous linux, lancer le script

    cd src
    bash compileAndRun.sh

Sous Windows, lancer le script

    cd src
    compileAndRun.bat

Une fois compilé, vous pouvez aussi lancer le jeu à la main en exécutant la classe Main:

    cd src
    java -cp . projet_bataille_navale.Main

qui vous donnera la possibilité de choisir l'un des 4 modes de jeu:

- Console : le joueur entre les commandes au clavier dans une console texte
- ConsoleAuto : le programme joue tout seul contre lui-même. Il n'y a aucune interaction avec le joueur. Ce mode sert à tester le jeu.
- Graphique : le joueur voit la grille dans un JPanel Swing et interagit avec le jeu via la souris.
- GraphiqueTest : mode graphique automatique

# Explication des diagrammes UML
Le code source des diagrammes UML ainsi que les PNG se trouvent dans uml/

Vous pouvez recompiler les PNG avec la commande:

    cd uml
    java -jar plantuml.jar *.txt

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

# Javadoc

La javadoc se trouve dans le répertoire javadocs/

Elle peut être regénérée avec la commande:

    cd javadocs
    javadoc -cp ../src projet_bataille_navale

