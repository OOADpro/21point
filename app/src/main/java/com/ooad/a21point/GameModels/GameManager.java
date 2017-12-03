package com.ooad.a21point.GameModels;


import java.util.ArrayList;

/**
 * Created by 10040 on 2017/12/2.
 */

public class GameManager {
    private static GameManager sGameManager;
    private ArrayList<Player> mplayer;
    private Banker mbanker;
    private Pile mpile;

    public GameManager(){
        mplayer=new ArrayList<>();
        mbanker=new Banker();
        mpile=new Pile();
    }

    public static GameManager getManager(){
        if(sGameManager==null)
            sGameManager=new GameManager();
        return sGameManager;
    }

    public Banker getBanker(){
        mbanker.initialHand(mpile);
        return mbanker;
    }

    public Player addPlayer(int chip){
        mplayer.add(new Player(chip));
        mplayer.get(0).initialHand(mpile);
        return mplayer.get(0);
    }

    public void doAiBanker(Hand hand){                          //人工智能·庄家
        while (!hand.isStand()) {
            int bpoint = mbanker.getHands().get(0).getPoint();  //庄家的点数
            if (bpoint >= 21)
                hand.stand();
            else {
                for (Hand phand : mplayer.get(0).getHands()) {
                    int ppoint = phand.getPoint();              //闲家的点数
                    if (ppoint > bpoint)
                        hand.getAClosedCard();                  //当庄家手牌点数小于闲家手牌且没有爆掉，抽一张暗牌
                }
            }
        }
    }

    public void makebet(Player player,int bet){
        player.makeBet(bet);
    }

    public void hit(Player player,Hand hand){       //拿牌
        hand.getAnOpenCard();                       //get a opencard
        while(hand.getPoint()>=21)                  //stand when point is over 21
            stand(player,hand);
    }

    public void doublebet(Player player,Hand hand){ //加倍 hit once and then stand
        player.makeBet(hand.getBet());
        hand.doubleBet();                           //handle bet
        hit(player,hand);
        stand(player,hand);
    }

    public void split(Player player){               //分牌
        player.split(mpile);
    }

    public ArrayList<Hand> judge(){                 //判断胜负
        ArrayList<Hand> winhand=new ArrayList<>();  // 新建赢家的手牌，初始为空
        int bpoint=mbanker.getHands().get(0).getPoint();//庄家的点数

        for(Hand phand:mplayer.get(0).getHands()) {
            int ppoint=phand.getPoint();                //闲家的点数
            if (ppoint > bpoint && ppoint <= 21) {
                mplayer.get(0).winchip(phand.getBet()); //闲家胜利，获得相应的筹码
                winhand.add(phand);
            } else if (ppoint < bpoint && bpoint <= 21) //庄家胜利
                winhand.add(mbanker.getHands().get(0));
        }
        return winhand;
    }

    public void stand(Player player,Hand hand){         //停牌
        while (!hand.isStand())
            hand.stand();
    }
}
