package com.example.cardapplication;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        addCard();

    }
    private void addCard(){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String email= String.valueOf(auth.getCurrentUser().getEmail());
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        Map<String,String> map=new HashMap<>();
        map.put(nameString,codeString);
        db.collection(email).document("cards").set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getActivity(), "ура победа должны появиться карты", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}