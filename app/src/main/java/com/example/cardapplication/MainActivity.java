package com.example.cardapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cardapplication.R.id;

public class MainActivity extends AppCompatActivity implements Adapter.ItemClickListener {
    FrameLayout frameLayout;
    CardFragment cardFragment;
    RecyclerView recyclerView;
    Adapter adapter;
    String[] catNames ={
            "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
            "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
            "Китти", "Масяня", "Симба"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();


//        recyclerView = findViewById(id.recyclerView);
//        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new Adapter(this, catNames);
//        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView = findViewById(id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, catNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                setFragment(cardFragment);
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
        //for list view
        //RecyclerView recyclerView=findViewById(id.recyclerView);
//        ListView listView = findViewById(R.id.listView);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, catNames);
//        Adapter adapter1=new Adapter() {
//            @NonNull
//            @Override
//            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//                    @Override
//                    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                        return false;
//                    }
//
//                    @Override
//                    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//                    }
//
//                    @Override
//                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//                    }
//                });
//                return null;
//            }
//
//            @Override
//            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 0;
//            }
//        };
//        listView.setAdapter(adapter);
        frameLayout = findViewById(R.id.framelayout);
        cardFragment = new CardFragment();
        //listView.setOnItemClickListener((adapterView, view, i, l) -> setFragment(cardFragment));
    }

    public void startFavourite(View view) {
        Intent intent = new Intent(this, favourite_cards.class);
        startActivity(intent);
    }

    public void startScanner(View view) {
        Intent intent = new Intent(this, scanner.class);
        startActivity(intent);
    }

    public void startMenu(View view) {
        Intent intent = new Intent(this, menu.class);
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
//    public void closeFragment(Fragment fragment){
//        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.remove(fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
}