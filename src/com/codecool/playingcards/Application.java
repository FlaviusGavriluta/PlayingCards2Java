package com.codecool.playingcards;

import com.codecool.playingcards.builder.CardGeneratorImpl;
import com.codecool.playingcards.builder.FrenchDeckBuilder;
import com.codecool.playingcards.builder.GermanDeckBuilder;
import com.codecool.playingcards.interfaces.DeckBuilder;
import com.codecool.playingcards.model.Deck;

import java.util.List;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        CardGeneratorImpl cardGenerator = new CardGeneratorImpl();
        DeckBuilder frenchDeckBuilder = new FrenchDeckBuilder(cardGenerator);
        DeckBuilder germanDeckBuilder = new GermanDeckBuilder(cardGenerator);

        List<Deck> decks = buildDecks(new DeckBuilder[]{frenchDeckBuilder, germanDeckBuilder});
        printCardCount(decks);
    }

    private static List<Deck> buildDecks(DeckBuilder[] builders) {
        List<Deck> decks = new ArrayList<>();
        for (DeckBuilder builder : builders) {
            decks.add(builder.createDeck());
        }
        return decks;
    }

    private static void printCardCount(List<Deck> decks) {
        for (Deck deck : decks) {
            System.out.println(deck);
        }
    }
}