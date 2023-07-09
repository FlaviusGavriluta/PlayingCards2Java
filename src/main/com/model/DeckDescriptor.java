package com.model;

public record DeckDescriptor(int[] numbers, String[] symbols, String[] suits) {
    public int[] getNumbers() {
        return numbers;
    }

    public String[] getSuits() {
        return suits;
    }
}
