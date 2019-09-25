package com.test.andorid_clean_architecture.component.di.components;

import com.test.andorid_clean_architecture.component.di.modules.AppModule;
import com.test.andorid_clean_architecture.view.user_detail.UserDetailsActivity;
import com.test.andorid_clean_architecture.view.user_list.UserListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(UserListActivity mainActivity);

    void inject(UserDetailsActivity userDetailsActivity);
}
