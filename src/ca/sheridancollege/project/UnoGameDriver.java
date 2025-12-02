/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author Halaa
 */
import java.util.ArrayList;

public class UnoGameDriver {
    
    public static void main(String[] args) {

        UnoGame game = new UnoGame("UNO - Byte Me");

        ArrayList<Player> players = new ArrayList<>();
        players.add(new UnoPlayer("Player 1"));
        players.add(new UnoPlayer("Player 2"));
        players.add(new UnoPlayer("Player 3"));
        players.add(new UnoPlayer("Player 4"));

        game.setPlayers(players);

        game.play();
    }
}
