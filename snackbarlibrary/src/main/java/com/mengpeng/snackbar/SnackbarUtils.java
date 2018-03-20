package com.mengpeng.snackbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 作者：MengPeng
 * 时间：2018/3/14 - 下午6:29
 * 说明：
 */
public class SnackbarUtils {

    private static Snackbar snackbar;
    private static Context mContext;
    private static SnackbarUtils snackbarUtils;
    private static View inflate;

    private static RelativeLayout snackbar_container;
    private static ImageView snackbar_icon;
    private static TextView snackbar_content;
    private static TextView snackbar_btn1;
    private static TextView snackbar_btn2;
    private static Snackbar.SnackbarLayout viewSnackbar;

    private static View mView;
    private static int defaultHeight = 50;


    public static SnackbarUtils create(Context context) {
        return create(context, null, 50);
    }

    public static SnackbarUtils create(Context context, View view, int viewHeight) {
        mContext = context;
        mView = view;
        defaultHeight = viewHeight;

        if (null != snackbar) {
            if (snackbar.isShownOrQueued()) {
                snackbar.dismiss();
            }
            snackbar = null;
        }
        if (snackbarUtils != null) {
            snackbarUtils = null;
        }
        snackbarUtils = new SnackbarUtils();

        snackbar = Snackbar.make(((Activity) context).getWindow().getDecorView(), "", Snackbar.LENGTH_SHORT);
        viewSnackbar = (Snackbar.SnackbarLayout) snackbar.getView();

        int paddingLeft = viewSnackbar.getPaddingLeft();
        int paddingRight = viewSnackbar.getPaddingRight();

        viewSnackbar.setPadding(0, 0, 0, 0);

        if (null == view) {
            inflate = LayoutInflater.from(mContext).inflate(R.layout.snackbar_view, null);
            snackbar_container = inflate.findViewById(R.id.snackbar_container);
            snackbar_icon = inflate.findViewById(R.id.snackbar_icon);
            snackbar_content = inflate.findViewById(R.id.snackbar_content);
            snackbar_btn1 = inflate.findViewById(R.id.snackbar_btn1);
            snackbar_btn2 = inflate.findViewById(R.id.snackbar_btn2);
            snackbar_container.setBackgroundColor(ContextCompat.getColor(mContext, R.color.toastDefaultColor));

            snackbar_container.setPadding(paddingLeft, 0, paddingRight, 0);
        }


        return snackbarUtils;
    }

    public SnackbarUtils setContent(String content) {
        return setContent(content, -1);
    }

    public SnackbarUtils setContent(String content, int contentColor) {
        if (null == inflate) {
            return snackbarUtils;
        }
        //设置消息内容
        if (!TextUtils.isEmpty(content)) {
            snackbar_content.setText(content);
        } else {
            snackbar_content.setText("");
        }
        //设置消息内容字体颜色
        if (-1 != contentColor) {
            snackbar_content.setTextColor(ContextCompat.getColor(mContext, contentColor));
        } else {
            snackbar_content.setTextColor(ContextCompat.getColor(mContext, R.color.toastDefaultTextColor));
        }
        return snackbarUtils;
    }

    public SnackbarUtils setSnackbarBackgroundColor(int snackbarBackgroundColor) {
        if (null == inflate) {
            return snackbarUtils;
        }
        //设置背景颜色
        snackbar_container.setBackgroundColor(ContextCompat.getColor(mContext, snackbarBackgroundColor));
        viewSnackbar.setBackgroundColor(ContextCompat.getColor(mContext, snackbarBackgroundColor));
        return snackbarUtils;
    }

    public SnackbarUtils setIcon(int icon) {
        if (null == inflate) {
            return snackbarUtils;
        }
        //设置icon
        if (-1 != icon) {
            snackbar_icon.setImageResource(icon);
            snackbar_icon.setVisibility(View.VISIBLE);
        } else {
            snackbar_icon.setVisibility(View.GONE);
        }
        return snackbarUtils;
    }

    public SnackbarUtils setDuration(int duration) {
        snackbar.setDuration(duration);
        return snackbarUtils;
    }

    public SnackbarUtils setActionBtn1(String btn1, View.OnClickListener listener1) {
        return setActionBtn1(btn1, -1, listener1);
    }

    public SnackbarUtils setActionBtn1(String btn1, int btn1Color, View.OnClickListener listener1) {
        if (null == inflate) {
            return snackbarUtils;
        }
        if (null == snackbar_btn1) {
            throw new NullPointerException("执行setActionBtn1之前，请务必先调用create方法...");
        }
        snackbar_btn1.setText(btn1);
        if (!TextUtils.isEmpty(btn1)) {
            snackbar_btn1.setVisibility(View.VISIBLE);
        } else {
            snackbar_btn1.setVisibility(View.GONE);
        }
        if (null != listener1) {
            snackbar_btn1.setOnClickListener(listener1);
        }
        //设置btn1字体颜色
        if (-1 != btn1Color) {
            snackbar_btn1.setTextColor(ContextCompat.getColor(mContext, btn1Color));
        } else {
            snackbar_btn1.setTextColor(ContextCompat.getColor(mContext, R.color.toastDefaultTextColor));
        }
        return snackbarUtils;
    }

    public SnackbarUtils setActionBtn2(String btn2, View.OnClickListener listener2) {
        return setActionBtn2(btn2, -1, listener2);
    }

    public SnackbarUtils setActionBtn2(String btn2, int btn2Color, View.OnClickListener listener2) {
        if (null == inflate) {
            return snackbarUtils;
        }
        if (null == snackbar_btn2) {
            throw new NullPointerException("执行setActionBtn2之前，请务必先调用create方法...");
        }
        snackbar_btn2.setText(btn2);
        if (!TextUtils.isEmpty(btn2)) {
            snackbar_btn2.setVisibility(View.VISIBLE);
        } else {
            snackbar_btn2.setVisibility(View.GONE);
        }
        if (null != listener2) {
            snackbar_btn2.setOnClickListener(listener2);
        }
        //设置btn2字体颜色
        if (-1 != btn2Color) {
            snackbar_btn2.setTextColor(ContextCompat.getColor(mContext, btn2Color));
        } else {
            snackbar_btn2.setTextColor(ContextCompat.getColor(mContext, R.color.toastDefaultTextColor));
        }
        return snackbarUtils;
    }


    public void build() {
        if (null == mContext) {
            throw new NullPointerException("执行build之前，请务必先调用create方法...");
        }
        showSnackbar();
    }

    private void showSnackbar() {

        //设置新建布局参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp2px(mContext, defaultHeight));
        params.gravity = Gravity.FILL;//设置新建布局在Snackbar内垂直居中显示
        if (null == mView) {
            viewSnackbar.addView(inflate, 1, params);
        } else {
            int height = mView.getHeight();
            Log.d("TAG", "height:" + height);
            viewSnackbar.addView(mView, 1, params);
            Drawable background = mView.getBackground();
            viewSnackbar.setBackground(background);
        }
        snackbar.show();
    }

    private static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }


    public static boolean isShown() {
        if (null != snackbar) {
            return snackbar.isShown();
        } else {
            return false;
        }
    }

    public static boolean isShownOrQueued() {
        if (null != snackbar) {
            return snackbar.isShownOrQueued();
        } else {
            return false;
        }
    }

    public static void dismiss() {
        if (null != snackbar && snackbar.isShown()) {
            snackbar.dismiss();
            snackbar = null;
        }
    }
}
