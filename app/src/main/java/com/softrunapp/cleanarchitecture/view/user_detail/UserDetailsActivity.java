package com.softrunapp.cleanarchitecture.view.user_detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.softrunapp.cleanarchitecture.MyApplication;
import com.softrunapp.cleanarchitecture.R;
import com.softrunapp.cleanarchitecture.base.view.BaseActivity;
import com.softrunapp.cleanarchitecture.view_model.UserModel;

import javax.inject.Inject;

import butterknife.BindView;

public class UserDetailsActivity extends BaseActivity implements UserDetailsPresenter.View {
    @BindView(R.id.image_avatar)
    ImageView avatar;
    @BindView(R.id.text_first_name)
    TextView firstName;
    @BindView(R.id.text_last_name)
    TextView lastName;
    @BindView(R.id.text_email)
    TextView email;
    @BindView(R.id.progressBar_detail)
    ProgressBar progressBar;

    @Inject
    UserDetailsPresenter presenter;

    @Override
    public void onStartView() {
        super.onStartView();
        initInjection();
        initPresenter();
        presenter.initialize();
    }

    private void initInjection() {
        ((MyApplication) getApplication()).getAppComponent().inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.setUserId(getUserId());
    }

    private int getUserId() {
        return getIntent().getExtras().getInt("userId");
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user_details;
    }

    @Override
    public void showUser(UserModel userModel) {
        firstName.setText(userModel.getFirstName());
        lastName.setText(userModel.getLastName());
        email.setText(userModel.getEmail());
        getImage(userModel.getAvatar(), avatar);
    }

    private void getImage(String avatar, ImageView avatarImageView) {
        Picasso.get().load(avatar).into(avatarImageView);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }


    public static void open(Context context, int userId) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }
}
