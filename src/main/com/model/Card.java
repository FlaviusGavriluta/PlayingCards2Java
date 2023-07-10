package com.model;

public record Card(String number, String symbol, String suit) {
    public String title() {
        return String.format("%s of %s", symbol, suit);
    }

    @Override
    public String toString() {
        return title();
    }
}