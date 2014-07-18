package jp.co.se.android.androidbook2.chapter0313;

import android.content.Context;

public class Calculater {
    // 入力中の文字列
    StringBuilder mInputNumber = new StringBuilder();
    // 入力中の演算子
    String mOperator;
    // 計算結果
    int mResult = 0;

    /***
     * パラメーターのKeyが数値ならTRUEを返却
     */
    private boolean isNumber(String key) {
        try {
            Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    /***
     * パラメーターのKeyがサポートしている演算子ならTRUEを返却
     */
    private boolean isSupportedOperator(String key) {
        if (key.equals("+")) {
            return true;
        } else if (key.equals("-")) {
            return true;
        } else if (key.equals("*")) {
            return true;
        } else if (key.equals("/")) {
            return true;
        } else if (key.equals("=")) {
            return true;
        }
        return false;
    }

    /***
     * 演算を実施
     */
    private void doCalculation(String ope, Context context) {
        if (ope.equals("+")) {
            mResult = mResult + Integer.parseInt(mInputNumber.toString());
        } else if (ope.equals("-")) {
            mResult = mResult - Integer.parseInt(mInputNumber.toString());
        } else if (ope.equals("*")) {
            mResult = mResult * Integer.parseInt(mInputNumber.toString());
        } else if (ope.equals("/")) {
            mResult = mResult / Integer.parseInt(mInputNumber.toString());
        }
        
        //SharedPreferencesに追加
        MyPreferences.addResult(context, String.valueOf(mResult));
        
        mInputNumber = new StringBuilder();
    }

    /***
     * クリア処理
     */
    public void reset() {
        mOperator = null;
        mResult = 0;
        mInputNumber = new StringBuilder();
    }

    /***
     * 入力された文字をもとに処理を行い、ディスプレイに表示する結果を返却する。
     */
    public String putInput(String key, Context context) {
        if (isNumber(key)) {
            // 数値の場合次の入力をまつ
            mInputNumber.append(key);
            return mInputNumber.toString();
        } else if (isSupportedOperator(key)) {
            // サポートしている演算子の場合、入力中の数値をもとに演算を行う
            if (key.equals("=")) {
                // ＝なら演算を行い結果を返却する
                if (mOperator != null) {
                    doCalculation(mOperator, context);
                    mOperator = null;
                }
                return Integer.toString(mResult);
            }
            else {
                if (mOperator != null) {
                    // 入力中の演算子があるなら前回の結果を用いて演算を行う
                    doCalculation(mOperator,context);
                    mOperator = null;
                } else if (mInputNumber.length() > 0) {
                    // はじめての演算子なら入力中の数値を設定する
                    mResult = Integer.parseInt(mInputNumber.toString());
                    mInputNumber = new StringBuilder();
                }
                mOperator = key;
                return mOperator;
            }
        } else {
            return null;
        }
    }
}
