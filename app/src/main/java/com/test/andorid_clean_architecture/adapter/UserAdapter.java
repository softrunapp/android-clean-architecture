package com.test.andorid_clean_architecture.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.andorid_clean_architecture.R;
import com.test.andorid_clean_architecture.adapter.view_holder.UserViewHolder;
import com.test.andorid_clean_architecture.view.user_list.UserListPresenter;
import com.test.andorid_clean_architecture.view_model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final UserListPresenter presenter;
    private final List<UserModel> userList;

    public UserAdapter(UserListPresenter presenter) {
        this.presenter = presenter;
        this.userList = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);

        return new UserViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.render(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addAll(Collection<UserModel> userModels) {
        userList.addAll(userModels);
    }
}
