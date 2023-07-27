package com.service.impl;

import com.model.Card;
import com.model.DeckDescriptor;
import com.service.CardGenerator;
import com.service.Logger;

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

    // Metoda care generează cărțile și înregistrează un mesaj de informare
    public List<Card> generateCards(String message) {
        logger.logInfo(message);
        // Logică de generare a cărților
        return new ArrayList<>();
    }

    // Metoda care înregistrează un mesaj de eroare
    public void someMethodThatLogsError(String errorMessage) {
        logger.logError(errorMessage);
    }
}
