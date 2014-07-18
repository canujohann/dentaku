package jp.co.se.android.androidbook2.chapter0313;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * SplashâÊñ 
 * ÇQïbÉçÉSÇï\é¶ÇµÅAéüÇÃactivityÇ÷ëJà⁄
 * @author canu
 *
 */
public class SplashActivity extends Activity {

	public int SPLASH_DISPLAY_LENGTH = 2000;
	public ImageView logo;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);				
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);

		logo = (ImageView) findViewById(R.id.logo);
	}

	public void onResume() {

		super.onResume();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Animation anim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.push_right_out);
				anim.setFillAfter(true);

				anim.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationEnd(Animation animation) {
						SplashActivity.this.finish();
						Intent mainIntent = new Intent(SplashActivity.this,	MainActivity.class);
						SplashActivity.this.startActivity(mainIntent);
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
					}

					@Override
					public void onAnimationStart(Animation animation) {
					}

				});

				logo.startAnimation(anim);

			}
		}, SPLASH_DISPLAY_LENGTH);
	}

}
