package com.hobbsa.finalprojectmdp_alexhobbs;

import java.util.ArrayList;

public class Game {
    private String name;
    private String publisher;
    private String category;
    private int purchaseYear;
    //array of games
    public static ArrayList<Game> games = new ArrayList();

    //constructors
    public Game(String name, String publisher, String category){
        this.setName(name);
        this.setPublisher(publisher);
        this.setCategory(category);
        this.setPurchaseYear(0); // for wish list, no purchase year
    }
    public Game(String name, String publisher, String category, int purchaseYear){
        this.setName(name);
        this.setPublisher(publisher);
        this.setCategory(category);
        this.setPurchaseYear(purchaseYear);
    }

    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }


}
