package jp.co.se.android.androidbook2.chapter0313;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * preferences関連のクラスです
 * @author canu
 *
 */
public class MyPreferences {
	
	public static final String PREF_NAME 		= "jp.ne.alij.test";
	public static final String PREF_NAME_RESULT = "result";
	public static final String DELIMIT 			= ",";

	public static SharedPreferences getMyPrefs(Context context){
			return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
	}
	
	/* 結果を追加 */
	public static void addResult(Context context, String val){
		SharedPreferences sp = getMyPrefs(context);
		String result = sp.getString(PREF_NAME_RESULT, "");
		String newResult = (result.equals("")) ? val : result + DELIMIT + val;
		
		//Preferencesに追加
		SharedPreferences.Editor prefEditor = sp.edit();
		prefEditor.putString(PREF_NAME_RESULT, newResult);
		prefEditor.commit();
	}
	
	/*　結果一覧を取得　*/
	public static String[] getResult(Context context){
		SharedPreferences sp = getMyPrefs(context);
		String result = sp.getString(PREF_NAME_RESULT, "");
		return result.split(",");
	}
	
	/*結果削除*/
	public static void deleteResult(Context context){
		SharedPreferences sp = getMyPrefs(context);
		SharedPreferences.Editor prefEditor = sp.edit();
		prefEditor.putString(PREF_NAME_RESULT, "");
		prefEditor.commit();
	}
	
	
	

	
}
