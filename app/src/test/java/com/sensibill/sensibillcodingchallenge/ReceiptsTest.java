package com.sensibill.sensibillcodingchallenge;

import android.util.Log;

import com.sensibill.sensibillcodingchallenge.model.Receipt;
import com.sensibill.sensibillcodingchallenge.presenter.ReceiptsPresenter;
import com.sensibill.sensibillcodingchallenge.service.SensibillAPI;
import com.sensibill.sensibillcodingchallenge.view.ReceiptsView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
        Log.class,
        Schedulers.class,
        AndroidSchedulers.class
})
public class ReceiptsTest {

    @Module
    class ReceiptsPresenterTestModule {
        @Provides
        public ReceiptsView provideView() {
            return mView;
        }

        @Provides
        public SensibillAPI provideApi() {
            return mApi;
        }
    }

    @Component(modules = ReceiptsPresenterTestModule.class)
    interface ReceiptsPresenterTestComponent {
        void inject(ReceiptsPresenter presenter);
    }

    @Mock
    SensibillAPI mApi;

    @Mock
    ReceiptsView mView;

    ReceiptsPresenter mPresenter;

    ReceiptsPresenterTestComponent mComponent;

    public ReceiptsTest() {
        // mock loggers
        mockStatic(Log.class);
        when(Log.d(anyString(), anyString())).thenReturn(0);
        when(Log.e(anyString(), anyString(), Matchers.<Throwable>anyObject())).thenReturn(0);

        // use immediate scheduler for tests so that calls are run immediately on same thread
        final Scheduler scheduler = Schedulers.immediate();

        mockStatic(Schedulers.class);
        when(Schedulers.io()).thenReturn(scheduler);

        mockStatic(AndroidSchedulers.class);
        when(AndroidSchedulers.mainThread()).thenReturn(scheduler);

        mComponent = DaggerReceiptsTest_ReceiptsPresenterTestComponent.builder()
                .receiptsPresenterTestModule(new ReceiptsPresenterTestModule())
                .build();
    }

    @Before
    public void setUp() {
        initMocks(this);
        mPresenter = new ReceiptsPresenter();
        mComponent.inject(mPresenter);
    }

    @After
    public void tearDown() {
        mPresenter = null;
        mView = null;
    }

    @Test
    public void getReceipts_error() throws Exception {
        when(mApi.getReceipts()).thenReturn(Observable.<List<Receipt>>error(new Exception("Error")));
        mPresenter.getReceipts();

        verify(mView).showError("Error");
        verifyNoMoreInteractions(mView);
    }

    @Test
    public void getReceipts_success() throws Exception {
        List<Receipt> list = new ArrayList<>();

        when(mApi.getReceipts()).thenReturn(Observable.just(list));
        mPresenter.getReceipts();

        verify(mView).showReceipts(list);
        verifyNoMoreInteractions(mView);
    }
}