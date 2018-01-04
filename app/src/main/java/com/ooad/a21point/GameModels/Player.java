package com.ooad.a21point.GameModels;

import java.util.ArrayList;


public class Player extends Person {
    //筹码
    private int mChip;
    //是否可分牌
    private boolean mSplitFlag;

    Player(int chip){
        mSplitFlag = false;
        mHands = new ArrayList<>();
        if (chip > 0)
            mChip = chip;
        else
            mChip = 0;
    }

    //下赌注
    void takeBet(int bet){
        mChip -= bet;
        if (mChip < 0)
            mChip = 0;
    }

    void winchip(int bet){
        mChip+=bet;
    }

    public int getChip(){
        return mChip;
    }

    //初始化手牌
    void initialHand(Pile pile){
        mHands.clear();
        Hand hand = new Hand(pile);
        hand.getAnOpenCard();
        hand.getAnOpenCard();
        ArrayList<Card> cards = hand.getAllCards();
        if (cards.get(0).getmPoint() == cards.get(1).getmPoint())
            mSplitFlag = true;
        mHands.add(hand);
    }


    //分牌
    void split(Pile pile){
        if (mSplitFlag){
            Card card = mHands.get(0).getAllCards().get(0);
            Hand hand = new Hand(pile);
            mHands.get(0).removeCard(card);
            hand.addCard(card);
            mHands.add(hand);
            mSplitFlag = false;
        }
    }


    public boolean isSplit(){
        return  mSplitFlag;
    }

    public void setSplitFlag(boolean splitFlag) {
        mSplitFlag = splitFlag;
    }
}
