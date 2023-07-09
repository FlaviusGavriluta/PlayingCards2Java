package com.service.impl;

import com.model.DeckDescriptor;
import com.service.CardGenerator;
import com.service.DeckBuilder;
import com.model.Deck;
import com.model.Card;

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
