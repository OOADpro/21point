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
    }

    public void split(Player player){

    }

    public Person judge(Player player, Banker banker){
        Hand phand=player.getHands().get(0);
        Hand bhand=banker.getHands().get(0);
        if (phand.getPoint()>bhand.getPoint()){
            player.winchip(phand.getBet());
        }
    }

    public void stand(Player player,Hand hand){
        hand.stand();

        while (player.isStand()){
            doAiBanker();
            judge();
        }
    }
}
