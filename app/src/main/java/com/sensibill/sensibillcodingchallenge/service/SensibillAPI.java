package com.sensibill.sensibillcodingchallenge.service;

import com.sensibill.sensibillcodingchallenge.BuildConfig;
import com.sensibill.sensibillcodingchallenge.model.Receipt;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by bhaskar on 2016-03-04
 */
public class SensibillAPI {

    private final SensibillService mService;

    public SensibillAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mService = retrofit.create(SensibillService.class);
    }

    /**
     * Get list of receipts
     * @return {@link Observable} list of receipts
     */
    public Observable<List<Receipt>> getReceipts() {
        return mService.getReceipts()
                .map(new Func1<ReceiptsResponse, List<Receipt>>() {
                    @Override
                    public List<Receipt> call(ReceiptsResponse response) {
                        return response.receipts;
                    }
                });
    }
}
