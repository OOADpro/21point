package com.ooad.a21point.GameModels;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 10040 on 2017/12/2.
 */

public class Pile  {

    private ArrayList<Card> mCard = new ArrayList<>();
    private int mLocation =0;
    private ArrayList<Integer> list=new ArrayList<>();

    public Pile() {
        //初始化牌组中所有牌的点数与花色
        for(int i=0;i<13;i++)
            for(int j=0;j<4;j++)
                mCard.add(new Card(j+1,i+1));
        for(int i=0;i<52;i++)
            list.add(i);
        reset();
    }

    public Card getCard(){
        if(mLocation<52) {
            mLocation = mLocation + 1;
            int mcardlocation=list.get(mLocation-1);
            return mCard.get(mcardlocation);
        }
        else
            reset();
            return getCard();
    }



    void reset(){
        //洗牌
        for(int i=0;i<50;i++) {
            int ranA = (int) (Math.random() * 52);
            int ranB = (int) (Math.random() * 52);
//            Log.d("ranA",String.valueOf(ranA));
//            Log.d("ranB",String.valueOf(ranB));
            Collections.swap(list,ranA,ranB);
//            Log.d("liatrana",String.valueOf(list.get(ranA))+String.valueOf(list.get(ranB)));
        }
        mLocation =0;

    }
}
