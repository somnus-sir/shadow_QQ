package com.whn.message;

import com.whn.whn_qq_datebase.R;
import com.whn.whn_qq_datebase.R.layout;
import com.whn.whn_qq_datebase.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ActivityMessage extends Activity {

	private Button fanhui_bt;

	private ImageView bg_iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		fanhui_bt = (Button) findViewById(R.id.fanhui_bt_message);

		bg_iv = (ImageView) findViewById(R.id.bg_iv_message);
		Intent intent = getIntent();
		String bg = intent.getStringExtra("bg");
		Log.d("Message", bg);
		initBG(Integer.parseInt(bg));
	}

	private void initBG(int bg) {
		switch (bg) {
		case 0:
			bg_iv.setBackgroundResource(R.drawable.bg_whn);
			break;
		case 1:
			bg_iv.setBackgroundResource(R.drawable.bg_lmx);
			break;
		case 2:
			bg_iv.setBackgroundResource(R.drawable.bg_yjf);
			break;
		case 3:
			bg_iv.setBackgroundResource(R.drawable.bg_csc);
			break;
		case 4:
			bg_iv.setBackgroundResource(R.drawable.bg_yxd);
			break;
		case 5:
			bg_iv.setBackgroundResource(R.drawable.bg_gjc);
			break;

		default:
			break;
		}

		fanhui_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Instrumentation instrumentation = new Instrumentation();
						instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
					}
				}).start();
			}
		});

	}

}
