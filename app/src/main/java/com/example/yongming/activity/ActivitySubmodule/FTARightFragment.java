package com.example.yongming.activity.ActivitySubmodule;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yongming.activity.R;

public class FTARightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_right, container, false);
        Toast.makeText(getActivity(), "FTA-onCreateView", Toast.LENGTH_SHORT).show();
        return view;
    }

    public void testFunction()
    {
        Toast.makeText(getActivity(), "This is from fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Toast.makeText(getActivity(), "FTA-onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toast.makeText(getActivity(), "FTA-onActivityCreated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Toast.makeText(getActivity(), "FTA-onDestroyView", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Toast.makeText(getActivity(), "FTA-onDetach", Toast.LENGTH_SHORT).show();
    }


}
