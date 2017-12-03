package com.ooad.a21point.GameModels;

import java.util.ArrayList;

/**
 * Created by 10040 on 2017/12/2.
 */

public abstract class Person {
    //手牌
    protected ArrayList<Hand> mHands;

    Person() {
        mHands = new ArrayList<>();
    }

    public ArrayList<Hand> getHands(){
        return mHands;
    }

    //是否停牌
    public boolean isStand(){
        for (Hand hand:
                mHands) {
            if (!hand.isStand())
                return false;
        }
        return true;
    }
}
