/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ressources;

/**
 *
 * @author ldavid
 */
public abstract class Hardware extends Ressource {

    private int price;
    // possibilite d'ameliorer la diffucsion des attaques
    // reduire le temps d'activation des attaques
    // ...

    public Hardware(String titre, String description) {
        super(titre, description);
    }

    public int getPrice() {
        return price;
    }



}
