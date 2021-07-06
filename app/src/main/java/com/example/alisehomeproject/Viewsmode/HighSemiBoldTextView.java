package com.example.alisehomeproject.Viewsmode;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * Created by Bodhidipta on 13/06/16.
 */
public class HighSemiBoldTextView extends AppCompatTextView {

    public HighSemiBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public HighSemiBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public HighSemiBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        super.setTypeface(FontCache.get("Gilroy-SemiBold.ttf", context));
    }
}