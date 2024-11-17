import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;

    public Game(String[] playerNames, String[] ranks, String[] suits, int[] values) {
        // initialize deck
        deck = new Deck(ranks, suits, values);
        // get the players
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        // deal initial cards (7 cards per player for uno)
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.deal());
            }
        }

    }

    // prints out the game instructions when the game is started
    public void printInstructions() {
        System.out.println("Welcome to UNO!");
        System.out.println("Players take turns matching the top card's color or number.");
        System.out.println("Special cards like Skip and Draw Two will alter gameplay.");
        System.out.println("First to discard all cards wins!");
    }

    public void playGame() {
        printInstructions();
        // create scanner to get user input for their choice
        // of cards
        Scanner scanner = new Scanner(System.in);
        // get the starting top card for the deck
        Card topCard = deck.deal();
        System.out.println("Starting card: " + topCard);

        // basically the game loop runs until a player's hand is empty
        // or if all the cards have been used
        // which is when there will be a return statement
        while (true) {
            for (Player player : players) {
                System.out.println(player.getName() + "'s turn.");
                System.out.println("Your hand: " + player.getHand());
                System.out.println("Top card: " + topCard);
                System.out.println("Choose a card to play or draw a card (enter index or 'd'): ");
                String input = scanner.nextLine();

                System.out.println("--------------");

                // do the game stuff here
                // if they draw a card, draw a card
                // otherwise check that their choice of card is valid
                // also check if the game has been won --> if someone's hand is empty

                if (player.getHand().isEmpty()) {
                    System.out.println(player.getName() + " wins!");
                    scanner.close();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        // all the card values for uno
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Draw Two"};
        String[] suits = {"Red", "Green", "Blue", "Yellow"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 20};

        String[] playerNames = {"User 1", "User 2"};
        Game unoGame = new Game(playerNames, ranks, suits, values);
        unoGame.playGame();
    }
}
