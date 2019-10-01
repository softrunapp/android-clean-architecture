package com.softrunapp.domain.intractor;

import com.softrunapp.domain.repository.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.schedulers.Schedulers;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class GetUserByIdTest {
    private static final int USER_ID = 20;
    private GetUserById getUserById;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getUserById = new GetUserById(
                Schedulers.trampoline(), Schedulers.trampoline(), userRepository);
    }

    @Test
    public void givenAConcreteUseCaseOfGetUserById() {
        assertThat(getUserById, instanceOf(GetUserById.class));
    }

    @Test
    public void getUserByIdObservableInUserRepository() {
        ArgumentCaptor<Integer> intValue = ArgumentCaptor.forClass(Integer.class);
        getUserById.setUserId(USER_ID);
        getUserById.createUseCaseObservable();
        verify(userRepository).user(intValue.capture());
        verifyNoMoreInteractions(userRepository);
        assertEquals(USER_ID, intValue.getValue().intValue());
    }

    @After
    public void tearDown() throws Exception {
        getUserById = null;
    }
}