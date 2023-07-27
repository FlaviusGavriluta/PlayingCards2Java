package com.service.impl;

import com.model.Card;
import com.model.Deck;
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

public class DeckTest {
    private Deck emptyDeck;
    private Deck deckWithCards;

    @BeforeEach
    void setUp() {
        emptyDeck = new Deck(new ArrayList<>());
        deckWithCards = new Deck(List.of(new Card("3", "Spades"), new Card("7", "Hearts")));
    }

    @ParameterizedTest
    @MethodSource("provideDecks")
    void testDrawOne(Deck deck, boolean expectCardPresent) {
        // Act
        Optional<Card> card = deck.drawOne();
        // Verificăm că rezultatul este Optional.empty() când pachetul este gol
        assertEquals(expectCardPresent, card.isPresent());
    }

    private static Stream<Arguments> provideDecks() {
        return Stream.of(
                Arguments.of(new Deck(new ArrayList<>()), false),
                Arguments.of(new Deck(List.of(new Card("3", "Spades"), new Card("7", "Hearts"))), true)
        );
    }
}
