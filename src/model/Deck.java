package com.codecool.playingcards.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Deck {
    private static final Random RANDOM = new Random();

    private final List<Card> cards;
    private final List<Card> drawn;

    public int getCardCount() {
        return cards.size();
    }

    public Deck(List<Card> cards) {
        this.cards = cards;
        this.drawn = new ArrayList<>();
    }

    public Optional<Card> drawOne() {
        if (cards.isEmpty()) {
            return Optional.empty();
        }
        Card card = cards.get(RANDOM.nextInt(0, cards.size()));
        handleDraw(card);
        return Optional.of(card);
    }

    private void handleDraw(Card card) {
        cards.remove(card);
        drawn.add(card);
    }

    public void reset() {
        List<Card> current = new ArrayList<>(cards);
        cards.clear();
        cards.addAll(current);
        cards.addAll(drawn);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                ", drawn=" + drawn +
                '}';
    }
}
