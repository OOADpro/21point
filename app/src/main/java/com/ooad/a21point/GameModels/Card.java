package com.ooad.a21point.GameModels;

/**
 * Created by 10040 on 2017/12/2.
 */

public class Card {
    //花色
    private int mColor;
    //点数
    private int mPoint;
    //明牌还是暗牌
    private boolean mOpenOrClosed;


    public  int     getmColor() {
        return mColor;
    }
    public void    setmColor(int mColor) {
        this.mColor = mColor;
    }
    public int     getmPoint() {
        return mPoint;
    }
    public void    setmPoint(int mPoint) {
        this.mPoint = mPoint;
    }
    public boolean ismOpenOrClosed() {
        return mOpenOrClosed;
    }
    public void    setmOpenOrClosed(boolean mOpenOrClosed) {
        this.mOpenOrClosed = mOpenOrClosed;
    }

}
