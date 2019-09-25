package com.test.data.datasource;

import com.test.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {
    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntity(int id);
}
