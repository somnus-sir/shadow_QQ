package com.whn.enter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Account_Data_Helper extends SQLiteOpenHelper{

	public Account_Data_Helper(Context context) {
		super(context, "login.db", null	, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table login(account String,pwd String)");
		db.execSQL("insert into login values('whn','123')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
