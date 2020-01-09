package com.navdemo.views;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.navdemo.R;

public class SharedElementViewManager extends ViewGroupManager<SharedElementView> {
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

    @ReactProp(name = "id")
    public void setIdentifier(ReactViewGroup view, String id) {
        // view.setTag(R.id.react_shared_element_transition_name, id);
    }
}
