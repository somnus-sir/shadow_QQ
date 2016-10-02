package com.whn.whn_qq_datebase;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActivityMsg extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_msg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_msg, menu);
		return true;
	}

}
