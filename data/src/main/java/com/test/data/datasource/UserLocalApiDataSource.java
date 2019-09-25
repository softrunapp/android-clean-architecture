package com.test.data.datasource;


import com.test.data.entity.UserEntity;
import com.test.data.local.LocalApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserLocalApiDataSource implements DataSource {
    private final LocalApi localApi;
    @Inject
    public UserLocalApiDataSource(LocalApi localApi) {
        this.localApi = localApi;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return localApi.userEntityList();
    }

    @Override
    public Observable<UserEntity> userEntity(int id) {
        return localApi.userEntity(id);
    }
}
