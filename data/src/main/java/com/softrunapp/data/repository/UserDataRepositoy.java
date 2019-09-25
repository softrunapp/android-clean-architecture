package com.softrunapp.data.repository;

import com.softrunapp.data.datasource.DataSource;
import com.softrunapp.data.datasource.UserDataSourceFactory;
import com.softrunapp.data.datasource.mapper.UserToUserEntityMapper;
import com.softrunapp.domain.model.User;
import com.softrunapp.domain.repository.UserRepository;

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
