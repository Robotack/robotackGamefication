package com.robotack.robogamification.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFont extends androidx.appcompat.widget.AppCompatTextView {
    public CustomFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "capitalbank-medium.ttf"));
    }
}

