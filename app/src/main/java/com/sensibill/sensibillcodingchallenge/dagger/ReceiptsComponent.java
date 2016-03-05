package com.sensibill.sensibillcodingchallenge.dagger;

import com.sensibill.sensibillcodingchallenge.presenter.ReceiptsPresenter;
import com.sensibill.sensibillcodingchallenge.view.ReceiptsFragment;

import dagger.Component;

/**
 * Created by bhaskar on 2016-03-05
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ReceiptsModule.class
)
public interface ReceiptsComponent {
    void inject (ReceiptsFragment fragment);
    void inject (ReceiptsPresenter presenter);
}
