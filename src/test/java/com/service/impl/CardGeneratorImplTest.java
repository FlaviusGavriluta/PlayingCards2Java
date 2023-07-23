package com.service.impl;

import com.model.Card;
import com.model.DeckDescriptor;
import com.service.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardGeneratorImplTest {
    private Logger logger;
    private CardGeneratorImpl cardGenerator;
    private int[] numbers;
    private String[] symbols;
    private String[] suits;

    @BeforeEach
    void setUp() {
        logger = new ConsoleLogger();
        cardGenerator = new CardGeneratorImpl(logger);
        numbers = new int[]{2, 3, 4};
        symbols = new String[]{"J", "Q", "K"};
        suits = new String[]{"Hearts", "Diamonds"};
    }

    private DeckDescriptor createDeckDescriptor() {
        return new DeckDescriptor(numbers, symbols, suits);
    }

    private List<Card> expectedCards() {
        List<Card> expectedCards = new ArrayList<>();
        for (int number : numbers) {
            for (String suit : suits) {
                expectedCards.add(new Card(String.valueOf(number), suit));
            }
        }
        for (String symbol : symbols) {
            for (String suit : suits) {
                expectedCards.add(new Card(symbol, suit));
            }
        }
        return expectedCards;
    }

    @Test
    void generateCardsReturnsExpectedNumberOfCards() {
        // Arrange
        DeckDescriptor deckDescriptor = createDeckDescriptor();
        int expectedCardCount = (deckDescriptor.numbers().length + deckDescriptor.symbols().length) * deckDescriptor.suits().length;

        // Act
        List<Card> cards = cardGenerator.generate(deckDescriptor);

        // Assert
        assertEquals(expectedCardCount, cards.size());
    }

    @Test
    void generateCardsGeneratesExpectedCards() {
        // Arrange
        DeckDescriptor deckDescriptor = createDeckDescriptor();
        List<Card> expectedCards = expectedCards();

        // Act
        List<Card> cards = cardGenerator.generate(deckDescriptor);

        // Assert
        assertEquals(expectedCards.size(), cards.size());
        assertTrue(expectedCards.containsAll(cards) && cards.containsAll(expectedCards));
    }

    @Test
    void generateCardsDeckDescriptorIsNullReturnsEmptyList() {
        // Act
        List<Card> cards = cardGenerator.generate(null);

        // Assert
        assertTrue(cards.isEmpty());
    }
}
