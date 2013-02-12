/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.ressources;

/**
 *
 * @author ldavid
 */
public abstract class Ressource {

    private String title;
    private String description;

    public Ressource(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }



}
