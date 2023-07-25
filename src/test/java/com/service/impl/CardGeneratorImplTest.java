package com.service.impl;

import com.model.Card;
import com.model.DeckDescriptor;
import com.service.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardGeneratorImplTest {
    private Logger logger;
    private Logger loggerMock;
    private ArgumentCaptor<String> infoCaptor;
    private ArgumentCaptor<String> errorCaptor;
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
        loggerMock = mock(Logger.class); // We create the mock object and store it in a class variable
        infoCaptor = ArgumentCaptor.forClass(String.class); // We create the captor for the info() method
        errorCaptor = ArgumentCaptor.forClass(String.class); // We create the captor for the error() method
        // Aceasta este instanta reala a CardGeneratorImpl
        cardGenerator = new CardGeneratorImpl(loggerMock); // We create the object we want to test
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
    void testLoggerInfoMethod() {
        // Act
        cardGenerator.generateCards();
        // Verificăm că metoda logInfo() a fost apelată cu argumentul corect
        Mockito.verify(loggerMock).logInfo(infoCaptor.capture());
        assertEquals("Generating cards...", infoCaptor.getValue());
    }
    @Test
    void testLoggerErrorMethod() {
        // Act
        cardGenerator.someMethodThatLogsError("This is an error message");
        // Verificăm că metoda logError() a fost apelată cu argumentul corect
        Mockito.verify(loggerMock).logError(infoCaptor.capture());
        assertEquals("This is an error message", infoCaptor.getValue());
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
