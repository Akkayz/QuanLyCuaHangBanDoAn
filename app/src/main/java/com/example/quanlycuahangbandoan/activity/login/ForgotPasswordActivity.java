package com.example.quanlycuahangbandoan.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlycuahangbandoan.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intentBackLogin = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intentBackLogin);*/
                finish();
            }
        });
    }
}