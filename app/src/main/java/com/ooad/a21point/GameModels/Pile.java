package com.ooad.a21point.GameModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Pile  {

    private ArrayList<Card> mCard = new ArrayList<>();
    private int mLocation =0;

    public Pile() {
        //初始化牌组中所有牌的点数与花色
//        for(int i=1;i<=13;i++)
//            for(int j=1;j<=4;j++)
//                mCard.add(new Card(j,i));
        //去掉黑桃，并且再随机抽掉2张牌
        Random random = new Random();
        int randPoint1 = random.nextInt(13) + 1;
        int randColor1 = random.nextInt(3) + 2;
        int randPoint2 = random.nextInt(13) + 1;
        int randColor2 = random.nextInt(3) + 2;
        for (int i=1;i<=13;i++)
            for (int j=2;j<=4;j++)
                if ((i!=randPoint1 || j!=randColor1) && (i!=randPoint2 || j!=randColor2))
                    mCard.add(new Card(j,i));
        reset();
    }

    public Card getCard(){
        if(mLocation<37) {
            mLocation = mLocation + 1;
            return mCard.get(mLocation - 1);
        }
        else
            return null;
    }



    void reset(){
        //洗牌
        for(int i=0;i<50;i++) {
//            int ranA = (int) (Math.random() * 52);
//            int ranB = (int) (Math.random() * 52);
            //删除黑桃以及两张随机牌后，牌的数量发生变化
            int ranA = (int) (Math.random() * 37);
            int ranB = (int) (Math.random() * 37);
            Collections.swap(mCard,ranA,ranB);
        }
        mLocation =0;
    }
}
