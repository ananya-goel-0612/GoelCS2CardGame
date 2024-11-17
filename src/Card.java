public class Card {

    // rank = type (number, skip, draw)
    private String rank;
    // suit = color
    private String suit;
    // value = index of card
    private int value;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // this is where each of the cards gets printed out so that the
    // user can choose what card to play
    public String toString() {
        return rank + " of " + suit;
    }
}
