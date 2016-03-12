package com.example.wakanushi.itotooshi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wakanushi.itotooshi.constant.PrefConstant;
import com.example.wakanushi.itotooshi.util.PreferenceUtil;

/**
 * <p>記録画面のActivity</p>
 */
public class RecordActivity extends AppCompatActivity {

    /** 最高値 */
    private TextView recordMax;
    /** 回数 */
    private TextView recordCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        recordMax = (TextView)findViewById(R.id.recordMax);
        recordCount = (TextView)findViewById(R.id.recordCount);

        // 値をセット
        recordMax.setText(String.valueOf(PreferenceUtil.getInt(this, PrefConstant.PREF_RECORD_MAX)));
        recordCount.setText(String.valueOf(PreferenceUtil.getInt(this, PrefConstant.PREF_RECORD_COUNT)));
    }
}
