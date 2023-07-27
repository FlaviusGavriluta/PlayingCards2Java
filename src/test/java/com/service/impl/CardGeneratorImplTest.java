package com.service.impl;

import com.model.Card;
import com.model.DeckDescriptor;
import com.service.CardGenerator;
import com.service.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class CardGeneratorImplTest {
    private final Logger logger = new ConsoleLogger();
    private final CardGenerator cardGenerator = new CardGeneratorImpl(logger);
    private CardGeneratorImpl cardGeneratorMock;
    private Logger loggerMock;
    private ArgumentCaptor<String> infoCaptor;
    private int[] numbers;
    private String[] symbols;
    private String[] suits;

    @BeforeEach
    void setUp() {
        cardGeneratorMock = new CardGeneratorImpl(logger);
        numbers = new int[]{2, 3, 4};
        symbols = new String[]{"J", "Q", "K"};
        suits = new String[]{"Hearts", "Diamonds"};
        loggerMock = mock(Logger.class); // We create the mock object and store it in a class variable
        infoCaptor = ArgumentCaptor.forClass(String.class); // We create the captor for the info() method
        // Aceasta este instanta reala a CardGeneratorImpl
        cardGeneratorMock = new CardGeneratorImpl(loggerMock); // We create the object we want to test
    }

    @ParameterizedTest
    @MethodSource("provideDeckDescriptors")
    void generateCardsReturnsExpectedNumberOfCards(DeckDescriptor deckDescriptor, int expectedCardCount) {
        // Act
        List<Card> cards = cardGenerator.generate(deckDescriptor);

        // Assert
        assertEquals(expectedCardCount, cards.size());
    }

    static Stream<Arguments> provideDeckDescriptors() {
        return Stream.of(
                Arguments.of(new DeckDescriptor(new int[] { 2, 3, 4 }, new String[] { "J", "Q", "K" }, new String[] { "Hearts", "Diamonds" }), 12),
                Arguments.of(new DeckDescriptor(new int[] { 1, 2 }, new String[] { "A", "B" }, new String[] { "Clubs", "Spades" }), 8)
        );
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
        cardGeneratorMock.generateCards();
        // Verificăm că metoda logInfo() a fost apelată cu argumentul corect
        Mockito.verify(loggerMock).logInfo(infoCaptor.capture());
        assertEquals("Generating cards...", infoCaptor.getValue());
    }

    @Test
    void testLoggerErrorMethod() {
        // Act
        cardGeneratorMock.someMethodThatLogsError("This is an error message");
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
