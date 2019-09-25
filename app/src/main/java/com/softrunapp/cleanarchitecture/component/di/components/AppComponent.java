package com.softrunapp.cleanarchitecture.component.di.components;

import com.softrunapp.cleanarchitecture.component.di.modules.AppModule;
import com.softrunapp.cleanarchitecture.view.user_detail.UserDetailsActivity;
import com.softrunapp.cleanarchitecture.view.user_list.UserListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(UserListActivity mainActivity);

    void inject(UserDetailsActivity userDetailsActivity);
}
