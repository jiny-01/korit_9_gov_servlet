package com.korit.servlet_study.ch07;

import com.korit.servlet_study.ch03.User;
import com.korit.servlet_study.ch03.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardRepository {
    private static BoardRepository instance;
    private List<Board> boards;
    private int boardId;

    private BoardRepository() {
        boards = new ArrayList<>();
        boardId = 0;
    }

    public static BoardRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BoardRepository();
        }
        return instance;
    }

    //리스트에 board 객체 추가
    public void insert(Board board) {
        board.setBoardId(++boardId);
        boards.add(board);
    }

    //전체 조회
    public List<Board> boardList() {
        return boards;
    }

//    public Board foundBoard() {
//        return boards;
//    }
}
