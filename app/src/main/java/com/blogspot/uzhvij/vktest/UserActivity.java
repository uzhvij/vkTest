package com.blogspot.uzhvij.vktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.uzhvij.vktest.databinding.ActivityUserBinding;
import com.blogspot.uzhvij.vktest.logic.FriendsAdapter;
import com.blogspot.uzhvij.vktest.models.VkUser;
import com.blogspot.uzhvij.vktest.requests.VkFriendsRequest;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiCallback;

import org.jetbrains.annotations.NotNull;

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
        window.friendsList.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        FriendsAdapter adapter = new FriendsAdapter();
        adapter.setData(friends);
        window.friendsList.setAdapter(adapter);
    }
}