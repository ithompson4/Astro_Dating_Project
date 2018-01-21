package com.example.irina.astro_dating_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.irina.astro_dating_project.classes.UserInformation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstNameET;
    private EditText lastNameET;
    private EditText heightET;
    private EditText weightET;
    private EditText hobbyET;

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;

    private Button registerBtn;

    String userID;
    String userEmail;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameET = (EditText) findViewById(R.id.firstName);
        lastNameET = (EditText) findViewById(R.id.lastName);
        heightET = (EditText) findViewById(R.id.height);
        weightET = (EditText) findViewById(R.id.weight);
        hobbyET = (EditText) findViewById(R.id.hobby);

        radioGroup1 = (RadioGroup) findViewById(R.id.group_1);
        radioGroup2 = (RadioGroup) findViewById(R.id.group_2);

        registerBtn = (Button) findViewById(R.id.btnRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        Intent regIntent = getIntent();
        userID = regIntent.getStringExtra(GoogleSignInActivity.ID_KEY);
        userEmail = regIntent.getStringExtra(GoogleSignInActivity.ID_EMAIL);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void register() {
        initialize();
        if (!(validate())) {
            Toast.makeText(this, "Register failed", Toast.LENGTH_LONG).show();
        } else {
            onRegisterSuccess();
        }
    }

    public void onRegisterSuccess() {
        UserInformation userInformation = new UserInformation(uID, fName, lName, sex, ht, wt, hobby, sName, elem, em);
        mDatabaseReference.child("users").child(userID).setValue(userInformation);
        Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(RegisterActivity.this, FindPartnerActivity.class);
        startActivity(intent);
    }

    public boolean validate() {
        boolean valid = true;

        if (fName.isEmpty() || fName.length() > 25) {
            firstNameET.setError("Please enter valid first name");
            valid = false;
        }
        if (lName.isEmpty() || lName.length() > 25) {
            lastNameET.setError("Please enter valid last name");
            valid = false;
        }
        if (ht.isEmpty()) {
            heightET.setError("Please enter valid height");
            valid = false;
        }
        if (wt.isEmpty()) {
            weightET.setError("Please enter valid weight");
            valid = false;
        }
        if (hobby.isEmpty() || hobby.length() > 30) {
            hobbyET.setError("Please enter valid hobby");
            valid = false;
        }
        return valid;
    }

    private String uID;
    private String fName;
    private String lName;
    private String sex;
    private String ht;
    private String wt;
    private String hobby;
    private String sName;
    private String elem;
    private String em;

    public void initialize() {
        uID = userID;
        em = userEmail;
        fName = firstNameET.getText().toString().trim();
        lName = lastNameET.getText().toString().trim();
        sex = ((RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId())).getText().toString();
        ht = heightET.getText().toString().trim();
        wt = weightET.getText().toString().trim();
        hobby = hobbyET.getText().toString().trim();
        sName = ((RadioButton) findViewById(radioGroup2.getCheckedRadioButtonId())).getText().toString();

        if (sName.equals("Aries") || sName.equals("Leo") || sName.equals("Sagittarius")) {
            elem = "Fire";
        }
        else if (sName.equals("Taurus") || sName.equals("Virgo") || sName.equals("Capricorn")) {
            elem = "Earth";
        }
        else if (sName.equals("Gemini") || sName.equals("Libra") || sName.equals("Aquarius")) {
            elem = "Air";
        }
        else if (sName.equals("Cancer") || sName.equals("Scorpio") || sName.equals("Pisces")) {
            elem = "Water";
        }
        else {
            Toast.makeText(this, "Cannot set Zodiac Sign Element", Toast.LENGTH_LONG).show();
        }
    }
}


