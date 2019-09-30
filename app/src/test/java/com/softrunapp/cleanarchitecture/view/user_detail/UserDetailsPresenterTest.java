package com.softrunapp.cleanarchitecture.view.user_detail;

import com.softrunapp.cleanarchitecture.view_model.UserModel;
import com.softrunapp.cleanarchitecture.view_model.mapper.UserModelToUserMapper;
import com.softrunapp.domain.intractor.GetUserById;
import com.softrunapp.domain.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserDetailsPresenterTest {
    private UserDetailsPresenter presenter;
    @Mock
    UserModelToUserMapper mapper;
    @Mock
    GetUserById getUserById;
    @Mock
    UserDetailsPresenter.View view;
    @Mock
    Throwable throwable;
    @Captor
    ArgumentCaptor<DisposableObserver<User>> disposableObserverArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        initPresenter();
    }

    @Test
    public void initialize() {
        verify(view, times(1)).showLoading();
        verify(getUserById).setUserId(20);
        verify(getUserById).execute(disposableObserverArgumentCaptor.capture());
    }

    @Test
    public void onNext() {
        verify(getUserById).execute(disposableObserverArgumentCaptor.capture());
        User user = getUser();
        disposableObserverArgumentCaptor.getValue().onNext(user);
        ArgumentCaptor<UserModel> userArgumentCaptor = ArgumentCaptor.forClass(UserModel.class);
        verify(view).showUser(userArgumentCaptor.capture());
    }

    @Test
    public void onError() {
        verify(getUserById).execute(disposableObserverArgumentCaptor.capture());
        disposableObserverArgumentCaptor.getValue().onError(throwable);
        verify(view).hideLoading();
        verify(view).showMessage("detected error!");
        verify(throwable).printStackTrace();
    }

    @Test
    public void onComplete() {
        verify(getUserById).execute(disposableObserverArgumentCaptor.capture());
        disposableObserverArgumentCaptor.getValue().onComplete();
        verify(view).hideLoading();
    }

    private void initPresenter() {
        presenter = new UserDetailsPresenter(getUserById, mapper);
        presenter.setView(view);
        presenter.setUserId(20);
        presenter.initialize();
    }

    private User getUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setEmail("john@gmail.com");
        user.setAvatar("https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg");
        return user;
    }

    @After
    public void tearDown() throws Exception {
        presenter = null;
    }
}