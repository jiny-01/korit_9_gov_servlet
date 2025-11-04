package com.korit.servlet_study.ch03;

import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.util.Map;
import java.util.Objects;

@WebServlet("/ch03/users")
public class UserServlet extends HttpServlet {
    private UserRepository userRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        userRepository = UserRepository.getInstance();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        List<User> users = userRepository.findAll();

        objectMapper.writeValue(resp.getWriter(), users);  //users 배열 전체 조회

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

          // Buffered 리더로 읽어온 문자열 -> StringBuilder 활용해서 객체로 변환
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        StringBuilder builder = new StringBuilder();
//
//        //버퍼드리더로 읽어온 내용
//        while (true) {
//            String line = bufferedReader.readLine();
//            if (Objects.isNull(line)) {
//                break;
//            }
//            builder.append(line);
//            System.out.println(bufferedReader.readLine());
//        }
//        //#1) 문자열 -> 객체
//        UserDto userDto = objectMapper.readValue(builder.toString(), UserDto.class);


        //#2) inputstream 을 알아서 objectMapper 가 변환해줌
        //38라인 ~49라인까지의 과정과 동일
        UserDto userDto = objectMapper.readValue(req.getInputStream(), UserDto.class);
        System.out.println(userDto);
        //req.getInputStream() 으로 Json 문자열을 읽어서 UserDto 객체로 바꿔줌

        User foundUser = userRepository.findByUsername(userDto.getUsername());

        // 응답형식 지정
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");    // 응답형식: json

        if (!Objects.isNull(foundUser)) {       //중복일 때
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(400)                //builder 의 status 는 초기값 0일 것
                    .message("이미 존재하는 username 입니다.")
                    .build();
            resp.setStatus(400);

            // error response 객체-> json 문자열로 변환
            objectMapper.writeValue(resp.getWriter(), errorResponse);
            return;           //doPost 메서드 탈출
        }

        //username 중복없을 때
        // dto -> entity 로 변환해서 insert 해라 -> autoid++ 되어서 insert 될 것
        User userEntity = userDto.toUser();
        userRepository.insert(userEntity);   //dto-> user 객체로 변환해서 insert

        SuccessResponse<User> userSuccessResponse = SuccessResponse.<User> builder() //Object 가 아닌 User 타입의 빌더가 완성
                .status(200)
                .message("사용자 등록을 완료하였습니다.")
                .body(userEntity)
                .build();
        objectMapper.writeValue(resp.getWriter(), userSuccessResponse);
        //.writeValue - Jackson 라이브러리(ObjectMapper) 의 "객체 → JSON 문자열 변환 메서드
        //ObjectMapper가 자바 객체를 JSON 형태로 변환해서 응답으로 내보내는 역할



    }


}
