package com.example.user_zf.scandroid.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.example.user_zf.scandroid.R;
import com.example.user_zf.scandroid.myview.DynamicRing;

public class ClockActivity extends AppCompatActivity {

    DynamicRing ring;

    TestObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        ring = (DynamicRing) findViewById(R.id.ring);
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ring.addF2();
            }
        });

//        testAnimator();
    }

    private void testAnimator(){
        final TestObject object = new TestObject();
        object.setAngle(0);
        ValueAnimator anim = ObjectAnimator.ofInt(object, "xAngle", 0, 100);
        anim.setDuration(2000);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                object.setxAngle(value);
                Log.e("zf_tag", "value="+value);
            }
        });
        anim.start();
    }

    private class TestObject{
        int angle;//起始角度
        int xAngle;//整个曲线长度所占角度

        public int getAngle() {
            return angle;
        }

        public void setAngle(int angle) {
            this.angle = angle;
        }

        public int getxAngle() {
            return xAngle;
        }

        public void setxAngle(int xAngle) {
            this.xAngle = xAngle;
        }
    }
}
