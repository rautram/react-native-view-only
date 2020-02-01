package com.navdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.gson.Gson;
import com.navdemo.fragment.ReactFragment;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ReactActivity implements DefaultHardwareBackBtnHandler {

  private static final String COMPONENT_NAME = "example";
  public static final String MyPREFERENCES = "MyPrefs";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Bundle bundle = getIntent().getExtras();

    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      ReactFragment reactFragment = new ReactFragment.Builder(COMPONENT_NAME).build();

      getSupportFragmentManager()
              .beginTransaction()
              .add(R.id.container_main, reactFragment)
              .commit();
    }

    if(bundle != null) {
      sendPushNotification(bundle);
    }

  }

  private void sendPushNotification(Bundle bundle) {
    WritableMap data = Arguments.createMap();
    Map<String, String> map = new HashMap<>();
    if(getReactInstanceManager().getCurrentReactContext() != null)
    {
      for(String key: bundle.keySet())
      {
        Object value = bundle.get(key);
        String stringValue = value.toString();
        data.putString(key, stringValue);
      }
      getReactInstanceManager().getCurrentReactContext()
              .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onReceived",data);
      Toast.makeText(this, "React Instance has been created", Toast.LENGTH_LONG).show();
    }
    else {
      SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
      // SharedPreferences.Editor editor = sharedPreferences.edit();

      for(String key: bundle.keySet())
      {
        Object value = bundle.get(key);
        String stringValue = value.toString();
        map.put(key, stringValue);
      }

      Toast.makeText(this, "OOps!! Instance not created", Toast.LENGTH_LONG);

      Gson gson = new Gson();
      String converted = gson.toJson(map);
      sharedPreferences.edit().putString("data", converted).commit();
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
