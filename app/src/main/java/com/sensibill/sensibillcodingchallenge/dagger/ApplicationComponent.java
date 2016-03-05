package com.sensibill.sensibillcodingchallenge.dagger;

import com.sensibill.sensibillcodingchallenge.SensibillApplication;
import com.sensibill.sensibillcodingchallenge.service.SensibillAPI;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bhaskar on 2016-03-04
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    SensibillApplication application();
    SensibillAPI api();
}
