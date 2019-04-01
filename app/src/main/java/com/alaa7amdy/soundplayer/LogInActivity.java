package com.alaa7amdy.soundplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LogInActivity extends AppCompatActivity {

    EditText username , password ;
    Button login , signUp;
    String url;

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //startActivity(new Intent(LogInActivity.this,MainActivity.class));

        session = new Session(this,1);

        username = (EditText) findViewById( R.id.username);
        password = (EditText) findViewById(R.id.password);
        login =(Button) findViewById(R.id.logein);
        signUp = (Button) findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this, RegisterActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInMethod();

            }
        });



    }

    public void signInMethod() {
        String user,pass;
        user = username.getText().toString().trim();
        pass = password.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(LogInActivity.this, "fill details", Toast.LENGTH_SHORT).show();

        } else {

            login(user, pass);

        }
    }

    public void login(final String user, final String pass){

        url = "https://geosynclinal-stands.000webhostapp.com/MusicPlayer/login.php?username="+user+"&password="+pass+"";
        Log.i("Hiteshurl",""+url);
        RequestQueue requestQueue = Volley.newRequestQueue(LogInActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);

                    try{

                        String id = jsonObject1.getString("id");
                        String username = jsonObject1.getString("username");
                        String pass = jsonObject1.getString("password");
                        String email = jsonObject1.getString("email");
                        String phone = jsonObject1.getString("phone");
                        String address = jsonObject1.getString("address");
                        String image = jsonObject1.getString("image");
                        session.setLogIn(false);

                        session.setUserData(id, username, pass, email, phone, address, image);

                        Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }catch (JSONException e){

                        Toast.makeText(LogInActivity.this,e.getMessage()+"Username Or Password Are Wrong", Toast.LENGTH_SHORT).show();


                    }
//                    SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = shared.edit();
//                    editor.putString("id",id);
//                    editor.putString("username", username);
//                    editor.putString("password", pass);
//                    editor.commit();
                } catch (JSONException e) {
                    Toast.makeText(LogInActivity.this,e.getMessage()+"Please ckeck Username Or Password", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HiteshURLerror",""+error);
                Toast.makeText(LogInActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);

    }

}
