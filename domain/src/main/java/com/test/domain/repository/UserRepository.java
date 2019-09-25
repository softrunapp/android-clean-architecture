package com.test.domain.repository;

import com.test.domain.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<List<User>> userList();

    Observable<User> user(int userId);
}
