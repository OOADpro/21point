package com.ooad.a21point.GameModels;


import java.util.ArrayList;

/**
 * Created by 10040 on 2017/12/2.
 */

public class GameManager {
    private static GameManager sGameManager;
    private ArrayList<Player> mPlayers;
    private Banker mBanker;
    private Pile mPile;

    public GameManager(){
        mPlayers =new ArrayList<>();
        mBanker =new Banker();
        mPile =new Pile();
    }

    public static GameManager getManager(){
        if(sGameManager==null)
            sGameManager=new GameManager();
        return sGameManager;
    }

    public Banker getBanker(){
        mBanker.initialHand(mPile);
        return mBanker;
    }

    public Player addPlayer(int chip){
        mPlayers.add(new Player(chip));
        mPlayers.get(0).initialHand(mPile);
        return mPlayers.get(0);
    }

//    public void doAiBanker(Hand hand){                          //人工智能·庄家
//        while (!hand.isStand()) {
//            int bpoint = mBanker.getHands().get(0).getPoint();  //庄家的点数
//            if (bpoint >= 21)
//                hand.stand();
//            else {
//                for (Hand phand : mPlayers.get(0).getHands()) {
//                    int ppoint = phand.getPoint();              //闲家的点数
//                    if (ppoint > bpoint)
//                        hand.getAClosedCard();                  //当庄家手牌点数小于闲家手牌且没有爆掉，抽一张暗牌
//                }
//            }
//        }
//    }

    public void makeBet(Player player, int bet){
        player.makeBet(bet);
    }

    public void hit(Person person,Hand hand){       //拿牌
        hand.getAnOpenCard();                       //get a opencard
        if (hand.getPoint()>=21)                  //stand when point is over 21
            stand(person,hand);
    }

    public void doubleBet(Player player, Hand hand){ //加倍 hit once and then stand
        player.makeBet(hand.getBet());
        hand.doubleBet();                           //handle bet
        hit(player,hand);
        stand(player,hand);
    }

    public void split(Player player){               //分牌
        player.split(mPile);
    }

    public ArrayList<Hand> judge(){                 //判断胜负
        ArrayList<Hand> winhand=new ArrayList<>();  // 新建赢家的手牌，初始为空
        int bpoint= mBanker.getHands().get(0).getPoint();//庄家的点数

        for(Hand phand: mPlayers.get(0).getHands()) {
            int ppoint=phand.getPoint();                //闲家的点数
            if (ppoint > bpoint && ppoint <= 21) {
                mPlayers.get(0).winchip(phand.getBet()); //闲家胜利，获得相应的筹码
                winhand.add(phand);
            } else if (ppoint < bpoint && bpoint <= 21) //庄家胜利
                winhand.add(mBanker.getHands().get(0));
        }
        return winhand;
    }

    public void stand(Person person,Hand hand){         //停牌
        if (!hand.isStand())
            hand.stand();
    }

    //翻开暗牌
    public void openCard(Card card){
        card.setOpenOrClosed(true);
    }
}
