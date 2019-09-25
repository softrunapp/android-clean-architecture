package com.test.domain.intractor;

import com.test.domain.model.User;
import com.test.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetUserById extends UseCase<User> {
    private final UserRepository userRepository;
    private int userId = 1;

    @Inject
    GetUserById(@Named("executor_thread") Scheduler executorThread,@Named("ui_thread") Scheduler uiThread, UserRepository userRepository) {
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
