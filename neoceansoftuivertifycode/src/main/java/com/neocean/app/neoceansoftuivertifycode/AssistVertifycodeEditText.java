package com.neocean.app.neoceansoftuivertifycode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * User weixn
 * Date 2019/5/29
 */
@SuppressLint("AppCompatCustomView")
 class AssistVertifycodeEditText extends EditText {
    public AssistVertifycodeEditText(Context context) {
        super(context);
    }

    public AssistVertifycodeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AssistVertifycodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //固定光标在最后
        setSelection(getText().length());
    }
    long currentTime=0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //屏蔽双击事件 聚焦
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(System.currentTimeMillis()-currentTime<500){
                    currentTime=System.currentTimeMillis();
                    return true;
                }else{
                    currentTime=System.currentTimeMillis();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
