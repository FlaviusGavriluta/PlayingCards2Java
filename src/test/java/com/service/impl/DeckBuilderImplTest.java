package com.service.impl;

import com.model.Card;
import com.model.Deck;
import com.model.DeckDescriptor;
import com.service.CardGenerator;
import com.service.DeckBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void createDeckReturnsNewDeckWithGeneratedCards() {
        // Arrange
        List<Card> original = List.of(new Card("Ace", "Spades"), new Card("Ace", "Hearts"));
        List<Card> cards = new ArrayList<>(original);

        when(cardGeneratorMock.generate(deckDescriptor)).thenReturn(cards); //When generate is called on the cardGeneratorMock, we return the cards list

        DeckBuilder deckBuilder = new DeckBuilderImpl(cardGeneratorMock, deckDescriptor);

        // Act
        Deck deck = deckBuilder.createDeck();
        int cardCount = deck.getCardCount();

        // Assert
        Optional<Card> drawn = deck.drawOne();
        while (drawn.isPresent()) {
            assertTrue(original.contains(drawn.get()));
            drawn = deck.drawOne();
        }

        assertEquals(2, cardCount);
    }
}