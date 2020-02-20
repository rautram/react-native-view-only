package com.navdemo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;


public class VideoView extends FrameLayout implements LifecycleEventListener   {
    ThemedReactContext mcontext;
    SimpleExoPlayer player;
    PlayerView playerView;


    public VideoView(@NonNull ThemedReactContext context) {
        super(context);
        mcontext = context;
        mcontext.addLifecycleEventListener(this);
        initilizeView();
    }

    private void initilizeView() {

        inflate(mcontext, R.layout.activity_video, this);

        playerView = findViewById(R.id.video_view);

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(mcontext, trackSelector);

        playerView.setPlayer(player);

        RtmpDataSourceFactory rtmpDataSourceFactory = new RtmpDataSourceFactory();

        MediaSource videoSource = new ExtractorMediaSource.Factory(rtmpDataSourceFactory)
                .createMediaSource(Uri.parse("rtmp://stream1.livestreamingservices.com:1935/tvmlive/tvmlive"));

        player.prepare(videoSource);

        player.setPlayWhenReady(true);


    }

    public void fastForward()
    {
        Toast.makeText(mcontext, "Fast Forward is Clicked", Toast.LENGTH_LONG).show();
    }

    public void rewind()
    {
        Toast.makeText(mcontext, "Rewind is Clicked", Toast.LENGTH_LONG).show();
    }

    public void releasePlayer()
    {
        Toast.makeText(mcontext, "Player Release is trigerred", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onHostResume() {
       Toast.makeText(mcontext, "on Host Resume Called", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHostPause() {
        Toast.makeText(mcontext, "on Host Pause Called", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHostDestroy() {
        mcontext.removeLifecycleEventListener(this);
        Toast.makeText(mcontext, "on Host Destroy Called", Toast.LENGTH_LONG).show();
    }
}
