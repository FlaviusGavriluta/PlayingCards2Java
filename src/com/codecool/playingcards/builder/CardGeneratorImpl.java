package com.codecool.playingcards.builder;

import com.codecool.playingcards.interfaces.CardGenerator;
import com.codecool.playingcards.interfaces.Logger;
import com.codecool.playingcards.model.Card;

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
            addNumberedCards(cards, suit, numbers);
            addCourtCards(cards, suit, symbols);
        }
        return cards;
    }

    private void addNumberedCards(List<Card> cards, String suit, int[] numbers) {
        for (int number : numbers) {
            Card card = new Card(Integer.toString(number), suit);
            logger.logInfo(String.format("Generate card %s", card));
            cards.add(card);

        }
    }

    private void addCourtCards(List<Card> cards, String suit, String[] symbols) {
        for (String symbol : symbols) {
            Card card = new Card(symbol, suit);
            logger.logInfo(String.format("Generate card %s", card));
            cards.add(card);
        }
    }

}
