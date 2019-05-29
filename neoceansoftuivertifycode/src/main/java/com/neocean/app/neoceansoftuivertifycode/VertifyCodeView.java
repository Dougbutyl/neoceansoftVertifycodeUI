package com.neocean.app.neoceansoftuivertifycode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * User weixn
 * Date 2019/5/28
 */
public class VertifyCodeView extends FrameLayout implements TextWatcher {

    int length = 6;
    int lineBgColor = getResources().getColor(R.color.gray_holo_light);
    int codeBoderColor = getResources().getColor(R.color.gray_holo_light);
    int codetype = 2;
    int frameType = 1;
    List<TextView> mTextViewList;
    String inputContent = "";//输入内容
    AssistVertifycodeEditText editText;
    VertifyCodeListener mVertifyCodeListener;


    public VertifyCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
    }

    public VertifyCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
    }


    void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ui_vertifycode);
        length = ta.getInteger(R.styleable.ui_vertifycode_code_length, 6);
        lineBgColor = ta.getColor(R.styleable.ui_vertifycode_code_linebg, getResources().getColor(R.color.gray_holo_light));
        codeBoderColor = ta.getColor(R.styleable.ui_vertifycode_code_bodercolor, getResources().getColor(R.color.gray_holo_light));
        codetype = ta.getInt(R.styleable.ui_vertifycode_code_type, 2);
        frameType = ta.getInt(R.styleable.ui_vertifycode_code_frametype, 1);
        addBaseView(context);
    }

    void addBaseView(Context context) {
        mTextViewList = new ArrayList<>();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < length; i++) {
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(16f);
            if (frameType == 2 || frameType == 3) {
                textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.code_frame));
                Drawable frameDrawable = textView.getBackground();
                if (frameDrawable instanceof GradientDrawable) {
                    ((GradientDrawable) frameDrawable).setStroke(DensityUtil.dip2px(context, 1.5f), codeBoderColor);
                }
                textView.setBackgroundDrawable(frameDrawable);
            }
            LinearLayout.LayoutParams tvlayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            tvlayoutParams.weight = 2f;
            if (i != 0 && frameType == 3) {
                tvlayoutParams.leftMargin = -DensityUtil.dip2px(context, 1.5f);
            }
            textView.setLayoutParams(tvlayoutParams);
            mTextViewList.add(textView);
            if (i == 0) {
                linearLayout.addView(textView);
            } else {
                View view = new View(context);
                LinearLayout.LayoutParams viewlayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                viewlayoutParams.weight = 1;
                view.setLayoutParams(viewlayoutParams);
                if (frameType != 3)
                    linearLayout.addView(view);
                linearLayout.addView(textView);
            }
        }
        addView(linearLayout);
        if (frameType == 1)
            addLineView(context);
        addFocusEdit(context);
    }

    /**
     * 底部划线
     *
     * @param context
     */
    void addLineView(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.BOTTOM);
        for (int i = 0; i < length; i++) {
            View lineView = new View(context);

            LinearLayout.LayoutParams tvlayoutParams = new LinearLayout.LayoutParams(0, 3);
            tvlayoutParams.weight = 2f;
            lineView.setBackgroundColor(lineBgColor);
            lineView.setLayoutParams(tvlayoutParams);
            if (i == 0) {
                linearLayout.addView(lineView);
            } else {
                View view = new View(context);
                LinearLayout.LayoutParams viewlayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
                viewlayoutParams.weight = 1;
                view.setLayoutParams(viewlayoutParams);
                linearLayout.addView(view);
                linearLayout.addView(lineView);
            }
        }
        addView(linearLayout);

    }

    void addFocusEdit(Context context) {
        FrameLayout.LayoutParams editlayoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        editText = new AssistVertifycodeEditText(context);
        editText.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
        editText.setTextColor(context.getResources().getColor(android.R.color.transparent));
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setLayoutParams(editlayoutParams);
        editText.setCursorVisible(false);//隐藏光标
        editText.addTextChangedListener(this);
        addView(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        inputContent = editText.getText().toString();

        for (int position = 0; position < length; position++) {
            if (position < inputContent.length()) {
                if (codetype == 1) {
                    mTextViewList.get(position).setText("*");
                } else {
                    mTextViewList.get(position).setText(String.valueOf(inputContent.charAt(position)));
                }
            } else {
                mTextViewList.get(position).setText("");
            }
        }
        if (inputContent.length() == length ) {
            if (mVertifyCodeListener != null) {
                //结束
                mVertifyCodeListener.onInputFinish(inputContent);
            }
        }
    }


    public void setVertifyCodeListener(VertifyCodeListener mVertifyCodeListener) {
        this.mVertifyCodeListener = mVertifyCodeListener;

    }


    public interface VertifyCodeListener {
        void onInputFinish(String inputContent);
    }
}
