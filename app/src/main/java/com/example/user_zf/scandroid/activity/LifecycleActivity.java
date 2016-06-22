package com.example.user_zf.scandroid.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.fragment.LifecycleFragment;
import com.example.user_zf.scandroid.fragment.ReplaceFragment;

/**
 * Activity生命周期demo，与fragment混合周期
 */
public class LifecycleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        Log.e("zf_tag", "---- Activity : onCreate ----");

        //添加fragment
        Fragment fragment = LifecycleFragment.newInstance();
        this.getFragmentManager().beginTransaction().add(R.id.flContainer, fragment).commit();
    }

    public void startWindowActivity(View source){
        //把MainActivity改为窗口启动
        startActivity(new Intent(this, MainActivity.class));
    }

    public void replaceFragment(View source){
        FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.flContainer, ReplaceFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zf_tag", "---- Activity : onStart ----");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("zf_tag", "---- Activity : onRestart ----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zf_tag", "---- Activity : onResume ----");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("zf_tag", "---- Activity : onPause ----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zf_tag", "---- Activity : onStop ----");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("zf_tag", "---- Activity : onDestroy ----");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("zf_tag", "---- Activity : onSaveInstanceState ----");
    }
}
