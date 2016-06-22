package com.example.user_zf.scandroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user_zf.scandroid.R;

/**
 * Fragment生命周期Demo
 */
public class ReplaceFragment extends Fragment {

    public static ReplaceFragment newInstance() {
        ReplaceFragment fragment = new ReplaceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_replace, container, false);
    }

}
