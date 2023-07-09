package com.service;

import com.model.DeckDescriptor;
import com.model.Card;

import java.util.List;

public interface CardGenerator {
    List<Card> generate(DeckDescriptor deckDescriptor);
}



