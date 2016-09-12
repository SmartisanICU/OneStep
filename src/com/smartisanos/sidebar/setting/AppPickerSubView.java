package com.smartisanos.sidebar.setting;

import com.smartisanos.sidebar.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AppPickerSubView extends RelativeLayout implements View.OnClickListener {

    private boolean mSelected = false;
    private ImageView mIcon, mSelectedView;
    private TextView mAppName;
    private OnCheckedChangeListener mListener;

    public AppPickerSubView(Context context) {
        this(context, null);
    }

    public AppPickerSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppPickerSubView(Context context, AttributeSet attrs,
            int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AppPickerSubView(Context context, AttributeSet attrs,
            int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIcon = (ImageView) findViewById(R.id.icon);
        mSelectedView = (ImageView) findViewById(R.id.selected);
        mAppName = (TextView) findViewById(R.id.app_name);
        setOnClickListener(this);
    }

    public void setImageBitmap(Bitmap bitmap) {
        mIcon.setImageBitmap(bitmap);
    }

    public void setText(CharSequence cs) {
        mAppName.setText(cs);
    }

    public void setSelected(boolean selected) {
        if (mSelected != selected) {
            mSelected = selected;
            mSelectedView.setVisibility(mSelected ? View.VISIBLE : View.INVISIBLE);
            if (mListener != null) {
                mListener.onCheckedChanged(this, mSelected);
            }
        }
    }

    public void setListener(OnCheckedChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        setSelected(!mSelected);
    }

    public static interface OnCheckedChangeListener {
        void onCheckedChanged(AppPickerSubView view, boolean isChecked);
    }
}