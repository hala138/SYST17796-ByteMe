
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;


public class GroupOfCards {

    private ArrayList<Card> cards;
    private int size;

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
    }

    
       
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    
    public int getSize() {
        return size;
    }

    
    public void setSize(int size) {
        this.size = size;
    }
   public Card draw(){
       if(cards.isEmpty()){
           return null;
       }
       return cards.remove(cards.size()-1);
   }
   public void addCard(Card card){
       if(card != null){
           cards.add(card);
       }
   }
   public boolean removeCard(Card card){
       if (card == null){
           return false;
       }
       return cards.remove(card);
   }
}
