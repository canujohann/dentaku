package jp.co.se.android.androidbook2.chapter0313;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;

/**
 * PreferencesÇ…ï€ë∂Ç≥ÇÍÇΩèÓïÒÇï\é¶Ç∑ÇÈactivity
 * @author canu
 *
 */
public class ResultActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_result);
		
		String[] results = MyPreferences.getResult(getApplicationContext());
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, results));
		

	}

}
