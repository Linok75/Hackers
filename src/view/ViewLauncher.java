/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Ara
 */
public class ViewLauncher extends Thread{
    public ViewLauncher(){
        
    }
    
    @Override
    public void run(){
        View.getInstance().start();
    }
}
