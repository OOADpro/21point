package com.ooad.a21point.ActivitiesAndViews;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ooad.a21point.GameModels.GameManager;
import com.ooad.a21point.GameModels.Player;
import com.ooad.a21point.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10040 on 2017/12/2.
 */

public class BetControllerView extends LinearLayout {
    //玩家
    private Player mPlayer;
    //下注数量显示
    @BindView(R.id.tv_bet_num)
    TextView mTvBetNum;
    //各分值赌注选择
    @BindView(R.id.bet_10)
    Button mBtBet10;
    @BindView(R.id.bet_20)
    Button mBtBet20;
    @BindView(R.id.bet_50)
    Button mBtBet50;
    @BindView(R.id.bet_100)
    Button mBtBet100;
    @BindView(R.id.bet_200)
    Button mBtBet200;
    //确定下注
    @BindView(R.id.bt_make_bet)
    Button mBtMakeBet;
    //已下注数量
    int mBetNum;
    //下注事件
    MakeBet mMakeBet;

    public BetControllerView(Context context) {
        super(context);
        layoutInit(context);
    }

    public BetControllerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        layoutInit(context);
    }

    public BetControllerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        layoutInit(context);
    }

    private void layoutInit(Context context){
        LayoutInflater.from(context).inflate(R.layout.bet_controller_view,this);
        ButterKnife.bind(this);

        initButtons();
    }

    public void init(Player player){
        mPlayer = player;
        mBetNum = 0;
        mTvBetNum.setText(String.valueOf(0));
    }

    public void setMakeBet(MakeBet makeBet){
        mMakeBet = makeBet;
    }

    private void initButtons(){
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                int bet = 0;
                switch (view.getId()){
                    case R.id.bet_10:
                        bet = 10;break;
                    case R.id.bet_20:
                        bet = 20;break;
                    case R.id.bet_50:
                        bet = 50;break;
                    case R.id.bet_100:
                        bet = 100;break;
                    case R.id.bet_200:
                        bet = 200;break;
                }
                if(mPlayer.getChip() >= mBetNum + bet){
                    mBetNum += bet;
                    mTvBetNum.setText(String.valueOf(mBetNum));
                }
                else
                    Toast.makeText(getContext(),"剩余筹码不足",Toast.LENGTH_SHORT).show();
            }
        };
        mBtBet10.setOnClickListener(listener);
        mBtBet20.setOnClickListener(listener);
        mBtBet50.setOnClickListener(listener);
        mBtBet100.setOnClickListener(listener);
        mBtBet200.setOnClickListener(listener);
        mBtMakeBet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mMakeBet.makeBet(mBetNum);
            }
        });
    }

    public interface MakeBet{
        void makeBet(int bet);
    }
}
