package com.softrunapp.cleanarchitecture.base.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        bindViews();
        onStartView();
    }

    /**
     * Use this method to initialize views in your activities.
     */
    public void onStartView() {
    }

    /**
     * initialize butterknife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }

    protected abstract int getContentLayoutId();
}
