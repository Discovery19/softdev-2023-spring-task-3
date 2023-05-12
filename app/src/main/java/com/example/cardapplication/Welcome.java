package com.example.cardapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        firebaseAuth=FirebaseAuth.getInstance();
        Handler handler=new Handler();
        handler.postDelayed(this::checkAuth,2000);
    }
    void checkAuth(){
        Intent intent;
        System.out.println("идет проверка");
        if (firebaseAuth.getCurrentUser()==null){

            intent= new Intent(this, Login.class);
        }
        else {
            System.out.println(firebaseAuth.getCurrentUser().getEmail());
            intent= new Intent(this, MainActivity.class);
        }
        startActivity(intent);
    }
}