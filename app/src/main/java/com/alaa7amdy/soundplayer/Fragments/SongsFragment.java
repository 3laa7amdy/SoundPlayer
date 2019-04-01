package com.alaa7amdy.soundplayer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alaa7amdy.soundplayer.Adapter.Adapter;
import com.alaa7amdy.soundplayer.Adapter.SongAdapter;
import com.alaa7amdy.soundplayer.Item;
import com.alaa7amdy.soundplayer.Main2Activity;
import com.alaa7amdy.soundplayer.Model.Song;
import com.alaa7amdy.soundplayer.R;

import java.util.ArrayList;


public class SongsFragment extends Fragment {

    RecyclerView recyclerView;
    SongAdapter adapter;
    ArrayList<Song> songs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        try{
            Intent intent = getActivity().getIntent();
            Bundle bundle = intent.getExtras();
            String username =  bundle.getString("username");
            String image = bundle.getString("image");
            String songname =  bundle.getString("songname");
            String songuri = bundle.getString("songuri");
            songs.add(new Song(username,songname,image,songuri));
            adapter.notifyDataSetChanged();

        }catch (Exception e){

        }

        recyclerView = view.findViewById(R.id.rec);
        songs = new ArrayList<>();
        adapter = new SongAdapter(getContext(),songs);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

//        SharedPreferences sharedPreferences = getSharedPreferences("Pref",MODE_PRIVATE) ;
//        int id = sharedPreferences.getInt("id",0);

        add();

        return view;

        //recyclerView.setAdapter();
    }




    public void add(){
        Song i1 = new Song("alaa 111","song1","https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",""+"https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
        Song i2 = new Song("alaa 2","song2","https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",""+"https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
        Song i3 = new Song("alaa 3","song3","https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",""+"https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
        Song i4 = new Song("alaa 4","song4","https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",""+"https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
        Song i5 = new Song("alaa 5","song5","https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",""+"https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");
        Song i6 = new Song("alaa 6","song6","https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",""+"https://images.unsplash.com/photo-1509503643053-8fc818177382?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80");

        songs.add(i1);
        songs.add(i2);
        songs.add(i3);
        songs.add(i4);
        songs.add(i5);
        songs.add(i6);

        adapter.notifyDataSetChanged();

    }

}
