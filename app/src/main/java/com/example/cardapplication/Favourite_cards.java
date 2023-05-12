package com.example.cardapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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
    RecyclerView cards;
    Fav_Adapter adapter;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_cards);
        cards = findViewById(R.id.cards);
        cards.setLayoutManager(new LinearLayoutManager(this));

//        List<String> names=new ArrayList<>();
//        List<String> codes=new ArrayList<>();
        String[]names;
        String[]codes;
        try {
        sharedPreferences = getApplicationContext().getSharedPreferences("My user", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getAll().keySet();
        names=new String[set.size()];
        codes=new String[set.size()];
        int i=0;
        for (String x : set)
            names[++i]=x;
        Collection<String> anotherset = (Collection<String>) sharedPreferences.getAll().values();
        for (String x : anotherset)
            codes[++i]=x;
            adapter = new Fav_Adapter(this, names);
            cards.setAdapter(adapter);
            //cards.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        } catch (Exception e){
//            List<String> empty = new ArrayList<>();
            String[] empty={"Здесь пусто"};
//            empty.add("Здесь пусто");
            adapter = new Fav_Adapter(this, empty);
            cards.setAdapter(adapter);
            //cards.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }
}