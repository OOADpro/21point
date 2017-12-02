package com.ooad.a21point.GameModels;

import java.util.ArrayList;

/**
 * Created by 10040 on 2017/12/2.
 */

public class Hand {
    //赌注
    private int mBet;
    //手牌列表
    private ArrayList<Card> mCards;
    //牌堆
    private Pile mPile;
    //是否停牌
    private boolean mIsStand;

    Hand(Pile pile){
        mPile = pile;
        mIsStand = false;
        mCards = new ArrayList<>();
    }

    public int getBet() {
        return mBet;
    }

    void setBet(int bet) {
        mBet = bet;
    }

    void doubleBet(){
        mBet *= 2;
    }

    //抽一张明牌
    void getAnOpenCard(){
        Card newCard = mPile.getCard();
        newCard.setOpenOrClosed(true);
        mCards.add(newCard);
    }

    //抽一张暗牌
    void getAClosedCard(){
        Card newCard = mPile.getCard();
        newCard.setOpenOrClosed(false);
        mCards.add(newCard);
    }

    //停牌
    void stand(){
        mIsStand = true;
    }

    //是否停牌
    public boolean isStand(){
        return mIsStand;
    }

    //添加一张牌
    void addCard(Card card){
        mCards.add(card);
    }

    //移除一张牌
    void removeCard(Card card){
        mCards.remove(card);
    }

    //获取所有手牌
    public ArrayList<Card> getAllCards(){
        return mCards;
    }

    //获取最优分数,返回最优分数，若为黑杰克，则返回-1
    public int getPoint(){
        int point = 0;
        //判断是否黑杰克
        if (mCards.size() == 2){
            int cardPoint1 = mCards.get(0).getmPoint();
            int cardPoint2 = mCards.get(1).getmPoint();
            if((cardPoint1 == 1 && cardPoint2 >= 11)||(cardPoint1 >= 11 && cardPoint2 == 1)){
                return Integer.MAX_VALUE;
            }
        }
        //将所有A当做11分计算
        for (Card card:
             mCards) {
            int cardPoint = card.getmPoint();
            if (cardPoint >= 10)
                point += 10;
            if (cardPoint == 1)
                point += 11;
        }
        //如果爆掉，则循环找A，每个A扣除10分，除非分数已经低于21
        if (point > 21){
            for (Card card:
                    mCards) {
                int cardPoint = card.getmPoint();
                if (cardPoint == 1)
                    point -= 10;
                if (point <= 21)
                    break;
            }
        }
        return point;
    }
}
