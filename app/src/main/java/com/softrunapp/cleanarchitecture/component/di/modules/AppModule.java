package com.softrunapp.cleanarchitecture.component.di.modules;

import android.content.Context;

import com.softrunapp.cleanarchitecture.MyApplication;
import com.softrunapp.data.repository.UserDataRepositoy;
import com.softrunapp.domain.repository.UserRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {
    private MyApplication myApplication;

    public AppModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return myApplication;
    }

    @Provides
    @Singleton
    @Named("executor_thread")
    public Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ui_thread")
    public Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    public UserRepository provideUserRepository(UserDataRepositoy userDataRepositoy) {
        return userDataRepositoy;
    }
}
