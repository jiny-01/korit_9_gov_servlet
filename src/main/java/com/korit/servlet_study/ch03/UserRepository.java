package com.korit.servlet_study.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepository {
    private static UserRepository instance;
    private List<User> users;
    private Long autoId;

    private UserRepository() {
        users = new ArrayList<>();
        autoId = 0L;
    }

    public static UserRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void insert(User user) {
        user.setId(++autoId);
        users.add(user);
    }

    public User findByUsername(String username) {
        //users 리스트 반복돌리며서 username 걸러내기
//        Optional<User> userOptional = users.stream()
//                .filter(user -> user.getUsername().equals(username))
//                .findFirst();
//
//        return userOptional.orElseGet(() -> null);

        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseGet(() -> null);
        //optional 이 null 이면 get 이 안됨
        //.orElse -> 없으면 새로운 User 객체를 만들어줌 (매개변수로 넣어준 user 줄 것) / 있으면 찾은 객체 리턴해줌
        //filter -> 다시 optional 리턴 / 없으면 null => null 처리 가능
        //orElseGet -> Supplier
    }

    //#2) Optional 없이
    //empty 인지 확인하고 null 리턴 -> Optional 사용하면 한 줄로 작성 가능
    public User findByUsernameNonOptional(String username) {
        List<User> foundUsers = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .toList();
        if(foundUsers.isEmpty()) {
            return null;
        }
        return foundUsers.get(0);
    }

    public List<User> findAll() {
        return users;
    }
}
