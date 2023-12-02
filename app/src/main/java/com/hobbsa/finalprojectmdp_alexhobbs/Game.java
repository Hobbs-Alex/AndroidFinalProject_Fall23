package com.hobbsa.finalprojectmdp_alexhobbs;

public class Game {
    private  String name;
    private String publisher;
    private String category;
    private int purchaseYear;

    //array of games
    public static Game[] games = createGame();

    //constructors
    public Game(int id, String name, String publisher, String category, int purchaseYear){
        this._id = id;
        this.name = name;
        this.publisher = publisher;
        this.category = category;
        this.purchaseYear = purchaseYear;
    }

    //getters & setters

}
