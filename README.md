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
