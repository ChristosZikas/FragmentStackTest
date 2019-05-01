package com.example.testbundlefragmenstack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.testbundlefragmenstack.BusCmdsKt.getStack;
import static com.example.testbundlefragmenstack.BusCmdsKt.goBack;
import static com.example.testbundlefragmenstack.BusCmdsKt.newFragment;

public class FooFragment extends Fragment {

    @BindView(R.id.tv)
    TextView textView;

    @OnClick(R.id.next)
    protected void onNext() {
        getStack().push(this);
        newFragment();
    }

    @OnClick(R.id.back)
    protected void onBack() {
        if (getStack().size() > 0)
            goBack();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        assert getFragmentManager() != null;
        outState.putDouble("Value", x);
    }

    double x;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null)
            x = (savedInstanceState.getDouble("Value", 0));

        if (x == 0)
            x = Math.random();

        textView.setText(Double.toString(x));

        return view;
    }
}
