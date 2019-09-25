package com.softrunapp.cleanarchitecture.base.presenter;

public abstract class Presenter<T extends Presenter.View> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize() {
    }

    public void destroy() {
    }

    public interface View {
        void showLoading();

        void hideLoading();

        void showMessage(String message);
    }
}
