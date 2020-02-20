package com.navdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.HashMap;
import java.util.Map;

public class VideoViewManager extends SimpleViewManager<VideoView>  {
    private static final int COMMAND_FAST = 0;
    private static final int COMMAND_REWIND = 1;
    private static final int RELEASE_PLAYER = 2;
    @NonNull
    @Override
    public String getName() {
        return "VideoView";
    }

    @NonNull
    @Override
    protected VideoView createViewInstance(@NonNull ThemedReactContext reactContext) {

        VideoView videoView = new VideoView(reactContext);

        return videoView;

    }




    @Override
    public void receiveCommand(@NonNull final VideoView root, int commandId, @Nullable final ReadableArray args) {
        switch (commandId)
        {
            case COMMAND_FAST:
                root.fastForward();
                break;
            case COMMAND_REWIND:
                root.rewind();
                break;
            case RELEASE_PLAYER:
                root.releasePlayer();
                break;
                default:
                    root.releasePlayer();
        }
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> map = this.CreateMap(
                "forward", COMMAND_FAST,
                "rewind", COMMAND_REWIND,
                "release", RELEASE_PLAYER
        );

        return map;
    }

    public static <K, V> Map<K, V> CreateMap(
            K k1, V v1, K k2, V v2, K k3, V v3) {
        Map map = new HashMap<K, V>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }


}
