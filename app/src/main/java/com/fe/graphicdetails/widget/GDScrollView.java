package com.fe.graphicdetails.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by chenpengfei on 2016/11/4.
 */
public class GDScrollView extends ScrollView {

    private LinearLayout mLl;
    private int mLlHeight;
    public static final String TAG_ONE = "up";
    public static final String TAG_TWO = "down";
    public static final int ID_ONE = 11111;
    public static final int ID_TWO = 22222;
    private GraphicDetailsLayout.ScrollListener mScrollListener;

    public GDScrollView(Context context) {
        super(context);
    }

    public GDScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GDScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GDScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollListener(GraphicDetailsLayout.ScrollListener scrollListener) {
        mScrollListener = scrollListener;
    }


    public void addFragment(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(mLl == null) mLl = (LinearLayout) getChildAt(0);
        fragmentTransaction.replace(mLl.getId(), fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLlHeight = mLl.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLl = (LinearLayout) getChildAt(0);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);

        if(getTag().equals(TAG_ONE)) {
            //上面的界面滚动到底部的时候
            if(isScrollBottom()) {
                criticalPointOperation(false, true, TAG_ONE);
            }
        }

        if(getTag().equals(TAG_TWO)) {
            //下面的界面滚动到顶部的时候
            if(getScrollY() <= 0) {
                criticalPointOperation(false, true, TAG_TWO);
            }
        }
    }

    private void criticalPointOperation(boolean allow, boolean intercept, String tag) {
        getParent().requestDisallowInterceptTouchEvent(allow);
        if(mScrollListener != null) mScrollListener.scrollBottom(intercept, tag);
    }

    public boolean isScrollBottom() {
        return getScrollY() >= (mLlHeight - getMeasuredHeight());
    }

}
