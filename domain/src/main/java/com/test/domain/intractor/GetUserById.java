package com.test.domain.intractor;

import com.test.domain.model.User;
import com.test.domain.repository.UserRepository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetUserById extends UseCase<User> {
    private final UserRepository userRepository;
    private int userId = 1;

    GetUserById(Scheduler executorThread, Scheduler uiThread, UserRepository userRepository) {
        super(executorThread, uiThread);
        this.userRepository = userRepository;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    protected Observable<User> createUseCaseObservable() {
        return userRepository.user(userId);
    }
}
