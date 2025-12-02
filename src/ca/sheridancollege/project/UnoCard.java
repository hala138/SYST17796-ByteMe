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

    /**
     * Check if this card can be played on top of another card.
     *
     * @param topCard the card on the discard pile
     * @return true if this card is playable on that card
     */
    public boolean isPlayableOn(UnoCard topCard) {
        // Wild cards can always be played
        if (this.rank == Rank.WILD || this.rank == Rank.WILD_DRAW_FOUR) {
            return true;
        }

        // Same colour or same rank
        return this.color == topCard.color || this.rank == topCard.rank;
    }

    @Override
    public String toString() {
        return color + " " + rank;
    }
}
