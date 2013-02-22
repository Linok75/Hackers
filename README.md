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
