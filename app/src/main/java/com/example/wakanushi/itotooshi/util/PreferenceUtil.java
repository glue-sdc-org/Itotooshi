package com.example.wakanushi.itotooshi.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p>Preference関連のUtil</p>
 */
public class PreferenceUtil {

    /** プリファレンスファイル名 */
    private static final String PREF_NAME = "itotooshi";

    /**
     * プリファレンスの値（String型）取得処理
     * @param context コンテキスト
     * @param paramName 保存キー
     * @return 保存している値
     */
    public static String getString(Context context, String paramName) {
        SharedPreferences pref =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(paramName, null);
    }

    /**
     * プリファレンスへの値（String）保存処理
     * @param context コンテキスト
     * @param paramName 保存キー
     * @param value 保存する値
     */
    public static void putString(Context context, String paramName, String value) {
        SharedPreferences pref =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(paramName, value);
        editor.apply();
    }

    /**
     * プリファレンスの値（int）取得処理
     * @param context コンテキスト
     * @param paramName 保存キー
     * @return 保存している値
     */
    public static int getInt(Context context, String paramName) {
        SharedPreferences pref =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getInt(paramName, 0);
    }

    /**
     * プリファレンスへの値（int）保存処理
     * @param context コンテキスト
     * @param paramName 保存キー
     * @param value 保存する値
     */
    public static void putInt(Context context, String paramName, int value) {
        SharedPreferences pref =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(paramName, value);
        editor.apply();
    }

    /**
     * プリファレンスの値削除処理
     * @param context コンテキスト
     * @param paramName 保存キー
     */
    public static void remove(Context context, String paramName) {
        SharedPreferences pref =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(paramName);
        editor.apply();
    }
}
