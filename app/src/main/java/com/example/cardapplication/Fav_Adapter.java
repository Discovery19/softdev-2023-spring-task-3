package com.example.cardapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Fav_Adapter extends RecyclerView.Adapter<Fav_Adapter.ViewHolder> {
    String[] data;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public Fav_Adapter(Context context,String[]data){
        this.data=data;
        this.mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public Fav_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.table_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String string = data[position];
        holder.textView.setText(string);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageButton imageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.table_name);
            imageButton=itemView.findViewById(R.id.table_menu);
        }
    }
    String getItem(int id) {
        return data[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

//
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.PopupMenu;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Fav_Adapter extends RecyclerView.Adapter<Fav_Adapter.MyViewHolder> {
//    private LayoutInflater mInflater;
//    List<String> movieList;
//    //String[] movieList;
//    Fav_Adapter(Context context){
//        this.mInflater = LayoutInflater.from(context);
//    }
//
//    public void updateMovieList(List<String> movieList) {
//        this.movieList = movieList;
//    }
//
//    @NonNull
//    @Override
//    public Fav_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.table_row, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
////        String string = movieList.get(position);
////        //holder.textView.setText(string);
////        holder.bindView(string);
//        holder.bindView(movieList.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return movieList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
//
//        private static final String TAG = "MyViewHolder";
//        TextView textView;
//        ImageButton imageButton;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.textView);
//            imageButton = itemView.findViewById(R.id.table_menu);
//            imageButton.setOnClickListener(this);
//        }
//
//        void bindView(String movie) {
//            textView.setText(movie);
//        }
//
//        @Override
//        public void onClick(View v) {
//            showPopupMenu(v);
//        }
//
//        private void showPopupMenu(View view) {
//            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
//            popupMenu.inflate(R.menu.side_menu);
//            popupMenu.setOnMenuItemClickListener(this);
//            popupMenu.show();
//        }
//
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
//        }
//    }
//
//}