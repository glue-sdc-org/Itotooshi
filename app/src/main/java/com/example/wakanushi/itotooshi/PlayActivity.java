package com.example.wakanushi.itotooshi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

import com.daasuu.library.DisplayObject;
import com.daasuu.library.FPSTextureView;
import com.daasuu.library.drawer.BitmapDrawer;
import com.daasuu.library.drawer.TextDrawer;
import com.daasuu.library.easing.Ease;
import com.daasuu.library.util.Util;

/**
 * Created by Megumi on 4/30/16.
 */
public class PlayActivity extends AppCompatActivity{
    private FPSTextureView mFPSTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mFPSTextureView = (FPSTextureView) findViewById(R.id.animation_texture_view);

        int cnt = 0;
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        paint.setTextSize(Util.convertDpToPixel(12, this));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        float bitmapInitialX = paint.measureText(Ease.BOUNCE_IN_OUT.name()) + 50;


        for (Ease ease : Ease.values()) {

            float initialY = paint.getTextSize() * cnt * 1.3f;

            DisplayObject easeName = new DisplayObject();
            easeName.with(new TextDrawer(ease.name(), paint))
                    .tween()
                    .transform(0, initialY)
                    .end();


            DisplayObject bitmapTween = new DisplayObject();
            bitmapTween.with(new BitmapDrawer(bitmap).dpSize(this))
                    .tween()
                    .tweenLoop(true)
                    .transform(bitmapInitialX, initialY)
                    .waitTime(1000)
                    .toX(1000, getWindowWidth(this) - Util.convertPixelsToDp(bitmap.getWidth(), this), ease)
                    .waitTime(1000)
                    .toX(1000, bitmapInitialX, ease)
                    .end();


            mFPSTextureView
                    .addChild(easeName)
                    .addChild(bitmapTween);

            cnt++;
        }
    }
            /**
             * @param context
             * @return
             */
            public static int getWindowWidth(Context context) {
                Display displayObj = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                Point size = new Point();
                displayObj.getSize(size);
                return size.x;
    }
}
