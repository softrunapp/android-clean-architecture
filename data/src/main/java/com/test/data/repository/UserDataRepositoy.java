package com.test.data.repository;

import com.test.data.datasource.DataSource;
import com.test.data.datasource.UserDataSourceFactory;
import com.test.data.datasource.mapper.UserToUserEntityMapper;
import com.test.domain.model.User;
import com.test.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserDataRepositoy implements UserRepository {

    private final DataSource dataSource;
    private final UserToUserEntityMapper userToUserEntityMapper;
    @Inject
    public UserDataRepositoy(UserDataSourceFactory userDataSourceFactory, UserToUserEntityMapper userToUserEntityMapper) {
        this.dataSource = userDataSourceFactory.createDataSource();
        this.userToUserEntityMapper = userToUserEntityMapper;
    }

    @Override
    public Observable<List<User>> userList() {
        return dataSource.userEntityList().map(userToUserEntityMapper::reversMap);
    }

    @Override
    public Observable<User> user(int userId) {
        return dataSource.userEntity(userId).map(userToUserEntityMapper::reversMap);
    }
}
