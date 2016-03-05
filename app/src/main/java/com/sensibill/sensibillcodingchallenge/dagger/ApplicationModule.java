package com.sensibill.sensibillcodingchallenge.dagger;

import com.sensibill.sensibillcodingchallenge.SensibillApplication;
import com.sensibill.sensibillcodingchallenge.service.SensibillAPI;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bhaskar on 2016-03-04
 */
@Module
public class ApplicationModule {

    private SensibillApplication mApplication;

    public ApplicationModule(SensibillApplication application) {
        mApplication = application;
    }

    @Provides
    public SensibillApplication provideApplication() {
        return mApplication;
    }

    @Provides
    public SensibillAPI provideApi() {
        return new SensibillAPI();
    }
}
