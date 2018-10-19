package edu.gatech.cs2340.cs2340project.mvc.controller;

public enum Inventory {

    CLOTHES("CL"), ACCESSORIES("AC"), ELECTRONICS("EL"), OTHER("OTH");

    private final String word;

    Inventory(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
}