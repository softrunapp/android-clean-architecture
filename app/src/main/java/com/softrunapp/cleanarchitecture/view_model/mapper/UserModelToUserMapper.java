package com.softrunapp.cleanarchitecture.view_model.mapper;

import com.softrunapp.cleanarchitecture.view_model.UserModel;
import com.softrunapp.data.datasource.mapper.Mapper;
import com.softrunapp.domain.model.User;

import javax.inject.Inject;

public class UserModelToUserMapper extends Mapper<UserModel, User> {
    @Inject
    public UserModelToUserMapper() {

    }

    @Override
    public User map(UserModel value) {
        User user = new User();
        user.setId(value.getId());
        user.setFirstName(value.getFirstName());
        user.setLastName(value.getLastName());
        user.setEmail(value.getEmail());
        user.setAvatar(value.getAvatar());
        return user;
    }

    @Override
    public UserModel reversMap(User value) {
        UserModel user = new UserModel();
        user.setId(value.getId());
        user.setFirstName(value.getFirstName());
        user.setLastName(value.getLastName());
        user.setEmail(value.getEmail());
        user.setAvatar(value.getAvatar());
        return user;

    }
}
