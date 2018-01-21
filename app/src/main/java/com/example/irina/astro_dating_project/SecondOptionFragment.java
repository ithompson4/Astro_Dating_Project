package com.example.irina.astro_dating_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class SecondOptionFragment extends Fragment {

    private static final String TAG = "SecondOptionFragment";

    RadioGroup radioGroup3;
    TextView matchResult;
    Button getBtn2;
    String radioValue;
    DatabaseReference mDatabaseReference;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("users");

    String signArray[] = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((FindPartnerActivity) getActivity()).setCurrentFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_second_option, container, false);

        getBtn2 = (Button) view.findViewById(R.id.getBtn2);
        matchResult = (TextView) view.findViewById(R.id.sign_result2);
        radioGroup3 = (RadioGroup) view.findViewById(R.id.radio_group_3);

        // current User info
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        final String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference newRef = mRef.child(currentUserID);
        Log.d("Test", "current User Id: " + currentUserID);

        getBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioValue = ((RadioButton) view.findViewById(radioGroup3.getCheckedRadioButtonId())).getText().toString();
                newRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String userSign = String.valueOf(dataSnapshot.child("signName").getValue());
                        Log.d("Test", "current User sign: " + userSign);

                        int pos1 = Arrays.asList(signArray).indexOf(userSign);
                        int pos2 = Arrays.asList(signArray).indexOf(radioValue);

                        if (pos1 == pos2) {
                            matchResult.setText("1:1 Relationship.\nThis combination is harmonious in the sense that you will often see things the same way." +
                                    " But you will also tend to magnify each other's faults. Good for your personal relationship," +
                                    " but perhaps not so good for handling difficult external challenges.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -1) {
                            matchResult.setText("1:2 Relationship.\nThis is a weakly harmonious relationship combination. " +
                                    "You have little in common however, and this relationship is unlikely to last.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -2) {
                            matchResult.setText("1:3 Relationship.\nThis is a creative relationship. You tend to inspire each other to" +
                                    " come up with new ideas, ways of doing things. This is good for problem solving. " +
                                    "There may be differences in terms of how you approach romance which may lead to occasional conflicts but boredom is unlikely.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -3) {
                            matchResult.setText("1:4 Relationship.\nThis is a challenge relationship. There is likely to be much conflict," +
                                    " differences in feelings, doing and thinking. Friction may be continual and it may take much effort and self-control" +
                                    " on both sides to keep this relationship going in the longer term. ");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -4) {
                            matchResult.setText("1:5 Relationship.\nThis is a relationship that is fundamentally harmonious. You complement rather than" +
                                    " duplicate each other. You tend to balance each other's strength and weaknesses and bring out the best in each other." +
                                    " Both love and friendship blossom under this influence.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -5) {
                            matchResult.setText("1:6 Relationship.\nThis tends to be a \"Master-Servant\" type of relationship where YOU tend" +
                                    " to be the main beneficiary. YOU need to work harder than usual to avoid exploiting your Potential Partner or" +
                                    " this relationship may disintegrate.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -6) {
                            matchResult.setText("1:7 Relationship.\nThis is a relationship based on partnership, open opposition, or sometimes of mixture of both. " +
                                    "You are very different individuals but here opposites attract each other. Maintaining this relationship can be hard work but " +
                                    "if successful, you may be an exceptionally effective \"Power Couple\".");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -7) {
                            matchResult.setText("1:8 Relationship.\nAlthough on the surface others may see little in common between you, this relationship may" +
                                    " have an exceptionally powerful hidden dimension. There may be an exceptionally strong sexual attraction" +
                                    " You may share hidden secrets. There may be an aspect of death and rebirth involved. ");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -8) {
                            matchResult.setText("1:9 Relationship.\nThis relationship ma y impact your belief system, philosophy, or outlook on life." +
                                    " Travel to foreign countries may be involved that opens your mind and broaden your outlook." +
                                    " It may help you raise your consciousness to a higher level.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -9) {
                            matchResult.setText("1:10 Relationship.\nAlthough there may be frequent disagreement, this relationship may impact heavily your ambition," +
                                    " and your career goals. The conflict between you may act to push you to greater level of achievement and creativity," +
                                    " but may make this a very challenging relationship to maintain in the longer term.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -10) {
                            matchResult.setText("1:11 Relationship.\nThis is a relationship based primarily on friendship. In a very real sense you" +
                                    " may be exceptionally close friends and this may be confused with love in some cases. It may be very easy for you" +
                                    " to communicate with each other and share your hopes, dreams and wishes.");
                        }
                        else if (pos1 < pos2 && (pos1 - pos2) == -11) {
                            matchResult.setText("1:12 Relationship.\nThis may be one of the more challenging relationship combination." +
                                    " This relationship may undermine you in some way either consciously or unconsciously. There may be an aspect" +
                                    " of restriction of confinement. You probably have little in common and there may be an aspect of compulsion" +
                                    " in that you may have been forced to stay together by external circumstances. There is a danger, however, that" +
                                    " once these circumstances are removed this relationship may collapse.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -1) {
                            matchResult.setText("1:2 Relationship.\nThis is a weakly harmonious relationship combination. " +
                                    "You have little in common however, and this relationship is unlikely to last.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -2) {
                            matchResult.setText("1:3 Relationship.\nThis is a creative relationship. You tend to inspire each other to" +
                                    " come up with new ideas, ways of doing things. This is good for problem solving. " +
                                    "There may be differences in terms of how you approach romance which may lead to occasional conflicts but boredom is unlikely.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -3) {
                            matchResult.setText("1:4 Relationship.\nThis is a challenge relationship. There is likely to be much conflict," +
                                    " differences in feelings, doing and thinking. Friction may be continual and it may take much effort and self-control" +
                                    " on both sides to keep this relationship going in the longer term.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -4) {
                            matchResult.setText("1:5 Relationship.\nThis is a relationship that is fundamentally harmonious. You complement rather than" +
                                    " duplicate each other. You tend to balance each other's strength and weaknesses and bring out the best in each other." +
                                    " Both love and friendship blossom under this influence.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -5) {
                            matchResult.setText("1:6 Relationship.\nThis tends to be a \"Master-Servant\" type of relationship where YOU tend" +
                                    " to be the main beneficiary. YOU need to work harder than usual to avoid exploiting your Potential Partner or" +
                                    " this relationship may disintegrate.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -6) {
                            matchResult.setText("1:7 Relationship.\nThis is a relationship based on partnership, open opposition, or sometimes of mixture of both." +
                                    " You are very different individuals but here opposites attract each other. Maintaining this relationship can be hard work but" +
                                    " if successful, you may be an exceptionally effective \"Power Couple\". ");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -7) {
                            matchResult.setText("1:8 Relationship.\nAlthough on the surface others may see little in common between you, this relationship may" +
                                    " have an exceptionally powerful hidden dimension. There may be an exceptionally strong sexual attraction." +
                                    " You may share hidden secrets. There may be an aspect of death and rebirth involved.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -8) {
                            matchResult.setText("1:9 Relationship.\nThis relationship ma y impact your belief system, philosophy, or outlook on life." +
                                    " Travel to foreign countries may be involved that opens your mind and broaden your outlook." +
                                    " It may help you raise your consciousness to a higher level.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -9) {
                            matchResult.setText("1:10 Relationship.\nAlthough there may be frequent disagreement, this relationship may impact heavily your ambition," +
                                    " and your career goals. The conflict between you may act to push you to greater level of achievement and creativity," +
                                    " but may make this a very challenging relationship to maintain in the longer term.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -10) {
                            matchResult.setText("1:11 Relationship.\nThis is a relationship based primarily on friendship. In a very real sense you" +
                                    " may be exceptionally close friends and this may be confused with love in some cases. It may be very easy for you" +
                                    " to communicate with each other and share your hopes, dreams and wishes.");
                        }
                        else if (pos1 > pos2 && ((pos1 - pos2) - 12) == -11) {
                            matchResult.setText("1:12 Relationship.\nThis may be one of the more challenging relationship combination." +
                                    " This relationship may undermine you in some way either consciously or unconsciously. There may be an aspect" +
                                    " of restriction of confinement. You probably have little in common and there may be an aspect of compulsion" +
                                    " in that you may have been forced to stay together by external circumstances. There is a danger, however, that" +
                                    " once these circumstances are removed this relationship may collapse.");
                        }
                        else {
                            Toast.makeText(getContext(), "Error!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "loadMessage:onCancelled", databaseError.toException());
                    }
                });
            }
        });
        return view;
    }
}
