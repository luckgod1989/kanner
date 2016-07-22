package io.kolabell.circle;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * TODO
 *
 * @author RobinVanYang
 * @createTime 2016-07-22 11:40
 */
public class CircleTextView extends TextView {
    private float mStrokeWidth;
    private int mStrokeColor, mSolidColor;

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleTextView, 0, 0);
        try {
            mStrokeWidth = typedArray.getInteger(R.styleable.CircleTextView_stokeWidth, 0);
            mStrokeColor = typedArray.getColor(R.styleable.CircleTextView_stokeColor, 0);
            mSolidColor = typedArray.getColor(R.styleable.CircleTextView_solidColor, 0);
        } finally {
            typedArray.recycle();
        }
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(mSolidColor);
        circlePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        Paint strokePaint = new Paint();
        strokePaint.setColor(mStrokeColor);
        strokePaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        int h = this.getHeight();
        int w = this.getWidth();

        int diameter = ((h > w) ? h : w);
        int radius = diameter / 2;

        this.setHeight(diameter);
        this.setWidth(diameter);

        canvas.drawCircle(diameter / 2, diameter / 2, radius, strokePaint);

        canvas.drawCircle(diameter / 2, diameter / 2, radius - mStrokeWidth, circlePaint);

        super.draw(canvas);
    }

    public void setStrokeWidth(int dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        mStrokeWidth = dp * scale;
    }

    public void setStrokeColor(String color) {
        mSolidColor = Color.parseColor(color);
    }

    public void setSolidColor(String color) {
        mSolidColor = Color.parseColor(color);
    }
}
