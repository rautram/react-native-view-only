package com.navdemo.modules;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.navdemo.fragment.SecondActivity;


public class ActivityStarter extends ReactContextBaseJavaModule {

     Context context;

    public ActivityStarter(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @NonNull
    @Override
    public String getName() {
        return "ActivityStarter";
    }

    @ReactMethod
    public void startActivity()
    {
        AppCompatActivity activity = (AppCompatActivity) getCurrentActivity();
        Intent intent = new Intent(activity, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
