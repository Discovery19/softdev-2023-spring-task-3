package com.example.cardapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CardSave extends Fragment implements View.OnClickListener {
    String string;
    CardSave(String string){
        this.string=string;
    }
    Button save;
    EditText cardCode,cardName;
    SharedPreferences sharedPreferences;
    String nameString,codeString;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_save, container, false);
        cardCode = v.findViewById(R.id.card_numb);
        cardName = v.findViewById(R.id.card_name);
        save = v.findViewById(R.id.saveButton);
        cardCode.setText(string);
        sharedPreferences= getActivity().getSharedPreferences("My user",Context.MODE_PRIVATE);
        save.setOnClickListener(this::onClick);
        return v;
    }

    @Override
    public void onClick(View view) {
        nameString=cardName.getText().toString();
        codeString=cardCode.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nameString, codeString);
        editor.commit();
        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

    }
}