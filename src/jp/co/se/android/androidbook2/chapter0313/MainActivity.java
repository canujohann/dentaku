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
 * �d����
 */
public class MainActivity extends Activity implements TextWatcher,
        OnClickListener {
	
    // Views
    TextView mTextView1;
    GridLayout parentLayout;
    Context mContext; 
    
    // �v�Z�@
    Calculater mCalculater = new Calculater();

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	//�e�N���X
        super.onCreate(savedInstanceState);
        
        //�e���v���[�g�ݒ�
        setContentView(R.layout.activity_main);

        //View�ݒ�
        setWidgets();
        
        //Context���
        mContext = getApplicationContext();
    }
    
    
    /*��ʏ�̃{�^���Ȃǂ�ݒ�*/
    private void setWidgets(){
    	
    	// ��ʂ̃C���X�^���X���擾
        mTextView1 = (TextView) findViewById(R.id.textView1);
        
        // �v�Z�@�̃{�^���Ƀ^�b�v���̏������s�����X�i�[��ݒ�        
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

    /* onclick���� */
    @Override
    public void onClick(View v) {
    	
        Button btn = (Button) v;  
        
        switch (btn.getId()){      
        case  R.id.button1 ://[reset]�{�^��
            mCalculater.reset();
            mTextView1.setText("0");
            break;
        case R.id.button_result ://���ʉ��
        	startActivity(new Intent(this, ResultActivity.class));
        	break;
        case R.id.button_result_delete://���ʍ폜	
        	MyPreferences.deleteResult(mContext);
        	Toast.makeText(getApplicationContext(), "�f�[�^���폜����܂����I", Toast.LENGTH_SHORT).show();
        	break;        	
        default://�v�Z 
            String input = btn.getText().toString();
            String dispText = mCalculater.putInput(input,mContext);
            if (dispText != null) {
                mTextView1.setText(dispText);
            }        
        }
    }
}
