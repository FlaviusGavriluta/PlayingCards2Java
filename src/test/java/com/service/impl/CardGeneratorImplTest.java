package com.service.impl;

import com.model.Card;
import com.model.DeckDescriptor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardGeneratorImplTest {
    @Test
    void testGenerate_ShouldGenerateCorrectNumberOfCards() {
        // Arrange
        CardGeneratorImpl generator = new CardGeneratorImpl(new ConsoleLogger());
        DeckDescriptor descriptor = new DeckDescriptor(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
                new String[]{},
                new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}
        );

        // Act
        List<Card> cards = generator.generate(descriptor);

        // Assert
        assertEquals(52, cards.size());
    }


    @Test
    void testGenerate_ShouldGenerateExpectedCards() {
        // Arrange
        CardGeneratorImpl generator = new CardGeneratorImpl(new ConsoleLogger());
        DeckDescriptor descriptor = new DeckDescriptor(new int[]{7}, new String[]{}, new String[]{"Hearts", "Diamonds", "Clubs", "Spades"});

        // Act
        List<Card> cards = generator.generate(descriptor);

        // Assert
        assertNotNull(cards);
        assertEquals(4, cards.size());
        assertTrue(cards.contains(new Card("7", "Hearts")));
        assertTrue(cards.contains(new Card("7", "Diamonds")));
        assertTrue(cards.contains(new Card("7", "Clubs")));
        assertTrue(cards.contains(new Card("7", "Spades")));
    }

    @Test
    void testGenerate_WithNullDescriptor_ShouldReturnEmptyList() {
        // Arrange
        CardGeneratorImpl generator = new CardGeneratorImpl(new ConsoleLogger());

        // Act
        List<Card> cards = generator.generate(null);

        // Assert
        assertNotNull(cards);
        assertTrue(cards.isEmpty());
    }
}
