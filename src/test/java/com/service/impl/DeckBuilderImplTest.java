package com.service.impl;

import com.model.Card;
import com.model.Deck;
import com.model.DeckDescriptor;
import com.service.CardGenerator;
import com.service.DeckBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeckBuilderImplTest {
    private CardGenerator cardGeneratorMock;
    private DeckDescriptor deckDescriptor;

    @BeforeEach
        // This method runs before every test
    void beforeEach() {
        cardGeneratorMock = mock(CardGenerator.class); // We create the mock object and store it in a class variable
        deckDescriptor = new DeckDescriptor(new int[0], new String[0], new String[0]); //DeckDescriptor contents doesn't matter - we just need to create a new DeckDescriptor object
    }

    @ParameterizedTest
    @MethodSource("provideDecksAndExpectedCardCounts")
    void createDeckReturnsNewDeckWithGeneratedCards(List<Card> cards, int expectedCardCount) {
        // Arrange
        when(cardGeneratorMock.generate(deckDescriptor)).thenReturn(cards); //When generate is called on the cardGeneratorMock, we return the cards list
        DeckBuilder deckBuilder = new DeckBuilderImpl(cardGeneratorMock, deckDescriptor);

        // Act
        Deck deck = deckBuilder.createDeck();
        int cardCount = deck.getCardCount();

        // Assert
        Optional<Card> drawn = deck.drawOne();
        while (drawn.isPresent()) {
            assertTrue(cards.contains(drawn.get()));
            drawn = deck.drawOne();
        }

        assertEquals(expectedCardCount, cardCount);
    }

    private static Stream<Arguments> provideDecksAndExpectedCardCounts() {
        List<Card> deck1 = List.of(new Card("Ace", "Spades"), new Card("Ace", "Hearts"));
        List<Card> deck2 = List.of(new Card("3", "Spades"), new Card("7", "Hearts"), new Card("A", "Diamonds"));
        return Stream.of(
                Arguments.of(deck1, 2),
                Arguments.of(deck2, 3)
        );
    }
}