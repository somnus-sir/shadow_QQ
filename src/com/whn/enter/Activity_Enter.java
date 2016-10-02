package com.whn.enter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.whn.accounts_list.Activity_Accounts;
import com.whn.comit.Activity_Comit;
import com.whn.whn_qq_datebase.R;
import com.whn.whn_qq_datebase.R.id;
import com.whn.whn_qq_datebase.R.layout;

/**
 * @author whn
 * 登录界面	
 * 
 * #头像自动更换     监听account_et
 * 
 */
@SuppressLint("NewApi")
public class Activity_Enter extends Activity {

	private EditText account_et;
	public static EditText pwd_et;
	private Button cleanAccount_bt;
	private Button cleanPwd_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter);

		account_et = (EditText) findViewById(R.id.et_account);
		pwd_et = (EditText) findViewById(R.id.et_pwd);
		cleanAccount_bt = (Button) findViewById(R.id.button_cleanaccount_main);
		cleanPwd_bt = (Button) findViewById(R.id.button_cleanpwd_main);
		
		//进入页面加载账号密码
		SharedPreferences sp = getSharedPreferences("dates", MODE_APPEND);
		account_et.setText(sp.getString("account", ""));
		pwd_et.setText(sp.getString("pwd", ""));
		
		//account_et.addTextChangedListener(new EditChangedListener());//文本改变监听
		
		//account_et 获取焦点显示cleanAccount_bt
		account_et.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					cleanAccount_bt.setVisibility(View.VISIBLE);
				}else{
					cleanAccount_bt.setVisibility(View.GONE);
				}
			}
		});
		//清除账号
		cleanAccount_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				account_et.setText("");
			}
		});
		
		//pwd_et 获取焦点显示 cleanPwd_bt
		pwd_et.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
					cleanPwd_bt.setVisibility(View.VISIBLE);
				}else{
					cleanPwd_bt.setVisibility(View.GONE);
				}
			}
		});
		//清除密码
		cleanPwd_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pwd_et.setText("");
			}
		});
		

		Button enter_bt = (Button) findViewById(R.id.bt_enter);
		Button regist_bt = (Button) findViewById(R.id.bt_regist);
		Button showlist_bt = (Button) findViewById(R.id.bt_showlist);

		/**
		 * show listview
		 */
		showlist_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activity_Enter.this,
						Activity_Accounts.class);
				startActivity(intent);
			}
		});

		/**
		 * 登录
		 */
		enter_bt.setOnClickListener(new OnClickListener() {

			private String account;
			private String pwd;

			@Override
			public void onClick(View v) {
				account = account_et.getText().toString().trim();
				pwd = pwd_et.getText().toString().trim();
				HashMap<String, String> map = getDBDate();
				
				//存储最后一次登录账号密码
				SharedPreferences sp = getSharedPreferences("dates", MODE_PRIVATE);
				Editor edit = sp.edit();
				edit.putString("account", account);
				edit.putString("pwd", pwd);
				edit.commit();

				// 判断是否为空
				if ("".equals(account) || "".equals(pwd)) {
					Toast.makeText(Activity_Enter.this, "账号密码不能为空!",
							Toast.LENGTH_SHORT).show();
				} else {
					//判断是否存在֤
					Set<String> keySet = map.keySet();
					if (keySet.contains(account)) {
						if (map.get(account).equals(pwd)) {
							Toast.makeText(Activity_Enter.this, "登录成功!",
									Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(Activity_Enter.this,Activity_Comit.class);
							startActivity(intent);
							finish();
						} else {
							Toast.makeText(Activity_Enter.this, "密码错误!",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(Activity_Enter.this, "账号不存在!",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		/**
		 * 注册
		 */
		regist_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = account_et.getText().toString().trim();
				String pwd = pwd_et.getText().toString().trim();

				// 判断是否为空
				if ("".equals(account) || "".equals(pwd)) {
					Toast.makeText(Activity_Enter.this, "账号密码不能为空!",
							Toast.LENGTH_SHORT).show();
				} else {
					//判断是否存在
					HashMap<String, String> dbDate = getDBDate();
					Set<String> set = dbDate.keySet();
					if (set.contains(account)) {
						Toast.makeText(Activity_Enter.this, "账号已存在!",
								Toast.LENGTH_SHORT).show();
					} else {
						Account_Data_Helper myhelper = new Account_Data_Helper(Activity_Enter.this);
						SQLiteDatabase db = myhelper.getReadableDatabase();
						ContentValues values = new ContentValues();
						values.put("account", account);
						values.put("pwd", pwd);
						long result = db.insert("login", "", values);
						if (result > 0) {
							Toast.makeText(Activity_Enter.this, "注册成功!",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(Activity_Enter.this, "注册失败!",
									Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		});
	}

	// 获取数据库数据-map
	public HashMap<String, String> getDBDate() {
		HashMap<String, String> map = new HashMap<String, String>();
		Account_Data_Helper myhelper = new Account_Data_Helper(Activity_Enter.this);
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor cursor = db.query("login", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			String account_DB = cursor.getString(cursor
					.getColumnIndex("account"));
			String pwd_DB = cursor.getString(cursor.getColumnIndex("pwd"));
			map.put(account_DB, pwd_DB);
		}
		return map;
	}
}
