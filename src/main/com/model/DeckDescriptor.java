package com.model;

import java.util.Arrays;

public record DeckDescriptor(int[] numbers, String[] symbols, String[] suits) {

    public int[] getNumbers() {
        return numbers;
    }

    public String[] getSuits() {
        return suits;
    }

    public String[] getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return "DeckDescriptor{" +
                "numbers=" + Arrays.toString(numbers) +
                ", symbols=" + Arrays.toString(symbols) +
                ", suits=" + Arrays.toString(suits) +
                '}';
    }
}
