package com.softrunapp.data.datasource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserDataSourceFactoryTest {
    private UserDataSourceFactory userDataSourceFactory;

    @Before
    public void setUp() throws Exception {
        userDataSourceFactory = new UserDataSourceFactory(RuntimeEnvironment.systemContext);
    }

    @Test
    public void createDataSource() {
        DataSource dataSource = userDataSourceFactory.createDataSource();
        assertThat(dataSource, is(notNullValue()));
        assertThat(dataSource, is(instanceOf(UserLocalApiDataSource.class)));
    }

    @After
    public void tearDown() throws Exception {
        userDataSourceFactory = null;
    }
}