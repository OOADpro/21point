package com.ooad.a21point.ActivitiesAndViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ooad.a21point.GameModels.Card;

import java.io.InputStream;

/**
 * Created by 10040 on 2017/12/2.
 */

public class CardView extends ImageView {

    public CardView(Card card, Context context) {
        super(context);

        String uri = "file:///andr oid_asset/";
        String color = "";
        switch (card.getmColor()){
            case Card.CLUB:
                color = "club";break;
            case Card.DIAMOND:
                color = "diamond";break;
            case Card.HEART:
                color = "heart";break;
            case Card.SPADE:
                color = "spade";break;
        }
        uri = uri + color + "/" + color + String.valueOf(card.getmPoint());
        Glide.with(context).load(uri).into(this);
    }
}
