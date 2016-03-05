package com.sensibill.sensibillcodingchallenge.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sensibill.sensibillcodingchallenge.R;
import com.sensibill.sensibillcodingchallenge.SensibillApplication;
import com.sensibill.sensibillcodingchallenge.dagger.DaggerReceiptsComponent;
import com.sensibill.sensibillcodingchallenge.dagger.ReceiptsComponent;
import com.sensibill.sensibillcodingchallenge.dagger.ReceiptsModule;
import com.sensibill.sensibillcodingchallenge.model.Receipt;
import com.sensibill.sensibillcodingchallenge.presenter.ReceiptsPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Receipts.
 */
public class ReceiptsFragment extends Fragment implements ReceiptsView {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private ReceiptsAdapter mAdapter = new ReceiptsAdapter();

    @Inject
    Context mContext;

    @Inject
    ReceiptsPresenter mPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReceiptsFragment() {
    }

    /**
     * Get an instance of receipts fragment
     * @param columnCount Number of columns
     * @return Instance of {@link ReceiptsFragment}
     */
    public static ReceiptsFragment newInstance(int columnCount) {
        ReceiptsFragment fragment = new ReceiptsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        ReceiptsComponent component = DaggerReceiptsComponent.builder()
                .applicationComponent(((SensibillApplication) getActivity().getApplication()).getComponent())
                .receiptsModule(new ReceiptsModule(this))
                .build();

        component.inject(this);
        component.inject(mPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receipt_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.addItemDecoration(new DividerItemDecoration(mContext, null));
            recyclerView.setAdapter(mAdapter);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getReceipts();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showReceipts(List<Receipt> receipts) {
        mAdapter.clear();
        mAdapter.addItems(receipts);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
