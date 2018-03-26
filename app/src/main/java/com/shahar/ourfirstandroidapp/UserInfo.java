package com.shahar.ourfirstandroidapp;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UserInfo extends AppCompatActivity {
    private static final int PICK_IMAGE =100;
    private static final String LIST_ACTIVITY ="SHAHAR";
    DatePickerDialog.OnDateSetListener m_DateSetListener;
    Button m_ImageButton,m_SubmitButton;
    TextView m_DatePick;
    ImageView m_ImageView;
    Uri m_ImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        m_ImageButton = (Button) findViewById(R.id.ImageButton);
        m_ImageView = (ImageView)findViewById(R.id.AvatarImage);
        m_ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        m_DatePick = (TextView)findViewById(R.id.birthDateText);
        m_DatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });
        m_SubmitButton=(Button)findViewById(R.id.submitButton);
        m_SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    checkIfValidAndSubmit();
                }
                catch (Exception e) {

                    //TODO
                }
            }
        });

    }

    private void checkIfValidAndSubmit() throws Exception {
        /*
        if(TextUtils.isEmpty(((EditText)findViewById(R.id.nameText)).getText()))
            throw new Exception("You must fill your name!");
        if(TextUtils.isEmpty(((EditText)findViewById(R.id.phoneNumberText)).getText()))
            throw new Exception("You must fill your phone number!");
        if(((EditText)findViewById(R.id.phoneNumberText)).getText().length()!=10)
            throw new Exception("incorrect phone number!");
        if(TextUtils.isEmpty(((EditText)findViewById(R.id.emailText)).getText()))
            throw new Exception("You must fill your email address");
        if(TextUtils.isEmpty(((EditText)findViewById(R.id.passwordText)).getText()))
            throw new Exception("You must fill your password");
        if(TextUtils.isEmpty(((EditText)findViewById(R.id.nameText)).getText()))
            throw new Exception("You must fill your name");
        if(TextUtils.isEmpty(((TextView)findViewById(R.id.birthDateText)).getText()))
            throw new Exception("You have to pick a birthdate");
        */
    try {
        //String name = ((EditText) findViewById(R.id.nameText)).getText().toString();
        Intent passDataIntent = new Intent(UserInfo.this, DisplayUserDetails.class);
        passDataIntent.putExtra("NickName", ((EditText) findViewById(R.id.nameText)).getText().toString());
        passDataIntent.putExtra("Email", ((EditText) findViewById(R.id.emailText)).getText().toString());
        passDataIntent.putExtra("Password", ((EditText) findViewById(R.id.passwordText)).getText().toString());
        passDataIntent.putExtra("Phone_Number", ((EditText) findViewById(R.id.phoneNumberText)).getText().toString());
        passDataIntent.putExtra("Birthdate", ((TextView) findViewById(R.id.birthDateText)).getText().toString());
        startActivity(passDataIntent);
        }
        catch (Exception e)
        {
            Log.d(LIST_ACTIVITY,e.getMessage());
        }
    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            m_ImageUri = data.getData();
            m_ImageView.setImageURI(m_ImageUri);
        }
    }

    private void openDatePicker(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(UserInfo.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,m_DateSetListener,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        m_DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                m_DatePick.setText(day +"/"+(month+1)+"/"+year);
            }
        };
    }
}