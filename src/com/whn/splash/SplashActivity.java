package com.whn.splash;

import com.whn.enter.Activity_Enter;
import com.whn.whn_qq_datebase.R;
import com.whn.whn_qq_datebase.R.id;
import com.whn.whn_qq_datebase.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

/**
 * 加载动画
 * @author whn
 *
 */
public class SplashActivity extends Activity {
	private RelativeLayout bg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		bg = (RelativeLayout) findViewById(R.id.bg_layout_splash);

		AlphaAnimation aa = new AlphaAnimation(0.5f, 1f);
		aa.setDuration(1500);
		bg.startAnimation(aa);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this,
						Activity_Enter.class);
				startActivity(intent);
				finish();
			}
		}, 1500);
	}

}
