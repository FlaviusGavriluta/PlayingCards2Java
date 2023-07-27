package com.service.impl;

import com.model.Card;
import com.model.DeckDescriptor;
import com.service.Logger;

import org.junit.jupiter.api.BeforeEach;
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
    private CardGeneratorImpl cardGenerator;
    private Logger loggerMock;
    private ArgumentCaptor<String> infoCaptor;
    private int[] numbers;
    private String[] symbols;
    private String[] suits;

    @BeforeEach
    void setUp() {
        numbers = new int[]{2, 3, 4};
        symbols = new String[]{"J", "Q", "K"};
        suits = new String[]{"Hearts", "Diamonds"};
        loggerMock = mock(Logger.class); // We create the mock object and store it in a class variable
        infoCaptor = ArgumentCaptor.forClass(String.class); // We create the ArgumentCaptor object
        // Aceasta este instanta reala a CardGeneratorImpl
        cardGenerator = new CardGeneratorImpl(loggerMock); // We create the object we want to test
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
                Arguments.of(new DeckDescriptor(new int[]{2, 3, 4}, new String[]{"J", "Q", "K"}, new String[]{"Hearts", "Diamonds"}), 12),
                Arguments.of(new DeckDescriptor(new int[]{1, 2}, new String[]{"A", "B"}, new String[]{"Clubs", "Spades"}), 8)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestDataForInfoMethod")
    void testLoggerInfoMethod(String message) {
        // Act
        cardGenerator.generateCards(message);
        // We check that the logInfo() method was called with the correct argument
        Mockito.verify(loggerMock).logInfo(infoCaptor.capture());
        assertEquals(message, infoCaptor.getValue()); // We check that the message is the one we expect
    }

    static Stream<Arguments> provideTestDataForInfoMethod() {
        return Stream.of(
                Arguments.of("Generating cards..."),
                Arguments.of("This is an info message"),
                Arguments.of("This is another info message")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestDataForErrorMethod")
    void testLoggerErrorMethod(String errorMessage) {
        // Act
        cardGenerator.someMethodThatLogsError(errorMessage);
        // Verificăm că metoda logError() a fost apelată cu argumentul corect
        Mockito.verify(loggerMock).logError(infoCaptor.capture());
        assertEquals(errorMessage, infoCaptor.getValue());
    }

    static Stream<Arguments> provideTestDataForErrorMethod() {
        return Stream.of(
                Arguments.of("This is an error message"),
                Arguments.of("This is another error message")
        );
    }

    @ParameterizedTest
    @MethodSource("provideDeckDescriptorAndExpectedCards")
    void generateCardsGeneratesExpectedCards(DeckDescriptor deckDescriptor, List<Card> expectedCards) {
        // Act
        List<Card> cards = cardGenerator.generate(deckDescriptor);
        // Assert
        assertEquals(expectedCards.size(), cards.size());
        assertTrue(expectedCards.containsAll(cards) && cards.containsAll(expectedCards));
    }

    private static Stream<Arguments> provideDeckDescriptorAndExpectedCards() {
        List<Card> expectedCards1 = new ArrayList<>();
        expectedCards1.add(new Card("2", "Hearts"));
        expectedCards1.add(new Card("2", "Diamonds"));
        expectedCards1.add(new Card("3", "Hearts"));
        expectedCards1.add(new Card("3", "Diamonds"));

        List<Card> expectedCards2 = new ArrayList<>();
        expectedCards2.add(new Card("J", "Hearts"));
        expectedCards2.add(new Card("J", "Diamonds"));
        expectedCards2.add(new Card("Q", "Hearts"));
        expectedCards2.add(new Card("Q", "Diamonds"));

        DeckDescriptor deckDescriptor1 = new DeckDescriptor(new int[]{2, 3}, new String[]{}, new String[]{"Hearts", "Diamonds"});
        DeckDescriptor deckDescriptor2 = new DeckDescriptor(new int[]{}, new String[]{"J", "Q"}, new String[]{"Hearts", "Diamonds"});

        return Stream.of(
                Arguments.of(deckDescriptor1, expectedCards1),
                Arguments.of(deckDescriptor2, expectedCards2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNullDeckDescriptor")
    void generateCardsDeckDescriptorIsNullReturnsEmptyList(DeckDescriptor deckDescriptor) {
        // Act
        List<Card> cards = cardGenerator.generate(deckDescriptor);
        // Assert
        assertTrue(cards.isEmpty());
    }

    private static Stream<Arguments> provideNullDeckDescriptor() {
        return Stream.of(
                Arguments.of((DeckDescriptor) null)
        );
    }
}
