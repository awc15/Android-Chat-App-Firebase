package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pl.droidsonroids.gif.GifImageView;

public class StartActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null){

            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        GifImageView gif=findViewById(R.id.gifImage);
        gif.setBackgroundResource(R.drawable.welcome);

    }

    public void openLogin(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }

    public void openRegistration(View view) {
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
    }
}