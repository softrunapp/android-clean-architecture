package com.softrunapp.domain.intractor;

import com.softrunapp.domain.model.User;
import com.softrunapp.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetUserList extends UseCase<List<User>> {
    private final UserRepository userRepository;
    @Inject
    public GetUserList(@Named("executor_thread") Scheduler executorThread, @Named("ui_thread") Scheduler uiThread, UserRepository userRepository) {
        super(executorThread, uiThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<List<User>> createUseCaseObservable() {
        return userRepository.userList();
    }
}
