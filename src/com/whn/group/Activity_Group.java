package com.whn.group;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Instrumentation;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.whn.accounts_list.Accounts;
import com.whn.accounts_list.Activity_Accounts;
import com.whn.enter.Account_Data_Helper;
import com.whn.message.ActivityMessage;
import com.whn.whn_qq_datebase.R;

/**
 * 群成员
 * 
 * @author whn
 * 
 *         #显示群成员信息 页面跳转
 * 
 *         #添加数据库: 初始化数据库的时候添加所有人 oncreate 加载数据库数据,然后显示在listview中
 * 
 * 
 * 
 * 
 */
public class Activity_Group extends Activity {

	private Button fh_bt;
	private ListView listview_persons;
	private ArrayList<Person_Group> persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_persons);
		persons = new ArrayList<Person_Group>();
		fh_bt = (Button) findViewById(R.id.fh_bt_comit);
		listview_persons = (ListView) findViewById(R.id.listview_persons);

		initdate();
		Group_List_Adapter personsAdapter = new Group_List_Adapter(Activity_Group.this, persons);
		listview_persons.setAdapter(personsAdapter);
		
		listview_persons.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(Activity_Group.this,ActivityMessage.class);
				intent.putExtra("bg", position+"");
				Log.d("!!!!!", position+"H");
				startActivity(intent);
			}
		});
		
		
		
		

		/**
		 * 返回button
		 */
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

	}

	/**
	 * 初始化数据
	 */
	private void initdate() {

		 Person_Group p1 = new Person_Group();
		 p1.touxiang = R.drawable.touxiang+"";
		 p1.quanxian = "群主";
		 p1.account = "尉浩楠";
		 persons.add(p1);
		
		 Person_Group p2 = new Person_Group();
		 p2.touxiang = R.drawable.lmx+"";
		 p2.quanxian = "管理员";
		 p2.account = "李孟迅";
		 persons.add(p2);

		 Person_Group p3 = new Person_Group();
		 p3.touxiang = R.drawable.yjf+"";
		 p3.quanxian = "管理员";
		 p3.account = "杨建飞";
		 persons.add(p3);

		 Person_Group p4 = new Person_Group();
		 p4.touxiang = R.drawable.csc+"";
		 p4.quanxian = "管理员";
		 p4.account = "蔡树春";
		 persons.add(p4);
		 
		 Person_Group p5 = new Person_Group();
		 p5.touxiang = R.drawable.yxd+"";
		 p5.quanxian = "管理员";
		 p5.account = "苑旭东";
		 persons.add(p5);
		 
		 Person_Group p6 = new Person_Group();
		 p6.touxiang = R.drawable.gjc+"";
		 p6.quanxian = "管理员";
		 p6.account = "苟继承";
		 persons.add(p6);


	}
}
