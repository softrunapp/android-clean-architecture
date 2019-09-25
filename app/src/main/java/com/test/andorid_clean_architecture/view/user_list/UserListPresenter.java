package com.test.andorid_clean_architecture.view.user_list;

import com.test.andorid_clean_architecture.base.presenter.Presenter;
import com.test.andorid_clean_architecture.view_model.UserModel;
import com.test.andorid_clean_architecture.view_model.mapper.UserModelToUserMapper;
import com.test.domain.intractor.GetUserList;
import com.test.domain.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class UserListPresenter extends Presenter<UserListPresenter.View> {
    private GetUserList getUserList;
    private UserModelToUserMapper userModelToUserMapper;

    @Inject
    public UserListPresenter(GetUserList getUserList, UserModelToUserMapper userModelToUserMapper) {
        this.getUserList = getUserList;
        this.userModelToUserMapper = userModelToUserMapper;
    }

    @Override
    public void initialize() {
        super.initialize();
        getView().showLoading();
        getUserList.execute(new DisposableObserver<List<User>>() {
            @Override
            public void onNext(List<User> users) {
                getView().showUsers(userModelToUserMapper.reversMap(users));
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

    public void onItemClicked(UserModel user) {
        getView().openUserPage(user);
    }

    @Override
    public void destroy() {
        super.destroy();
        getUserList.dispose();
        setView(null);
    }

    public interface View extends Presenter.View {
        void showUsers(List<UserModel> users);

        void openUserPage(UserModel user);
    }
}
