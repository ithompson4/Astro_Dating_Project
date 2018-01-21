package com.example.irina.astro_dating_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ListView;

import java.util.ArrayList;

import com.example.irina.astro_dating_project.classes.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirstOptionFragment extends Fragment {

    private static final String TAG = "FirstOptionFragment";

    private Button getBtn;
    private ArrayList<UserInformation> list;
    private Activity mActivity;
    DatabaseReference mDatabaseReference;
    DatabaseReference mRef;
    private String cSex;
    private String cElement;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();

        mRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FindPartnerActivity) getActivity()).setCurrentFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_option, container, false);

        getBtn = (Button) view.findViewById(R.id.getBtn);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //current User Info
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference newRef = mRef.child(currentUserID);

        list = new ArrayList<>();
        final ListView listView = (ListView) view.findViewById(R.id.elemList);

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        cElement = String.valueOf(dataSnapshot.child("element").getValue());
                        cSex = String.valueOf(dataSnapshot.child("sex").getValue());
                        Log.d("Test", "current User element: " + cElement);
                        Log.d("Test", "current User sex: " + cSex);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting message failed, log a message
                        Log.w(TAG, "loadMessage:onCancelled", databaseError.toException());
                    }
                });

                mDatabaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            UserInformation tempElement = snapshot.getValue(UserInformation.class);
                            if (tempElement.getElement().equals(cElement) && (!tempElement.getSex().equals(cSex))) {
                                list.add(tempElement);
                            }
                        }
                        ElementAdapter elementAdapter = new ElementAdapter(list, mActivity);
                        listView.setAdapter(elementAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting message failed, log a message
                        Log.w(TAG, "loadMessage:onCancelled", databaseError.toException());
                    }
                });
            }
        });
        return view;
    }
}

