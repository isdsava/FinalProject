package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.InterstitialAd;

import au.com.fintechapps.androidjoke.JokeActivity;
import au.com.fintechapps.gcebackend.jokeApi.model.JokeBean;
import au.com.fintechapps.javajoshingredux.Joshing;






public class MainActivity extends ActionBarActivity implements GceAsyncTask.GceTaskListener<JokeBean>,MainActivityFragment.OnInterResult,MainActivityFragment.OnJokeChosenListener{

    private JokeBean jokeBean =null;
    private Intent primeIntent;
    private boolean statialFacial=false;
    private InterstitialAd mInterstatialAd;
    private int mJokeNo;
    private ProgressBar mProg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            mProg = (ProgressBar) findViewById(R.id.progressBar);
            mProg.setVisibility(View.VISIBLE);
            MainActivityFragment fraggie = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fraggie).commit();

        GceAsyncTask gceAsyncTask = new GceAsyncTask(getApplicationContext(),this);


            gceAsyncTask.execute(getApplicationContext());




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


    @Override
    public void onComplete(JokeBean joke, Exception eJoked) {

        jokeBean = joke;
        primeIntent = new Intent(this, JokeActivity.class);
        mProg.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onResult(boolean result,InterstitialAd iA) {

        statialFacial = result;
                if (result){
                    mInterstatialAd = iA;
                }
    }


    @Override
    public void onJokeChosen(int jokeNo) {
        mJokeNo = jokeNo;

        if (jokeBean!=null) {

            primeIntent.putExtra("jokeReq",jokeBean.getJokeArray().get(mJokeNo).get(0));
            primeIntent.putExtra("punchline",jokeBean.getJokeArray().get(mJokeNo).get(1));
            startActivity(primeIntent);

            if  (BuildConfig.VERSION.equals("FREE") && statialFacial) {
                mInterstatialAd.show();

            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Please make sure you are connected AND have started the GCE server",Toast.LENGTH_LONG).show();
        }

    }
}
