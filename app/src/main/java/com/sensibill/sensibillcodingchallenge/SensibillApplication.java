package com.sensibill.sensibillcodingchallenge;

import android.app.Application;

import com.sensibill.sensibillcodingchallenge.dagger.ApplicationComponent;
import com.sensibill.sensibillcodingchallenge.dagger.ApplicationModule;
import com.sensibill.sensibillcodingchallenge.dagger.DaggerApplicationComponent;

/**
 * Created by bhaskar on 2016-03-04
 */
public class SensibillApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // build app component graph
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
