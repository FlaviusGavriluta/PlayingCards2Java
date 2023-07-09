package com.service.impl;

import com.model.DeckDescriptor;
import com.service.CardGenerator;
import com.service.Logger;
import com.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardGeneratorImpl implements CardGenerator {
    private final Logger logger;

    public CardGeneratorImpl(Logger logger) {
        this.logger = logger;
    }

    public List<Card> generate(DeckDescriptor deckDescriptor) {
        int[] numbers = deckDescriptor.numbers();
        String[] symbols = deckDescriptor.symbols();
        String[] suits = deckDescriptor.suits();

        return generateCards(numbers, symbols, suits);
    }

    private List<Card> generateCards(int[] numbers, String[] symbols, String[] suits) {
        List<Card> cards = new ArrayList<>();

        for (String suit : suits) {
            addCards(cards, symbols, suit);
            addCards(cards, numbers, suit);
        }
        return cards;
    }

    private void addCards(List<Card> cards, int[] values, String suit) {
        for (int value : values) {
            Card card = new Card(Integer.toString(value), suit);
            logger.logInfo(String.format("Generate card %s", card));
            cards.add(card);
        }
    }

    private void addCards(List<Card> cards, String[] values, String suit) {
        for (String value : values) {
            Card card = new Card(value, suit);
            logger.logInfo(String.format("Generate card %s", card));
            cards.add(card);
        }
    }
}
