package com.blogspot.uzhvij.vktest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.uzhvij.vktest.models.*;
import com.blogspot.uzhvij.vktest.requests.*;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.uzhvij.vktest.databinding.ActivityUserBinding;
import com.squareup.picasso.Picasso;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        window = ActivityUserBinding.inflate(getLayoutInflater());
        View mainView = window.getRoot();
        setContentView(mainView);

        window.logoutButton.setOnClickListener(view -> {
            VK.logout();
            App.startActivity(this, MainActivity.class);
            finish();
        });
        window.listButton.setOnClickListener(view -> requestFriends());
    }

    private void requestFriends() {

        VK.execute(new VkFriendsRequest(), new VKApiCallback<List<VkUser>>() {
            @Override
            public void success(List<VkUser> result) {
                showFriendsList(result);
            }

            @Override
            public void fail(@NotNull Exception e) {
                Log.e(App.TAG, e.toString());
            }
        });
    }


    private void showFriendsList(List<VkUser> friends) {
        Log.d(App.TAG, "showFriendsList: " + friends.get(0).toString());

        window.friendsList.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        FriendsAdapter adapter = new FriendsAdapter();
        adapter.setData(friends);
        window.friendsList.setAdapter(adapter);
    }

    class FriendsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<VkUser> friends = new ArrayList<>();

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null);
            return new UserHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((UserHolder)holder).bind(friends.get(position));
        }

        @Override
        public int getItemCount() {
            return friends.size();
        }

        public void setData(List<VkUser> friends) {
            this.friends.clear();
            this.friends.addAll(friends);
            notifyDataSetChanged();
        }
    }

    class UserHolder extends RecyclerView.ViewHolder{
        private final ImageView avatarIV;
        private final TextView nameTV;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            avatarIV  = itemView.findViewById(R.id.avatarIV);
            nameTV = itemView.findViewById(R.id.nameTV);
        }

        public void bind(VkUser user) {
            nameTV.setText(String.format("%s %s", user.firstName, user.lastName));
            if (!TextUtils.isEmpty(user.photo)) {
                Picasso.get().load(user.photo).error(R.drawable.user_placeholder).into(avatarIV);
            } else {
                avatarIV.setImageResource(R.drawable.user_placeholder);
            }
        }
    }
}