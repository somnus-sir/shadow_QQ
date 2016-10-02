package com.whn.accounts_list;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.whn.enter.Account_Data_Helper;
import com.whn.whn_qq_datebase.R;
import com.whn.whn_qq_datebase.R.id;
import com.whn.whn_qq_datebase.R.layout;


/**
 * 账号密码显示界面
 * @author whn
 * 
 *
 */
public class Activity_Accounts extends Activity {

	private List<Accounts> datas = new ArrayList<Accounts>();
	private Accounts_List_Adapter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_show);
		
		/**
		 * 返回button
		 */
		Button fh_bt = (Button) findViewById(R.id.fh);
		fh_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread() {
					public void run() {
						try {
							Instrumentation inst = new Instrumentation();
							inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});

		initdate();// 初始化数据dates

		ListView listView = (ListView) findViewById(R.id.listview);
		myAdapter = new Accounts_List_Adapter(Activity_Accounts.this, datas);
		listView.setAdapter(myAdapter);// 初始化listview

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_Accounts.this);
				dialog.setTitle("删除账户: " + datas.get(position).account);
				dialog.setMessage("确定吗？");
				dialog.setCancelable(false);
				dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Account_Data_Helper myhelper = new Account_Data_Helper(Activity_Accounts.this);
						SQLiteDatabase db = myhelper.getReadableDatabase();
						db.delete("login", "account = ?", new String[] { datas.get(position).account });
						datas.remove(position);//跑的是数据库的数据,把表中对应数据也删除
						myAdapter.notifyDataSetChanged();
						Toast.makeText(Activity_Accounts.this, "此用户已被删除", Toast.LENGTH_SHORT).show();
					}
				});

				dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(Activity_Accounts.this, "取消", Toast.LENGTH_SHORT).show();
					}
				});
				dialog.show();
			}
		});
	}

	// 初始化数据
	private void initdate() {
		Account_Data_Helper myhelper = new Account_Data_Helper(Activity_Accounts.this);
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor cursor = db.query("login", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			String account = cursor.getString(cursor.getColumnIndex("account"));
			String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
			
			Accounts d = new Accounts();
			d.account = account;
			d.pwd = pwd;
			datas.add(d);
		}
	}

}
