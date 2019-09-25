package com.test.andorid_clean_architecture.component.di.modules;

import android.content.Context;

import com.test.andorid_clean_architecture.MyApplication;
import com.test.data.repository.UserDataRepositoy;
import com.test.domain.repository.UserRepository;

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
