package com.alaa7amdy.soundplayer.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alaa7amdy.soundplayer.Model.Song;
import com.alaa7amdy.soundplayer.R;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    Context mContext;
    ArrayList<Song> mSongs;

    public SongAdapter(Context mContext, ArrayList<Song> mSongs) {
        this.mContext = mContext;
        this.mSongs = mSongs;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.song_item, viewGroup, false);
        return new SongAdapter.SongHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder songHolder, int i) {
        Song song = mSongs.get(i);

        songHolder.songNameTextView.setText(song.getSongName());
        songHolder.userNameTextView.setText(song.getUserName());
        Glide.with(mContext).load(song.getImageUrl()).into(songHolder.profileImageView);



    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public class SongHolder extends RecyclerView.ViewHolder{

        public TextView songNameTextView,userNameTextView;
        public CircularImageView profileImageView;
        public Button playButton;


        public SongHolder(@NonNull View itemView) {
            super(itemView);
            songNameTextView =itemView.findViewById(R.id.songname);
            userNameTextView =itemView.findViewById(R.id.username);
            profileImageView =itemView.findViewById(R.id.image_profile);
            playButton =itemView.findViewById(R.id.btn_play);

        }
    }
}
