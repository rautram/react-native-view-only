package com.navdemo.views;

import android.content.Context;
import android.view.ViewGroup;

public class SharedElementView extends ViewGroup {

    public SharedElementView(Context context) {
        super(context);

        int width  = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, height);
        this.setLayoutParams(params);
        this.setFitsSystemWindows(false);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    @Override
    public void requestLayout() {
        super.requestLayout();
        post(measureAndLayout);
    }
}
