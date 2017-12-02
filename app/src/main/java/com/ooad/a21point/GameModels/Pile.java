package com.ooad.a21point.GameModels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 10040 on 2017/12/2.
 */

public class Pile  {

    ArrayList<Card> card=new ArrayList<Card>();
    private int location=0;

    public Pile() {
        //初始化牌组中所有牌的点数与花色
        for(int i=0;i<13;i++)
            for(int j=0;j<4;j++)
                card.add(new Card(j+1,i+1));

    }

    public Card getCard(){
        location=location+1;
        return card.get(location-1);
    }

    public void reset(){
        Collections.shuffle(card);//洗牌
        location=0;
    }
}
