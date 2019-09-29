package com.softrunapp.cleanarchitecture.view.user_list;

import com.softrunapp.cleanarchitecture.view_model.UserModel;
import com.softrunapp.cleanarchitecture.view_model.mapper.UserModelToUserMapper;
import com.softrunapp.data.repository.UserDataRepositoy;
import com.softrunapp.domain.intractor.GetUserList;
import com.softrunapp.domain.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserListPresenterTest {

    private UserListPresenter userListPresenter;
    @Mock
    GetUserList getUserList;
    @Mock
    UserModelToUserMapper userModelToUserMapper;
    @Mock
    UserListPresenter.View userListView;
    @Mock
    Throwable throwable;

    @Captor
    ArgumentCaptor<DisposableObserver<List<User>>> disposableObserverArgumentCaptor;
    @Captor
    ArgumentCaptor<List<User>> listOfUserArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userListPresenter = new UserListPresenter(getUserList, userModelToUserMapper);
        userListPresenter.setView(userListView);
        userListPresenter.initialize();
    }

    @Test
    public void initialize() {
        verify(getUserList).execute(disposableObserverArgumentCaptor.capture());

        disposableObserverArgumentCaptor.getValue().onComplete();
        disposableObserverArgumentCaptor.getValue().onError(throwable);

        verify(userListView).showMessage("detected error!");
        verify(userListView, times(2)).hideLoading();


        disposableObserverArgumentCaptor.getValue().onNext(listOfUserArgumentCaptor.capture());
        verify(userListView).showUsers(
                userModelToUserMapper.reversMap(listOfUserArgumentCaptor.capture()));
    }

    @After
    public void tearDown() throws Exception {
        userListPresenter = null;
    }
}