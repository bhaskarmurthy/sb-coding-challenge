package com.sensibill.sensibillcodingchallenge.view;

import com.sensibill.sensibillcodingchallenge.model.Receipt;

import java.util.List;

/**
 * Created by bhaskar on 2016-03-04
 */
public interface ReceiptsView {
    void showReceipts(List<Receipt> receipts);
    void showError(String error);
}
