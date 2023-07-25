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
        String[] symbols = descriptor.getSymbols();
        String[] suits = descriptor.getSuits();

        for (int number : numbers) {
            for (String suit : suits) {
                Card card = new Card(String.valueOf(number), suit);
                System.out.println(card);
                generatedCards.add(card);
            }
        }

        for (String symbol : symbols) {
            for (String suit : suits) {
                Card card = new Card(symbol, suit);
                System.out.println(card);
                generatedCards.add(card);
            }
        }

        return generatedCards;
    }

    private String numberToString(int number) {
        if (number >= 2 && number <= 10) {
            return Integer.toString(number);
        } else if (number >= 11 && number <= 13) {
            switch (number) {
                case 11:
                    return "J";
                case 12:
                    return "Q";
                case 13:
                    return "K";
            }
        } else if (number == 1) {
            return "Ace";
        }

        throw new IllegalArgumentException("Invalid card number: " + number);
    }

    // Metoda care generează cărțile și înregistrează un mesaj de informare
    public List<Card> generateCards() {
        logger.logInfo("Generating cards...");
        // Logică de generare a cărților
        return new ArrayList<>();
    }
    // Metoda care înregistrează un mesaj de eroare
    public void someMethodThatLogsError(String errorMessage) {
        logger.logError(errorMessage);
    }
}
