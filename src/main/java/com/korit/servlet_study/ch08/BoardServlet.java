package com.korit.servlet_study.ch08;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.ch05.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {

    private List<Board> boardsList;   // 전역 리스트
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        System.out.println("BoardServlet 생성");
        boardsList = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST 요청 들어옴");
        //요청 데이터 형식
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

//      //Buffered 리더로 읽어온 문자열 -> StringBuilder 활용해서 객체로 변환
//        InputStreamReader inputStream = new InputStreamReader(req.getInputStream());
//        BufferedReader br = new BufferedReader(inputStream);
        //.getReader -> InputStreamReader + BufferedReader

//        BufferedReader br = req.getReader();
//        StringBuilder sb = new StringBuilder();
//
//        //버퍼드리더로 읽어온 내용
//        while (true) {
//            String readData = br.readLine();  //한 줄씩 읽어옴
//            if (readData == null) {           //마지막에 null 일 때
//                break;
//            }
//            sb.append(readData);
////            System.out.println("포스트맨에서 가져온 데이터: ");
//            System.out.println(readData);
//        }
//
//        // JSON 문자열로 변환
//        String json = sb.toString();

        //ObjectMapper 라이브러리 이용
        // JSON → 객체 변환
        ObjectMapper objectMapper = new ObjectMapper();
        Board board = objectMapper.readValue(req.getReader(), Board.class);

        boardsList.add(board);
        System.out.println(boardsList);


        //응답
        Response response = Response.builder()
                    .message("게시글 작성 완료")
                    .build();





    }
}
