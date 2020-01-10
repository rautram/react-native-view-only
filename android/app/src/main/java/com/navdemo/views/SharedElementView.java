package com.navdemo.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.navdemo.R;

public class SharedElementView extends LinearLayout
{
   private Context context;

    public SharedElementView(Context context) {
        super(context);
        this.context = context;
        inflateView();
    }

    public void inflateView() {

        inflate(this.context, R.layout.shared_view, this);

    }
}

