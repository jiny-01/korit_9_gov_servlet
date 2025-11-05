package com.korit.servlet_study.ch07;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch03.ErrorResponse;
import com.korit.servlet_study.ch03.SuccessResponse;
import com.korit.servlet_study.ch03.User;
import com.korit.servlet_study.ch03.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@WebServlet("/ch07/boards")
public class BoardServlet extends HttpServlet {

    private BoardRepository boardRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        boardRepository = BoardRepository.getInstance();  //UserRepository 싱글톤 가져옴
        objectMapper = new ObjectMapper();  //ObjectMapper = JSON 변환 도구
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.getContentType("application/json");
        List<Board> boards = boardRepository.boardList();  //users 전체 조회

        //boards 배열 전체 조회 -> Json 배열로 응답
        objectMapper.writeValue(resp.getWriter(), boards);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");

        // JSON -> Board 객체로 변환
        Board board = objectMapper.readValue(req.getReader(), Board.class);


//        if (!Objects.isNull(foundBoard)) {
//            Response response = Response.builder()
//                    .message("게시글 작성 완료")
//                    .build();
//
//            objectMapper.writeValue(resp.getWriter(), response);
//            return;
//
//            boardRepository.insert(board);
//
//        }

    }
}





