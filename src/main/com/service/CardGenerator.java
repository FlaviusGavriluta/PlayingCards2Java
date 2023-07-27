package com.service;

import com.model.Card;
import com.model.DeckDescriptor;

import java.util.List;

public interface CardGenerator {
    List<Card> generate(DeckDescriptor deckDescriptor);
}



