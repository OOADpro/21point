package com.ooad.a21point.ActivitiesAndViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

    public CardListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Hand hand){
        mHand = hand;
        mCardNum = 0;
        removeAllViews();
        refreshList();
    }

    public void addCard(Card card){
        CardView cardView = new CardView(card,getContext());
//        cardView.setMaxHeight(100);
//        cardView.setLeft(mCardNum * 20);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(150, 300);
        params.leftMargin = 10 + mCardNum * 60;
        cardView.setLayoutParams(params);
        addView(cardView);
    }

    public void refreshAllCards(){
        init(mHand);
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
