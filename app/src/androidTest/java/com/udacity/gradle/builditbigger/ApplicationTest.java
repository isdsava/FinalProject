package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.util.Pair;

import java.util.concurrent.CountDownLatch;

import au.com.fintechapps.gcebackend.jokeApi.model.JokeBean;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> implements GceAsyncTask.GceTaskListener<JokeBean>{

    CountDownLatch signal = null;
    JokeBean  mJoke = null;

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

        GceAsyncTask gceAsyncTask = new GceAsyncTask(getContext(),this);
        gceAsyncTask.execute(getContext());
        signal.await();

        // Get the  first joke, should start with horse
        String zeJoke = mJoke.getJokeArray().get(0).get(0);
         assertTrue(zeJoke.startsWith("Horse"));
        }

    @Override
    public void onComplete(JokeBean joke, Exception eJoked) {
        mJoke = joke;
        signal.countDown();
    }
}