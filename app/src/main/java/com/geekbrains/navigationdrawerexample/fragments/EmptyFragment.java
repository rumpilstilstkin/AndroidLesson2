package com.geekbrains.navigationdrawerexample.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.geekbrains.navigationdrawerexample.R;


public class EmptyFragment extends Fragment {

    static String ARGS_COLOR = "COLOR_ARGS";


    public static EmptyFragment getInstats(int color){
        EmptyFragment fragment = new EmptyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_COLOR, color);
        fragment.setArguments(bundle);
        return fragment;
    }

    LinearLayout rootContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_empty, container, false);
        rootContainer = (LinearLayout)root.findViewById(R.id.fe_container_root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootContainer.setBackgroundColor(getArguments().getInt(ARGS_COLOR, Color.YELLOW));

    }
}
