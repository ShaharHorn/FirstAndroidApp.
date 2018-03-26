package com.shahar.ourfirstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayUserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_details);
        ((TextView)findViewById(R.id.nameText)).setText("NickName : " + getIntent().getExtras().getString("NickName"));
        ((TextView)findViewById(R.id.phoneNumberText)).setText("Phone Number : " + getIntent().getExtras().getString("Phone_Number"));
        ((TextView)findViewById(R.id.passwordText)).setText("Password: " + getIntent().getExtras().getString("Password"));
        ((TextView)findViewById(R.id.emailText)).setText("Email: " + getIntent().getExtras().getString("Email"));
       // ((TextView)findViewById(R.id.birthDateText)).setText("BirthDate: " + getIntent().getExtras().getString("Birthdate"));

    }
}
