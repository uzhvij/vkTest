package com.blogspot.uzhvij.vktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blogspot.uzhvij.vktest.databinding.ActivityUserBinding;
import com.vk.api.sdk.VK;

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
        
        window.listButton.setOnClickListener(view -> showFriendsList());
    }

    private void showFriendsList() {
        Log.d(App.TAG, "showFriendsList: ");
        /*String response = "";
        JSONObject jsonObject = new JSONObject();
        VKRequest request = new VKRequest("users.getSubscriptions", "5.126");
        //request.execute()
        try {
            Log.d(App.TAG, "parse");
            Log.d(App.TAG, " " + request.toString());
            request.parse(jsonObject);
            Log.d(App.TAG, "response " + response);
            Log.d(App.TAG, "response " + jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}