package com.kc.toucheasy.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kc.toucheasyLib.R;

public class TouchView extends FloatingActionButton {

    private View aimView;
    private Context context;

    public TouchView(Context context) {
        this(context,null);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
        initAnimator(this);
    }

    private void initView(final Context context){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(context,v);
            }
        });
    }

    private PopupWindow popupWindow = null;
    private View popupView ;

    private void showDialog(Context context,final View aimView) {
        if (popupWindow == null) {
            popupView = View.inflate(context, R.layout.dialog_view, null);
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            popupWindow = new PopupWindow(popupView, (int) (displayMetrics.widthPixels * 0.6), WindowManager.LayoutParams.WRAP_CONTENT);
        }
        aimView.setVisibility(GONE);
//        popupWindow.setAnimationStyle();
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(this, Gravity.CENTER, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                aimView.setVisibility(VISIBLE);
            }
        });
    }

    private AnimationSet set;
    private void initAnimator(View view){
        set = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(view.getX(), 200, view.getY(), 200);
        translateAnimation.setDuration(600);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
        scaleAnimation.setDuration(600);

        set.addAnimation(translateAnimation);
        set.addAnimation(translateAnimation);
    }

}
