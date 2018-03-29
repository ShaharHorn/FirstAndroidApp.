package com.shahar.ourfirstandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class DisplayUserInfoActivity extends AppCompatActivity {

    private String phoneNumber;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_user_info_activity);
        ((TextView)findViewById(R.id.nameText)).setText("Full Name: " + getIntent().getExtras().getString("full_name"));
        phoneNumber = getIntent().getExtras().getString("phone_number");
        email = getIntent().getExtras().getString("email");
        ((TextView)findViewById(R.id.phoneNumberText)).setText("Phone Number: " + phoneNumber);
        ((TextView)findViewById(R.id.emailText)).setText("Email: " + email);
        ((TextView)findViewById(R.id.passwordText)).setText("Password: " + getIntent().getExtras().getString("password"));
        ((TextView)findViewById(R.id.gender)).setText("Gender: " + getIntent().getExtras().getString("gender"));
        ((TextView)findViewById(R.id.date_of_birth)).setText("Date Of Birth: " + getIntent().getExtras().getString("date_of_birth"));
        ((ImageView)findViewById(R.id.avatar)).setImageURI(Uri.parse(getIntent().getExtras().getString("image")));
    }

    public void onDialButtonClicked(View v){
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(dialIntent);
    }

    public void onSendEmailButtonClicked(View v){
        Intent sendMailIntent = new Intent(Intent.ACTION_VIEW);
        sendMailIntent.setData(Uri.parse("mailto:?to=" + email));
        startActivity(sendMailIntent);
    }
}
