package com.codecool.playingcards.service;

import com.codecool.playingcards.model.DeckDescriptor;
import com.codecool.playingcards.model.Card;

import java.util.List;

public interface CardGenerator {
    List<Card> generate(DeckDescriptor deckDescriptor);
}



