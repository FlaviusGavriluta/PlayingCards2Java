package com.service.impl;

import com.model.Card;
import com.model.DeckDescriptor;
import com.service.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardGeneratorImplTest {
    @Test
    void generateCardsReturnsExpectedNumberOfCards() {
        // Arrange
        Logger logger = new ConsoleLogger();
        CardGeneratorImpl cardGenerator = new CardGeneratorImpl(logger);
        int[] numbers = {2, 3, 4};
        String[] symbols = {"J", "Q", "K"};
        String[] suits = {"Hearts", "Diamonds"};

        DeckDescriptor deckDescriptor = new DeckDescriptor(numbers, symbols, suits);
        int expectedCardCount = (deckDescriptor.numbers().length + deckDescriptor.symbols().length) * deckDescriptor.suits().length;

        // Act
        List<Card> cards = cardGenerator.generate(deckDescriptor);

        // Assert
        assertEquals(expectedCardCount, cards.size());
    }
}
