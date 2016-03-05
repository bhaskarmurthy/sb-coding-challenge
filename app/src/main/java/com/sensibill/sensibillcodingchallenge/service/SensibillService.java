package com.sensibill.sensibillcodingchallenge.service;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by bhaskar on 2016-03-04
 */
interface SensibillService {
    @GET("tests/receipts")
    Observable<ReceiptsResponse> getReceipts();
}
