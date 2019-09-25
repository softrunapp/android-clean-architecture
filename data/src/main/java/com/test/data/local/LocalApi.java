package com.test.data.local;

import com.test.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public interface LocalApi {
    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntity(int id);
}
