package com.sensibill.sensibillcodingchallenge.dagger;

import android.content.Context;

import com.sensibill.sensibillcodingchallenge.presenter.ReceiptsPresenter;
import com.sensibill.sensibillcodingchallenge.view.ReceiptsFragment;
import com.sensibill.sensibillcodingchallenge.view.ReceiptsView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bhaskar on 2016-03-05
 */
@Module
public class ReceiptsModule {
    private ReceiptsFragment mReceiptsFragment;

    public ReceiptsModule(ReceiptsFragment fragment) {
        mReceiptsFragment = fragment;
    }

    @Provides
    public Context provideContext() {
        return mReceiptsFragment.getActivity();
    }

    @Provides
    public ReceiptsView provideView() {
        return mReceiptsFragment;
    }

    @Provides
    public ReceiptsPresenter providePresenter() {
        return new ReceiptsPresenter();
    }
}
