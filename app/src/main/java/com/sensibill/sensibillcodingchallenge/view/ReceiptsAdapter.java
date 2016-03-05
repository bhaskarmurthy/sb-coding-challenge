package com.sensibill.sensibillcodingchallenge.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sensibill.sensibillcodingchallenge.R;
import com.sensibill.sensibillcodingchallenge.model.Receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Receipt}
 */
public class ReceiptsAdapter extends RecyclerView.Adapter<ReceiptsAdapter.ViewHolder> {

    private final List<Receipt> mReceipts = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_receipt, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Receipt receipt = mReceipts.get(position);
        holder.name.setText(receipt.display.name);
        holder.price.setText(receipt.display.amount);
        holder.date.setText(receipt.display.date);
    }

    @Override
    public int getItemCount() {
        return mReceipts != null ? mReceipts.size() : 0;
    }

    public void clear() {
        mReceipts.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<Receipt> receipts) {
        int position = mReceipts.size();
        mReceipts.addAll(receipts);
        notifyItemRangeInserted(position, receipts.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView price;
        public TextView date;

        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            date = (TextView) view.findViewById(R.id.date);
        }
    }
}
