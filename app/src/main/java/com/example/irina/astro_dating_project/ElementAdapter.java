package com.example.irina.astro_dating_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.irina.astro_dating_project.classes.UserInformation;
import com.firebase.ui.FirebaseUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by Irina on 2017-03-28.
 */

public class ElementAdapter extends BaseAdapter {

    private ArrayList<UserInformation> elements;
    private Activity mActivity;

    public ElementAdapter(ArrayList<UserInformation> elements, Activity activity) {
        this.elements = elements;
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.elementviewcomponent, null);

        TextView element = (TextView) row.findViewById(R.id.element1);
        TextView name = (TextView) row.findViewById(R.id.fullName);
        TextView height = (TextView) row.findViewById(R.id.height1);
        TextView weight = (TextView) row.findViewById(R.id.weight1);
        TextView hobby = (TextView) row.findViewById(R.id.hobby1);
        TextView sign = (TextView) row.findViewById(R.id.sign1);
        TextView email = (TextView) row.findViewById(R.id.email1);

        element.setText(elements.get(i).getElement());
        name.setText("Partner Name: " + elements.get(i).getFirstName() + " " + elements.get(i).getLastName());
        height.setText("Height: " + elements.get(i).getHeight() + " cm");
        weight.setText("Weight: " + elements.get(i).getWeight() + " kg");
        hobby.setText("Hobby: " + elements.get(i).getHobby());
        sign.setText("Sign: " + elements.get(i).getSignName());
        email.setText("Email: " + elements.get(i).getEmail());

        return row;
    }
}
