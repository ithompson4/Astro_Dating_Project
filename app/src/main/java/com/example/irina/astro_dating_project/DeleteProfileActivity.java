package com.example.irina.astro_dating_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.irina.astro_dating_project.classes.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteProfileActivity extends AppCompatActivity {
    private static final String TAG = "DeleteProfileActivity";

    private Button deleteBtn;

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("users");
    String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    final DatabaseReference newRef = mRef.child(currentUserID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);
        deleteBtn = (Button) findViewById(R.id.delete_profile);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().setValue(null);
                        Toast.makeText(DeleteProfileActivity.this, "Profile has been deleted.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting message failed, log a message
                        Log.w(TAG, "loadMessage:onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }
}
