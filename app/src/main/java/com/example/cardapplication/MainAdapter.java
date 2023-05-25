package com.example.cardapplication;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    Context context;

    List<String> list;


    public MainAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.table_row,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //User user = list.get(position);

        holder.email.setText( list.get(position));
        //holder.lastName.setText(user.getCard_code());
        //holder.age.setText(user.getAge());

        //доделать
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatActivity activity= (AppCompatActivity) view.getContext();
//                BarcodeFragment barcodeFragment=new BarcodeFragment(codes.get(pos));
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.barcode_frame,barcodeFragment)
//                        .addToBackStack(null).commit();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    String getItem(int id) {
        return (String) list.get(id);
    }

    public void setClickListener(MainActivity mainActivity) {
        Log.d(TAG, "here fragment ");
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{

        TextView email;
        ImageButton imageButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.table_name);
            imageButton = itemView.findViewById(R.id.table_menu);
            imageButton.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            showPopupMenu(v);
        }

        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.side_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_rename:
                    Log.d(TAG, "onMenuItemClick: action_popup_rename @ " + getAdapterPosition());
                    //доделать
                    return true;
                case R.id.action_delete:
                    Log.d(TAG, "onMenuItemClick: action_popup_delete @ " + getAdapterPosition());
//                    for (int i=0;i<.size();i++){
//                        System.out.println(movieList.get(i));
//                    }
//                    sharedPreferences.edit().remove(movieList.get(getAdapterPosition())).commit();
//                    updateMovieList(movieList);
//                    new Favourite_cards();
                    return true;
                default:
                    return false;
            }
        }
    }

}