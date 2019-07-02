package com.example.icloset;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public final class ChoiceTouchListener implements View.OnTouchListener {

private int _xDelta;
private int _yDelta;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lparams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                 _xDelta = X - lparams.leftMargin;
                _yDelta = Y - lparams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutparams.leftMargin = X- _xDelta;
                layoutparams.topMargin = Y- _yDelta;
                layoutparams.rightMargin = -250;
                layoutparams.bottomMargin = -250;
                v.setLayoutParams(layoutparams);
                break;



        }



        return false;
    }
}
