package com.example.administrator.myall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Administrator on 2016/5/22 0022.
 */
public abstract class BaseActivity extends SlidingFragmentActivity {

    public enum DIRECTION {

        LEFT,RIGHT,TOP,BOTTOM,SCALE,FADE
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void intent2Activity(Class<? extends  Activity> activity) {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DIRECTION drection = getCreateDrection();
        drection = DIRECTION.BOTTOM;
        switch (drection) {
            case LEFT:
                overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
                     break;
            case RIGHT:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case TOP:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case BOTTOM:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case SCALE:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case FADE:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;

                 default:
                     break;
               }

    }

    abstract protected DIRECTION getCreateDrection();
    abstract protected DIRECTION getDestroyDrection();

    @Override
    public void finish() {
        super.finish();
        DIRECTION drection = getCreateDrection();
        drection = DIRECTION.BOTTOM;
        switch (drection) {
            case LEFT:
                overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
                break;
            case RIGHT:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case TOP:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case BOTTOM:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case SCALE:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;
            case FADE:
                overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
                break;

            default:
                break;
        }
    }
}
