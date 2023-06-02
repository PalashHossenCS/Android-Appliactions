package com.example.signuplogin;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        try {
            YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view1);
            getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = "PnueuY3PltM";
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });

            youTubePlayerView = view.findViewById(R.id.youtube_player_view2);
            getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = "3WvVSKGEWTI";
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });

            youTubePlayerView = view.findViewById(R.id.youtube_player_view3);
            getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = "3vuoNiV6OY8";
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });

            youTubePlayerView = view.findViewById(R.id.youtube_player_view4);
            getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = "Q181q-J53_c";
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });

            youTubePlayerView = view.findViewById(R.id.youtube_player_view5);
            getLifecycle().addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = "QWG0jIdBrFk";
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(), "Stopped", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}