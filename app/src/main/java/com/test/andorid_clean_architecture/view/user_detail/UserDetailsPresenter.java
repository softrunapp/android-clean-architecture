package com.test.andorid_clean_architecture.view.user_detail;

import com.test.andorid_clean_architecture.base.presenter.Presenter;
import com.test.andorid_clean_architecture.view_model.UserModel;
import com.test.andorid_clean_architecture.view_model.mapper.UserModelToUserMapper;
import com.test.domain.intractor.GetUserById;
import com.test.domain.model.User;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class UserDetailsPresenter extends Presenter<UserDetailsPresenter.View> {
    private GetUserById getUserById;
    private UserModelToUserMapper userModelToUserMapper;
    private int userId;

    @Inject
    public UserDetailsPresenter(GetUserById getUserById, UserModelToUserMapper userModelToUserMapper) {
        this.getUserById = getUserById;
        this.userModelToUserMapper = userModelToUserMapper;
    }

    @Override
    public void initialize() {
        super.initialize();
        getView().showLoading();
        getUserById.setUserId(userId);
        getUserById.execute(new DisposableObserver<User>() {
            @Override
            public void onNext(User user) {
                getView().showUser(userModelToUserMapper.reversMap(user));
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                getView().showMessage("detected error!");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void destroy() {
        getUserById.dispose();
        setView(null);
    }

    public interface View extends Presenter.View {
        void showUser(UserModel userModel);
    }


}
