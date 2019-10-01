package com.softrunapp.domain.intractor;

import com.softrunapp.domain.repository.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class GetUserListTest {
    private GetUserList getUserList;
    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getUserList = new GetUserList(
                Schedulers.trampoline(), Schedulers.trampoline(), userRepository);
    }

    @Test
    public void givenAConcreteUseCaseOfGetUserList() {
        assertThat(getUserList, instanceOf(GetUserList.class));
    }

    @Test
    public void getUserListObservableFromUserRepository() {
        getUserList.createUseCaseObservable();
        verify(userRepository).userList();
        verifyNoMoreInteractions(userRepository);
    }

    @After
    public void tearDown() throws Exception {
        getUserList = null;
    }
}