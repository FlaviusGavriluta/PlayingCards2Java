package com.codecool.playingcards.interfaces;

import com.codecool.playingcards.builder.DeckDescriptor;
import com.codecool.playingcards.model.Card;

import java.util.List;

public interface CardGenerator {
    List<Card> generate(DeckDescriptor deckDescriptor);
}



