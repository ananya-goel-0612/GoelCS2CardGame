import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        // Initialize cards
        cards = new ArrayList<Card>();
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                // Add a new card with different ranks and values to each suit
                cards.add(new Card(ranks[i], suit, values[i]));
            }
        }
        cardsLeft = cards.size();
        // Shuffle deck after you add everything to cards
        shuffle();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }


    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        // Returns null if there's no cards left
        if (isEmpty()) {
            return null;
        }
        return cards.get(--cardsLeft);
    }

    public void shuffle() {
        // Collections has a shuffle method that we can use on cards
        // So this shuffles the deck
        cardsLeft = cards.size();
        // for i = last index of the deck down to 0
        for (int i = cardsLeft; i > 0; i--) {
            // Generate a random integer r (using Math.random) between 0 and i, inclusive
            int r = (int) (Math.random() * cardsLeft);
            // Exchange cards[i] and cards[r]
            Card temp = cards.get(i);
            cards.set(i, cards.get(r));
            cards.set(r, temp);
        }
    }
}

