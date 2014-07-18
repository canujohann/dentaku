package jp.co.se.android.androidbook2.chapter0313;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 電卓画面
 */
public class MainActivity extends Activity implements TextWatcher,
        OnClickListener {
	
    // Views
    TextView mTextView1;
    GridLayout parentLayout;
    Context mContext; 
    
    // 計算機
    Calculater mCalculater = new Calculater();

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//親クラス
        super.onCreate(savedInstanceState);
        
        //テンプレート設定
        setContentView(R.layout.activity_main);

        //View設定
        setWidgets();
        
        //Context情報
        mContext = getApplicationContext();
    }
    
    
    /*画面上のボタンなどを設定*/
    private void setWidgets(){
    	
    	// 画面のインスタンスを取得
        mTextView1 = (TextView) findViewById(R.id.textView1);
        
        // 計算機のボタンにタップ時の処理を行うリスナーを設定        
        parentLayout =  (GridLayout) findViewById(R.id.parent_layout);        
        int childrensNb = parentLayout.getChildCount();        
        for(int i=0; i < childrensNb; i++){
        	View child = parentLayout.getChildAt(i);
        	if (child instanceof Button)
        		child.setOnClickListener(this);
        }       
    }


    @Override
    public void afterTextChanged(Editable s) {
        String input = s.toString();
        Log.d("MainActivity", "input=" + input);
        if (input.length() > 0) {
            String dispText = mCalculater.putInput(input,mContext);
            if (dispText != null) {
                mTextView1.setText(dispText);
            }
            s.clear();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    /* onclick処理 */
    @Override
    public void onClick(View v) {
    	
        Button btn = (Button) v;  
        
        switch (btn.getId()){      
        case  R.id.button1 ://[reset]ボタン
            mCalculater.reset();
            mTextView1.setText("0");
            break;
        case R.id.button_result ://結果画面
        	startActivity(new Intent(this, ResultActivity.class));
        	break;
        case R.id.button_result_delete://結果削除	
        	MyPreferences.deleteResult(mContext);
        	Toast.makeText(getApplicationContext(), "データが削除されました！", Toast.LENGTH_SHORT).show();
        	break;        	
        default://計算 
            String input = btn.getText().toString();
            String dispText = mCalculater.putInput(input,mContext);
            if (dispText != null) {
                mTextView1.setText(dispText);
            }        
        }
    }
}
