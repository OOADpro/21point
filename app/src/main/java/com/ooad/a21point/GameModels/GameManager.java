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

    public static GameManager getManager(){
        if(sGameManager==null)
            sGameManager=new GameManager();
        return sGameManager;
    }

    public GameManager(){
        mPlayers =new ArrayList<>();
        mBanker =new Banker();
        mPile =new Pile();
        mBanker.initialHand(mPile);
    }

    public void init(){
        for (Player player:mPlayers){
            player.getHands().clear();
        }
        mBanker.getHands().clear();
    }

    public Banker getBanker(){
        return mBanker;
    }

    public Player addPlayer(int chip){
        Player player = new Player(chip);
        mPlayers.add(player);
        return player;
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
        player.takeBet(bet);
        player.initialHand(mPile);
        mBanker.initialHand(mPile);
        player.getHands().get(0).setBet(bet);
    }

    public void bankerHit(Hand hand){       //拿牌
        hand.getAnOpenCard();                       //get a opencard
        if (hand.getPoint()>=21)                  //stand when point is over 21
            bankerStand(hand);
    }

    public void hit(Player player,Hand hand){       //拿牌
        player.setSplitFlag(false);
        hand.getAnOpenCard();                       //get a opencard
        if (hand.getPoint()>21)                  //stand when point is over 21
            stand(player,hand);
    }

    public void doubleBet(Player player, Hand hand){ //加倍 hit once and then stand
        player.takeBet(hand.getBet());
        hand.doubleBet();                           //handle bet
        hit(player,hand);
        stand(player,hand);
    }

    public void split(Player player){               //分牌
        ArrayList<Hand> hands = player.getHands();
        int bet = hands.get(0).getBet();
        player.split(mPile);
        player.takeBet(bet);
        hands.get(1).setBet(bet);
    }

    public ArrayList<Hand> judge(){                 //判断胜负
        ArrayList<Hand> winhand=new ArrayList<>();  // 新建赢家的手牌，初始为空
        int bpoint= mBanker.getHands().get(0).getPoint();//庄家的点数
        boolean isBankerWinner = false;

        for (Player player:mPlayers) {
            for (Hand phand : player.getHands()) {
                int ppoint = phand.getPoint();                //闲家的点数
                if (ppoint == Hand.BLACK_JACK){
                    winhand.add(phand);
                    if (bpoint != Hand.BLACK_JACK)
                        player.winchip(phand.getBet() * 3); //闲家胜利，获得相应的筹码
                }
                else if (ppoint > 21 || bpoint == Hand.BLACK_JACK){
                    isBankerWinner = true;
                }
                else if (bpoint > 21 || ppoint > bpoint) {
                    player.winchip(phand.getBet() * 2); //闲家胜利，获得相应的筹码
                    winhand.add(phand);
                }
                else if (ppoint < bpoint) //庄家胜利
                    isBankerWinner = true;
            }
        }
        if (isBankerWinner)
            winhand.add(mBanker.getHand());
        return winhand;
    }

    public void bankerStand(Hand hand){         //停牌
        if (!hand.isStand())
            hand.stand();
    }

    public void stand(Player player,Hand hand){         //停牌
        player.setSplitFlag(false);
        if (!hand.isStand())
            hand.stand();
    }
    //翻开暗牌
    public void openCard(Card card){
        card.setOpenOrClosed(true);
    }
}
