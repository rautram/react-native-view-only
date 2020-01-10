package com.navdemo.views;

import android.content.Context;
import android.widget.LinearLayout;

import com.navdemo.R;

public class SharedElementDetail extends LinearLayout
{
    private Context context;

    public SharedElementDetail(Context context) {
        super(context);
        this.context = context;
        inflateView();
    }

    public void inflateView() {

        inflate(this.context, R.layout.shared_detail, this);

    }
}
