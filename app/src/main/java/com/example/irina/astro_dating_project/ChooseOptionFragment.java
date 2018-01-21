package com.example.irina.astro_dating_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

public class ChooseOptionFragment extends Fragment {

    private RadioButton option1;
    private RadioButton option2;
    private Button submitBtn;

    @Override
    public void onResume() {
        super.onResume();
        ((FindPartnerActivity) getActivity()).setCurrentFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_option, container, false);

        option1 = (RadioButton) view.findViewById(R.id.radio_opt1);
        option2 = (RadioButton) view.findViewById(R.id.radio_opt2);
        submitBtn = (Button) view.findViewById(R.id.option_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (option1.isChecked()) {
                    Fragment fragment = new FirstOptionFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
                } else if (option2.isChecked()) {
                    Fragment fragment = new SecondOptionFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
                }
            }
        });
        return view;
    }
}