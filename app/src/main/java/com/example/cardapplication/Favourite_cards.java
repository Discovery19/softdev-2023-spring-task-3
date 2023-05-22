package com.example.cardapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Favourite_cards extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    SharedPreferences sharedPreferences;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_cards);
        recyclerView = findViewById(R.id.cards);
        frameLayout = findViewById(R.id.barcode_frame);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);
        initMovies();

    }

    void initMovies() {
        List<String> names = new ArrayList<>();
        List<String> codes = new ArrayList<>();
        sharedPreferences = getApplicationContext().getSharedPreferences("My user", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getAll().keySet();
        for (String x : set) {
            names.add(x);
        }
        Collection<String> set2= (Collection<String>) sharedPreferences.getAll().values();
        for (String x:set2) codes.add(x);
        recyclerAdapter.updateMovieList(names);
        recyclerAdapter.setSharedPreferences(sharedPreferences,codes);
    }

    void delete(String string) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("My user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(string);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }
}