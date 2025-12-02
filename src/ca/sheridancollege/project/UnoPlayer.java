/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Halaa
 */
public class UnoPlayer extends Player{
    
    private GroupOfCards hand;
    private boolean hasSaidUno =false;

    public UnoPlayer(String name) {
        super(name);
        // hand will be set later by the game setup
    }

    public GroupOfCards getHand() {
        return hand;
    }

    public void setHand(GroupOfCards hand) {
        this.hand = hand;
    }
    public boolean hasSaidUno(){
        return hasSaidUno;
    }
    public void setHasSaidUno(boolean hasSaidUno){
        this.hasSaidUno=hasSaidUno;
    }
    /**
     * The player says UNO when they have one card left.
     * For now we just print a message.
     */
    public void sayUno() {
        if (!hasSaidUno){
        System.out.println(getName() + " says UNO!");
        hasSaidUno =true;
        }
    }

    /**
     * Simple implementation of play.
     * The actual turn logic will be handled by UnoGame.
     */
    @Override
    public void play() {
        // In this design, UnoGame controls the turn flow,
       
        System.out.println(getName() + " is taking a turn.");
    }
  

}
