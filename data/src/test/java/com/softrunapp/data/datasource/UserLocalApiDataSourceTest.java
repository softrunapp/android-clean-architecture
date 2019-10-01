package com.softrunapp.data.datasource;

import com.softrunapp.data.entity.UserEntity;
import com.softrunapp.data.local.LocalApi;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class UserLocalApiDataSourceTest {
    private static final int USER_ID = 20;
    private UserLocalApiDataSource userLocalApiDataSource;
    @Mock
    LocalApi localApi;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userLocalApiDataSource = new UserLocalApiDataSource(localApi);
    }

    @Test
    public void userEntityList() {
        userLocalApiDataSource.userEntityList();
        verify(localApi).userEntityList();
        verifyNoMoreInteractions(localApi);
    }

    @Test
    public void userEntity() {
        userLocalApiDataSource.userEntity(USER_ID);
        verify(localApi).userEntity(USER_ID);
        verifyNoMoreInteractions(localApi);
    }

    @Test
    public void givenAnObservableCollectionUserEntity() {
        List<UserEntity> userEntities = new ArrayList<>();
        Observable<List<UserEntity>> observable = Observable.just(userEntities);
        given(localApi.userEntityList()).willReturn(observable);
    }

    @Test
    public void givenAnObservableUserEntity() {
        UserEntity userEntity = new UserEntity();
        Observable<UserEntity> observable = Observable.just(userEntity);
        given(localApi.userEntity(USER_ID)).willReturn(observable);
    }

    @After
    public void tearDown() throws Exception {
        userLocalApiDataSource = null;
    }
}