package com.korit.servlet_study.ch06;

import java.util.NoSuchElementException;
import java.util.Optional;

//Optional 관련 내용 정리
public class OptionalMain {
    public static void main(String[] args) {
        //Optional 생성하는 방법
        Optional<String> stringOptional1 = Optional.empty();             //빈 Optional
        Optional<String> stringOptional2 = Optional.of("데이터");    //null 불가능 - requirenonnull
        Optional<String> stringOptional3 = Optional.ofNullable(null);  //null 허용

        boolean flag = false;
        Optional<String> op = Optional.ofNullable(flag ? "데이터1" : "null");
        System.out.println(op);

        // optional 에서 값 가져오기
//        System.out.println(op.get());   //get : String 리턴-> 데이터1 / null : 값 못 가져옴
        System.out.println(op.orElseGet(() -> "데이터"));
        System.out.println(op.orElseGet(() -> null));
        //true 이므로 둘다 데이터1 가져옴 / false  라면 둘다 null
        System.out.println(op.orElse("대체: " + "데이터3"));  //값 없을 때 다른 값으로 대체 가능

        // 조건부로 값 가져오기 - 비었는지 여부
        System.out.println(op.isEmpty());
        System.out.println(op.isPresent());

        //같은 의미
        if (op.isPresent()) {
            System.out.println(op.get());
        } else {
            System.out.println("null");
        }

        // 조건부 + Optional
        op.ifPresent(value -> System.out.println("값이 있으면: " + value));  //ifPresent - consumer 이므로 리턴값 X
        op.ifPresentOrElse(
                value -> System.out.println("값이 있으면 consumer 실행됨: " + value),    //있으면 consumer 실행
                () -> System.out.println("값이 없어서 이거 (runnable) 실행됨")                  //없으면 runnable 실행
        );

        //.orElseThrow - String
        try {
            String data = op.orElseThrow();
            System.out.println("에외 안 터지고 실행됨: " + data);
        } catch (NoSuchElementException e) {
            System.out.println("예외 터짐");
        }

        //.orElseThrow - 예외처리
        try {
            String data = op.orElseThrow(() -> new RuntimeException("내가 생성한 예외"));
        } catch (NoSuchElementException e) {

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("이쪽으로 예외 처리함");
        }











    }
}
