package com.hobbsa.finalprojectmdp_alexhobbs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

// Alex Hobbs
// Final Project - Fall 2023
// Mobile Device Programming (Android)
/* this app allows a user to enter board games into a database
   to save a personal inventory (collection) and a wishlist.
   this activity displays the collection or wishlist (via fragment)
 */

public class MainActivity extends AppCompatActivity {

    private Button viewCollectionBtn;
    private Button viewWishlistBtn;

    private static GamesDbHelper dbHelper;
    private static SQLiteDatabase database;
    private static final ArrayList<Game> games = Game.games;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // buttons
        viewCollectionBtn = findViewById(R.id.buttonCollection);
        viewWishlistBtn = findViewById(R.id.buttonWishlist);

        // Database
        dbHelper = new GamesDbHelper(this);
        database = dbHelper.getReadableDatabase();

        // Nav/Drawer
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.toolbarAccent));
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //TODO check if pre-populated data already in table
        //populate sample data into table
        populateGameData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onButtonClicked(View view){
        //get id of clicked button
        Button button = (Button) findViewById(view.getId());
        int filter = (int) button.getId();

        //create fragment & manager objects
        DisplayFragment displayFragment = new DisplayFragment();
        displayFragment.setFilter(filter); // call method from fragment class
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, displayFragment, null) //replace layout w/content from this fragment
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();
    }

    // feature 4: allow user to navigate to other activity using Intent
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_add_update){
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static void populateGameData(){
        // wishlist purchase year autofills to 0 (does not apply)
        games.add(new Game("Azul", "Next Move Games", "collection", 2023));
        games.add(new Game("Community Garden", "TESA Collective", "wishlist"));
        games.add(new Game("Cosmic Encounter", "Fantasy Flight Games", "wishlist"));
        games.add(new Game("Dixit", "Libellud", "collection", 2017));
        games.add(new Game("Everdell", "Asmodee", "collection", 2020));
        games.add(new Game("Mysterium", "Libellud", "wishlist"));
        games.add(new Game("Pandemic", "Asmodee", "collection", 2021));
        games.add(new Game("Splendor", "Space Cowboys", "collection", 2016));
        games.add(new Game("Strike!", "TESA Collective", "collection", 2019));
        games.add(new Game("Ticket to Ride", "Asmodee", "wishlist"));

        for(Game game:games){
            dbHelper.insertData(database,game);
        }
    }

    public void onAddClicked(MenuItem menuItem){
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }
    public void onAboutClicked(MenuItem menuItem){
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

}