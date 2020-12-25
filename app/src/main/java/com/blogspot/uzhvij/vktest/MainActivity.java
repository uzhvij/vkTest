package com.blogspot.uzhvij.vktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vk.api.sdk.utils.VKUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.text1);

        Log.d(TAG, "create");

        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
        Log.d(TAG, fingerprints[0] + " " + fingerprints.length);

        textView.setText("wow");

    }
}