package com.codecool.playingcards.builder;

import com.codecool.playingcards.interfaces.CardGenerator;
import com.codecool.playingcards.interfaces.DeckBuilder;
import com.codecool.playingcards.model.Card;
import com.codecool.playingcards.model.Deck;

import java.util.List;

public class GermanDeckBuilder implements DeckBuilder {
    private static final int[] NUMBERS = {7, 8, 9, 10};
    private static final String[] SYMBOLS = {"Unter", "Ober", "King", "Ace"};
    private static final String[] SUITS = {"Acorns", "Leaves", "Hearts", "Bells"};
    private final CardGenerator cardGenerator;

    public GermanDeckBuilder(CardGeneratorImpl cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public Deck createDeck() {
        List<Card> cards = cardGenerator.generate(NUMBERS, SYMBOLS, SUITS);
        return new Deck(cards);
    }
}
