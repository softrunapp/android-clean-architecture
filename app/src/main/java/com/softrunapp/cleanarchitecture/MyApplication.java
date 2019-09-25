package com.softrunapp.cleanarchitecture;

import android.app.Application;

import com.softrunapp.cleanarchitecture.component.di.components.AppComponent;
import com.softrunapp.cleanarchitecture.component.di.components.DaggerAppComponent;
import com.softrunapp.cleanarchitecture.component.di.modules.AppModule;

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
