package com.test.domain.intractor;

import com.test.domain.model.User;
import com.test.domain.repository.UserRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetUserList extends UseCase<List<User>> {
    private final UserRepository userRepository;

    GetUserList(Scheduler executorThread, Scheduler uiThread, UserRepository userRepository) {
        super(executorThread, uiThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<List<User>> createUseCaseObservable() {
        return userRepository.userList();
    }
}
