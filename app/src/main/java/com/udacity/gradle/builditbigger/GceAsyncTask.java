package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import au.com.fintechapps.gcebackend.jokeApi.JokeApi;
import au.com.fintechapps.gcebackend.jokeApi.model.JokeBean;

/**
 * Created by Admin on 18-Mar-16.
 */
public class GceAsyncTask extends AsyncTask<Context, Void, JokeBean> {
    private static JokeApi jokeApi = null;
    private Context context;
    private GceTaskListener<JokeBean> mListener = null;
    private Exception mError = null;


    public GceAsyncTask(Context context, GceTaskListener gceTaskListener) {
        this.context = context;
        this.mListener = gceTaskListener;
    }

    @Override
    protected JokeBean doInBackground(Context... params) {

        if (jokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    //.setRootUrl("https://gcebeckendproject.appspot.com/_ah/api/");
                     //TODO change to 10.0.2.2 for emulator
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            jokeApi = builder.build();
        }


        context = params[0];
        try {
            Log.v("buildit", "about to hit it");
            return jokeApi.funnyJoke().execute();
        } catch (IOException e) {
            return null;
        }

    }


    @Override
    protected void onPostExecute(JokeBean jokeBean) {
        Log.v("buildit", "hit port execture");
        if (this.mListener != null) {
            this.mListener.onComplete(jokeBean, mError);
        } else {
            Log.v("buildit", "its null");
            this.mListener.onComplete(jokeBean, mError);
        }


    }

    @Override
    protected void onCancelled() {

    }

    public interface GceTaskListener<JokeBean> {

        public void onComplete(JokeBean joke, Exception eJoked)
                ;

    }

    public GceAsyncTask setListener(GceTaskListener gceTaskListener) {
        this.mListener = gceTaskListener;
        return this;
    }

}
