package com.navdemo.views;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class SharedElementDetailManager extends SimpleViewManager<LinearLayout>
{

    @NonNull
    @Override
    public String getName() {
        return "SharedElementDetail";
    }

    @NonNull
    @Override
    protected SharedElementDetail createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new SharedElementDetail(reactContext);
    }
}
