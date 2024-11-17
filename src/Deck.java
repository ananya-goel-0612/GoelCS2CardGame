import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] values) {
        // initialize cards
        cards = new ArrayList<Card>();
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                // add a new card with different ranks and values to each suit
                cards.add(new Card(ranks[i], suit, values[i]));
            }
        }
        cardsLeft = cards.size();
        // shuffle deck after you add everything to cards
        shuffle();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        // returns null if there's no cards left
        if (isEmpty()) {
            return null;
        }
        return cards.get(--cardsLeft);
    }

    public void shuffle() {
        // collections has a shuffle method that we can use on cards
        // so this shuffles the deck
        Collections.shuffle(cards);
        cardsLeft = cards.size();
    }
}

