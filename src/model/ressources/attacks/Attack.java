/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ressources.attacks;

import model.maps.Defence;
import model.maps.Node;
import model.ressources.Ressource;

/**
 *
 * @author ldavid
 */
public abstract class Attack extends Ressource implements Activable {

    protected boolean activated;
    //private ... time; //durée total d'execution
    //private ... currentTime; //temps passé pour l'execution
    private Defence defence;

    //methdode de diffusion
    //private DiffusionMethod diffusion;


    public Attack(String titre, String description, Defence defence) {
        super(titre, description);
        this.activated = false;
        this.defence = defence;
    }

    public Defence getDefence() {
        return defence;
    }

    public abstract void execute(Node node);

    // système de thread avec le temps ? => en général déconseillé mais peut-être intéressant dans ce cas
    // autre solution ?
//    private Thread t;
//    public void active(Node node) {
//        this.activated = true;
//        this.t = new AttackThread(node);
//        this.t.run();
//        //node.hack(this);
//    }
//
//    public void cancel() {
//        // réinitialiser currentTime
//        this.t.interrupt();
//        this.activated = false;
//    }
//
//    public void diffuse(Node[] nodes) {
//
//    }
//
//    public class AttackThread extends Thread {
//
//        private Node node;
//        private boolean stop;
//
//        public AttackThread(Node node) {
//            this.node = node;
//        }
//
//        @Override
//        public void run() {
//            //sleep(this.time); // pas terrible ...
//            //this.node.hack(Attack.this);
//            ArrayList<Node> nodes = new ArrayList<Node>();
//            nodes.add(node);
//            Attack.this.diffusion.start(nodes);
//        }
//
//    }


}
