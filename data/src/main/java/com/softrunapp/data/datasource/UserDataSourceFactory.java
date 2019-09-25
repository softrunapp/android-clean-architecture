package com.softrunapp.data.datasource;

import android.content.Context;

import com.softrunapp.data.datasource.mapper.UserEntityJsonMapper;
import com.softrunapp.data.local.LocalImpl;

import javax.inject.Inject;

public class UserDataSourceFactory {
    private final Context context;
    @Inject
    public UserDataSourceFactory(Context context) {
        this.context = context;
    }

    public DataSource createDataSource() {
        UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
        LocalImpl local = new LocalImpl(context, userEntityJsonMapper);
        return new UserLocalApiDataSource(local);
    }
}
