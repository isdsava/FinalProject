package au.com.fintechapps.androidjoke;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JokeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid);

        String jokeReq = getIntent().getStringExtra("jokeReq");
        String jokePunch = getIntent().getStringExtra("punchline");

        Button button = (Button) findViewById(R.id.jokeReq);
                button.setText(jokeReq);

        TextView textView2 = (TextView) findViewById(R.id.punchline);
                textView2.setText(jokePunch);
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

    public void showPunch(View view){


        TextView textView = (TextView) view.getRootView().findViewById(R.id.punchline);
                textView.setVisibility(View.VISIBLE);
    }
}
