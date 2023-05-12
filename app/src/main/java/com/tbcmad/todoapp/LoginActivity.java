package com.tbcmad.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.et_username);
        userName.setHintTextColor(Color.WHITE);
        password = (EditText) findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.login_activity_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userName.getText().toString().equals("test") && password.getText().toString().equals("test")) {
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo pref", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("authentication", true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    userName.requestFocus();
                    userName.setError("Name is required");
                    password.requestFocus();
                    password.setError("Password is required");
                    return;
                }
            }
        });
    }
}