package com.blogspot.uzhvij.vktest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKTokenExpiredHandler;

public class App extends Application {
    public static final String TAG = "myLogs";
    private static App ourInstance;
    private static final VKTokenExpiredHandler handler = () -> startActivity(App.getInstance(), MainActivity.class);

    static App getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        VK.addTokenExpiredHandler(handler);
    }

    static void startActivity(Context context, Class<?> t){
        Intent intent = new Intent(context, t);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
