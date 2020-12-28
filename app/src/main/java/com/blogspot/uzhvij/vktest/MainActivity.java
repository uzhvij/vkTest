package com.blogspot.uzhvij.vktest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.uzhvij.vktest.databinding.ActivityMainBinding;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;
import com.vk.api.sdk.auth.VKScope;
import com.vk.api.sdk.utils.VKUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if we are login - go to User activity, else stay here
        if (VK.isLoggedIn()) {
            App.startActivity(this, UserActivity.class);
            finish();
            return;
        }

        window = ActivityMainBinding.inflate(getLayoutInflater());
        View mainView = window.getRoot();
        setContentView(mainView);
        window.loginButton.setOnClickListener(view -> loginToVkWithPermissions());
    }

    //login result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        VKAuthCallback vkAuthCallback = new VKAuthCallback() {
            @Override
            public void onLogin(@NotNull VKAccessToken vkAccessToken) {
                Log.d(App.TAG, "onLogin: " + vkAccessToken.getAccessToken());
                App.startActivity(MainActivity.this, UserActivity.class);
                finish();
            }

            @Override
            public void onLoginFailed(int i) {
                Log.d(App.TAG, "onLoginFailed");
            }
        };

        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, vkAuthCallback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //open web view to login to vk
    private void loginToVkWithPermissions() {
        List<VKScope> permissionsList = new ArrayList<>();
        permissionsList.add(VKScope.FRIENDS);
        permissionsList.add(VKScope.PHONE);
        permissionsList.add(VKScope.PHOTOS);
        VK.login(this, permissionsList);
    }

    //need for vk developers site to creating your app
    private void getAppFingerprint() {
        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
        assert fingerprints != null : "fingerprint is null";
        Log.d(App.TAG, fingerprints[0] + " " + fingerprints.length);
    }
}