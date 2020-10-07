package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        allFindViewByIds();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
    }

    private void allFindViewByIds() {
        toolbar = findViewById(R.id.toolbar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void login_User(final View view) {
        String txtEmail = email.getText().toString();
        String txtPassword = password.getText().toString();

        if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
            Snackbar.make(view,"All fields required", BaseTransientBottomBar.LENGTH_SHORT).show();
        }else{
            auth.signInWithEmailAndPassword(txtEmail,txtPassword)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        Snackbar.make(view,"Authentication Failed",BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}