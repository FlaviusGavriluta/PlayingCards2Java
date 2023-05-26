package com.codecool.playingcards.model;

import java.util.Objects;

public class Card {

    private final String symbol;
    private final String suit;
    private final String title;

    public Card(String symbol, String suit) {
        this.symbol = symbol;
        this.suit = suit;
        this.title = this.symbol + " of " + this.suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(symbol, card.symbol) && suit.equals(card.suit) && Objects.equals(title, card.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, suit);
    }

    @Override
    public String toString() {
        return title;
    }
}