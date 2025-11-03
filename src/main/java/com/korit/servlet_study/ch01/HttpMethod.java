package com.korit.servlet_study.ch01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* HTTP 프로토콜 Method
* 1. Get
*       - 용도: 리소스 조회
*       - 특징:
*           서버로부터 데이터를 요청만 하고 수정하지 않음
*           요청 데이터(파라미터)가 URL 에 포함됨
*           ex) http://test.com/users?username=test1234   request parameter
*           브라우저 히스토리에 남음
*           북마크 가능
*           캐싱 가능
*
* 2. Post
*       - 용도: 생성
*       - 특징:
*           서버에 데이터를 전송하여 새로운 리소스 생성
*           요청 데이터가 HTTP Body 에 포함됨
*           브라우저 히스토리에 남지 않음
*           캐싱되지 않음
*
* ----------------------------수정-----------------------------
* 3. Put
*       - 용도: 리소스 전체 수정/생성
*       - 특징:
*           리소스가 있으면 전체를 교체, 없으면 생성
*           전체 데이터를 전송해야함
*
* 4. Patch
*       - 용도: 리소스 부분 수정
*       - 특징:
*           리소스의 일부만 수정
*           Put 보다 효율적(변경할 필드만 전송) -> 변경사항이 있는 애만 변경됨
*
* ex) 만약 "자기소개" 가 있는데 지우고 싶음 => put 요청
* Patch 를 쓰면 지우고 보냄 - 수정사항이 없다고 판단함, a든 뭐든 써야 변경된 것이 있다고 판단
*
* ----------------------------삭제-----------------------------
* 5. Delete
*       - 용도: 리소스 삭제
*       - 특징:
*           지정된 리소스를 삭제
*           멱등성 있음  (여러번 수행해도 결과 처음 한번 수행 시와 동일하게 유지)
*
* 6. HEAD
*       - 용도: 리소스 존재여부 또는 메타데이터 확인
*
*  7. OPTIONS
*       - 용도: HTTP 메서드의 존재여부 또는 CORS(Cross Origin Resource Sharing)
*              프리플라이트 요청에 사용
*
*  8. CONNECT
*       - 용도: 프록시 서버를 통한 터널링에 사용, SSL 연결에 활용됨
*
*  9. TRACE
*       - 용도: 디버깅, 요청 경로 루프백 테스트
*
*
*
* */
//이 주석 쓰면 xml 에 매핑 안해도 됨
@WebServlet("/ch02/method/get")
public class HttpMethod extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET 요청 들어옴");
    }











}
