package com.alaa7amdy.soundplayer.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alaa7amdy.soundplayer.MainActivity;
import com.alaa7amdy.soundplayer.R;
import com.alaa7amdy.soundplayer.RegisterActivity;
import com.alaa7amdy.soundplayer.Session;
import com.alaa7amdy.soundplayer.WelcomeActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class ProfileFragment extends Fragment {

    TextView logoutTextView,usernameTextView,emailTextView,phoneTextView,addressTextView;
    CircularImageView imageProfileCircularImageView;
    Button uploadButton;
    Session session;

    String url = "https://geosynclinal-stands.000webhostapp.com/MusicPlayer/addsong.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        session = new Session(getContext(),1);

        logoutTextView = view.findViewById(R.id.logout);
        usernameTextView = view.findViewById(R.id.username);
        emailTextView = view.findViewById(R.id.email);
        phoneTextView = view.findViewById(R.id.phone);
        addressTextView = view.findViewById(R.id.address);
        imageProfileCircularImageView = view.findViewById(R.id.profileimage);
        uploadButton = view.findViewById(R.id.upload);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/mpeg");
                startActivityForResult(Intent.createChooser(intent, "music"), 11);
            }
        });
        

        usernameTextView.setText(session.getUSERNAME());
        emailTextView.setText(session.getEMAIL());

        phoneTextView.setText(session.getPHONE());
        addressTextView.setText(session.getADDRESS());
        //decode base64 string to image
        byte[] imageBytes = Base64.decode(session.getIMAGE(), Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        //image.setImageBitmap(decodedImage);
        imageProfileCircularImageView.setImageBitmap(decodedImage);

        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                session.clearUserData();
                startActivity(new Intent(getContext(),WelcomeActivity.class));
            }
        });

        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == Activity.RESULT_OK){
            if ((data != null) && (data.getData() != null)){
                Uri audioFileUri = data.getData();




//                try {
//                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getOriginalUri());
//
//                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
//                    byte[] imageBytes = baos.toByteArray();
//                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }




                // Now you can use that Uri to get the file path, or upload it, ...
            }
        }
    }

    public void upload (final String userid, final String songname,
                        final String songuri){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh", "resssssss" + response);

                Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh", "eror" + error);
                Toast.makeText(getContext(), "" + error.getCause(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mapData = new HashMap<>();
                mapData.put("userid", userid);
                mapData.put("songname", songname);
                mapData.put("songuri", songuri);
                return mapData;
            }
        };
        requestQueue.add(stringRequest);

    }

}
