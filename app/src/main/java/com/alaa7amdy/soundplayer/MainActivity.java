package com.alaa7amdy.soundplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alaa7amdy.soundplayer.Fragments.ProfileFragment;
import com.alaa7amdy.soundplayer.Fragments.SongsFragment;
import com.irozon.justbar.BarItem;
import com.irozon.justbar.JustBar;
import com.irozon.justbar.interfaces.OnBarItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    JustBar justBar;

    Fragment selectedfragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        justBar = findViewById(R.id.justBar);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                new SongsFragment()).commit();

        justBar.setOnBarItemClickListener(new OnBarItemClickListener() {
            @Override
            public void onBarItemClick(BarItem barItem, int position) {
                // Your code here

                switch (barItem.getId()){
                    case R.id.songs:
                        selectedfragment = new SongsFragment();
                        break;
                    case R.id.profile:
                        selectedfragment = new ProfileFragment();
                        break;

                }

                if (selectedfragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,
                            selectedfragment).commit();
                }
            }
        });

    }
}
