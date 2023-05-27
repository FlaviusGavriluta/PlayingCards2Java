package com.codecool.playingcards.service.impl;

import com.codecool.playingcards.model.DeckDescriptor;
import com.codecool.playingcards.service.CardGenerator;
import com.codecool.playingcards.service.DeckBuilder;
import com.codecool.playingcards.model.Deck;
import com.codecool.playingcards.model.Card;

import java.util.List;

public class DeckBuilderImpl implements DeckBuilder {
    private final CardGenerator cardGenerator;

    private final DeckDescriptor deckDescriptor;

    public DeckBuilderImpl(CardGenerator cardGenerator, DeckDescriptor deckDescriptor) {
        this.cardGenerator = cardGenerator;
        this.deckDescriptor = deckDescriptor;
    }

    @Override
    public Deck createDeck() {
        List<Card> cards = cardGenerator.generate(deckDescriptor);
        return new Deck(cards);
    }
}
