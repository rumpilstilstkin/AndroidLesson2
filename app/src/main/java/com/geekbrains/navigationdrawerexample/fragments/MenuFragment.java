package com.geekbrains.navigationdrawerexample.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geekbrains.navigationdrawerexample.FragmentNavigationActivity;
import com.geekbrains.navigationdrawerexample.R;


public class MenuFragment extends Fragment implements View.OnClickListener {

    TextView item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_menu, container, false);
        item = (TextView)root.findViewById(R.id.fm_tv_item);
        item.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fm_tv_item: {
                ((FragmentNavigationActivity)getActivity()).setNewScreen(EmptyFragment.getInstats(Color.MAGENTA));
                break;
            }
        }
    }
}
