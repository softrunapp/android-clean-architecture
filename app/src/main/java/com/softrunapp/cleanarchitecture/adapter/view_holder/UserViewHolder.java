package com.softrunapp.cleanarchitecture.adapter.view_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.softrunapp.cleanarchitecture.R;
import com.softrunapp.cleanarchitecture.view.user_list.UserListPresenter;
import com.softrunapp.cleanarchitecture.view_model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private UserListPresenter userListPresenter;

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.first_name)
    TextView firstName;
    @BindView(R.id.email)
    TextView email;

    public UserViewHolder(@NonNull View itemView, UserListPresenter userListPresenter) {
        super(itemView);
        this.userListPresenter = userListPresenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(UserModel user) {
        onClickItem(user);
        firstName.setText(user.getFirstName());
        email.setText(user.getEmail());
        Picasso.get().load(user.getAvatar()).into(imageView);
    }

    private void onClickItem(final UserModel user) {
        itemView.setOnClickListener(v -> userListPresenter.onItemClicked(user));
    }
}
