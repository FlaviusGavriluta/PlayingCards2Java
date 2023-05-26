package com.codecool.playingcards.builder;

import com.codecool.playingcards.model.Card;
import com.codecool.playingcards.model.Deck;

import java.util.ArrayList;

public class GermanDeckBuilder {
    private static final int[] NUMBERS = {7, 8, 9, 10};
    private static final String[] SYMBOLS = {"Unter", "Ober", "King", "Ace"};
    private static final String[] SUITS = {"Acorns", "Leaves", "Hearts", "Bells"};

    public Deck createDeck() {
        return generateDeck();
    }

    private Deck generateDeck() {
        ArrayList<Card> cards = new ArrayList<>();

        for (String suit : SUITS) {
            addNumberedCards(cards, suit);
            addCourtCards(cards, suit);
        }

        return new Deck(cards);
    }

    private void addNumberedCards(ArrayList<Card> cards, String suit) {
        for (int number : NUMBERS) {
            Card card = new Card(Integer.toString(number), suit);
            cards.add(card);
        }
    }

    private void addCourtCards(ArrayList<Card> cards, String suit) {
        for (String symbol : SYMBOLS) {
            Card card = new Card(symbol, suit);
            cards.add(card);
        }
    }
}
