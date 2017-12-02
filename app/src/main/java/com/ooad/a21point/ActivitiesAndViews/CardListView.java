package com.ooad.a21point.ActivitiesAndViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.ooad.a21point.GameModels.Card;
import com.ooad.a21point.GameModels.Hand;

import java.util.ArrayList;


/**
 * Created by 10040 on 2017/12/2.
 */

public class CardListView extends FrameLayout{
    //手牌数据
    private Hand mHand;
    //当前显示手牌数量
    private int mCardNum;

    public CardListView(Context context) {
        super(context);
    }

    public void init(Hand hand){
        mHand = hand;
        mCardNum = 0;
        removeAllViews();
        refreshList();
    }

    public void addCard(Card card){
        CardView cardView = new CardView(card,getContext());
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)cardView.getLayoutParams();
        params.leftMargin = mCardNum * 20;
        cardView.setLayoutParams(params);
        addView(cardView);
    }

    //更新牌列表
    public void refreshList(){
        ArrayList<Card> cards = mHand.getAllCards();
        while (mCardNum < cards.size()){
            addCard(cards.get(mCardNum));
            mCardNum++;
        }
    }
}
