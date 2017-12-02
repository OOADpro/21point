package com.ooad.a21point.GameModels;

import android.provider.BlockedNumberContract;

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

    public void doAiBanker(){

    }

    public void makebet(Player player,int bet){
        player.makeBet(bet);
    }

    public void doublebet(Player player,Hand hand){
        player.makeBet(hand.getBet());
        hand.doubleBet();
    }

    public void hit(Player player,Hand hand){

        hand.getAnOpenCard();
        while(hand.getPoint()>=21)
            stand(player,hand);
    }

    public void split(Player player){

    }

    public ArrayList<Hand> judge(){
        ArrayList<Hand> winhand=new ArrayList<>();// 新建赢家的手牌，空
        int bpoint=mbanker.getHands().get(0).getPoint();//庄家的点数

        for(Hand phand:mplayer.get(0).getHands()) {
            int ppoint=phand.getPoint();                //闲家的点数
            if (ppoint > bpoint) {
                mplayer.get(0).winchip(phand.getBet()); //闲家胜利，获得相应的筹码
                winhand.add(phand);
            } else if (ppoint < bpoint)
                winhand.add(mbanker.getHands().get(0));
        }
        return winhand;
    }

    public void stand(Player player,Hand hand){
        hand.stand();

        while (player.isStand()){
            doAiBanker();
            judge();
        }
    }
}
