package com.sensibill.sensibillcodingchallenge.presenter;

import android.os.Bundle;
import android.util.Log;

import com.sensibill.sensibillcodingchallenge.model.Receipt;
import com.sensibill.sensibillcodingchallenge.service.SensibillAPI;
import com.sensibill.sensibillcodingchallenge.view.ReceiptsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bhaskar on 2016-03-04
 */
public class ReceiptsPresenter {

    private static final String TAG = "ReceiptsPresenter";
    private static final String EXTRA_RECEIPTS = "receipts";

    @Inject
    SensibillAPI mApi;

    @Inject
    ReceiptsView mView;

    private List<Receipt> mReceipts = new ArrayList<>();

    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.putParcelableArrayList(EXTRA_RECEIPTS, new ArrayList<>(mReceipts));
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mReceipts = savedInstanceState.getParcelableArrayList(EXTRA_RECEIPTS);
        }
    }

    /**
     * Get list of receipts from api, sort them alphabetically, and display list
     */
    public void getReceipts() {
        mApi.getReceipts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Receipt>>() {
                    @Override
                    public void call(List<Receipt> receipts) {
                        // sort by display name
                        Collections.sort(receipts, new Comparator<Receipt>() {
                            @Override
                            public int compare(Receipt lhs, Receipt rhs) {
                                return lhs.display.name.compareToIgnoreCase(rhs.display.name);
                            }
                        });

                        mReceipts = receipts;
                        mView.showReceipts(receipts);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, throwable.getMessage(), throwable);
                        mView.showError(throwable.getMessage()); // show raw error
                    }
                });
    }
}
