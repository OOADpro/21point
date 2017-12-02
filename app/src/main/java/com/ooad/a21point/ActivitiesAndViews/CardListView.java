package com.ooad.a21point.ActivitiesAndViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.ooad.a21point.GameModels.Card;


/**
 * Created by 10040 on 2017/12/2.
 */

public class CardListView extends FrameLayout{
    //当前显示牌数量
    private int mCardNum;

    public CardListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCardNum = 0;
    }

    public void addCard(Card card){
        CardView cardView = new CardView(card,getContext());
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)cardView.getLayoutParams();
        params.leftMargin = mCardNum * 20;
        cardView.setLayoutParams(params);
        addView(cardView);

    }

}
