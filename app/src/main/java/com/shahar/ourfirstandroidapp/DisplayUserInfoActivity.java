package com.shahar.ourfirstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayUserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_user_info_activity);
        ((TextView)findViewById(R.id.nameText)).setText("Full Name: " + getIntent().getExtras().getString("full_name"));
        ((TextView)findViewById(R.id.phoneNumberText)).setText("Phone Number: " + getIntent().getExtras().getString("phone_number"));
        ((TextView)findViewById(R.id.emailText)).setText("Email: " + getIntent().getExtras().getString("email"));
        ((TextView)findViewById(R.id.passwordText)).setText("Password: " + getIntent().getExtras().getString("password"));
        ((TextView)findViewById(R.id.emailText)).setText("Gender: " + getIntent().getExtras().getString("gender"));
        ((TextView)findViewById(R.id.emailText)).setText("Date Of Birth: " + getIntent().getExtras().getString("date_of_birth"));
    }
}
