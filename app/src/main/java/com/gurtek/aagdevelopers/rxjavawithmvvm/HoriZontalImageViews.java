package com.gurtek.aagdevelopers.rxjavawithmvvm;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * * Created by Gurtek Singh on 7/19/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class HoriZontalImageViews extends HorizontalScrollView {
    public HoriZontalImageViews(Context context) {
        super(context);
        init(context,null);
    }

    public HoriZontalImageViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public HoriZontalImageViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HoriZontalImageViews(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    public void init(Context context, AttributeSet attrs){

    }

}
