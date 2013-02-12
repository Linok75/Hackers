/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author ldavid
 */
public final class Mission {

    private String title;
    private String description;

    //private ... rewards
    //private ... bonus

    public Mission(String title, String description) {
        this.title = title;
        this.description = description;
    }


    @Override
    public String toString() {
        String str = "";
        str += "Title : "+this.title+"\n";
        str += "Description : "+this.description;
        return str;
    }



}
