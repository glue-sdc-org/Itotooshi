package com.example.wakanushi.itotooshi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;

import com.daasuu.library.DisplayObject;
import com.daasuu.library.FPSTextureView;
import com.daasuu.library.callback.AnimCallBack;
import com.daasuu.library.drawer.BitmapDrawer;
import com.daasuu.library.easing.Ease;
import com.daasuu.library.util.Util;

/**
 * Created by Megumi on 4/30/16.
 */
public class PlayActivity extends AppCompatActivity{
    /** 画像追加間隔（ミリ秒） */
    private static final int BITMAP_ADD_INTERVAL = 1500;
    /** FPSテクスチャ */
    private FPSTextureView mFPSTextureView;
    /** 画像追加用ハンドラ */
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mFPSTextureView = (FPSTextureView) findViewById(R.id.animation_texture_view);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 画像追加処理
                DisplayObject bitmapTween = createDisplayObject((int)(Math.random() * 1000) + 1);
                mFPSTextureView.addChild(bitmapTween);
                handler.postDelayed(this, BITMAP_ADD_INTERVAL);
            }
        }, BITMAP_ADD_INTERVAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFPSTextureView.tickStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFPSTextureView.tickStop();
        handler.removeCallbacksAndMessages(null);
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

    private DisplayObject createDisplayObject(int initialY) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        paint.setTextSize(Util.convertDpToPixel(12, this));

        // 表示する画像
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        final DisplayObject bitmapTween = new DisplayObject();
        bitmapTween.with(new BitmapDrawer(bitmap).dpSize(this))
                .tween()
                .tweenLoop(false)
                .transform(getWindowWidth(this) - Util.convertPixelsToDp(bitmap.getWidth(), this), initialY)
                .toX(2000, 0, Ease.LINEAR)
                .call(new AnimCallBack() {
                    @Override
                    public void call() {
                        // 画像削除
                        mFPSTextureView.removeChild(bitmapTween);
                    }
                })
                .end();
        return bitmapTween;
   }
}
