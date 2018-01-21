package com.example.irina.astro_dating_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    List<String> list;
    ListView listView;
    String firstName;
    String lastName;
    String sex;
    String height;
    String weight;
    String hobby;
    String signName;
    String element;
    String email;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("users");
    String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    final DatabaseReference newRef = mRef.child(currentUserID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listView = (ListView) findViewById(R.id.userProfile);
        list = new ArrayList<>();

        newRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                firstName = String.valueOf(dataSnapshot.child("firstName").getValue());
                lastName = String.valueOf(dataSnapshot.child("lastName").getValue());
                sex = String.valueOf(dataSnapshot.child("sex").getValue());
                height = String.valueOf(dataSnapshot.child("height").getValue());
                weight = String.valueOf(dataSnapshot.child("weight").getValue());
                hobby = String.valueOf(dataSnapshot.child("hobby").getValue());
                signName = String.valueOf(dataSnapshot.child("signName").getValue());
                element = String.valueOf(dataSnapshot.child("element").getValue());
                email = String.valueOf(dataSnapshot.child("email").getValue());

                list.add("First Name: " + firstName);
                list.add("Last Name: " + lastName);
                list.add("Sex: " + sex);
                list.add("Height: " + height + " cm");
                list.add("Weight: " + weight + " kg");
                list.add("Hobby: " + hobby);
                list.add("Sign Name: " + signName);
                list.add("Sign Element: " + element);
                list.add("User Email: " + email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting message failed, log a message
                Log.w(TAG, "loadMessage:onCancelled", databaseError.toException());
            }
        });
        ListAdapter adapter = new ArrayAdapter<>(ProfileActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}
