package com.test.andorid_clean_architecture;

import android.app.Application;

import com.test.andorid_clean_architecture.component.di.components.AppComponent;
import com.test.andorid_clean_architecture.component.di.components.DaggerAppComponent;
import com.test.andorid_clean_architecture.component.di.modules.AppModule;

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
