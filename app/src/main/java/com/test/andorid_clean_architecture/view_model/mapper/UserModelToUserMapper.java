package com.test.andorid_clean_architecture.view_model.mapper;

import com.test.andorid_clean_architecture.view_model.UserModel;
import com.test.data.datasource.mapper.Mapper;
import com.test.domain.model.User;

import javax.inject.Inject;

public class UserModelToUserMapper extends Mapper<UserModel, User> {
    @Inject
    UserModelToUserMapper() {

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
