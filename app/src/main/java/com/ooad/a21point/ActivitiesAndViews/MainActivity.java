package com.ooad.a21point.ActivitiesAndViews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ooad.a21point.GameModels.Banker;
import com.ooad.a21point.GameModels.GameManager;
import com.ooad.a21point.GameModels.Hand;
import com.ooad.a21point.GameModels.Player;
import com.ooad.a21point.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    //游戏管理器
    GameManager mGameManager;
    //开始游戏按键
    @BindView(R.id.bt_begin)
    Button mBtBegin;
    //玩家主手牌
    @BindView(R.id.player_hand_view)
    HandControllerView mPlayerHand;
    //玩家分牌
    @BindView(R.id.player_split_hand_view)
    HandControllerView mPlayerSplitHand;
    //庄家手牌
    @BindView(R.id.banker_hand_view)
    CardListView mBankerHand;
    //下注控件
    @BindView(R.id.bet_view)
    BetControllerView mBetControllerView;
    //剩余分数显示
    @BindView(R.id.tv_chip)
    TextView mTvChip;
    //分牌
    @BindView(R.id.bt_split)
    Button mBtSplit;
    //玩家
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mGameManager = GameManager.getManager();
        mPlayer = mGameManager.addPlayer(1000);

        mBtBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginNewGame();
            }
        });
        init();

        //临时，便于复制
        mPlayerHand.setVisibility(View.VISIBLE);
        mPlayerSplitHand.setVisibility(View.VISIBLE);
        mBankerHand.setVisibility(View.VISIBLE);
        mBetControllerView.setVisibility(View.VISIBLE);
        mTvChip.setVisibility(View.VISIBLE);
        mBtSplit.setVisibility(View.VISIBLE);
    }

    //初始化各控件结束条件
    private void init(){
        //下注监听器
        mBetControllerView.setMakeBet(new BetControllerView.MakeBet() {
            @Override
            public void makeBet(int bet) {
                mGameManager.makeBet(mPlayer,bet);
                beginNewTurn();
            }
        });

        //分牌按键监听器
        mBtSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayer.getHands().get(0).getBet() < mPlayer.getChip()){
                    mGameManager.split(mPlayer);
                    split();
                }
                else {
                    Toast.makeText(MainActivity.this,"剩余筹码不足",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //玩家操作结束事件
        mPlayerHand.setPlayerEnd(new Runnable() {
            @Override
            public void run() {
                bankerTurn();
            }
        });
    }

    //刷新分数
    private void refreshChip(){
        mTvChip.setText(mPlayer.getChip());
    }

    //开局新游戏
    void beginNewGame(){
        mBetControllerView.init(mPlayer);
        refreshChip();

        //设置控件可见性
        mBetControllerView.setVisibility(View.VISIBLE);
        mTvChip.setVisibility(View.VISIBLE);
        mPlayerHand.setVisibility(View.GONE);
        mPlayerSplitHand.setVisibility(View.GONE);
        mBankerHand.setVisibility(View.GONE);
        mBtSplit.setVisibility(View.GONE);
        mBtBegin.setVisibility(View.GONE);
    }

    //下注
    void beginNewTurn(){
        mPlayerHand.init(mPlayer, mPlayer.getHands().get(0));
        mBankerHand.init(mGameManager.getBanker().getHand());
        refreshChip();

        //设置控件可见性
        mPlayerHand.setVisibility(View.VISIBLE);
        mBankerHand.setVisibility(View.VISIBLE);
        mBetControllerView.setVisibility(View.GONE);
        if(mPlayer.isSplit()){
            mBtSplit.setVisibility(View.VISIBLE);
        }
    }

    //玩家stand，庄家操作
    void bankerTurn(){
        mGameManager.openCard(mGameManager.getBanker().getHand().getAllCards().get(1));
        mBankerHand.refreshAllCards();
        sleep(1);
        doAIBanker();
    }

    //模拟庄家
    public void doAIBanker(){
        ArrayList<Hand> hands = mPlayer.getHands();
        Banker banker = mGameManager.getBanker();
        Hand bankerHand = banker.getHand();
        int targetPoint = 0;
        if (hands.size() == 2 && hands.get(1).getBet() > hands.get(0).getBet())
            targetPoint = 1;
        targetPoint = hands.get(targetPoint).getPoint();
        while (!bankerHand.isStand() && bankerHand.getPoint() < targetPoint){
            mGameManager.hit(banker,bankerHand);
            mBankerHand.refreshList();
            sleep(1);
        }
        mGameManager.stand(banker,bankerHand);
        judge();
    }

    //分牌
    void split(){
        mGameManager.split(mPlayer);
        mPlayerSplitHand.init(mPlayer,mPlayer.getHands().get(1));

        mPlayerSplitHand.setVisibility(View.VISIBLE);
        mBtSplit.setVisibility(View.GONE);
    }

    //结算
    void judge(){
        ArrayList<Hand> winnerHands = mGameManager.judge();
        ArrayList<Hand> hands = mPlayer.getHands();
        //显示主手牌结果
        mPlayerHand.showResult(winnerHands.contains(hands.get(0)));
        //若分牌，则显示分牌结果
        if (hands.size() == 2){
            mPlayerHand.showResult(winnerHands.contains(hands.get(1)));
        }
        //开始新游戏
        beginNewTurn();
    }

    private void sleep(int second){
        try {
            Thread.sleep(1000 * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
