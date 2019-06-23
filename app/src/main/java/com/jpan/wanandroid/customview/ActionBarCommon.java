package com.jpan.wanandroid.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.jpan.wanandroid.R;

public class ActionBarCommon extends FrameLayout {

    /**
     * 普通类型
     * （          标题          ）
     */
    private static final int ACTIONBAR_TYPE_NORMAL = 0;
    /**
     * 右侧包含搜索图标类型
     * （          标题       搜索）
     */
    private static final int ACTIONBAR_TYPE_SEARCH = 1;

    private int mActionBarType;

    public ActionBarCommon(Context context) {
        super(context);
    }

    public ActionBarCommon(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttrs(context, attrs);
    }

    public ActionBarCommon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs);
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ActionBarCommon);
        mActionBarType = typedArray.getInteger(R.styleable.ActionBarCommon_actionbar_type, ACTIONBAR_TYPE_NORMAL);
        typedArray.recycle();

        initView(context);
    }

    private void initView(Context context) {
        switch (mActionBarType) {
            case ACTIONBAR_TYPE_NORMAL:
                View.inflate(context, R.layout.actionbar_layout_normal, this);
                break;
            case ACTIONBAR_TYPE_SEARCH:
                View.inflate(context, R.layout.actionbar_layout_search, this);
                break;
            default:
                break;
        }
    }
}
