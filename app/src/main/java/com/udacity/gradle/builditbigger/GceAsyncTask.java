package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import au.com.fintechapps.gcebackend.myApi.MyApi;

/**
 * Created by Admin on 18-Mar-16.
 */
public class GceAsyncTask extends AsyncTask<Pair<Context,String>,Void,String>{
    private static MyApi myApi = null;
    private Context context;
    private GceTaskListener mListener = null;
    private Exception mError = null;

    @Override
    protected String doInBackground(Pair<Context,String>... params) {

        if(myApi==null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(),null)
                                //.setRootUrl("https://gcebeckendproject.appspot.com/_ah/api/");

                    .setRootUrl("http://10.0.0.3:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });


            myApi= builder.build();
        }
        context = params[0].first;
        String num = params[0].second;

        try{
            return myApi.sayHi(num).execute().getData();
        }catch (IOException e){
        return  e.getMessage();
        }

    }


    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        if(this.mListener!=null){
            this.mListener.onComplete(s,mError);
        }
    }

    @Override
    protected void onCancelled(){

    }

    public static interface GceTaskListener {

        public void onComplete(String joke, Exception eJoked);

  }
public GceAsyncTask setListener(GceTaskListener gceTaskListener){
    this.mListener = gceTaskListener;
    return this;
}

}
