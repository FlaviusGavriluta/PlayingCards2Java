package com.service.impl;

import com.model.DeckDescriptor;
import com.service.CardGenerator;
import com.service.Logger;
import com.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGeneratorImpl implements CardGenerator {
    private final Logger logger;

    public CardGeneratorImpl(Logger logger) {
        this.logger = logger;
    }

    public List<Card> generate(DeckDescriptor descriptor) {
        if (descriptor == null) {
            return Collections.emptyList();
        }

        List<Card> generatedCards = new ArrayList<>();
        int[] numbers = descriptor.getNumbers();
        String[] suits = descriptor.getSuits();

        for (int number : numbers) {
            for (String suit : suits) {
                Card card = new Card(numberToString(number), suit);
                generatedCards.add(card);
            }
        }

        return generatedCards;
    }

    private String numberToString(int number) {
        if (number >= 2 && number <= 10) {
            return Integer.toString(number);
        } else {
            switch (number) {
                case 1:
                    return "Ace";
                case 11:
                    return "Jack";
                case 12:
                    return "Queen";
                case 13:
                    return "King";
                default:
                    throw new IllegalArgumentException("Invalid card number: " + number);
            }
        }
    }
}
