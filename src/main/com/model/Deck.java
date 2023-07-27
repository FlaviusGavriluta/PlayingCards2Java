package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Deck {
    private final List<Card> cards;
    private final List<Card> drawn;
    private Random random;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.drawn = new ArrayList<>();
        this.random = new Random();
    }

    public int getCardCount() {
        return cards.size();
    }

    public Optional<Card> drawOne() {
        if (cards.isEmpty()) {
            return Optional.empty();
        }
        int index = random.nextInt(cards.size());
        Card drawnCard = cards.remove(index);
        return Optional.of(drawnCard);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                ", drawn=" + drawn +
                '}';
    }
}
