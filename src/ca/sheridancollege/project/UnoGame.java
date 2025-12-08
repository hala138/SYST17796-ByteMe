/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnoGame extends Game{
    
    private GroupOfCards drawPile;
    private GroupOfCards discardPile;
    private int currentIndex;
    private int direction; // 1 = clockwise, -1 = counter-clockwise
    private Color currentColor;
    private Player winner;
    private int turnNumber;

    public UnoGame(String name) {
        super(name);
        this.drawPile = new GroupOfCards(108);
        this.discardPile = new GroupOfCards(108);
        this.currentIndex = 0;
        this.direction = 1;
        this.currentColor = null;
        this.winner = null;
    }

    /**
     * Create the deck, shuffle, deal cards and start the discard pile.
     */
    public void setUp() {

    Color[] colours = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

// Build full UNO deck

for (Color c : colours) {

    // ZERO appears once per colour
    drawPile.addCard(new UnoCard(c, Rank.ZERO));

    // Numbers 1–9 appear twice per colour
    Rank[] numberRanks = {
        Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE,
        Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE
    };

    for (Rank r : numberRanks) {
        drawPile.addCard(new UnoCard(c, r));
        drawPile.addCard(new UnoCard(c, r));
    }

    // Action cards (Skip, Reverse, Draw Two) appear twice per colour
    Rank[] actionRanks = {Rank.SKIP, Rank.REVERSE, Rank.DRAW_TWO};

    for (Rank r : actionRanks) {
        drawPile.addCard(new UnoCard(c, r));
        drawPile.addCard(new UnoCard(c, r));
    }
}

// Wild cards (4 of each)
for (int i = 0; i < 4; i++) {
    drawPile.addCard(new UnoCard(Color.WILD, Rank.WILD));
    drawPile.addCard(new UnoCard(Color.WILD, Rank.WILD_DRAW_FOUR));
}

// Shuffle after building the full deck
drawPile.shuffle();


        for (Player p : getPlayers()) {
            UnoPlayer up = (UnoPlayer) p;
            GroupOfCards hand = new GroupOfCards(7);
            for (int i = 0; i < 7; i++) {
                hand.addCard(drawPile.draw());
            }
            up.setHand(hand);
        }

        UnoCard firstCard = (UnoCard) drawPile.draw();
        discardPile.addCard(firstCard);
        currentColor = firstCard.getColor();

        currentIndex = 0;
        direction = 1;
        winner = null;
        turnNumber = 1;
    }

    @Override
    public void play() {
      setUp();
    System.out.println("Starting UNO game: " + getName());

    int maxTurns = 100; // for demo only
    //int turnNumber = 1;

    while (!isRoundOver() && turnNumber <= maxTurns) {
        System.out.println("\n----- TURN " + turnNumber + " -----");
        playTurn();
        turnNumber++;
    }

    declareWinner();
    }

    @Override
    public void declareWinner() {
        if (winner != null) {
            System.out.println("Winner is: " + winner.getName());
        } else {
            System.out.println("Round ended with no winner.");
        }
    }

 
    public void playTurn() {

        List<Player> players = getPlayers();
        UnoPlayer currentPlayer = (UnoPlayer) players.get(currentIndex);
        GroupOfCards hand = currentPlayer.getHand();

        ArrayList<Card> discardCards = discardPile.getCards();
        UnoCard topCard = (UnoCard) discardCards.get(discardCards.size() - 1);

         System.out.println();
         System.out.println("----- TURN " + turnNumber + " -----");
         System.out.println("Top card : " + topCard);
         System.out.println("Current colour : " + currentColor);
         System.out.println("Current player: " + currentPlayer.getName());

        UnoCard chosenCard = null;
        for (Card c : hand.getCards()) {
            UnoCard uc = (UnoCard) c;
            if (uc.isPlayableOn(topCard)) {
                chosenCard = uc;
                break;
            }
        }

        if (chosenCard != null) {
            hand.removeCard(chosenCard);
            discardPile.addCard(chosenCard);
            currentColor = chosenCard.getColor();
            System.out.println(currentPlayer.getName() + " plays " + chosenCard);

            applyCardEffect(chosenCard);

        } else {
            Card drawn = drawPile.draw();
            if (drawn != null) {
                hand.addCard(drawn);
                System.out.println(currentPlayer.getName() + " draws a card.");
            }
        }
           int cardsLeft = hand.getCards().size();
          if (cardsLeft == 1 && !currentPlayer.hasSaidUno()) {
             currentPlayer.sayUno();
        }
       

        if (cardsLeft == 0) {
            winner = currentPlayer;
            return; 
        }

        moveToNextPlayer();
        turnNumber++;
    }

    /**
     * Apply the effect of a special UNO card.
     */
    public void applyCardEffect(UnoCard card) {

        Rank rank = card.getRank();

        switch (rank) {
            case SKIP:
                moveToNextPlayer();
                break;

            case REVERSE:
                direction = direction * -1;
                break;

            case DRAW_TWO:
                drawCardsForNextPlayer(2);
                break;

            case WILD:
                chooseNewColor(card);
                break;

            case WILD_DRAW_FOUR:
                chooseNewColor(card);
                drawCardsForNextPlayer(4);
                break;

            default:
                break;
        }
    }

    /**
     * Check if the round is over.
     */
    public boolean isRoundOver() {
        return winner != null;
    }

    /**
     * Move index to the next player based on direction.
     */
    public void moveToNextPlayer() {
        List<Player> players = getPlayers();
        int playerCount = players.size();
        currentIndex = (currentIndex + direction + playerCount) % playerCount;
    }

    /**
     * Draw a number of cards for the next player.
     */
    private void drawCardsForNextPlayer(int count) {
        List<Player> players = getPlayers();
        int playerCount = players.size();
        int nextIndex = (currentIndex + direction + playerCount) % playerCount;
        UnoPlayer nextPlayer = (UnoPlayer) players.get(nextIndex);

        for (int i = 0; i < count; i++) {
            Card c = drawPile.draw();
            if (c != null) {
                nextPlayer.getHand().addCard(c);
            }
        }

        System.out.println(nextPlayer.getName() + " draws " + count + " cards.");
    }

    /**
     * Ask the user to choose a new color when a Wild card is played.
     */
    private void chooseNewColor(UnoCard card) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a colour (RED, YELLOW, GREEN, BLUE): ");
        String input = scanner.nextLine().trim().toUpperCase();

        try {
            Color chosen = Color.valueOf(input);
            currentColor = chosen;
            card.setColor(chosen); 
        } catch (IllegalArgumentException ex) {
            currentColor = Color.RED;
            card.setColor(Color.RED);
        }

        System.out.println("Current colour is now: " + currentColor);
    }

}
