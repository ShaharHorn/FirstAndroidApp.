package com.shahar.ourfirstandroidapp;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";
    private static final int PICK_IMAGE = 100;
    //private static final String LIST_ACTIVITY ="SHAHAR";
    private DatePickerDialog.OnDateSetListener m_DateSetListener;
    private Button m_SelectImageButton,m_SubmitButton;
    private TextView m_DatePickTextView;
    private ImageView m_ImageView;
    private RadioButton m_MaleRadioButton, m_FemaleRaidoButton;
    private Uri m_ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e(TAG, "onCreate() >>");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        m_MaleRadioButton = (RadioButton)findViewById(R.id.maleRadioButton);
        m_FemaleRaidoButton = (RadioButton)findViewById(R.id.femaleRadioButton);
        m_SelectImageButton = (Button)findViewById(R.id.selectImageButton);
        m_ImageView = (ImageView)findViewById(R.id.AvatarImage);
        m_SelectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        m_DatePickTextView = (TextView)findViewById(R.id.dateOfBirthText);
        m_DatePickTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDatePicker();
            }
        });
        m_SubmitButton=(Button)findViewById(R.id.submitButton);
        m_SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ValidateUserInput())
                    return;

                Intent userData = new Intent(MainActivity.this, DisplayUserInfoActivity.class);
                userData.putExtra("full_name", ((EditText) findViewById(R.id.nameText)).getText().toString());
                userData.putExtra("email", ((EditText) findViewById(R.id.emailText)).getText().toString());
                userData.putExtra("password", ((EditText) findViewById(R.id.passwordText)).getText().toString());
                userData.putExtra("phone_number", ((EditText) findViewById(R.id.phoneNumberText)).getText().toString());
                userData.putExtra("date_of_birth", ((TextView) findViewById(R.id.dateOfBirthText)).getText().toString());
                userData.putExtra("image", m_ImageUri.toString());

                if (m_MaleRadioButton.isChecked())
                    userData.putExtra("gender", "Male");
                else
                    userData.putExtra("gender", "Female");

                startActivity(userData);
            }
        });
        Log.e(TAG, "onCreate() <<");
    }

    private boolean ValidateUserInput() {
        Log.e(TAG, "CheckIfValidAndSubmit() <<");

        boolean isValid = true;

        if(isValid && TextUtils.isEmpty(((EditText)findViewById(R.id.nameText)).getText()))
        {
            DisplayMessage("Please fill your name.");
            isValid =  false;
        }
        if(isValid && TextUtils.isEmpty(((EditText)findViewById(R.id.phoneNumberText)).getText()))
        {
            DisplayMessage("Please fill your phone number.");
            isValid =  false;
        }
        if(isValid && ((EditText)findViewById(R.id.phoneNumberText)).getText().length()!=10)
        {
            DisplayMessage("Phone number must contain 10 digits.");
            isValid =  false;
        }
        if(isValid && !TextUtils.isDigitsOnly(((EditText)findViewById(R.id.phoneNumberText)).getText()))
        {
            DisplayMessage("Phone number must contain only digits.");
            isValid =  false;
        }
        if(isValid && TextUtils.isEmpty(((EditText)findViewById(R.id.emailText)).getText()))
        {
            DisplayMessage("Please fill your email address.");
            isValid =  false;
        }
        if(isValid && !VerifyEmailAddress(((EditText)findViewById(R.id.emailText)).getText().toString()))
        {
            DisplayMessage("Please fill your email address.");
            isValid =  false;
        }
        if(isValid && TextUtils.isEmpty(((EditText)findViewById(R.id.passwordText)).getText()))
        {
            DisplayMessage("Please fill your password.");
            isValid =  false;
        }
        if(isValid && ((EditText)findViewById(R.id.passwordText)).getText().length() < 6)
        {
            DisplayMessage("Your password needs to contain at least 6 characters.");
            isValid =  false;
        }
        if(isValid && TextUtils.isEmpty(((TextView)findViewById(R.id.dateOfBirthText)).getText()))
        {
            DisplayMessage("Please pick a date of birth.");
            isValid =  false;
        }
        if(isValid && !m_MaleRadioButton.isChecked() && !m_FemaleRaidoButton.isChecked())
        {
            DisplayMessage("Please choose your gender.");
            isValid =  false;
        }

        Log.e(TAG, "CheckIfValidAndSubmit() >>");
        return isValid;
    }

    private void DisplayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private boolean VerifyEmailAddress(String email) {

        String regExpn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email == null) return false;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return (matcher.matches());
    }
    private void OpenGallery() {
        Log.e(TAG, "OpenGallery() <<");
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
        Log.e(TAG, "OpenGallery() >>");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        Log.e(TAG, "onActivityResult() <<");

        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            m_ImageUri = data.getData();
            m_ImageView.setImageURI(m_ImageUri);
        }
        Log.e(TAG, "onActivityResult() >>");
    }

    private void OpenDatePicker(){
        Log.e(TAG, "OpenDatePicker() <<");

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,m_DateSetListener,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        m_DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                m_DatePickTextView.setText(day +"/"+(month+1)+"/"+year);
            }
        };
        Log.e(TAG, "OpenDatePicker() >>");
    }
}