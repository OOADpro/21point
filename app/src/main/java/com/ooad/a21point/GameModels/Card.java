package com.ooad.a21point.GameModels;


public class Card {
    //花色
    public static final int SPADE = 1; //黑桃
    public static final int HEART = 2; //红桃
    public static final int CLUB = 3; //草花
    public static final int DIAMOND = 4; //方块
    private int mColor;
    //点数
    private int mPoint;
    //明牌还是暗牌
    private boolean mOpenOrClosed;

    Card(int color, int point){
        //默认黑桃2
        mColor = 2;
        mPoint = 2;
        //默认明牌
        mOpenOrClosed = true;
        if (color <= 4 && color >= 1)
            mColor = color;
        if (0 < point && point < 14)
            mPoint = point;
    }

    public int getmColor() {
        return mColor;
    }
    public int getmPoint() {
        return mPoint;
    }
    public boolean isOpen() {
        return mOpenOrClosed;
    }
    void setOpenOrClosed(boolean mOpenOrClosed) {
        this.mOpenOrClosed = mOpenOrClosed;
    }

}
