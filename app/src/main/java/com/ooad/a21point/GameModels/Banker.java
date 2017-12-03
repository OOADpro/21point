package com.ooad.a21point.GameModels;

import java.util.ArrayList;

/**
 * Created by 10040 on 2017/12/2.
 */

public class Banker extends Person {

    Banker() {
        mHands = new ArrayList<>();
    }

    public Hand getHand(){
        return mHands.get(0);
    }

    //初始化手牌
    void initialHand(Pile pile){
        mHands.clear();
        Hand hand = new Hand(pile);
        hand.getAnOpenCard();
        hand.getAClosedCard();
        mHands.add(hand);
    }

}
