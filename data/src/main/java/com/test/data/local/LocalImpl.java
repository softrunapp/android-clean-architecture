package com.test.data.local;

import android.content.Context;

import com.test.data.datasource.mapper.UserEntityJsonMapper;
import com.test.data.entity.UserEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.Observable;

public class LocalImpl implements LocalApi {
    private final Context context;
    private final UserEntityJsonMapper userEntityJsonMapper;

    public LocalImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        this.context = context;
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return Observable.create(emitter -> {
            List<UserEntity> userEntities = getAllUsers();
            if (userEntities != null) {
                emitter.onNext(userEntities);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("error getting data list from the local json (users.json)"));
            }
        });
    }

    @Override
    public Observable<UserEntity> userEntity(int id) {
        return Observable.create(emitter -> {
            UserEntity userEntity = getUserById(id);
            if (userEntity != null) {
                emitter.onNext(userEntity);
                emitter.onComplete();
            } else {
                emitter.onError(
                        new Throwable("error getting data user from the local json (users.json)"));
            }

        });
    }

    private List<UserEntity> getAllUsers() {
        return userEntityJsonMapper.transformUserList(getUsersJson());
    }

    private UserEntity getUserById(int id) {
        for (UserEntity userEntity : getAllUsers()) {
            if (userEntity.getId() == id) {
                return userEntity;
            }
        }
        return null;
    }

    private String getUsersJson() {
        final String USERS_FILE = "users.josn";
        String result = "";
        try {
            StringBuilder builder = new StringBuilder();
            InputStream json = context.getAssets().open(USERS_FILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(json));

            while ((result = in.readLine()) != null) {
                builder.append(result);
            }

            result = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
