package com.alaa7amdy.soundplayer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alaa7amdy.soundplayer.Item;
import com.alaa7amdy.soundplayer.Main2Activity;
import com.alaa7amdy.soundplayer.MainActivity;
import com.alaa7amdy.soundplayer.Model.Song;
import com.alaa7amdy.soundplayer.R;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Song> mSongs;

    ArrayList<MediaPlayer> mediaPlayers;



    public Adapter(Context Context,ArrayList<Song> songs){
        mContext = Context;
        mSongs = songs;
    }
    @Override
    public int getCount() {
        return mSongs.size();
    }

    @Override
    public Object getItem(int position) {
        return mSongs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.song_item,parent,false);
        TextView songName = (TextView)view.findViewById(R.id.songname);
        TextView userName = (TextView)view.findViewById(R.id.username);
        CircularImageView profileImage = (CircularImageView)view.findViewById(R.id.image_profile);

        RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.item);


        songName.setText(mSongs.get(position).getSongName());
        userName.setText(mSongs.get(position).getUserName());

        //Glide.with(mContext).load(mSongs.get(position).getImageUrl()).into(profileImage);
        profileImage.setImageResource(Integer.parseInt(mSongs.get(position).getImageUrl()));

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, Main2Activity.class)
                        .putExtra("name",mSongs.get(position).getSongName()).putExtra("id",mSongs.get(position).getSongUrl()));


            }
        });



        return view;
    }

}
