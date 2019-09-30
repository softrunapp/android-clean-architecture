package com.softrunapp.cleanarchitecture.view.user_list;

import com.softrunapp.cleanarchitecture.view_model.UserModel;
import com.softrunapp.cleanarchitecture.view_model.mapper.UserModelToUserMapper;
import com.softrunapp.domain.intractor.GetUserList;
import com.softrunapp.domain.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserListPresenterTest {
    private static final Random RANDOM = new Random();
    private UserListPresenter userListPresenter;
    private UserModelToUserMapper userModelToUserMapper;

    @Mock
    GetUserList getUserList;
    @Mock
    UserListPresenter.View userListView;
    @Mock
    Throwable throwable;
    @Captor
    ArgumentCaptor<DisposableObserver<List<User>>> disposableObserverArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userModelToUserMapper = new UserModelToUserMapper();
        userListPresenter = new UserListPresenter(getUserList, userModelToUserMapper);
        userListPresenter.setView(userListView);
        userListPresenter.initialize();
    }

    @Test
    public void initialize() {
        verify(userListView, times(1)).showLoading();
        verify(getUserList, times(1))
                .execute(disposableObserverArgumentCaptor.capture());
    }

    @Test
    public void onNext() {
        verify(getUserList).execute(disposableObserverArgumentCaptor.capture());
        List<User> userList = getUserList();
        disposableObserverArgumentCaptor.getValue().onNext(userList);
        ArgumentCaptor<List> listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(userListView).showUsers(listArgumentCaptor.capture());
        assertEquals(userList.size(), listArgumentCaptor.getValue().size());
    }

    @Test
    public void onError() {
        verify(getUserList).execute(disposableObserverArgumentCaptor.capture());
        disposableObserverArgumentCaptor.getValue().onError(throwable);
        verify(userListView, times(1)).hideLoading();
        verify(userListView, times(1)).showMessage("detected error!");
    }

    @Test
    public void onComplete() {
        verify(getUserList).execute(disposableObserverArgumentCaptor.capture());
        disposableObserverArgumentCaptor.getValue().onComplete();
        verify(userListView, times(1)).hideLoading();
    }

    @Test
    public void onItemClick() {
        ArgumentCaptor<UserModel> userModelArgumentCaptor =
                ArgumentCaptor.forClass(UserModel.class);
        userListPresenter.onItemClicked(userModelArgumentCaptor.capture());
        verify(userListView, times(1))
                .openUserPage(userModelArgumentCaptor.capture());
    }

    private List<User> getUserList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 10; i++) {
            user.setId(RANDOM.nextInt(Integer.MAX_VALUE));
            user.setFirstName("John");
            user.setLastName("Smith");
            user.setEmail("john@gmail.com");
            user.setAvatar("https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg");
            users.add(user);
        }
        return users;
    }


    @After
    public void tearDown() throws Exception {
        userListPresenter = null;
        userModelToUserMapper = null;
    }

}