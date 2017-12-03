package com.ooad.a21point.ActivitiesAndViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ooad.a21point.GameModels.GameManager;
import com.ooad.a21point.GameModels.Hand;
import com.ooad.a21point.GameModels.Player;
import com.ooad.a21point.R;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10040 on 2017/12/2.
 */

public class HandControllerView extends LinearLayout{
    //玩家
    private Player mPlayer;
    //手牌数据
    private Hand mHand;
    //hit,stand,double按钮
    @BindView(R.id.bt_hit)
    Button mBtHit;
    @BindView(R.id.bt_stand)
    Button mBtStand;
    @BindView(R.id.bt_double)
    Button mBtDouble;
    //牌列表
    @BindView(R.id.card_list_view)
    CardListView mCardListView;
    //胜负结果
    @BindView(R.id.tv_result)
    TextView mTvResult;

    public HandControllerView( Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.hand_controller_view, this);
        ButterKnife.bind(this);
        initButtons();
    }

    public void init(Player player,Hand hand){
        mPlayer = player;
        mHand = hand;

        mTvResult.setVisibility(View.GONE);
        mCardListView.init(mHand);
        refreshList();
    }

    //设置结果
    public void showResult(boolean result){
        String resultMsg = "";
        if (result){
            //赢了
            resultMsg = "Win";
        }else {
            //输了
            resultMsg = "Lose";
        }
        mTvResult.setText(resultMsg);
        mTvResult.setVisibility(View.VISIBLE);
    }

    //初始化按键监听器
    private void initButtons(){
        mBtHit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GameManager gm = GameManager.getManager();
                gm.hit(mPlayer,mHand);
                mCardListView.refreshList();
            }
        });
        mBtStand.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GameManager gm = GameManager.getManager();
                gm.stand(mPlayer,mHand);
            }
        });
        mBtDouble.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlayer.getChip() > mHand.getBet()){
                    GameManager gm = GameManager.getManager();
                    gm.doublebet(mPlayer,mHand);
                    mCardListView.refreshList();
                }
                else {
                    Toast.makeText(getContext(),"剩余筹码不足",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //更新牌列表
    public void refreshList() {
        mCardListView.refreshList();
    }
}
