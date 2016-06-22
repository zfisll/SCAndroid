package com.example.user_zf.scandroid.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user_zf.scandroid.R;

/**
 * Fragment生命周期Demo
 */
public class LifecycleFragment extends Fragment {

    /**
     * 唯一获取Fragment实例处
     * @return
     */
    public static LifecycleFragment newInstance() {
        Log.e("zf_tag", "---- Fragment : new Instance ----");
        LifecycleFragment fragment = new LifecycleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("zf_tag", "---- Fragment : onCreate ----");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("zf_tag", "---- Fragment : onCreateView ----");
        return inflater.inflate(R.layout.fragment_lifecycle, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("zf_tag", "---- Fragment : onAttach ----");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("zf_tag", "---- Fragment : onActivityCreated ----");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("zf_tag", "---- Fragment : onStart ----");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("zf_tag", "---- Fragment : onResume ----");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("zf_tag", "---- Fragment : onPause ----");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("zf_tag", "---- Fragment : onStop ----");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("zf_tag", "---- Fragment : onDestroyView ----");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zf_tag", "---- Fragment : onDestroy ----");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("zf_tag", "---- Fragment : onDetach ----");
    }
}
