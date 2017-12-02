package com.ooad.a21point.ActivitiesAndViews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ooad.a21point.GameModels.GameManager;
import com.ooad.a21point.R;

public class MainActivity extends Activity {
    //游戏管理器
    GameManager mGameManager;
    //玩家主手牌
    HandControllerView mPlayerHand;
    //玩家分牌
    HandControllerView mPlayerSplitHand;
    //玩家手牌区
    LinearLayout mPlayerHandLayout;
    //庄家手牌
    CardListView mBankerHand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
