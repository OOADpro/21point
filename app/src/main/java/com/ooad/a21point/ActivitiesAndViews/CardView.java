package com.ooad.a21point.ActivitiesAndViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ooad.a21point.GameModels.Card;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 10040 on 2017/12/2.
 */

@SuppressLint("ViewConstructor")
public class CardView extends ImageView {

    public CardView(Card card, Context context) {
        super(context);
        setCard(context,card);
    }

    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setCard(Context context, Card card){
        //String uri = "file:///andr oid_asset/";
        String uri = "";
        if (card.isOpen()){
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
            uri = uri + color + "/" + color + String.valueOf(card.getmPoint() + ".jpg");
        }else {
            uri += "back.jpg";
        }
        //Glide.with(context).load(uri).into(this);
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(uri);
            setImageDrawable(Drawable.createFromStream(is,null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
