package com.example.dreamwish.entities;

public class Board {

    private int id;
    private int wishesId;

    // default constructor
    public Board() {
    }

    //constructor with all parameters
    public Board(int id, int wishesId) {
        this.id = id;
        this.wishesId = wishesId;
    }

    public int getId() {
        return id;
    }

    public int getWishesId() {
        return wishesId;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", wishesId=" + wishesId +
                '}';
    }
}
