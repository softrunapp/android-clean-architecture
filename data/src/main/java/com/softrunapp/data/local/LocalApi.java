package com.softrunapp.data.local;

import com.softrunapp.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public interface LocalApi {
    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntity(int id);
}
