import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;

    public Game(String[] playerNames, String[] ranks, String[] suits, int[] values) {
        // Initialize deck
        deck = new Deck(ranks, suits, values);
        // Get the players
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        // Deal initial cards (7 cards per player for uno)
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.deal());
            }
        }
    }

    // Prints out the game instructions when the game is started
    public void printInstructions() {
        System.out.println("Welcome to UNO!");
        System.out.println("Players take turns matching the top card's color or number.");
        System.out.println("Special cards like Skip and Draw Two will alter gameplay.");
        System.out.println("First to discard all cards wins!");
    }

    public void playGame() {
        printInstructions();
        // Create scanner to get user input for their choice
        // Of cards
        Scanner scanner = new Scanner(System.in);
        // Get the starting top card for the deck
        Card topCard = deck.deal();
        System.out.println("Starting card: " + topCard);
        int currentPlayerIndex = 0;
        boolean skipNextTurn = false;

        // Basically the game loop runs until a player's hand is empty
        // Or if all the cards have been used
        // Which is when there will be a return statement
        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            if (skipNextTurn) {
                System.out.println(currentPlayer.getName() + " is skipped!");
                skipNextTurn = false;
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }

            System.out.println(currentPlayer.getName() + "'s turn.");
            System.out.println(currentPlayer.getName() + "'s hand: " + currentPlayer.getHand());
            System.out.println("Top card: " + topCard);
            System.out.println("Choose a card to play or draw a card (enter index or 'd'): ");
            String input = scanner.nextLine();

            System.out.println("--------------");

            // Do the game stuff here
            // If they choose to draw a card, draw a card and add it to their hand
            if (input.equals("d")) {
                // Draw a card
                Card drawnCard = deck.deal();
                // Print out the drawn card and add it to the player's hand
                // As long as the drawn card is not null
                if (drawnCard != null) {
                    System.out.println("You drew: " + drawnCard);
                    currentPlayer.addCard(drawnCard);
                }
                // Otherwise print out a message saying that the deck is empty
                else {
                    System.out.println("Deck is empty. Skipping draw.");
                }
            }
            // Otherwise check that their choice of card is valid
            else {
                int index = getValidCardIndex(input, currentPlayer.getHand());
                // Check that the input is a valid number
                if (index == -1) {
                    System.out.println("Invalid input. Skipping turn.");
                }
                // If it was a valid move, set the top card equal to their played card
                else if (isValidMove(currentPlayer.getHand().get(index), topCard)) {
                    topCard = playCard(currentPlayer, index);
                }
                // Otherwise it was an invalid move
                else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            // Also check if the game has been won --> if someone's hand is empty
            if (currentPlayer.getHand().isEmpty()) {
                System.out.println(currentPlayer.getName() + " wins!");
                scanner.close();
                return;
            }
        }
    }

    private int getValidCardIndex(String input, ArrayList<Card> hand) {
        // Found this documentation for converting from strings to ints
        // On Stack Overflow
        // https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
        try {
            int index = Integer.parseInt(input);
            if (index >= 0 && index < hand.size()) {
                return index;
            }
        }
        catch (NumberFormatException e) {
            // This means that the input is not a valid number
        }
        // Return -1 if input is invalid
        return -1;
    }

    // Checks if the numbers or colors of the top card and played card match
    // Returns true or false
    private boolean isValidMove(Card playedCard, Card topCard) {
        return playedCard.getRank().equals(topCard.getRank()) ||
                playedCard.getSuit().equals(topCard.getSuit());
    }

    // Removes a card from the player's hand once they've played it
    // Prints out which card they played
    // Returns the card that they played
    private Card playCard(Player player, int index) {
        Card playedCard = player.getHand().remove(index);
        System.out.println("You played: " + playedCard);
        return playedCard;
    }

    // Main function
    public static void main(String[] args) {
        // All the card values for uno
        String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Draw Two"};
        String[] suits = {"Red", "Green", "Blue", "Yellow"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 20};

        String[] playerNames = {"User 1", "User 2"};
        Game unoGame = new Game(playerNames, ranks, suits, values);
        unoGame.playGame();
    }
}
