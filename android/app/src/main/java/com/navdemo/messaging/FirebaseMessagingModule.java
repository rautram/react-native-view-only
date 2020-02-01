package com.navdemo.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Map;

import javax.annotation.Nonnull;

public class FirebaseMessagingModule extends ReactContextBaseJavaModule {

    private String token;

    private static FirebaseMessagingModule mInstance = null;

    public FirebaseMessagingModule(@Nonnull ReactApplicationContext reactContext) {
        super(reactContext);

        if (mInstance == null)
        {
            mInstance = this;
        }
    }

    public static FirebaseMessagingModule getInstance()
    {
        return mInstance;
    }

    @Nonnull
    @Override
    public String getName() {
        return "FirebaseMessaging";
    }


    @ReactMethod
    public void getFirebaseToken(Promise promise)
    {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful())
                        {
                            System.out.println(task.getException());
                            promise.reject("err", "Unable to get token");
                            return;
                        }
                        token = task.getResult().getToken();
                        System.out.println("the token is "+ token);
                        promise.resolve(token);
                        Toast.makeText(getReactApplicationContext(), "the token is "+token, Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**--- This method is called from MainActivity when app is in Killed mode **/
    @ReactMethod
    public void getData(Promise promise)
    {
        SharedPreferences sharedPreferences = getReactApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        WritableMap writableMap = Arguments.createMap();
        String data =  sharedPreferences.getString("data", null);
        writableMap.putString("data",data);
        promise.resolve(writableMap);
        // sendDataToReact(writableMap);
       // sharedPreferences.edit().clear().commit();

    }

    @ReactMethod
    public void clearPreference()
    {
        SharedPreferences sharedPreferences = getReactApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }



    public void sendDataToReact(WritableMap data)
    {
        getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("onReceived", data);

    }


}

