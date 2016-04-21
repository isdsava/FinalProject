package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    CountDownLatch signal = null;
    String mJoke = null;

    public ApplicationTest() {
        super(Application.class);
    }

@Override
    protected void setUp() throws Exception {

    signal = new CountDownLatch(1);
}

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void  testMeTask() throws InterruptedException {

        GceAsyncTask gceAsyncTask = new GceAsyncTask();

         Pair<Context,String> mePair = new Pair<Context,String>(getContext(),"freddy");

        GceAsyncTask.GceTaskListener gceTaskListener = new GceAsyncTask.GceTaskListener() {
            @Override
            public void onComplete(String joke, Exception eJoked) {
                mJoke = joke;
                signal.countDown();
            }
        };

        gceAsyncTask.setListener(gceTaskListener);
        gceAsyncTask.execute(mePair);
        signal.await();

        assertTrue(mJoke.startsWith("fart"));
    }
}