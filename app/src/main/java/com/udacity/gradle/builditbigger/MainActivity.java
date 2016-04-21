package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import au.com.fintechapps.androidjoke.JokeActivity;
import au.com.fintechapps.javajoshingredux.Joshing;





public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            MainActivityFragment fraggie = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fraggie).commit();


        new GceAsyncTask().execute(new Pair<Context, String>(this,"freddy"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){

        Joshing derp = new Joshing();

        Toast.makeText(this, derp.joshOn(), Toast.LENGTH_SHORT).show();
    }

public void droiding(View view){

    Intent felch = new Intent(this, JokeActivity.class);
    startActivity(felch);
}
}
