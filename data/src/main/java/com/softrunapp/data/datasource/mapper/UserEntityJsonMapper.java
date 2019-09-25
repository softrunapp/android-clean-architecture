package com.softrunapp.data.datasource.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.softrunapp.data.entity.UserEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class UserEntityJsonMapper {
    private final Gson gson;
    @Inject
    public UserEntityJsonMapper() {
        gson = new Gson();
    }

    public UserEntity transformUserEntity(String userJson) throws JsonSyntaxException {
        UserEntity userEntity;
        try {
            Type type = new TypeToken<UserEntity>() {
            }.getType();
            userEntity = gson.fromJson(userJson, type);
            return userEntity;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<UserEntity> transformUserList(String userJson) throws JsonSyntaxException {
        List<UserEntity> userEntities;
        try {
            Type type = new TypeToken<List<UserEntity>>() {
            }.getType();
            userEntities = gson.fromJson(userJson, type);
            return userEntities;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
