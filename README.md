Hackers
=======

Hackers - Projet Tutoré 2013


Antoine : La méthode isHackable plante si elle reçoit une attack null c'est mal XD 
         (vive le boulet qui lui a envoyé une attack null moi je dit mdr)
         
         Complément Model à apporter : 
                  Les node doivent avoir une description et une image (il en va de même pour la cible principale).

Benji: en plus de la ligne et du slick.jar, je dois ajouter quoi pour que ça marche ?

         Antoine :

                  Configuration pour SLICK : 
                           Bibliothèque : 
                                    - LWJGL
                                      ♦ jinput.jar
                                      ♦ lwjgl.jar
                                      ♦ lwjgl_util.jar
                                    
                                    - SLICK
                                      ♦ slick.jar
                                      
                           Propriétés du projet : 
                                    Exécuter -> Options de machine virtuelle : 
                                             Chemin vers les natives : 
                                                      exemple Windows : 
                                                               -Djava.library.path=C:\Users\X\Documents\NetBeansProjects\lib\lwjgl-2.8.5\native\windows
                                                      exemple Linux :
                                                               -Djava.library.path=/home/X/NetBeansProjects/lib/lwjgl-2.8.5/native/linux

Benji : voila le message que me renvoi netbeans à l'execution : "unbalanced quotes in -Djava.library.path=C:\Users\Maxou's computer\Dropbox\Hackers\Hackers\lib\lwjgl-2.8.5\native\windows"
Déjà vu ?

Antoine : Jamais vu mais vu le message d'erreur je pense que c'est parce 
que tu as une apostrophe dans le nom de ton dossier (Maxou's).
Soit tu renommes ton dossier, soit tu trouve le caractère d'échappement que je ne connais pas^^

Ludo : Nom de Zeus ! t'a commit le readme pendant que j'ecrivais et ca a supp ce que j'avais ecrit x)
Je disais que j'ai add un level en xml et une librairie en jar et que du coup, avec le gitignore, ca synchro pas...

Antoine : Nickez vous, j'étais en train d'écrire un putain de truc pour expliquer ou en était la vue et ça ma tout effacer
aussi parce que t'as commit...la flemme de tout réécrire je vous explique ou ça en est lundi.
(donne le nom de ton fichier xml et de ton jar que je laisse passez que ceux la sinon on va 
se retrouver avec le fichier config de netbeans qui est un .xml)

Ludo : lol x), xstream-1.4.2.jar et level1.xml (y'aura surement d'autre xml comme ca plus tard, donc si tu peux l'appliquer a au dossier (package) levels c'est mieux)

Antoine : C'est bon essaye et dit moi si ça marche.

Ludo : Marche pas... (Le gitignore a pas l'air d'etre a jour)

Antoine : bon git me troll mais la ça devrait être bon ;)

Ludo : Nikel =D

Antoine : pas con j'y avais pas pensé à faire appel au render en envoyant le graphic...
ça change pas mal de truc je vais essayer de retravailler la vue avec ça.
Bon du coup j'ai pensé à plein de truc à simplifier donc ça me prendra un peu de temps.
(je laisserai peut être les animations de coté quelque temps pour qu'on ai vite une vue utilisable)
Par contre pour vous c'est normal si l'attack est bloqué lorsqu'elle rencontre une cible déjà hacké ?
Moi je trouve pas ça très logique qu'une cible déjà hacké l'empêche de propagé.

Quentin : le Menu.java cherche un fichier il faut donc modifier le chemin pour chaque personne à chaque fois, 
je ne sais pas s'il y a un moyen plus simple mais bon ! Juste pour prévenir les futurs utilisateurs !

Antoine : C'est juste que Ludo a fait ça comme une brut en mettant les chemins en absolue pff...
ludo tu me déçois tu me fera le plaisir de mettre le chemin en relatif ;)

Antoine : Voilà à cause de toi ludo (XD) je me suis senti obliger de tout refaire donc si j'ai pas avancer c'est de ta faute.
Enfin bref je me sentais plus du coup j'ai tout recoder version M.Propre et j'en ai profiter pour faire de jolie package
que vous cherchiez pas 15 jours qu'est ce qui correspond à quoi.

Benji : voila mon message d'erreur pour le run du MainView :

         Exception in thread "Thread-0" java.lang.RuntimeException: Resource not found: /C:/Users/Maxou's%20computer/Documents/NetBeansProjects/Hackers_Git/Hackers/build/classes/view/ressources/quit.png
                  at org.newdawn.slick.util.ResourceLoader.getResourceAsStream(ResourceLoader.java:69)
                  at org.newdawn.slick.opengl.InternalTextureLoader.getTexture(InternalTextureLoader.java:273)
                  at org.newdawn.slick.Image.<init>(Image.java:270)
                  at org.newdawn.slick.Image.<init>(Image.java:244)
                  at org.newdawn.slick.Image.<init>(Image.java:232)
                  at org.newdawn.slick.Image.<init>(Image.java:198)
                  at view.MainMenu.init(MainMenu.java:49)
                  at view.MasterFrame.initStatesList(MasterFrame.java:35)
                  at org.newdawn.slick.state.StateBasedGame.init(StateBasedGame.java:170)
                  at org.newdawn.slick.AppGameContainer.setup(AppGameContainer.java:433)
                  at org.newdawn.slick.AppGameContainer.start(AppGameContainer.java:357)
                  at view.View.start(View.java:55)
                  at view.ViewLauncher.run(ViewLauncher.java:18)
                  
Car tu demande l'image dans l'init de MainView ANTOINE !

Antoine : Je regarde ça tout de suite (en passant ça c'est pas moi c'est Quentin ;) et oui je balance ! )
         
         C'est bon problème réglé !
         (à noté pour mémoire que le getRessource par de l'emplacement auquel est la class qu'il l'utilise)

Benji : J'ai toujours le même message ...

Antoine : C'est bizarre moi je l'ai plus 
(t'es bien en jdk 7? parce que je sais que y a des erreurs à la con qui peuvent venir de la)


         Antoine :
                  Vue fonctionnelle !
                  Donc elle est moche les messages d'erreur s'affiche en console et pas en vue mais
                  elle fonctionne de A à X (pas Z mais presque ^^).
                  Donc on peut effectuer la partie en entière corrompre les cibles puis lancer le DDOS
                  et on a le petit écran congratulation qui s'affiche à la sortie.
                  Ce qui serait cool c'est qu'on se mettent bien d'accord sur comment le ddos est lancer
                  (je suis pas sur de l'avoir fait de la façon dont vous y aviez pensé.) et aussi que 
                  le passage d'un level à un autre soit programmer dans le model que je puisse aller au niveau
                  suivant plutôt que de retourner au menu à la fin du niveau 1^^.
                  (Bien sur énormément de correction sont à apporter mais comme je savai que je serai aux ski
                  la première semaine je tenai à ce que une vue non pas potable mais utilisable pour des tests
                  soit prête avant que je parte.)
                  Du coup si vous pouviez test ça très vite que je corrige les choses urgentes qui gênerai pour les
                  tests voily voilou je file manger mes chocapic.
                  
                  EDIT : J'ai chercher pourquoi sur certains pc la police est dégueulasse, c'est parce que
                  l'instanciation en truetypefont est déprécié et ne correspond plus au version actuel de java.
                  Maintenant il faut instancier en Unicode (alors c'est trois ligne en plus donc la flemme pour l'instant
                  je le ferai plus tard^^)

Ludo : Ok, (Je me disais bien qu'il restait quelques problèmes de police), donc il ne reste plus qu'a gerer le systeme 
de passage au niveaux suivants, les sauvegardes, et apres conception des niveaux.

         Antoine : Il semblerait que tout spécialement MAC supporte mal les polices ttf (de slick) et après sur tout 
         les os quand tu dépasse 70-80 c'est mort.
         Si vous mettez dans le modèle la sauvegarde et le passage au niveau suivant avant que je parte au ski 
         j'essayerai de l'ajouter à la vu avant de partir.
         Et quand je reviendrai du ski j'améliorai la vue dans sa dimension esthétique (avec mes maigres compétence)

Ludo : Bon, prochain objectif : terminer le système de sauvegarde, chargement => Faire que le Modele soit completement 
fonctionnel, et faire les derniers "schema" de la vue en cours de jeu pour voir quelles sont les informations que doit 
voir l'utilisateur et ou les mettre. Apres, ce sera la creation de niveaux, de contenu (description personnage, histoire?,
images, etc...) et la mise en place de la vue "final".
Je vais donc essayer d'avancer un max pendant ces vacances pour qu'apres ca soit quasiment que de la finition.
