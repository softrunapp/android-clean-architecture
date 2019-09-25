package com.test.andorid_clean_architecture.component.di.components;

import com.test.andorid_clean_architecture.MainActivity;
import com.test.andorid_clean_architecture.component.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
