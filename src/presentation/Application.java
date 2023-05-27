package presentation;

import service.impl.CardGeneratorImpl;
import utility.DeckDescriptors;
import service.CardGenerator;
import service.DeckBuilder;
import service.impl.DeckBuilderImpl;
import service.Logger;
import service.impl.ConsoleLogger;
import model.Deck;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        CardGenerator cardGenerator = new CardGeneratorImpl(logger);
        DeckBuilder frenchDeckBuilder = new DeckBuilderImpl(cardGenerator, DeckDescriptors.FRENCH_DECK_DESCRIPTOR);
        DeckBuilder germanDeckBuilder = new DeckBuilderImpl(cardGenerator, DeckDescriptors.GERMAN_DECK_DESCRIPTOR);

        List<Deck> decks = buildDecks(frenchDeckBuilder);
        printCardCount(decks);

        decks = buildDecks(germanDeckBuilder);
        printCardCount(decks);
    }

    private static List<Deck> buildDecks(DeckBuilder builder) {
        List<Deck> decks = new ArrayList<>();
        decks.add(builder.createDeck());
        return decks;
    }

    private static void printCardCount(List<Deck> decks) {
        for (Deck deck : decks) {
            System.out.println(deck);
        }
    }
}