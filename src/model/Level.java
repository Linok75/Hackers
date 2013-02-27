/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import model.maps.IMap;
import model.maps.Node;
import model.maps.Target;

/**
 *
 * @author ldavid
 */
public final class Level {

    private IMap map;
    private int time;
    private Target target;
    private Mission mission;
    private boolean isCompleted;
    
    public Level(IMap map, int time, Target target, Mission mission) {
        this.map = map;
        this.time = time;
        this.target = target;
        this.mission = mission;
        this.isCompleted = false;
    }

    public IMap getMap() {
        return map;
    }

    public Mission getMission() {
        return mission;
    }

    public Target getTarget() {
        return target;
    }

    public int getTime() {
        return time;
    }
    
    public boolean completed () {
        if (this.target.isHack()) {
            this.isCompleted= true;
        }
        return this.isCompleted;
    }

    @Override
    public String toString() {
        String str = "";
        //str += "************ MAP ************\n"+this.map.toString()+"\n";
        str += "************ TIME ************\n"+this.time+"\n";
        str += "************ TARGET ************\n"+this.target.toString()+"\n";
        str += "************ MISSION ************\n"+this.mission.toString()+"\n";
        return str;
    }



}
