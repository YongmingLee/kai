package com.example.yongming.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yongming.activity.R;

public class FTAOtherFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_test_other, container, false);
    }

    public void testFunction()
    {
        Toast.makeText(getActivity(), "This is fragment", Toast.LENGTH_SHORT).show();
    }
}
