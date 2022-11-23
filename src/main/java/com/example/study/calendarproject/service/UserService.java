package com.example.study.calendarproject.service;

import com.example.study.calendarproject.domain.User;
import com.example.study.calendarproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipal;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    //userId로 user 정보 보기
    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    //userId로 user 정보 수정하기
    public User updateUser(String userId, User user) {
        User tempUser = userRepository.findByUserId(userId);

        tempUser.setPassword(user.getPassword());
        tempUser.setUserName(user.getUserName());
        tempUser.setEmail(user.getPassword());

        User updatedUser = userRepository.save(tempUser);
        return updatedUser;
    }

    //userId로 user 정보 삭제하기
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

}
