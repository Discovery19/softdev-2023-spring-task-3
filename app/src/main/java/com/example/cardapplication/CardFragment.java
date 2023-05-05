package com.example.cardapplication;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class CardFragment extends Fragment implements View.OnClickListener{
    Button close;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_card, container, false);
        close=v.findViewById(R.id.close);
        close.setOnClickListener(this::onClick);
        return v;
    }

    @Override
    public void onClick(View view) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
