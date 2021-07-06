package com.example.alisehomeproject.Viewsmode;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;




/**
 * Created by Bodhidipta on 13/06/16.
 */
public class
HighBoldTextView extends AppCompatTextView {

    public HighBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public HighBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public HighBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        super.setTypeface(FontCache.get("Gilroy-ExtraBold.otf", context));
    }
}