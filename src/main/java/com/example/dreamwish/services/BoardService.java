package com.example.dreamwish.services;

import com.example.dreamwish.entities.Wish;
import com.example.dreamwish.repositories.BoardRepo;

import java.util.ArrayList;

public class BoardService {

    BoardRepo boardRepo = new BoardRepo();

    /**
     * get all wishes from board
     * @return
     */
    public ArrayList<Wish> getwishesFromBoard() {
        return boardRepo.gettingWishes();
    }
}
