package com.ooad.a21point.ActivitiesAndViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ooad.a21point.GameModels.Card;
import com.ooad.a21point.GameModels.GameManager;
import com.ooad.a21point.GameModels.Hand;
import com.ooad.a21point.GameModels.Player;
import com.ooad.a21point.R;

import java.util.ArrayList;

/**
 * Created by 10040 on 2017/12/2.
 */

public class HandControllerView extends LinearLayout{
    //玩家
    private Player mPlayer;
    //手牌数据
    private Hand mHand;
    //当前显示手牌数量
    private int mHandNum;
    //hit,stand,double按钮
    Button mBtHit;
    Button mBtStand;
    Button mBtDouble;
    //牌列表
    CardListView mCardListView;

    public HandControllerView(Player player,Hand hand, Context context) {
        super(context);
        mPlayer = player;
        mHand = hand;
        mHandNum = 0;

        LayoutInflater.from(context).inflate(R.layout.hand_controller_view, this);
        mBtHit = findViewById(R.id.bt_hit);
        mBtStand = findViewById(R.id.bt_stand);
        mBtDouble = findViewById(R.id.bt_double);
        mCardListView = findViewById(R.id.card_list_view);

        initButtons();
        refreshList();
    }

    //初始化按键监听器
    private void initButtons(){
        mBtHit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GameManager gm = GameManager.getManager();
                gm.hit(mPlayer,mHand);
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
                GameManager gm = GameManager.getManager();
                gm.doublebet(mPlayer,mHand);
            }
        });
    }

    //更新牌列表
    private void refreshList(){
        ArrayList<Card> cards = mHand.getAllCards();
        while (mHandNum < cards.size()){
            mCardListView.addCard(cards.get(mHandNum));
            mHandNum++;
        }
    }
}
