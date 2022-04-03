package com.example.dreamwish.entities;

public class Board {

    private int id;
    private int wishesId;

    public Board() {

    }

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
