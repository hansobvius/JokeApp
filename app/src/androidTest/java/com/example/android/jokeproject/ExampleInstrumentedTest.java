package com.example.android.jokeproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext(){
        Context context = InstrumentationRegistry.getTargetContext();
        assertEquals(context.getString(R.string.package_name), context.getPackageName());
    }
}
