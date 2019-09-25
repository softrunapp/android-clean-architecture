package com.softrunapp.cleanarchitecture.view.user_list;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softrunapp.cleanarchitecture.MyApplication;
import com.softrunapp.cleanarchitecture.R;
import com.softrunapp.cleanarchitecture.adapter.UserAdapter;
import com.softrunapp.cleanarchitecture.base.view.BaseActivity;
import com.softrunapp.cleanarchitecture.view.user_detail.UserDetailsActivity;
import com.softrunapp.cleanarchitecture.view_model.UserModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class UserListActivity extends BaseActivity implements UserListPresenter.View {
    @Inject
    UserListPresenter presenter;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private UserAdapter adapter;
    @Override
    public void onStartView() {
        super.onStartView();
        initInjection();
        initPresenter();
        initAdapter();
        initRecyclerView();
        presenter.initialize();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initAdapter() {
        adapter = new UserAdapter(presenter);
    }

    private void initPresenter() {
        presenter.setView(this);
    }

    private void initInjection() {
        ((MyApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showUsers(List<UserModel> users) {
        adapter.addAll(users);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openUserPage(UserModel user) {
        UserDetailsActivity.open(this,user.getId());
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
}
