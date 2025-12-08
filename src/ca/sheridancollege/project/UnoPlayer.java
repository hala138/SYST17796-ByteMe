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
         this.hasSaidUno = false;
        
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
   
    public void sayUno() {
        if (!hasSaidUno){
        System.out.println(getName() + " says UNO!");
        hasSaidUno =true;
        }
    }

  
    @Override
    public void play() {
       
        
    }
  

}
