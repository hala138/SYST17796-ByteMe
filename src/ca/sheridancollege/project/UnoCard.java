/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Halaa
 */
public class UnoCard extends Card {
     private Color color;
    private Rank rank;

    public UnoCard(Color color, Rank rank) {
        this.color = color;
        this.rank = rank;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

  
    public boolean isPlayableOn(UnoCard topCard) {
        if (this.rank == Rank.WILD 
                || this.rank == Rank.WILD_DRAW_FOUR) {
            return true;
        }

        return this.color == topCard.color 
                || this.rank == topCard.rank 
                || this.color == color.WILD;
    }

    @Override
    public String toString() {
        if (rank == Rank.WILD){
            return "WILD";
        }else if (rank == Rank.WILD_DRAW_FOUR){
            return "WILD DRAW FOUR";
        }else{
             
        return color + " " + rank;
    }
 }
}