package com.example.android.jokeproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    private String s = null;
    private Exception mException = null;
    private CountDownLatch mCountDownLatch = null;
    Context mContext = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void asyncTest(){
        mCountDownLatch = new CountDownLatch(1);
            EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask(){
                @Override
                public void onPostExecute(String s){
                    if(s == null){
                        throw new AssertionError("The GMS endpoint are null");
                    }else if(s.equals("")){
                        throw  new AssertionError("The GMS endpoint are empty");
                    }else if(s.contains("connect timed out")){
                        throw new AssertionError("No GMS backend response");
                    }else{
                        assertNotNull(s);
                        assertTrue("Test passed", true);
                        mCountDownLatch.countDown();
                    }
                }
            };
            endpointAsyncTask.execute(mContext);
        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
