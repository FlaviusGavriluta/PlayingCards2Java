package service;

import model.DeckDescriptor;
import model.Card;

import java.util.List;

public interface CardGenerator {
    List<Card> generate(DeckDescriptor deckDescriptor);
}



