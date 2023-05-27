package com.codecool.playingcards.builder;

import com.codecool.playingcards.interfaces.CardGenerator;
import com.codecool.playingcards.interfaces.DeckBuilder;
import com.codecool.playingcards.model.Card;
import com.codecool.playingcards.model.Deck;

import java.util.List;

public class FrenchDeckBuilder implements DeckBuilder {
    private static final int[] NUMBERS = {2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final String[] SYMBOLS = {"Jack", "Queen", "King", "Ace"};
    private static final String[] SUITS = {"Clubs", "Spades", "Hearts", "Diamonds"};
    private final CardGenerator cardGenerator;

    public FrenchDeckBuilder(CardGeneratorImpl cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public Deck createDeck() {
        List<Card> cards = cardGenerator.generate(NUMBERS, SYMBOLS, SUITS);
        return new Deck(cards);
    }
}
