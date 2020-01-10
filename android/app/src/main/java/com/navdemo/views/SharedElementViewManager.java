package com.navdemo.views;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.navdemo.R;

public class SharedElementViewManager extends SimpleViewManager<LinearLayout>
{

    @NonNull
    @Override
    public String getName() {
        return "SharedElement";
    }

    @NonNull
    @Override
    protected SharedElementView createViewInstance(@NonNull ThemedReactContext reactContext) {
        return new SharedElementView(reactContext);
    }
}