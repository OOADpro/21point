package com.ooad.a21point.GameModels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 10040 on 2017/12/2.
 */

public class Pile  {

    private ArrayList<Card> mCard = new ArrayList<>();
    private int mLocation =0;

    public Pile() {
        //初始化牌组中所有牌的点数与花色
        for(int i=0;i<13;i++)
            for(int j=0;j<4;j++)
                mCard.add(new Card(j+1,i+1));

    }

    public Card getCard(){
        if(mLocation<52) {
            mLocation = mLocation + 1;
            return mCard.get(mLocation - 1);
        }
        else
            return null;
    }



    void reset(){
        Collections.shuffle(mCard);//洗牌
        mLocation =0;

    }
}
