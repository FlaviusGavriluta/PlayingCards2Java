package com.codecool.playingcards.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class Deck {
    private static final Random Random = new Random();

    private final ArrayList<Card> cards;
    private final ArrayList<Card> drawn;

    public int getCardCount() {
        return cards.size();
    }

    public Deck(ArrayList<Card> cards) {
        this.cards = cards;
        this.drawn = new ArrayList<>();
    }

    public Card drawOne() {
        Card card = cards.get(Random.nextInt(0, cards.size() - 1));
        HandleDraw(card);
        return card;
    }

    private void HandleDraw(Card card) {
        cards.remove(card);
        drawn.add(card);
    }

    public void reset() {
        ArrayList<Card> current = new ArrayList<>(cards);
        cards.clear();
        cards.addAll(Stream.concat(current.stream(), drawn.stream()).toList());
    }
}
