package com.test.data.datasource;

import android.content.Context;

import com.test.data.datasource.mapper.UserEntityJsonMapper;
import com.test.data.local.LocalImpl;

public class UserDataSourceFactory {
    private final Context context;

    public UserDataSourceFactory(Context context) {
        this.context = context;
    }

    public DataSource createDataSource() {
        UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
        LocalImpl local = new LocalImpl(context, userEntityJsonMapper);
        return new UserLocalApiDataSource(local);
    }
}