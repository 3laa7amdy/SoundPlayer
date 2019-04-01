package com.alaa7amdy.soundplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    public static final String url = "https://geosynclinal-stands.000webhostapp.com/MusicPlayer/reg.php";
    EditText res_username, res_address, res_phone, res_email, res_password, res_re_password;
    TextView chosePhotoTextView;
    CircularImageView profileCircularImageView;

    String encodedImage = null;

    byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        res_username = (EditText) findViewById(R.id.res_username);
        res_email = (EditText) findViewById(R.id.res_email);
        res_address = (EditText) findViewById(R.id.res_address);
        res_phone = (EditText) findViewById(R.id.res_phoneunmber);
        res_password = (EditText) findViewById(R.id.res_password);
        res_re_password = (EditText) findViewById(R.id.res_re_password);
        chosePhotoTextView = (TextView)findViewById(R.id.chosephoto);
        profileCircularImageView = (CircularImageView)findViewById(R.id.profileimage);

        chosePhotoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .start(RegisterActivity.this);
            }
        });

        Button btn_reg = (Button) findViewById(R.id.btn_signup);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = res_username.getText().toString().trim();
                String email = res_email.getText().toString().trim();
                String address = res_address.getText().toString().trim();
                String phone = res_phone.getText().toString().trim();
                String pass = res_password.getText().toString().trim();
                String re_pass = res_re_password.getText().toString().trim();

                if (username.isEmpty() || pass.isEmpty() || address.isEmpty()
                        || phone.isEmpty() || email.isEmpty() || re_pass.isEmpty()) {

                    Toast.makeText(RegisterActivity.this, "Fill all details", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(re_pass)) {
                    Toast.makeText(RegisterActivity.this, "Please check Password and Re_Password", Toast.LENGTH_SHORT).show();
                } else {


                    signup( username, pass, address, phone, email);
                    SharedPreferences preferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.putString("username", username);
                    editor.putString("password", pass);
                    editor.putString("address", address);
                    editor.putString("phone", phone);
                    editor.putString("email", email);
                    editor.commit();
                    //show();

                }

            }
        });

    }

    public void signup (final String username, final String pass,
                         final String address, final String phone, final String email){

        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Hitesh", "resssssss" + response);

                show();

                Toast.makeText(RegisterActivity.this, "" + response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Hitesh", "eror" + error);
                Toast.makeText(RegisterActivity.this, "" + error.getCause(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> mapData = new HashMap<>();
                mapData.put("username", username);
                mapData.put("password", pass);
                mapData.put("address", address);
                mapData.put("phone", phone);
                mapData.put("email", email);
                mapData.put("image", encodedImage);
                return mapData;
            }
        };
        requestQueue.add(stringRequest);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getOriginalUri());

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                profileCircularImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //result.getBitmap();
            //String dataString = data.getDataString();




        } else {
            Toast.makeText(this, "Something gone wrong!", Toast.LENGTH_SHORT).show();
        }
    }


    public void show() {

//        Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);

        Intent intent = new Intent(RegisterActivity.this,LogInActivity.class);
        startActivity(intent);
        finish();
    }




}