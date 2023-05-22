package com.example.cardapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.cardapplication.R.id;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity implements Adapter.ItemClickListener {
    FrameLayout frameLayout;
    CardFragment cardFragment;
    RecyclerView recyclerView;
    MainAdapter adapter;
    List<Object> list;
    FirebaseFirestore db;

    String[] catNames ={
            "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
            "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
            "Китти", "Масяня", "Симба"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(id.recyclerView);
        db=FirebaseFirestore.getInstance();
        setCollection();
        eventChangeListener();
    }
    public void  eventChangeListener(){
        DocumentReference docRef = db.collection("users").document("users");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String str = document.getData().values().toString();
                        str= str.replaceAll("[\\[\\] ]", "");
                        List<String> dataList = List.of(str.split(","));
                        for (int i=0;i<dataList.size();i++) System.out.println(dataList.get(i));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new MainAdapter(getApplicationContext(),dataList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }
    public void setCollection(){
        Map <String,List<String>> users=new HashMap<>();
        List<String> mapList=new ArrayList<>();
        mapList.add("someemail@gmail.com");
        mapList.add("someemail2@gmail.com");
        mapList.add("someemail3@gmail.com");
        mapList.add("someemail4@gmail.com");
        users.put("users list",mapList);
        db.collection("users").document("users").set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "ура победа", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void startFavourite(View view) {
        Intent intent = new Intent(this, Favourite_cards.class);
        startActivity(intent);
    }
    public void startScanner(View view) {
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);
    }

    public void startMenu(View view) {
        Intent intent = new Intent(this, LogOut.class);
        startActivity(intent);
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(View view, int position) {
        setFragment(cardFragment);
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}