package com.whn.comit;

import com.whn.group.Activity_Group;
import com.whn.whn_qq_datebase.R;
import com.whn.whn_qq_datebase.R.id;
import com.whn.whn_qq_datebase.R.layout;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 聊天主界面
 * @author whn
 * 
 * 返回提醒,再按一次退出	
 * 
 * 
 */
public class Activity_Comit extends Activity {

	private Button fh_bt;
	private Button gonggao_bt;
	private Button persons_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fh_bt = (Button) findViewById(R.id.fh_bt_comit);
		fh_bt.setOnClickListener(new OnClickListener() {

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

		gonggao_bt = (Button) findViewById(R.id.gonggao_bt_comit);
		gonggao_bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(Activity_Comit.this, "没有新公告", Toast.LENGTH_SHORT).show();
			}
		});
		
		persons_bt = (Button) findViewById(R.id.persons_bt_comit);
		persons_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activity_Comit.this,Activity_Group.class);
				startActivity(intent);
			}
		});

	}

}
