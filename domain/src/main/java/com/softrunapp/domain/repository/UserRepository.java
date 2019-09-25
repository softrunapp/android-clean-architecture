package com.softrunapp.domain.repository;

import com.softrunapp.domain.model.User;

import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {
    Observable<List<User>> userList();

    Observable<User> user(int userId);
}
