package com.example.cardapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    //    RecyclerView cards;
//    Fav_Adapter adapter;
//    SharedPreferences sharedPreferences;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favourite_cards);
//        cards = findViewById(R.id.cards);
//        cards.setLayoutManager(new LinearLayoutManager(this));
////        List<String> names=new ArrayList<>();
////        List<String> codes=new ArrayList<>();
//        String[]names;
//        String[]codes;
//        try {
//            System.out.println("suka work bu cho");
//        sharedPreferences = getApplicationContext().getSharedPreferences("My user", Context.MODE_PRIVATE);
//        Set<String> set = sharedPreferences.getAll().keySet();
//        names=new String[set.size()];
//        codes=new String[set.size()];
//        int i=0;
//        for (String x : set)
// //           names.add(x);
//            names[i++]=x;
//        Collection<String> anotherset = (Collection<String>) sharedPreferences.getAll().values();
//        i=0;
//        for (String x : anotherset)
//            //codes.add(x);
//            codes[i++]=x;
//            System.out.println(Arrays.toString(names));
//            System.out.println(Arrays.toString(codes));
//        adapter = new Fav_Adapter(this, names);
//        cards.setAdapter(adapter);
//            //cards.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//            //adapter.updateMovieList(codes);
//
//        } catch (Exception e){
//            System.out.println("now error");
//            e.printStackTrace();
//            //List<String> empty = new ArrayList<>();
//            String[] empty={"Здесь пусто"};
////            empty.add("Здесь пусто");
//            adapter = new Fav_Adapter(this, empty);
//            cards.setAdapter(adapter);
//            //cards.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//            //adapter.updateMovieList(empty);
//        }
//    }
//}
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_cards);
        recyclerView = findViewById(R.id.cards);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);
        initMovies();

    }

    void initMovies() {
        List<String> names = new ArrayList<>();
        sharedPreferences = getApplicationContext().getSharedPreferences("My user", Context.MODE_PRIVATE);
        Set<String> set = sharedPreferences.getAll().keySet();
        int i = 0;
        for (String x : set) {
            names.add(x);
            System.out.println(names.get(i));
            i++;
        }
        recyclerAdapter.updateMovieList(names);
        recyclerAdapter.setSharedPreferences(sharedPreferences);
    }

    void delete(String string) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("My user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(string);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }
}