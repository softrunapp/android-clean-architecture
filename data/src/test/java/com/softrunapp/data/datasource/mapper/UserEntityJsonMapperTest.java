package com.softrunapp.data.datasource.mapper;

import com.google.gson.JsonSyntaxException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class UserEntityJsonMapperTest {
    private UserEntityJsonMapper userEntityJsonMapper;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        userEntityJsonMapper = new UserEntityJsonMapper();
    }

    @Test
    public void givenExceptedExceptionTransformUserEntityNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        userEntityJsonMapper.transformUserEntity("Excepts a json object");
    }

    @Test
    public void givenExceptedExceptionTransformUserEntityListNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        userEntityJsonMapper.transformUserList("Excepts a json object");
    }
}