package com.service.impl;

import com.model.Card;
import com.model.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck emptyDeck;
    private Deck deckWithCards;

    @BeforeEach
    void setUp() {
        emptyDeck = new Deck(new ArrayList<>());
        deckWithCards = new Deck(List.of(new Card("3", "Spades"), new Card("7", "Hearts")));
    }

    @Test
    void testDrawOneWhenDeckIsEmpty() {
        // Act
        Optional<Card> card = emptyDeck.drawOne();
        // Verificăm că rezultatul este Optional.empty() când pachetul este gol
        assertTrue(card.isEmpty());
    }

    @Test
    void testDrawOneWhenDeckIsNotEmpty() {
        // Act
        Optional<Card> card = deckWithCards.drawOne();
        // Verificăm că rezultatul este Optional<Card> (adică că s-a extras o carte) când pachetul are cărți
        assertTrue(card.isPresent());
    }
}
