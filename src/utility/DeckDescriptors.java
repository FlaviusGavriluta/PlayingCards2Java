package utility;

import model.DeckDescriptor;

public final class DeckDescriptors {
    private static final int[] FRENCH_NUMBERS = {2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final String[] FRENCH_SYMBOLS = {"Jack", "Queen", "King", "Ace"};
    private static final String[] FRENCH_SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final int[] GERMAN_NUMBERS = {7, 8, 9, 10};
    private static final String[] GERMAN_SYMBOLS = {"Unter", "Ober", "King", "Ace"};
    private static final String[] GERMAN_SUITS = {"Acorns", "Leaves", "Hearts", "Bells"};

    public static final DeckDescriptor FRENCH_DECK_DESCRIPTOR = new DeckDescriptor(FRENCH_NUMBERS, FRENCH_SYMBOLS, FRENCH_SUITS);
    public static final DeckDescriptor GERMAN_DECK_DESCRIPTOR = new DeckDescriptor(GERMAN_NUMBERS, GERMAN_SYMBOLS, GERMAN_SUITS);
}
