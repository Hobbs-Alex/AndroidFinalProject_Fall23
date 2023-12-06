package com.hobbsa.finalprojectmdp_alexhobbs;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class cursorAdapter extends CursorAdapter {

    public cursorAdapter(Context context, Cursor cursor) {
        super(context, cursor,0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //inflate & return new view (using single row layout file)
        return LayoutInflater.from(context).inflate(R.layout.single_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // bind all data to view

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView publisherTextView = (TextView) view.findViewById(R.id.publisher);
        //use cursor to get values from table
        String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
        String publisher = cursor.getString(cursor.getColumnIndexOrThrow("PUBLISHER"));
        // apply values to widgets
        nameTextView.setText(name);
        publisherTextView.setText(publisher);
    }
}
