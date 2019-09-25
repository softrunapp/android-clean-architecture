package com.test.data.repository;

import com.test.data.datasource.DataSource;
import com.test.data.datasource.UserDataSourceFactory;
import com.test.data.datasource.mapper.UserToUserEntitiyMapper;
import com.test.data.entity.UserEntity;
import com.test.domain.model.User;
import com.test.domain.repository.UserRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserDataReposity implements UserRepository {

    private final DataSource dataSource;
    private final UserToUserEntitiyMapper userToUserEntitiyMapper;

    public UserDataReposity(UserDataSourceFactory userDataSourceFactory, UserToUserEntitiyMapper userToUserEntitiyMapper) {
        this.dataSource = userDataSourceFactory.createDataSource();
        this.userToUserEntitiyMapper = userToUserEntitiyMapper;
    }

    @Override
    public Observable<List<User>> userList() {
        return dataSource.userEntityList().map(userToUserEntitiyMapper::reversMap);
    }

    @Override
    public Observable<User> user(int userId) {
        return dataSource.userEntity(userId).map(userToUserEntitiyMapper::reversMap);
    }
}
