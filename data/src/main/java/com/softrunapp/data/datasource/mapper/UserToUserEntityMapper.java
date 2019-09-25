package com.softrunapp.data.datasource.mapper;

import com.softrunapp.data.entity.UserEntity;
import com.softrunapp.domain.model.User;

import javax.inject.Inject;

public class UserToUserEntityMapper extends Mapper<User, UserEntity> {
    @Inject
    public UserToUserEntityMapper() {
    }

    @Override
    public UserEntity map(User value) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(value.getId());
        userEntity.setEmail(value.getEmail());
        userEntity.setFirstName(value.getFirstName());
        userEntity.setLastName(value.getLastName());
        userEntity.setAvatar(value.getAvatar());
        return userEntity;
    }

    @Override
    public User reversMap(UserEntity value) {
        User user = new User();
        user.setId(value.getId());
        user.setEmail(value.getEmail());
        user.setFirstName(value.getFirstName());
        user.setLastName(value.getLastName());
        user.setAvatar(value.getAvatar());
        return user;
    }
}
