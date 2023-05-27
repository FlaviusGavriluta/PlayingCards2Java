package com.codecool.playingcards.model;

import java.util.Objects;

public class Card {

    private final String symbol;
    private final String suit;
    private final String title;

    public Card(String symbol, String suit) {
        this.symbol = symbol;
        this.suit = suit;
        this.title = this.symbol + " of " + suit;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSuit() {
        return suit;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit.equals(card.suit) && symbol.equals(card.symbol);
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