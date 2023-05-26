package com.codecool.playingcards;

import com.codecool.playingcards.model.*;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Deck frenchDeck = generateFrenchDeck();
        System.out.println("French deck created. Card count: " + frenchDeck.getCardCount()); // 52

        Card card = frenchDeck.drawOne();
        System.out.println(card + "  was drawn. Card count: " + frenchDeck.getCardCount()); //51

        frenchDeck.reset();
        System.out.println("Deck has been reset. Card count: " + frenchDeck.getCardCount()); //52
    }

    private static Deck generateGermanDeck() {
        int[] numbers = {7, 8, 9, 10};
        String[] symbols = {"Unter", "Ober", "King", "Ace"};
        String[] suits = {"Acorns", "Leaves", "Hearts", "Bells"};

        return generateDeck(numbers, symbols, suits);
    }

    private static Deck generateDeck(int[] numbers, String[] symbols, String[] suits) {
        ArrayList<Card> cards = new ArrayList<>();

        for (String suit : suits) {
            addNumberedCards(cards, numbers, suit);
            addCourCards(cards, symbols, suit);
        }

        return new Deck(cards);
    }

    private static void addNumberedCards(ArrayList<Card> cards, int[] numbers, String suit) {
        for (int number : numbers) {
            Card card = new Card(Integer.toString(number), suit);
            cards.add(card);
        }
    }

    private static void addCourCards(ArrayList<Card> cards, String[] symbols, String suit) {
        for (String symbol : symbols) {
            Card card = new Card(symbol, suit);
            cards.add(card);
        }
    }
}