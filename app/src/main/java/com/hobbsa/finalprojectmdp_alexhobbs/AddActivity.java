package com.hobbsa.finalprojectmdp_alexhobbs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddActivity extends AppCompatActivity {

    private GamesDbHelper dbHelper;
    private SQLiteDatabase database;
    private EditText txt_name;
    private EditText txt_publisher;
    private EditText txt_date;
    private TextView label_date;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button addButton;
    private Button clearButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);

        txt_name = (EditText) findViewById(R.id.textbox_name);
        txt_publisher = (EditText) findViewById(R.id.textbox_publisher);
        txt_date = (EditText) findViewById(R.id.textbox_date);
        label_date = (TextView) findViewById(R.id.label_date);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        txt_publisher.setText("");

        // Database
        dbHelper = new GamesDbHelper(this);
        database = dbHelper.getWritableDatabase();

        //hide date fields (unless collection radio button clicked)
        txt_date.setVisibility(View.INVISIBLE);
        label_date.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //clear button click event
    public void onClear(View view) {
        txt_name.setText("");
        txt_publisher.setText("");
        txt_date.setText("");
    }

    //add button click event
    public void onAdd(View view) {
        String category = "";
        String name, pub;
        try {
            name = (txt_name.getText()).toString();
            pub = (txt_publisher.getText()).toString();
            int radioChecked = radioGroup.getCheckedRadioButtonId();
            if (radioChecked == R.id.radioButton_category_collection) {
                category = "collection";
                int date = Integer.parseInt((txt_date.getText()).toString());
                Game game = new Game(name, pub, category, date);
                dbHelper.insertData(database, game);
            } else {
                category = "wishlist";
                Game game = new Game(name, pub, category);
                dbHelper.insertData(database, game);
            }
            Toast.makeText(AddActivity.this, "Game add successful!", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(AddActivity.this, "Error, please try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCategory(View view) {
        //show date fields if collection radio checked
        txt_date.setVisibility(View.VISIBLE);
        label_date.setVisibility(View.VISIBLE);
    }
    public void onWishlist(View view) {
        //hide date fields if collection radio checked
        txt_date.setVisibility(View.INVISIBLE);
        label_date.setVisibility(View.INVISIBLE);
    }

   public void onHome(MenuItem menuItem){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
   }

}