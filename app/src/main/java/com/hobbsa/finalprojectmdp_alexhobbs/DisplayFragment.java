package com.hobbsa.finalprojectmdp_alexhobbs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DisplayFragment extends Fragment {
/* this class populates the MainActivity with the table
   that matches the selected topic (collection or wishlist) */

    private SQLiteDatabase database;
    private static final String TABLE_NAME = "games";
    private int filter;
    private Cursor cursor;
    public void setFilter(int filter){
        this.filter = filter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();

        //get activity's context
        View view = getView();
        TextView textView = (TextView) view.findViewById(R.id.textView_fragLabel);
        ListView listView = (ListView) view.findViewById(R.id.listView_frag);

        if(view != null){

            String sql;

            // cursor setup
            GamesDbHelper dbHelper = new GamesDbHelper(getContext());
            database = dbHelper.getWritableDatabase();

            // if Collection button clicked
            if(filter==(R.id.buttonCollection)){
                textView.setText("Your Collection");
                Cursor cursor = database.rawQuery("SELECT * FROM GAMES WHERE CATEGORY = 'collection'", null);
                cursorAdapter cursorAdapt = new cursorAdapter(getContext(), cursor);
                listView.setAdapter(cursorAdapt);

                cursorAdapt.changeCursor(cursor);
            }
            else if(filter==R.id.buttonWishlist){ //if wishlist button clicked
                textView.setText("Your Wishlist");
                Cursor cursor = database.rawQuery("SELECT * FROM GAMES WHERE CATEGORY = 'wishlist'", null);
                cursorAdapter cursorAdapt = new cursorAdapter(getContext(), cursor);
                listView.setAdapter(cursorAdapt);

                cursorAdapt.changeCursor(cursor);
            }
            else { // hide fragment views until button clicked
                textView.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.INVISIBLE);
            }
        }
    }
}