package jp.co.se.android.androidbook2.chapter0313;

import android.content.Context;

public class Calculater {
    // ���͒��̕�����
    StringBuilder mInputNumber = new StringBuilder();
    // ���͒��̉��Z�q
    String mOperator;
    // �v�Z����
    int mResult = 0;

    /***
     * �p�����[�^�[��Key�����l�Ȃ�TRUE��ԋp
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
     * �p�����[�^�[��Key���T�|�[�g���Ă��鉉�Z�q�Ȃ�TRUE��ԋp
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
     * ���Z�����{
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
        
        //SharedPreferences�ɒǉ�
        MyPreferences.addResult(context, String.valueOf(mResult));
        
        mInputNumber = new StringBuilder();
    }

    /***
     * �N���A����
     */
    public void reset() {
        mOperator = null;
        mResult = 0;
        mInputNumber = new StringBuilder();
    }

    /***
     * ���͂��ꂽ���������Ƃɏ������s���A�f�B�X�v���C�ɕ\�����錋�ʂ�ԋp����B
     */
    public String putInput(String key, Context context) {
        if (isNumber(key)) {
            // ���l�̏ꍇ���̓��͂��܂�
            mInputNumber.append(key);
            return mInputNumber.toString();
        } else if (isSupportedOperator(key)) {
            // �T�|�[�g���Ă��鉉�Z�q�̏ꍇ�A���͒��̐��l�����Ƃɉ��Z���s��
            if (key.equals("=")) {
                // ���Ȃ牉�Z���s�����ʂ�ԋp����
                if (mOperator != null) {
                    doCalculation(mOperator, context);
                    mOperator = null;
                }
                return Integer.toString(mResult);
            }
            else {
                if (mOperator != null) {
                    // ���͒��̉��Z�q������Ȃ�O��̌��ʂ�p���ĉ��Z���s��
                    doCalculation(mOperator,context);
                    mOperator = null;
                } else if (mInputNumber.length() > 0) {
                    // �͂��߂Ẳ��Z�q�Ȃ���͒��̐��l��ݒ肷��
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
