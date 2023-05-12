package com.example.cardapplication;

//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.PopupMenu;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class Fav_Adapter extends RecyclerView.Adapter<Fav_Adapter.ViewHolder> {
//    String[] data;
//    private LayoutInflater mInflater;
//    private ViewHolder.ItemClickListener mClickListener;
//
//    public Fav_Adapter(Context context,String[]data){
//        this.data=data;
//        this.mInflater = LayoutInflater.from(context);
//    }
//    @NonNull
//    @Override
//    public Fav_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=mInflater.inflate(R.layout.table_row,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        String string = data[position];
//        holder.textView.setText(string);
//    }
//
//    @Override
//    public int getItemCount() {
//        return data.length;
//    }
//
//    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        TextView textView;
//        ImageButton button;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            textView=itemView.findViewById(R.id.table_name);
//            button=itemView.findViewById(R.id.table_menu);
//            itemView.setOnClickListener(this);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
//                    popupMenu.inflate(R.menu.side_menu);
//                    popupMenu.setOnMenuItemClickListener(this);
//                    popupMenu.show();
//                }
//            });
//
//        }
//        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.action_rename:
//                    Log.d(TAG, "onMenuItemClick: action_popup_edit @ " + getAdapterPosition());
//                    return true;
//                case R.id.action_delete:
//                    Log.d(TAG, "onMenuItemClick: action_popup_delete @ " + getAdapterPosition());
//                    return true;
//                default:
//                    return false;
//            }
//    }
//    String getItem(int id) {
//        return data[id];
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
//
//        // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
//}}


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fav_Adapter extends RecyclerView.Adapter<Fav_Adapter.MyViewHolder> {
    private LayoutInflater mInflater;

    //List<String> movieList;
    String[] movieList;
    Fav_Adapter(Context context, String[] data){
        this.movieList=data;
        this.mInflater = LayoutInflater.from(context);
    }

//    public void updateMovieList(List<String> movieList) {
//        this.movieList = movieList;
//    }

    @NonNull
    @Override
    public Fav_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.table_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String string = movieList[position];
        //holder.textView.setText(string);
        holder.bindView(string);
    }

    @Override
    public int getItemCount() {
        return movieList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

        private static final String TAG = "MyViewHolder";
        TextView textView;
        ImageButton imageButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.table_name);
            imageButton = itemView.findViewById(R.id.table_menu);
            imageButton.setOnClickListener(this);
        }

        void bindView(String movie) {
            textView.setText(movie);
        }

        @Override
        public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
            popupMenu.inflate(R.menu.side_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }


        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_rename:
                    Log.d(TAG, "onMenuItemClick: action_popup_edit @ " + getAdapterPosition());
                    return true;
                case R.id.action_delete:
                    Log.d(TAG, "onMenuItemClick: action_popup_delete @ " + getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

}