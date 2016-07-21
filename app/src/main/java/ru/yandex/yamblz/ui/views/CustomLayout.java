package ru.yandex.yamblz.ui.views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CustomLayout extends ViewGroup {

    public CustomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int curWidth, curHeight, curLeft, curTop;

        final int childLeft = this.getPaddingLeft();
        final int childTop = this.getPaddingTop();

        curLeft = childLeft;
        curTop = childTop;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                break;
            }
            curWidth = child.getMeasuredWidth();
            curHeight = child.getMeasuredHeight();


            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
            curLeft += curWidth;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        final int height = MeasureSpec.getSize(heightMeasureSpec);
        int leftOffset = 0;

        final int count = getChildCount();

        View viewWithMatchParentWidth = null;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            if (child.getLayoutParams().width == LayoutParams.MATCH_PARENT) {
                viewWithMatchParentWidth = child;
            } else {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                leftOffset += child.getMeasuredWidth();
            }
        }

        if (viewWithMatchParentWidth != null) {
            int lastSpec = MeasureSpec.makeMeasureSpec(width - leftOffset, MeasureSpec.EXACTLY);
            viewWithMatchParentWidth.measure(lastSpec, heightMeasureSpec);
        }
        setMeasuredDimension(width, height);
    }
}
