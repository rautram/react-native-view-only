package com.navdemo.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.navdemo.R;

public class SecondActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {


    private static final String COMPONENT_NAME = "second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ReactFragment reactFragment = new ReactFragment.Builder(COMPONENT_NAME).build();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_main, reactFragment)
                    .commit();
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    /**
     * Forward onKeyUp events to the ReactFragment in order to handle double tap reloads
     * and dev menus
     *
     * @param keyCode
     * @param event
     * @return true if event was handled
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean handled = false;
        Fragment activeFragment = getSupportFragmentManager().findFragmentById(R.id.container_main);
        if (activeFragment instanceof ReactFragment) {
            handled = ((ReactFragment) activeFragment).onKeyUp(keyCode, event);
        }
        return handled || super.onKeyUp(keyCode, event);
    }
}
