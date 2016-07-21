package ru.yandex.yamblz.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;


public class CustomLinearLayout extends LinearLayout {
    private int layoutInvokeCount;
    private int measureInvokeCount;

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("LogTag","Invoke layout count = " + (++layoutInvokeCount));
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("LogTag","Invoke measure count = " + (++measureInvokeCount));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
